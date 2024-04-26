import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.net.ServerSocket;

public class GameServerTest {

    private GameServer server;

    @Before
    public void setUp() {
        server = new GameServer();
        server.startServerTestable(); // This should be an adapted start method for testing
    }

    @Test
    public void testServerStart() {
        assertNotNull("Server socket should be initialized", server.serverSocket);
        assertTrue("Server should be running", server.isRunning());
    }

    @Test
    public void testServerShutdown() {
        server.closeServer();
        assertFalse("Server should not be running after shutdown", server.isRunning());
        assertTrue("Server socket should be closed", server.serverSocket.isClosed());
    }
}
