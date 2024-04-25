package battleship.server.socket;

import battleship.server.controller.GameController;
import battleship.server.model.TwoPlayerBoard;
import battleship.server.model.ships.ShipType;
import battleship.server.socket.MessageConstant;
import battleship.server.socket.PlayerHandler;

import java.util.*;

// GameSession:
// 与单个玩家的套接字连接。
// 读取玩家发送的消息，并通过服务器的 broadcastOpponentMessage 方法发送给所有其他玩家。
// 在玩家断开连接时，清理资源。
public class GameSession {
    private List<PlayerHandler> players;
    private List<GameController> controllers;

    private Set<PlayerHandler> connected;
    private int round = 1;
    private boolean gameEnd;

    public GameSession(List<PlayerHandler> players) {
        this.players = players;
        this.gameEnd = false;
        for (PlayerHandler player : players) player.setSession(this);
        controllers = new ArrayList<>(Arrays.asList(new GameController(new TwoPlayerBoard()), new GameController(new TwoPlayerBoard())));
        this.connected = new HashSet<>();
    }

    public synchronized void processPlayerMove(String move, int index) {
        if (index == (round + 1) % 2) {
            // 假设根据玩家的移动更新游戏状态
            updateGameState(move, index);
            round++;
            if (gameEnd) clearUp();
        } else {
            this.players.get(index).sendResponseMessage(MessageConstant.ERROR + MessageConstant.INVALID_TURN);;
        }
    }

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

    // 发送消息给当前玩家
    public void broadcastOpponentMessage(String message, int index) {
        players.get(1 - index).sendOpponentMessage(message);
    }

    public void broadcastSystemMessage(String message, int index) {
        if (1 - index < players.size()) {
            players.get(1 - index).sendSystemMessage(message);
        }
    }

    public void sendSystemResponseMessage(String message, int index) {
        if (index < players.size()) {
            players.get(index).sendResponseMessage(message);
        }
    }

    public void broadcastOpponentInfo() {
        players.get(0).sendResponseMessage(MessageConstant.CONNECTION + MessageConstant.OPPONENT_NAME + players.get(1).getName());
        players.get(1).sendResponseMessage(MessageConstant.CONNECTION + MessageConstant.OPPONENT_NAME + players.get(0).getName());
    }

    public void clearUp() {
        for (PlayerHandler player : players) player.clearUp();
        players.clear();
    }
    public int setShip(String req, int index) {
        String[] params = req.split(",");
        int row = Integer.parseInt(params[0]);
        int col = Integer.parseInt(params[1]);
        boolean isHorizontal = (params[2].equals("true"));
        return controllers.get(index).placeOneShip(row, col, isHorizontal, params[3]);
    }

    public boolean shootAt(int row, int col, int index) {
        return controllers.get(1 - index).attack(row, col);
    }

    public void connected(PlayerHandler playerHandler) {
        connected.add(playerHandler);
        if (connected.size() == 2) {
            for (PlayerHandler player : players) player.sendResponseMessage(MessageConstant.CONNECTION + MessageConstant.SUCCESS + MessageConstant.GAME_START);
        }
    }

    public void add(PlayerHandler playerHandler) {
        players.add(playerHandler);
    }

    public boolean isGameEnd() {
        return gameEnd;
    }

    public void setGameEnd(boolean gameEnd) {
        this.gameEnd = gameEnd;
    }

    public int getSize() {
        return players.size();
    }
}