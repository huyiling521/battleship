package controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import controller.TwoPlayerGameController;
import model.TwoPlayerBoard;
import model.ships.Ship;
import model.ships.ShipType;
import org.junit.Before;
import org.junit.Test;

public class TwoPlayerGameControllerTest {
    private TwoPlayerGameController gameController;
    private TwoPlayerBoard twoPlayerBoard;

    @Before
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
