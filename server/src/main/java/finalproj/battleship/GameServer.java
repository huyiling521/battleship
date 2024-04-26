package finalproj.battleship;

import finalproj.battleship.network.GameSession;
import finalproj.battleship.network.PlayerHandler;
import finalproj.battleship.network.MessageConstant;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The {@code finalproj.battleship.GameServer} class manages the network layer for a Battleship game server. It handles incoming player
 * connections, orchestrates game sessions, and manages session lifecycles with automatic shutdown if necessary.
 * The server runs continuously, accepting connections and allocating them to new or existing game sessions.
 */
public class GameServer {
    private static final int PORT = 1235;
    private List<GameSession> sessions = new ArrayList<>();
    private volatile boolean isRunning = true;
    ServerSocket serverSocket;
    private long lastConnectionTime;
    private GameSession session;
    private Timer shutdownTimer;

    /**
     * The main entry point for the server. Starts the server.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        GameServer server = new GameServer();
        server.startServer();
    }

    /**
     * Initializes and starts the game server, accepting connections and managing game sessions.
     */
    public void startServer() {
        System.out.println("Server Stared:");
        lastConnectionTime = System.currentTimeMillis();
        setupShutdownTimer();
        try {
            serverSocket = new ServerSocket(PORT);
            while (isRunning) {
                session = new GameSession();
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

    /**
     * Ends a game session and removes it from the active sessions list.
     *
     * @param session The {@link GameSession} to be ended and cleaned up.
     */
    public void endGameSession(GameSession session) {
        sessions.remove(session);
        session.clearUp();
    }

    /**
     * Sets up a timer task that checks for inactive sessions and closes the server
     * if no active connections are present for a specified duration.
     */
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

    /**
     * Closes the server and cleans up all resources, including active game sessions and network sockets.
     */
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

    public boolean isRunning() {
        return isRunning;
    }
}
