package finalproj.battleship.model.ships;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DestroyerTest {
    private Destroyer destroyer;

    @BeforeEach
    public void setUp() {
        destroyer = new Destroyer();
    }

    @Test
    public void testLength() {
        assertEquals(3, destroyer.getLength()); // Verify that the destroyer's length is 3
    }

    @Test
    public void testGetShotAt() {
        assertTrue(destroyer.getShotAt(0, 0)); // Assuming (0, 0) is a valid position
    }

    @Test
    public void testIsSunk() {
        destroyer.getShotAt(0, 0);
        destroyer.getShotAt(0, 1);
        destroyer.getShotAt(0, 2);
        assertTrue(destroyer.isSunk()); // Check if the ship is sunk after hitting all sections
    }
}
