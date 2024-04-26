package finalproj.battleship.model.ships;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SubmarineTest {
    private Submarine submarine;

    @BeforeEach
    public void setUp() {
        submarine = new Submarine();
        submarine.placeAt(0, 0, true);
    }

    @Test
    public void testLength() {
        assertEquals(3, submarine.getLength()); // Verify that the submarine's length is 1
    }

    @Test
    public void testGetShotAt() {
        assertTrue(submarine.getShotAt(0, 0)); // Assuming (0, 0) is a valid position
    }

    @Test
    public void testIsSunk() {
        submarine.getShotAt(0, 0);
        submarine.getShotAt(0, 1);
        submarine.getShotAt(0, 2);
        assertTrue(submarine.isSunk()); // Check if the ship is sunk after it is hit
    }
}
