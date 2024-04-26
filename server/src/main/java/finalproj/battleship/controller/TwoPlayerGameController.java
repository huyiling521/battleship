package finalproj.battleship.controller;

import finalproj.battleship.model.IGameController;
import finalproj.battleship.model.ships.ShipType;
import finalproj.battleship.model.TwoPlayerBoard;

/**
 * The {@code TwoPlayerGameController} class implements the {@link IGameController} interface
 * to manage the game logic for a two-player version of Battleship. This controller is responsible
 * for interfacing with the {@link TwoPlayerBoard} model to handle game actions such as placing ships,
 * making shots, and checking game status.
 *
 * This controller facilitates all the operations required to play the game between two players,
 * maintaining the state of the game board and delegating actions to the underlying game model.
 */
public class TwoPlayerGameController implements IGameController {
    private TwoPlayerBoard twoPlayerBoard;

    /**
     * Constructs a new TwoPlayerGameController with a specified {@link TwoPlayerBoard}.
     *
     * @param twoPlayerBoard the game board on which the game is played
     */
    public TwoPlayerGameController(TwoPlayerBoard twoPlayerBoard) {
        this.twoPlayerBoard = twoPlayerBoard;
    }

    /**
     * Attempts to place a single ship on the game board.
     *
     * @param row the row of ship's bow
     * @param col the col of ship's bow
     * @param isHorizontal true if the ship is placed horizontally, false if vertically
     * @param shipType the type of ship to be placed
     * @return an integer indicating the success or failure of placing the ship
     */
    public int placeOneShip(int row, int col, boolean isHorizontal, String shipType) {
        return twoPlayerBoard.placeOneShip(row, col, isHorizontal, shipType);
    }

    /**
     * Makes an attempt to shoot at a specified location on the board.
     *
     * @param row the row index to be targeted
     * @param col the column index to be targeted
     * @return a boolean indicating whether the shot hit a ship
     */
    public boolean shootAt(int row, int col) {
        return twoPlayerBoard.shootAt(row, col);
    }

    /**
     * Checks if a ship at a specified location is sunk.
     *
     * @param row the row index of the ship
     * @param col the column index of the ship
     * @return a boolean indicating whether the ship at the specified location is sunk
     */
    public boolean isSunk(int row, int col) {
        return twoPlayerBoard.getShip(row, col).isSunk();
    }

    /**
     * Determines if the game is over (i.e., all ships have been sunk).
     *
     * @return a boolean indicating whether the game is over
     */
    public boolean isGameOver() {
        return twoPlayerBoard.isGameOver();
    }

    /**
     * Provides a conclusion message reflecting the number of shots fired during the game.
     *
     * @return a {@link String} message detailing the number of shots fired
     */
    public String getConclusion() {
        return "You have shot " + twoPlayerBoard.getShotsFired() + " times.";
    }

    /**
     * Retrieves the type of ship located at a specific position on the board.
     *
     * @param row the row index of the ship
     * @param col the column index of the ship
     * @return the {@link ShipType} of the ship at the specified coordinates
     */
    public ShipType getShipType(int row, int col) {
        return twoPlayerBoard.getShip(row, col).getShipType();
    }
}
