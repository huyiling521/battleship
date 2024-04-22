package battleship.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public GameClient(String serverAddress, int port) throws Exception {
        // 连接到服务器
        socket = new Socket(serverAddress, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    // 发送消息到服务器
    public void send(String message) {
        out.println(message);
    }

    // 从服务器接收消息
    public String receive() throws Exception {
        return in.readLine();
    }

    // 关闭连接
    public void close() throws Exception {
        in.close();
        out.close();
        socket.close();
    }
}
