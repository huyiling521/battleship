package battleship.server;

import battleship.server.socket.MessageConstant;
import battleship.server.socket.PlayerHandler;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

// GameServer 提供连接和广播功能
public class GameServer {
    // 服务器监听指定端口
    private static final int PORT = 1235;
    private List<PlayerHandler> players = new ArrayList<>();

    public static void main(String[] args) {
        GameServer server = new GameServer();
        server.startServer();
    }

    public void startServer() {
        System.out.println("服务器已启动，等待玩家连接...");
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            while (players.size() < 2) {
                // 连接
                Socket playerSocket = serverSocket.accept();
                PlayerHandler playerHandler = new PlayerHandler(playerSocket, players.size());
                System.out.println("Player" + (players.size() + 1 ) + " connected.");
                playerHandler.sendResponseMessage(MessageConstant.SUCCESS + "User Connected!");
                players.add(playerHandler);
                new Thread(playerHandler).start();
            }
            GameSession session = new GameSession(players);
            session.broadcastConnectionSuccess();
            session.run();
            session.clearUp();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close(); // 确保在finally块中关闭ServerSocket
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void endGame() {
        players = null;
    }
}
