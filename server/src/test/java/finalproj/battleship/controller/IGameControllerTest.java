package finalproj.battleship.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import finalproj.battleship.model.IGameController;
import finalproj.battleship.model.ships.ShipType;
import org.junit.jupiter.api.*;


public class IGameControllerTest {
    private IGameController gameController;

    @BeforeEach
    public void setUp() {
        gameController = mock(IGameController.class);
    }

    @Test
    public void testShootAt() {
        when(gameController.shootAt(0, 0)).thenReturn(true);
        assertTrue(gameController.shootAt(0, 0));
    }

    @Test
    public void testIsSunk() {
        when(gameController.isSunk(0, 0)).thenReturn(true);
        assertTrue(gameController.isSunk(0, 0));
    }

    @Test
    public void testGetShipType() {
        when(gameController.getShipType(0, 0)).thenReturn(ShipType.BATTLESHIP);
        assertEquals(ShipType.BATTLESHIP, gameController.getShipType(0, 0));
    }

    @Test
    public void testIsGameOver() {
        when(gameController.isGameOver()).thenReturn(true);
        assertTrue(gameController.isGameOver());
    }

    @Test
    public void testGetConclusion() {
        when(gameController.getConclusion()).thenReturn("Player 1 wins!");
        assertEquals("Player 1 wins!", gameController.getConclusion());
    }
}
