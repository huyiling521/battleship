package finalproj.battleship.model;

import finalproj.battleship.model.ships.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TwoPlayerBoardTest {

    private TwoPlayerBoard board;

    @BeforeEach
    public void setUp() {
        board = new TwoPlayerBoard();
    }

    @Test
    public void testBoardInitialization() {
        Ship[][] ships = board.getShipArray();
        // Check if all positions are initialized to EmptySea
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertTrue(ships[i][j] instanceof EmptySea, "Position should be initialized to EmptySea");
            }
        }
    }
}
