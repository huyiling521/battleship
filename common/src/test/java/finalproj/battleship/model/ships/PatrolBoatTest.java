package finalproj.battleship.model.ships;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PatrolBoatTest {
    private PatrolBoat patrolBoat;

    @BeforeEach
    public void setUp() {
        patrolBoat = new PatrolBoat();
        patrolBoat.placeAt(0, 0, true);
    }

    @Test
    public void testLength() {
        assertEquals(2, patrolBoat.getLength()); // Verify that the patrol boat's length is 2
    }

    @Test
    public void testGetShotAt() {
        assertTrue(patrolBoat.getShotAt(0, 0)); // Assuming (0, 0) is a valid position
    }

    @Test
    public void testIsSunk() {
        patrolBoat.getShotAt(0, 0);
        patrolBoat.getShotAt(0, 1);
        assertTrue(patrolBoat.isSunk()); // Check if the ship is sunk after hitting all sections
    }
}
