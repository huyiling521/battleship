package battleship.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// GameSession:
// 与单个玩家的套接字连接。
// 读取玩家发送的消息，并通过服务器的 broadcast 方法发送给所有其他玩家。
// 在玩家断开连接时，清理资源。
public class GameSession implements Runnable {
    private Socket socket;
    private GameServer server;
    private PrintWriter writer;

    public GameSession(Socket socket, GameServer server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            // 接收到玩家的操作后
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                // 调用 server 的广播功能
                System.out.println("接收到来自玩家的消息：" + inputLine);
                server.broadcast(inputLine, this);
            }
            // 异常
        } catch (Exception e) {
            System.out.println("处理玩家输入时发生错误：" + e.getMessage());
            e.printStackTrace();
            // 关闭
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                System.out.println("关闭玩家连接时发生错误：" + e.getMessage());
            }
        }
    }

    // 发送消息给当前玩家
    public void sendMessage(String message) {
        writer.println(message);
    }
}
