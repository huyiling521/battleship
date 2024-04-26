package model;

import model.ships.EmptySea;
import model.ships.Ship;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TwoPlayerBoardTest {

    private TwoPlayerBoard board;

    @Before
    public void setUp() {
        board = new TwoPlayerBoard();
    }

    @Test
    public void testBoardInitialization() {
        Ship[][] ships = board.getShipArray();
        // Check if all positions are initialized to EmptySea
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertTrue("Position should be initialized to EmptySea", ships[i][j] instanceof EmptySea);
            }
        }
    }
}
