package finalproj.battleship.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OnePlayerBoardTest {
    private OnePlayerBoard onePlayerBoard;

    @BeforeEach
    public void setUp() {
       onePlayerBoard = new OnePlayerBoard();
    }

    @Test
    public void testBoardInitialization() {
        assertTrue(onePlayerBoard.getShipList().size() == 11);
    }

    @Test
    public void testShipPlacement() {
        // Test the placement of ships on the board.
        // This test should verify that ships are placed within the bounds of the board and do not overlap.
        // Assert conditions where ship placements should fail (e.g., out of bounds, overlapping another ship).
    }

    @Test
    public void testShootAt() {
        boolean shot = onePlayerBoard.shootAt(0,0);
        if (shot == true) {
            Exception e = assertThrows(IllegalArgumentException.class, () -> {
                onePlayerBoard.shootAt(0, 0);
            });
            assertEquals("The position has been shot. Please choose another one.", e.getMessage());
        }
    }

    @Test
    public void testIsGameOver() {
        assertFalse(onePlayerBoard.isGameOver());

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                onePlayerBoard.shootAt(i, j);
            }
        }
        assertTrue(onePlayerBoard.isGameOver());
    }

    @Test
    public void testGetShotsFired() {
        assertEquals(0, onePlayerBoard.getShotsFired());
        assertEquals(0, onePlayerBoard.getHitCount());
        int n = onePlayerBoard.getShotsFired();
        int m = onePlayerBoard.getHitCount();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                boolean shot = onePlayerBoard.shootAt(i, j);
                assertEquals(++n, onePlayerBoard.getShotsFired());
                if (shot) assertEquals(++m, onePlayerBoard.getHitCount());
            }
        }
    }
}
