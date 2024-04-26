package finalproj.battleship.network;

import finalproj.battleship.BattleshipClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BattleshipClientTest {
    @Mock
    private BattleshipClient client;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testConnection() {
        // 模拟网络连接测试
    }

    @Test
    public void testSendMessage() {
        // 模拟发送消息的测试
    }
}
