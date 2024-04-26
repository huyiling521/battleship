package battleship.client;

import battleship.client.GUI.BattleshipFrame;
import battleship.client.controller.GUIController;

// import java.lang.reflect.InvocationTargetException;

public class BattleshipClient {
    public static void main(String[] args) {
        try {
            // GUI控制器
            GUIController guiController = new GUIController();
            BattleshipFrame first = new BattleshipFrame(guiController);
            first.setVisible(true);

            // 创建游戏客户端，连接到服务器
//            GameClient client = new GameClient("localhost", 12345);
            // 示例：发送一条消息到服务器
//            client.send("Hello Server!");
            // 示例：接收服务器的响应
//            String response = client.receive();
//            System.out.println("Received from server: " + response);

            // 关闭连接
//            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}