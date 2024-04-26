package finalproj.battleship.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class IBoardTest {
    @Mock
    private IBoard board;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShootAt() {
        when(board.shootAt(0, 0)).thenReturn(true);
        assertTrue(board.shootAt(0, 0));
        when(board.shootAt(0, 0)).thenReturn(false);
        assertFalse(board.shootAt(0, 0));
    }

    @Test
    public void testIsGameOver() {
        when(board.isGameOver()).thenReturn(false);
        assertFalse(board.isGameOver());
        when(board.isGameOver()).thenReturn(true);
        assertTrue(board.isGameOver());
    }
}
