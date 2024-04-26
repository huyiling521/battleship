package finalproj.battleship.network;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import java.io.*;
import java.net.Socket;

@ExtendWith(MockitoExtension.class)
public class PlayerHandlerTest {

    private PlayerHandler handler;
    private GameSession session;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    @BeforeEach
    public void setUp() throws IOException {
        socket = mock(Socket.class);
        session = mock(GameSession.class);
        ByteArrayInputStream input = new ByteArrayInputStream("PlayerName".getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        reader = new BufferedReader(new InputStreamReader(input));
        writer = new PrintWriter(output, true);

        when(socket.getInputStream()).thenReturn(new ByteArrayInputStream("REQUEST:ATTACK:5,5".getBytes()));
        when(socket.getOutputStream()).thenReturn(output);
        handler = new PlayerHandler(socket, 0);
        handler.setSession(session);
    }

    @Test
    public void testProcessRequest_Attack() throws IOException {
        handler.run();  // This will process the mock input
        verify(session, times(1)).processPlayerMove("5,5", 0);
    }
}
