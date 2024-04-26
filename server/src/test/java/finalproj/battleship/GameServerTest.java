package finalproj.battleship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

public class GameServerTest {

    private GameServer server;

    @BeforeEach
    public void setUp() {
        server = new GameServer();
        server.startServerTestable(); // This should be an adapted start method for testing
    }

    @Test
    public void testServerStart() {
        assertNotNull((Object) "Server socket should be initialized", (Supplier<String>) server.serverSocket);
        assertTrue(server.isRunning(), "Server should be running");
    }

    @Test
    public void testServerShutdown() {
        server.closeServer();
        assertFalse(server.isRunning(), "Server should not be running after shutdown");
        assertTrue(server.serverSocket.isClosed(), "Server socket should be closed");
    }
}
