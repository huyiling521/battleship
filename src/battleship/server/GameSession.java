package battleship.server;

import battleship.server.controller.GameController;
import battleship.server.model.TwoPlayerBoard;
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

    public GameSession(List<PlayerHandler> players) {
        this.players = players;
        for (PlayerHandler player : players) player.setSession(this);
        controllers = new ArrayList<>(Arrays.asList(new GameController(new TwoPlayerBoard()), new GameController(new TwoPlayerBoard())));
        this.connected = new HashSet<>();
    }

    // 发送消息给当前玩家
    public void broadcastOpponentMessage(String message, int index) {
        players.get(1 - index).receiveOpponentMessage(message);
    }

    public void broadcastSystemMessage(String message, int index) {
        players.get(1 - index).receiveSystemMessage(message);
    }

    public void broadcastConnectionSuccess() {
        for (PlayerHandler player : players) player.sendResponseMessage(MessageConstant.CONNECTION + MessageConstant.SUCCESS);
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
            broadcastSystemMessage(MessageConstant.OPPONENT_NAME + players.get(0).getName(), 0);
            broadcastSystemMessage(MessageConstant.OPPONENT_NAME + players.get(1).getName(), 1);
        }
    }

    public void run() {
    }
}