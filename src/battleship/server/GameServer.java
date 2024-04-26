package battleship.server;

import battleship.server.network.GameSession;
import battleship.common.network.MessageConstant;
import battleship.server.network.PlayerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

// GameServer 提供连接和广播功能
public class GameServer {
    private static final int PORT = 1235;
    private List<GameSession> sessions = new ArrayList<>();
    private volatile boolean isRunning = true;
    ServerSocket serverSocket;
    private long lastConnectionTime;
    private GameSession session;
    private Timer shutdownTimer;

    public static void main(String[] args) {
        GameServer server = new GameServer();
        server.startServer();
    }

    public void startServer() {
        System.out.println("Server Stared:");
        lastConnectionTime = System.currentTimeMillis(); // 初始化最后连接时间
        setupShutdownTimer();
        try {
            serverSocket = new ServerSocket(PORT);
            while (isRunning) {
                session = new GameSession(new ArrayList<>());
                while (session.getSize() < 2) {
                    Socket playerSocket = serverSocket.accept();
                    lastConnectionTime = System.currentTimeMillis();
                    PlayerHandler playerHandler = new PlayerHandler(playerSocket, session.getSize());
                    System.out.println("Player" + (session.getSize() + 1) + " connected.");
                    playerHandler.sendResponseMessage(MessageConstant.SUCCESS + "User Connected!");
                    session.add(playerHandler);
                    playerHandler.setSession(session);
                    new Thread(playerHandler).start();
                }
                sessions.add(session);
                session.broadcastOpponentInfo();
            }
        } catch (Exception e) {
            if (isRunning) {
                e.printStackTrace();
            } else {
                System.out.println("Server is shutting down.");
            }
        }
    }

    public void endGameSession(GameSession session) {
        sessions.remove(session);
        session.clearUp();
    }
    private void setupShutdownTimer() {
        shutdownTimer = new Timer();
        shutdownTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (System.currentTimeMillis() - lastConnectionTime > 30000 && session.getSize() == 1) {
                    session.sendSystemResponseMessage(MessageConstant.GAME_QUIT + "No player matched, ending the session.", 0);
                    System.out.println("No new connections to current sessions, shutting down server.");
                    session.clearUp();
                }
                if (System.currentTimeMillis() - lastConnectionTime > 600000 && sessions.isEmpty()) {
                    System.out.println("No new connections and no active sessions, shutting down server.");
                    closeServer();
                }
            }
        }, 0, 60000);
    }

    public void closeServer() {
        System.out.println("Closing server...");
        isRunning = false;
        try {
            for (GameSession session : sessions) {
                session.clearUp();
            }
            sessions.clear();
            if (shutdownTimer != null) {
                shutdownTimer.cancel();
                shutdownTimer = null;
            }
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            System.err.println("Error closing the server: " + e.getMessage());
        }
        System.out.println("Server closed successfully.");
    }

    public void startServerTestable() {
    }
}
