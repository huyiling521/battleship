package finalproj.battleship.model.ships;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SubmarineTest {
    private Submarine submarine;

    @BeforeEach
    public void setUp() {
        submarine = new Submarine();
    }

    @Test
    public void testLength() {
        assertEquals(1, submarine.getLength()); // Verify that the submarine's length is 1
    }

    @Test
    public void testGetShotAt() {
        assertTrue(submarine.getShotAt(0, 0)); // Assuming (0, 0) is a valid position
    }

    @Test
    public void testIsSunk() {
        submarine.getShotAt(0, 0);
        assertTrue(submarine.isSunk()); // Check if the ship is sunk after it is hit
    }
}
