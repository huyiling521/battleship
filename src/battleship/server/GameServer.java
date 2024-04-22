package battleship.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

// GameServer 提供连接和广播功能
public class GameServer {
    // 服务器监听指定端口
    private static final int PORT = 12345;
    private List<GameSession> sessions = new ArrayList<>();

    public static void main(String[] args) {
        GameServer server = new GameServer();
        server.startServer();
    }

    public void startServer() {
        System.out.println("服务器已启动，等待玩家连接...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                // 连接
                Socket playerSocket = serverSocket.accept();
                System.out.println("玩家连接成功.");

                // 对于每个连接的玩家，创建一个新的 GameSession 并开始新线程
                GameSession session = new GameSession(playerSocket, this);
                sessions.add(session);
                new Thread(session).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // broadcast 方法：将一名玩家的消息发送给所有其他玩家
    public void broadcast(String message, GameSession sender) {
        for (GameSession session : sessions) {
            if (session != sender) {
                session.sendMessage(message);
            }
        }
    }
}
