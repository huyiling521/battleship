package battleship.client.network;

import battleship.client.controller.GUIController;
import common.model.network.MessageConstant;
//import battleship.client.controller.MessageController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class GameClient {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private volatile boolean running = true;
    private GUIController guiController;
    private BlockingQueue<String> responseQueue = new LinkedBlockingQueue<>();

    public GameClient(String serverAddress, int port, GUIController guiController) throws Exception {
        // 连接到服务器
        socket = new Socket(serverAddress, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.guiController = guiController;
        startListenerThread();
    }

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
    public String sendGameMessage(String req) throws Exception {
        return sendMoveAndWaitForResponse(MessageConstant.REQUEST + req);
    }

    public String sendOpponentMessage(String req) throws Exception {
        return sendMoveAndWaitForResponse(MessageConstant.MESSAGE + req);
    }

    private String sendMoveAndWaitForResponse(String move) throws Exception {
        out.println(move);  // 发送动作到服务器
        String res = responseQueue.take();
        if (res == null) throw new Exception("Unknown Error.");
        if (res.startsWith(MessageConstant.ERROR)) throw new Exception(res.substring(res.indexOf(":") + 1));
        return res;
    }

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


//            if (message.startsWith(MessageConstant.SYSTEM_MESSAGE + MessageConstant.OPPONENT_ATTACK)) {
//                String res = message.substring(message.lastIndexOf(":") + 1);
//                String[] resArr = res.split(",");
//                int row = Integer.parseInt(resArr[0]);
//                int col = Integer.parseInt(resArr[1]);
//                boolean isHit = resArr[2].equals("true");
//                String systemMessage = guiController.getOpponentName() + " attacked (" + (char) (row - 1 + 'A') + ", " + col + "): " + ((isHit) ? "HIT" : "MISS");
//                guiController.receiveSystemMessage(systemMessage);
//                guiController.receiveOpponentAttack(row, col, isHit);
//            } else if (message.startsWith(MessageConstant.SYSTEM_MESSAGE + MessageConstant.GAME_QUIT)) {
//                guiController.throwWholeErrorMessage(message.substring(message.lastIndexOf(":") + 1));
//                guiController.toWelcomePanel();
//            } else if (message.startsWith(MessageConstant.SYSTEM_MESSAGE + MessageConstant.OPPONENT_NAME)) {
//                guiController.setOpponentName(message.substring(message.lastIndexOf(":") + 1));
//            } else if (message.startsWith(MessageConstant.SYSTEM_MESSAGE)) {
//                guiController.receiveSystemMessage(message.substring(message.lastIndexOf(":") + 1));
//            }else if (message.startsWith(MessageConstant.OPPONENT_MESSAGE)) {
//                guiController.receiveOpponentMessage(message.substring(message.indexOf(":") + 1));
//            }
        } catch (Exception exception) {
            guiController.throwWholeErrorMessage(exception.getMessage());
        }
    }

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

    public void close() {
        running = false;  // Stop the listener thread
        closeResources(); // Close all network resources and clean up
    }

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

    public int setShip(String req) throws Exception {
        String res = sendGameMessage(MessageConstant.SETTING + req);
        String[] parts = res.split(":");
        return Integer.parseInt(parts[parts.length - 1]);
    }

    public boolean attack(String req) throws Exception {
        String res = sendGameMessage(MessageConstant.ATTACK + req);
        return res.substring(res.indexOf(":") + 1).equals("true");
    }
}
