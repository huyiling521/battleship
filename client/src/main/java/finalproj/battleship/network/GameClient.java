package finalproj.battleship.network;

import finalproj.battleship.controller.GUIController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * The network.GameClient class manages the network communication between the client and the game server.
 * It handles sending requests to the server, processing incoming messages, and maintaining the connection state.
 * This class also works closely with the GUIController to update the user interface based on game events.
 */
public class GameClient {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private volatile boolean running = true;
    private GUIController guiController;
    private BlockingQueue<String> responseQueue = new LinkedBlockingQueue<>();

    /**
     * Constructs a new network.GameClient which establishes a socket connection to the server,
     * initializes communication streams, and starts the listener thread.
     *
     * @param serverAddress the IP address of the game server
     * @param port the port number of the game server
     * @param guiController the GUI controller for updating the user interface
     * @throws Exception if an error occurs during the creation of the socket or streams
     */
    public GameClient(String serverAddress, int port, GUIController guiController) throws Exception {
        socket = new Socket(serverAddress, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.guiController = guiController;
        startListenerThread();
    }

    /**
     * Starts a new listener thread to continuously read messages from the server.
     */
    private void startListenerThread() {
        Thread listenerThread = new Thread(() -> {
            try {
                String message;
                while (running && (message = in.readLine()) != null) {
                    System.out.println(message);
                    if (isResponse(message)) {
                        responseQueue.offer(message.substring(message.indexOf(":") + 1));  // 处理响应
                    } else {
                        processServerMessage(message.substring(message.indexOf(":") + 1));  // 处理通知
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading from server.");
                e.printStackTrace();
            }
        });
        listenerThread.start();
    }

    //logic of sending message
    /**
     * Sends a game-related message to the server and waits for a response.
     *
     * @param req the game request to send
     * @return the response from the server
     * @throws Exception if an error occurs during communication or the response is invalid
     */
    public String sendGameMessage(String req) throws Exception {
        return sendMoveAndWaitForResponse(MessageConstant.REQUEST + req);
    }

    /**
     * Sends a message to the opponent through the server.
     *
     * @param req the message to send to the opponent
     * @return the response from the server
     * @throws Exception if an error occurs during communication or the response is invalid
     */
    public String sendOpponentMessage(String req) throws Exception {
        return sendMoveAndWaitForResponse(MessageConstant.MESSAGE + req);
    }

    /**
     * Helper to send messages to server and handle basic errors based on server responses
     *
     * @param message the message to send to the server
     */
    private String sendMoveAndWaitForResponse(String message) throws Exception {
        out.println(message);
        String res = responseQueue.take();
        if (res == null) throw new Exception("Unknown Error.");
        if (res.startsWith(MessageConstant.ERROR)) throw new Exception(res.substring(res.indexOf(":") + 1));
        return res;
    }

    /**
     * Processes a server Notification message.
     *
     * @param message the Notification message from the server to be processed
     */
    public void processServerMessage(String message) {
        try {
            if (message.startsWith(MessageConstant.SYSTEM_MESSAGE)) {
                String[] msgArr = message.split(":");
                switch (msgArr[1]+":") {
                    case MessageConstant.OPPONENT_ATTACK:
                        String res = message.substring(message.lastIndexOf(":") + 1);
                        String[] resArr = res.split(",");
                        int row = Integer.parseInt(resArr[0]);
                        int col = Integer.parseInt(resArr[1]);
                        boolean isHit = resArr[2].equals("true");
                        String systemMessage = guiController.getOpponentName() + " attacked (" + (char) (row - 1 + 'A') + ", " + col + "): " + ((isHit) ? "HIT" : "MISS");
                        guiController.receiveSystemMessage(systemMessage);
                        guiController.receiveOpponentAttack(row, col, isHit);
                        break;
                    case MessageConstant.GAME_QUIT:
                        guiController.throwWholeErrorMessage(message.substring(message.lastIndexOf(":") + 1));
                        close();
                        guiController.toWelcomePanel();
                        break;
                    case MessageConstant.GAME_OVER:
                        guiController.throwInformationMessage(message.substring(message.lastIndexOf(":") + 1), "Game Over");
                        close();
                        guiController.toWelcomePanel();
                        break;
                    case MessageConstant.OPPONENT_NAME:
                        guiController.setOpponentName(message.substring(message.lastIndexOf(":") + 1));
                        break;
                    default:
                        guiController.receiveSystemMessage(message.substring(message.lastIndexOf(":") + 1));
                }
            } else if (message.startsWith(MessageConstant.OPPONENT_MESSAGE)) {
                guiController.receiveOpponentMessage(message.substring(message.indexOf(":") + 1));
            }
        } catch (Exception exception) {
            guiController.throwWholeErrorMessage(exception.getMessage());
        }
    }

    /**
     * Determines if a string received from the server is a response to a request.
     *
     * @param res the string to be checked
     * @return true if the string is a response, false otherwise
     */
    private boolean isResponse(String res){
        try {
            if (res == null) throw new Exception("The response from server is invalid.");
            if (res.startsWith(MessageConstant.NOTIFICATION)) return false;
            if (res.startsWith(MessageConstant.RESPONSE)) return true;
            throw new Exception("The response from server is invalid.");
        } catch (Exception exception) {
            guiController.throwWholeErrorMessage(exception.getMessage());
        }
        return false;
    }

    /**
     * Closes the client's socket and associated resources.
     */
    public void close() {
        running = false;  // Stop the listener thread
        closeResources(); // Close all network resources and clean up
    }

    /**
     * Closes the input, output streams and socket.
     */
    private void closeResources() {
        try {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            System.err.println("Error while closing resources: " + e.getMessage());
        }
    }

    /**
     * Sends a request to the server to set a ship at a specific location.
     *
     * @param req the set ship request string
     * @return an integer status code returned from the server
     * @throws Exception if an error occurs during communication or the response is invalid
     */
    public int setShip(String req) throws Exception {
        String res = sendGameMessage(MessageConstant.SETTING + req);
        String[] parts = res.split(":");
        return Integer.parseInt(parts[parts.length - 1]);
    }

    /**
     * Sends an attack request to the server for a specific coordinate.
     *
     * @param req the attack request string
     * @return true if the attack was successful, false otherwise
     * @throws Exception if an error occurs during communication or the response is invalid
     */
    public boolean attack(String req) throws Exception {
        String res = sendGameMessage(MessageConstant.ATTACK + req);
        return res.substring(res.indexOf(":") + 1).equals("true");
    }
}
