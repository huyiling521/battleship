package finalproj.battleship.network;

import finalproj.battleship.controller.GUIController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.*;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameClientTest {
    @Mock
    private Socket socket;
    @Mock
    private PrintWriter out;
    @Mock
    private BufferedReader in;
    @Mock
    private GUIController guiController;

    private GameClient gameClient;

    @BeforeEach
    void setUp() throws Exception {
        // Create mocks for Socket, PrintWriter, and BufferedReader
        socket = mock(Socket.class);
        out = mock(PrintWriter.class);
        in = mock(BufferedReader.class);

        // Mock the socket's output stream
        when(socket.getOutputStream()).thenReturn(new ByteArrayOutputStream());
        // Mock the socket's input stream
        when(socket.getInputStream()).thenReturn(new ByteArrayInputStream(new byte[0]));

        // Mock the PrintWriter and BufferedReader to return the mocked output and input streams
        when(new PrintWriter(any(OutputStream.class), anyBoolean())).thenReturn(out);
        when(new BufferedReader(any(InputStreamReader.class))).thenReturn(in);

        // Now create the GameClient with the mocked Socket
        gameClient = new GameClient("localhost", 1234, guiController);

        when(socket.getOutputStream()).thenReturn(new ByteArrayOutputStream());
        when(socket.getInputStream()).thenReturn(new ByteArrayInputStream(new byte[0]));

        // Initialize the GameClient with mocked Socket and GUIController
        gameClient = new GameClient("localhost", 1234, guiController);
    }

    @Test
    void testSendGameMessage() throws Exception {
        // Mock the response queue to immediately return a response

        gameClient.getResponseQueue().add("RESPONSE:OK");

        String response = gameClient.sendGameMessage("SOME_GAME_MESSAGE");
        assertEquals("OK", response);

        // Verify the out.println() was called with the correct message
        verify(out).println("REQUEST:SOME_GAME_MESSAGE");
    }

    @Test
    void testProcessServerMessage() {
        // Insert your testing logic here, possibly by sending a specific message and
        // verifying that the correct methods in GUIController are called as a result.

        // Example:
        // gameClient.processServerMessage("SYSTEM_MESSAGE:OPPONENT_NAME:test_opponent");
        // verify(guiController).setOpponentName("test_opponent");
    }


    @Test
    void testSendOpponentMessage() {
    }
    
    @Test
    void testClose() {
    }

    @Test
    void testSetShip() {
    }

    @Test
    void testAttack() {
    }

    @Test
    void testSetResponseQueue() {
    }
}