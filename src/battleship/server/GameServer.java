package battleship.server;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServerTest {
    private GameServer server;
    private ServerSocket mockServerSocket;
    private Socket mockSocket;

    @BeforeEach
    void setup() throws Exception {
        // 创建模拟对象
        mockServerSocket = mock(ServerSocket.class);
        mockSocket = mock(Socket.class);

        // 实例化 GameServer，但不启动它
        server = new GameServer();

        // 使用 Mockito 控制 ServerSocket 行为
        when(mockServerSocket.accept()).thenReturn(mockSocket);

        // 反射或者其他方式设置私有字段
        setPrivateField(server, "serverSocket", mockServerSocket);
        setPrivateField(server, "isRunning", true); // 控制循环条件
    }

    @Test
    void testAcceptConnections() throws Exception {
        // 模拟一次连接接受
        server.runServerLoop();
        verify(mockServerSocket, times(1)).accept();
        verify(mockSocket, atLeastOnce()).getInputStream(); // 检查是否调用了获取输入流
    }

    // 反射工具方法，用于设置私有字段
    private void setPrivateField(Object object, String fieldName, Object value) throws Exception {
        var field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(object, value);
    }
}
