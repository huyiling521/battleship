package finalproj.battleship.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * The {@code PlayerHandler} class is responsible for managing communication for one player in a networked game session.
 * It handles all I/O interactions over a socket connection, processing incoming requests and sending responses
 * back to the client. This class implements {@link Runnable} to allow each player's session to be managed in its own thread.
 */
public class PlayerHandler implements Runnable {
    private Socket socket;
    private GameSession session;
    private PrintWriter writer;
    private BufferedReader reader;
    private String name;
    private final int index;


    /**
     * Constructs a new PlayerHandler with a socket connection and an index indicating the player's number.
     * It sets up input and output streams for communication and reads the player's name from the initial message.
     *
     * @param socket the socket through which the client is connected
     * @param index the index of the player in the game session
     * @throws IOException if an I/O error occurs while setting up the streams
     */
    public PlayerHandler(Socket socket, int index) throws IOException {
        this.socket = socket;
        this.index = index;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);
        String s = reader.readLine();
        name = s.substring(s.lastIndexOf(":") + 1);
    }

    /**
     * Listens for messages from the client and processes them as long as the socket connection remains open.
     * Handles clean up if the socket closes or an error occurs.
     */
    @Override
    public void run() {
        try {
            String inputLine;
            while (!socket.isClosed() && (inputLine = reader.readLine()) != null) {
                System.out.println(this.name + "：" + inputLine);
                allocate(inputLine);
            }
        } catch (IOException e) {
            System.out.println("Error when closing socket IOE：" + e.getMessage());
        } finally {
            try {
                if (socket != null && !socket.isClosed()) socket.close();
            } catch (Exception exception) {
                System.out.println("Error when closing socket：" + exception.getMessage());
            }
        }
    }

    /**
     * Processes individual commands received from the client, routing them to appropriate handling methods
     * within the {@link GameSession}.
     *
     * @param req the request string from the client
     */
    private void allocate(String req) {
        try {
            if (req.startsWith(MessageConstant.REQUEST)) {
                req = req.substring(req.indexOf(":") + 1);
                if (req.startsWith(MessageConstant.SETTING)) {
                    int remain = session.setShip(req.substring(req.indexOf(":") + 1), index);
                    sendResponseMessage(MessageConstant.SUCCESS + remain);
                } else if (req.startsWith(MessageConstant.ATTACK)) {
                    session.processPlayerMove(req.substring(req.lastIndexOf(":") + 1), index);
                } else if (req.startsWith(MessageConstant.GAME_START)) {
                    session.connected(this);
                } else if (req.startsWith(MessageConstant.GAME_QUIT)) {
                    session.broadcastSystemMessage(MessageConstant.GAME_QUIT + this.name + " has ended the game.", index);
                    sendResponseMessage(MessageConstant.GAME_QUIT + " You have ended the game.");
                    session.setGameEnd(true);
                }
            } else if (req.startsWith(MessageConstant.MESSAGE)) {
                sendResponseMessage(MessageConstant.SUCCESS);
                session.broadcastOpponentMessage(req.substring(req.indexOf(":") + 1), index);
            }
        } catch (Exception e) {
            sendResponseMessage(MessageConstant.ERROR + e.getMessage());
        }
    }

    /**
     * Closes the socket and cleans up resources associated with this player's session.
     */
    public void clearUp() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            System.out.println("Error closing socket.");
        }
    }

    /**
     * Sends a message from the opponent of this player.
     *
     * @param message the message to send
     */
    public void sendOpponentMessage(String message) {
        writer.println(MessageConstant.NOTIFICATION + MessageConstant.OPPONENT_MESSAGE +message);
    }

    /**
     * Sends a system  notification message to the client.
     *
     * @param message the system message to send
     */
    public void sendSystemMessage(String message) {
        writer.println(MessageConstant.NOTIFICATION + MessageConstant.SYSTEM_MESSAGE + message);
    }

    /**
     * Sends a response message back to the client.
     *
     * @param message the response message
     */
    public void sendResponseMessage(String message) {
        writer.println(MessageConstant.RESPONSE+ message);
    }

    /**
     * Gets the name of the player.
     *
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Associates this player handler with a game session.
     *
     * @param session the {@link GameSession} to associate with this player
     */
    public void setSession(GameSession session) {
        this.session = session;
    }
}
