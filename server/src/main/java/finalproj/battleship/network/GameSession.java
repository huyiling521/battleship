package finalproj.battleship.network;

import finalproj.battleship.controller.TwoPlayerGameController;
import finalproj.battleship.model.TwoPlayerBoard;
import finalproj.battleship.model.ships.ShipType;

import java.util.*;


/**
 * The {@code GameSession} class manages a session of a two-player Battleship game, handling
 * game states, player moves, and communication between players. It utilizes {@link TwoPlayerGameController}
 * instances to manage game logic and updates, and interacts with {@link PlayerHandler} to
 * manage network-based communication.
 *
 * This class orchestrates the flow of the game, processes player moves, and maintains
 * a record of the game's progress and conclusion.
 */
public class GameSession {
    private List<PlayerHandler> players;
    private List<TwoPlayerGameController> controllers;

    private Set<PlayerHandler> connected;
    private int round = 1;
    private boolean gameEnd;

    /**
     * Constructs a new GameSession with the provided list of PlayerHandler objects.
     * Initializes game controllers and sets up the session.
     */
    public GameSession() {
        this.players = new ArrayList<>();
        this.gameEnd = false;
        this.controllers = new ArrayList<>();
        this.connected = new HashSet<>();
    }

    /**
     * Processes a move from a player, updating game state accordingly.
     * Ensures that moves are made in turn and that the game state is updated only if the move is valid.
     *
     * @param move the move made by the player, typically represented as a string
     * @param index the index of the player making the move
     */
    public synchronized void processPlayerMove(String move, int index) {
        if (index == (round + 1) % 2) {
            updateGameState(move, index);
            round++;
            if (gameEnd) clearUp();
        } else {
            this.players.get(index).sendResponseMessage(MessageConstant.ERROR + MessageConstant.INVALID_TURN);;
        }
    }

    /**
     * Updates the game state based on the provided move. Handles hit/miss detection,
     * sinking of ships, and checks for game over conditions.
     *
     * @param req the move made by the player
     * @param index the index of the player who made the move
     */
    private void updateGameState(String req, int index) {
        PlayerHandler player = players.get(index);
        String[] reqArr = req.split(",");
        int row = Integer.parseInt(reqArr[0]);
        int col = Integer.parseInt(reqArr[1]);
        boolean isHit = shootAt(row, col, index);
        player.sendResponseMessage("" + isHit);
        broadcastSystemMessage(MessageConstant.OPPONENT_ATTACK + (row + 1) + "," + (col + 1) + "," + isHit, index);
        if (isHit) {
            if (this.controllers.get(1 - index).isSunk(row, col)) {
                ShipType shipType = this.controllers.get(1 - index).getShipType(row, col);
                player.sendSystemMessage(player.getName() + " sunk a " + shipType);
                broadcastSystemMessage(player.getName() + " sunk a " + shipType, index);
                if (this.controllers.get(1 - index).isGameOver()) {
                    player.sendSystemMessage(MessageConstant.GAME_OVER + "You win! " + this.controllers.get(1 - index).getConclusion());
                    broadcastSystemMessage(MessageConstant.GAME_OVER + player.getName() + " win! " + this.controllers.get(index).getConclusion(), index);
                    gameEnd = true;
                }
            }
        }
    }

    /**
     * Broadcasts a message from one player to their opponent.
     *
     * @param message the message to be sent to the opponent
     * @param index the index of the player whose opponent will receive the message
     */
    public void broadcastOpponentMessage(String message, int index) {
        players.get(1 - index).sendOpponentMessage(message);
    }

    /**
     * Broadcasts a system message to the opponent of the player at the given index.
     *
     * @param message the message to be sent
     * @param index the index of the player whose opponent will receive the message
     */
    public void broadcastSystemMessage(String message, int index) {
        if (1 - index < players.size()) {
            players.get(1 - index).sendSystemMessage(message);
        }
    }

    /**
     * Sends a system response message to the specified player.
     *
     * @param message the system response message to send
     * @param index the index of the player who will receive the message
     */
    public void sendSystemResponseMessage(String message, int index) {
        if (index < players.size()) {
            players.get(index).sendResponseMessage(message);
        }
    }

    /**
     * Broadcasts opponent information at the start of the game, informing each player of the other's name.
     */
    public void broadcastOpponentInfo() {
        players.get(0).sendResponseMessage(MessageConstant.CONNECTION + MessageConstant.OPPONENT_NAME + players.get(1).getName());
        players.get(1).sendResponseMessage(MessageConstant.CONNECTION + MessageConstant.OPPONENT_NAME + players.get(0).getName());
    }

    /**
     * Cleans up the session by clearing player data and other resources once the game concludes.
     */
    public void clearUp() {
        for (PlayerHandler player : players) player.clearUp();
        players.clear();
    }

    /**
     * Handles the placement of a ship on the board for a specific player.
     *
     * @param req the ship placement request, formatted as a string
     * @param index the index of the player placing the ship
     * @return the result of the ship placement
     */
    public int setShip(String req, int index) {
        String[] params = req.split(",");
        int row = Integer.parseInt(params[0]);
        int col = Integer.parseInt(params[1]);
        boolean isHorizontal = (params[2].equals("true"));
        return controllers.get(index).placeOneShip(row, col, isHorizontal, params[3]);
    }

    /**
     * Attempts to shoot at a specified location on the opponent's board.
     *
     * @param row the row to target
     * @param col the column to target
     * @param index the index of the player making the shot
     * @return {@code true} if the shot hits a ship, {@code false} otherwise
     */
    public boolean shootAt(int row, int col, int index) {
        return controllers.get(1 - index).shootAt(row, col);
    }


    /**
     * Registers a player as connected to the session.
     *
     * @param playerHandler the player to register
     */
    public void connected(PlayerHandler playerHandler) {
        connected.add(playerHandler);
        if (connected.size() == 2) {
            for (PlayerHandler player : players) player.sendResponseMessage(MessageConstant.CONNECTION + MessageConstant.SUCCESS + MessageConstant.GAME_START);
        }
    }

    /**
     * Adds a new player to the game session.
     *
     * @param playerHandler the {@link PlayerHandler} object representing the new player
     */
    public void add(PlayerHandler playerHandler) {
        players.add(playerHandler);
        controllers.add(new TwoPlayerGameController(new TwoPlayerBoard()));
    }

    /**
     * Determines if the game has ended.
     *
     * @return {@code true} if the game has ended, {@code false} otherwise
     */
    public boolean isGameEnd() {
        return gameEnd;
    }

    /**
     * Sets the game end status. This is typically called to update the game's end state.
     *
     * @param gameEnd the new end status of the game
     */
    public void setGameEnd(boolean gameEnd) {
        this.gameEnd = gameEnd;
    }

    /**
     * Gets the number of players currently connected to the session.
     *
     * @return the number of players in the session
     */
    public int getSize() {
        return players.size();
    }
}