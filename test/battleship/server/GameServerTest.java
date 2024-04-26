package battleship.server;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.net.ServerSocket;
import java.net.Socket;

public class GameServerTest {
    private GameServer server;
    private ServerSocket serverSocket;
    private Socket socket;

    @BeforeEach
    void setup() throws Exception {
        serverSocket = mock(ServerSocket.class);
        socket = mock(Socket.class);
        server = new GameServer();
        server.serverSocket = serverSocket;
        when(serverSocket.accept()).thenReturn(socket);
    }

    @Test
    void testServerAcceptsConnections() throws Exception {
        server.startServerTestable();  // You will need to adapt `GameServer` to make it testable
        verify(serverSocket, times(1)).accept();
    }

    @AfterEach
    void tearDown() throws Exception {
        server.closeServer();
    }
}
