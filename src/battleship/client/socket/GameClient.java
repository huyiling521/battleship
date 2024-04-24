package battleship.client.socket;

import battleship.client.controller.GUIController;
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
    private boolean running = true;
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
                while (running) {
                    String message = in.readLine();
                    System.out.println(message);
                    if (message != null) {
                        if (isResponse(message)) {
                            responseQueue.offer(message.substring(message.indexOf(":") + 1));  // 处理响应
                        } else {
                            processServerMessage(message.substring(message.indexOf(":") + 1));  // 处理通知
                        }
                    }
                }
            } catch (Exception e) {
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
        if (res.startsWith("ERROR:")) throw new Exception(res.substring(6));
        return res;
    }

    public void processServerMessage(String message) {
        if (message.startsWith(MessageConstant.SYSTEM_MESSAGE + MessageConstant.OPPONENT_ATTACK)) {
            String[] resArr = message.split(":");
            int row = Integer.parseInt(resArr[2]) + 1;
            int col = Integer.parseInt(resArr[3]) + 1;
            boolean isHit = resArr[1].equals(true);
            String systemMessage = guiController.getOpponentName() + " attacked (" + (char)(row - 1 + 'A') + ", " + col + "): " + ((isHit)? "HIT" : "MISS" );
            guiController.receiveSystemMessage(systemMessage);
            guiController.receiveOpponentAttack(row, col, isHit);
        } else if (message.startsWith(MessageConstant.SYSTEM_MESSAGE + MessageConstant.OPPONENT_NAME)) {
            guiController.setOpponentName(message.substring(message.lastIndexOf(":") + 1));
        } else if (message.startsWith(MessageConstant.OPPONENT_MESSAGE)) {
            guiController.receiveOpponentMessage(message.substring(message.indexOf(":") + 1));
        }
    }

    private boolean isResponse(String res) throws Exception {
        if (res == null) throw new Exception("The response from server is invalid.");
        if (res.startsWith(MessageConstant.NOTIFICATION)) return false;
        if (res.startsWith(MessageConstant.RESPONSE)) return true;
        throw new Exception("The response from server is invalid.");
    }

    // 关闭连接
    public void close() throws Exception {
        in.close();
        out.close();
        socket.close();
    }

    public int setShip(String req) throws Exception {
        String res = sendGameMessage(MessageConstant.SETTING + req);
        String[] parts = res.split(":");
        return Integer.parseInt(parts[parts.length - 1]);
    }

    public boolean attack(String req) throws Exception {
        String res = sendGameMessage(MessageConstant.ATTACK + req);
        return res.split(",")[1].equals("true");
    }
}
