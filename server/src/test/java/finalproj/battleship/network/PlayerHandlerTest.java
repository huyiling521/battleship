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

        String simulatedUserInput = "PlayerName\nREQUEST:ATTACK:5,5\n";
        ByteArrayInputStream input = new ByteArrayInputStream(simulatedUserInput.getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        reader = new BufferedReader(new InputStreamReader(input));
        writer = new PrintWriter(output, true);

        when(socket.getInputStream()).thenReturn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        when(socket.getOutputStream()).thenReturn(new ByteArrayOutputStream());
        handler = new PlayerHandler(socket, 0);
        handler.setSession(session);
    }

    @Test
    public void testProcessRequest_Attack() throws Exception {
        Thread testThread = new Thread(handler);
        testThread.start();
        testThread.join();

        verify(session, times(1)).processPlayerMove("5,5", 0);
    }
}
