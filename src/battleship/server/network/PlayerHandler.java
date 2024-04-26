package battleship.server.network;

import common.model.network.MessageConstant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// PlayerHandler:
// 与单个玩家的套接字连接。
// 读取玩家发送的消息，并通过服务器的 broadcastOpponentMessage 方法发送给所有其他玩家。
// 在玩家断开连接时，清理资源。
public class PlayerHandler implements Runnable {
    private Socket socket;
    private GameSession session;
    private PrintWriter writer;
    private BufferedReader reader;
    private String name;
    private final int index;

    public PlayerHandler(Socket socket, int index) throws IOException {
        this.socket = socket;
        this.index = index;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // Setup output to send data to client
        writer = new PrintWriter(socket.getOutputStream(), true);
        String s = reader.readLine();
        name = s.substring(s.lastIndexOf(":") + 1);
    }

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

    public void clearUp() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            System.out.println("Error closing socket.");
        }
    }

    public void sendOpponentMessage(String message) {
        writer.println(MessageConstant.NOTIFICATION + MessageConstant.OPPONENT_MESSAGE +message);
    }

    public void sendSystemMessage(String message) {
        writer.println(MessageConstant.NOTIFICATION + MessageConstant.SYSTEM_MESSAGE + message);
    }

    public void sendResponseMessage(String message) {
        writer.println(MessageConstant.RESPONSE+ message);
    }

    public String getName() {
        return name;
    }

    public void setSession(GameSession session) {
        this.session = session;
    }
}
