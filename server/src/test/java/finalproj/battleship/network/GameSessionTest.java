package finalproj.battleship.network;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.ArrayList; // Make sure to add this import

public class GameSessionTest {

    private GameSession session;
    private PlayerHandler player1;
    private PlayerHandler player2;

    @BeforeEach
    public void setUp() {
        session = new GameSession();
        player1 = mock(PlayerHandler.class);
        player2 = mock(PlayerHandler.class);
        session.add(player1);
        session.add(player2);

    }

    @Test
    public void testProcessPlayerMove_ValidMove() {
        String move = "5,5";
        session.processPlayerMove(move, 0);
        verify(player1, times(1)).sendResponseMessage(anyString());  // Assuming a response is sent
        verify(player2, times(1)).sendSystemMessage(anyString());   // Assuming a broadcast is done
    }
}
