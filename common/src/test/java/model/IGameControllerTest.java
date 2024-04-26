package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import model.ships.ShipType;

public class IGameControllerTest {
    @Mock
    private IGameController gameController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShootAt() {
        when(gameController.shootAt(0, 0)).thenReturn(true);
        assertTrue(gameController.shootAt(0, 0));
        when(gameController.shootAt(0, 0)).thenReturn(false);
        assertFalse(gameController.shootAt(0, 0));
    }

    @Test
    public void testIsSunk() {
        when(gameController.isSunk(0, 0)).thenReturn(true);
        assertTrue(gameController.isSunk(0, 0));
        when(gameController.isSunk(0, 0)).thenReturn(false);
        assertFalse(gameController.isSunk(0, 0));
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
        when(gameController.isGameOver()).thenReturn(false);
        assertFalse(gameController.isGameOver());
    }

    @Test
    public void testGetConclusion() {
        String conclusion = "Game over after 20 shots";
        when(gameController.getConclusion()).thenReturn(conclusion);
        assertEquals(conclusion, gameController.getConclusion());
    }
}
