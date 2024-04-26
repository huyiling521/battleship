package finalproj.battleship.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import finalproj.battleship.model.TwoPlayerBoard;
import finalproj.battleship.model.ships.Ship;
import finalproj.battleship.model.ships.ShipType;
import org.junit.jupiter.api.*;

public class TwoPlayerGameControllerTest {
    private TwoPlayerGameController gameController;
    private TwoPlayerBoard twoPlayerBoard;

    @BeforeEach
    public void setUp() {
        twoPlayerBoard = mock(TwoPlayerBoard.class);
        gameController = new TwoPlayerGameController(twoPlayerBoard);
    }

    @Test
    public void testShootAt() {
        when(twoPlayerBoard.shootAt(0, 0)).thenReturn(true);
        assertTrue(gameController.shootAt(0, 0));
    }

    @Test
    public void testIsSunk() {
        when(twoPlayerBoard.getShip(0, 0)).thenReturn(new Ship() {
            @Override
            public boolean isSunk() {
                return true;
            }
            @Override
            public ShipType getShipType() {
                return ShipType.DESTROYER;
            }
        });
        assertTrue(gameController.isSunk(0, 0));
    }

    @Test
    public void testIsGameOver() {
        when(twoPlayerBoard.isGameOver()).thenReturn(true);
        assertTrue(gameController.isGameOver());
    }

    @Test
    public void testGetConclusion() {
        when(twoPlayerBoard.getShotsFired()).thenReturn(8);
        assertEquals("You have shot 8 times.", gameController.getConclusion());
    }

    @Test
    public void testGetShipType() {
        when(twoPlayerBoard.getShip(0, 0)).thenReturn(new Ship() {
            @Override
            public ShipType getShipType() {
                return ShipType.BATTLESHIP;
            }
        });
        assertEquals(ShipType.BATTLESHIP, gameController.getShipType(0, 0));
    }
}
