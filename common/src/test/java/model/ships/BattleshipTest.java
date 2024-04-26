package model.ships;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BattleshipTest {
    private Battleship battleship;

    @BeforeEach
    public void setUp() {
        battleship = new Battleship();
    }

    @Test
    public void testLength() {
        assertEquals(4, battleship.getLength()); // Check if length is correctly set to 4
    }

    @Test
    public void testGetShotAt() {
        assertTrue(battleship.getShotAt(0, 0)); // Assume (0, 0) is a valid position to hit
        assertFalse(battleship.getShotAt(0, 0)); // Attempt to hit the same position again
    }

    @Test
    public void testIsSunk() {
        battleship.getShotAt(0, 0);
        battleship.getShotAt(0, 1);
        battleship.getShotAt(0, 2);
        battleship.getShotAt(0, 3);
        assertTrue(battleship.isSunk()); // Check if the ship is sunk after all parts are hit
    }
}
