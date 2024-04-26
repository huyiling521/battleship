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
        // Setup the mock to simulate a hit at (0, 0)
        when(twoPlayerBoard.shootAt(0, 0)).thenReturn(true);
        assertTrue(gameController.shootAt(0, 0), "Shooting at (0, 0) should return true if a ship is hit");
    }

    @Test
    public void testIsSunk() {
        // Mock behavior for a sunk ship at (0, 0)
        Ship mockShip = mock(Ship.class);
        when(mockShip.isSunk()).thenReturn(true);
        when(twoPlayerBoard.getShip(0, 0)).thenReturn(mockShip);

        assertTrue(gameController.isSunk(0, 0), "Ship at (0, 0) should be reported as sunk");
    }

    @Test
    public void testIsGameOver() {
        when(twoPlayerBoard.isGameOver()).thenReturn(true);
        assertTrue(gameController.isGameOver(), "Game should be over when all ships are sunk");
    }

    @Test
    public void testGetConclusion() {
        when(twoPlayerBoard.getShotsFired()).thenReturn(8);
        assertEquals("You have shot 8 times.", gameController.getConclusion(), "Conclusion should reflect the number of shots fired");
    }

    @Test
    public void testGetShipType() {
        // Mock behavior for retrieving the ship type at (0, 0)
        Ship mockShip = mock(Ship.class);
        when(mockShip.getShipType()).thenReturn(ShipType.BATTLESHIP);
        when(twoPlayerBoard.getShip(0, 0)).thenReturn(mockShip);

        assertEquals(ShipType.BATTLESHIP, gameController.getShipType(0, 0), "Should return the correct ship type");
    }
}
