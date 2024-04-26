package network;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.ArrayList; // Make sure to add this import

public class GameSessionTest {

    private GameSession session;
    private PlayerHandler player1;
    private PlayerHandler player2;

    @Before
    public void setUp() {
        player1 = mock(PlayerHandler.class);
        player2 = mock(PlayerHandler.class);
        List<PlayerHandler> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        session = new GameSession(players);
    }

    @Test
    public void testProcessPlayerMove_ValidMove() {
        String move = "5,5";
        session.processPlayerMove(move, 0);
        verify(player1, times(1)).sendResponseMessage(anyString());  // Assuming a response is sent
        verify(player2, times(1)).sendSystemMessage(anyString());   // Assuming a broadcast is done
    }
}
