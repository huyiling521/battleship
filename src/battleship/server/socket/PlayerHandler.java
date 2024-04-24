package battleship.server.socket;

import battleship.server.GameSession;

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
    private int index;
    private boolean gameEnd;


    public PlayerHandler(Socket socket, int index) throws IOException {
        this.socket = socket;
        this.index = index;
        this.gameEnd = false;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // Setup output to send data to client
        writer = new PrintWriter(socket.getOutputStream(), true);
        String s = reader.readLine();
        name = s.substring(s.lastIndexOf(":") + 1);
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 接收到玩家的操作后
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                System.out.println("Receiving message from " + this.name + "：" + inputLine);
                allocate(inputLine);
                if (gameEnd) {
                    session.clearUp();
                    break;
                }
            }
            // 异常
        } catch (Exception e) {
            sendResponseMessage(MessageConstant.ERROR + e.getMessage());
        } finally {
            try {
                if (socket != null && !socket.isClosed()) socket.close();
            } catch (Exception exception) {
                System.out.println("Error when closing socket：" + exception.getMessage());
            }
        }
    }

    private void allocate(String req) {
        if (req.startsWith(MessageConstant.REQUEST)) {
            req = req.substring(8);
            if (req.startsWith(MessageConstant.SETTING)) {
                int remain = session.setShip(req.substring(8), index);
                sendResponseMessage(MessageConstant.SUCCESS + remain);
            } else if (req.startsWith(MessageConstant.ATTACK)) {
                String[] reqArr = req.split(",");
                int row = Integer.parseInt(reqArr[1]);
                int col = Integer.parseInt(reqArr[2]);
                boolean isHit = session.shootAt(row, col, index);
                sendResponseMessage("" + isHit);
                session.broadcastSystemMessage(MessageConstant.OPPONENT_ATTACK + isHit + "," + (row + 1) + "," + (col + 1), index);
            } else if (req.startsWith(MessageConstant.GAME_START)) {
                session.connected(this);
            } else if (req.startsWith(MessageConstant.GAME_END)) {
                gameEnd = true;
                sendResponseMessage(MessageConstant.GAME_END + " You have ended the game.");
                session.broadcastSystemMessage(MessageConstant.GAME_END + this.name + "has ended the game.", index);
            }
        } else if (req.startsWith(MessageConstant.MESSAGE)) {
            session.broadcastOpponentMessage(req.substring(11), index);
        }
    }

    public void clearUp() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close(); // 关闭与客户端的连接
            }
        } catch (IOException e) {
            System.out.println("Error closing socket.");
        }
    }


    // 发送消息给当前玩家
    public void receiveOpponentMessage(String message) {
        writer.println(MessageConstant.NOTIFICATION + MessageConstant.OPPONENT_MESSAGE +message);
    }

    public void receiveSystemMessage(String message) {
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
