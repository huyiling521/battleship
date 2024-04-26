package finalproj.battleship.controller;

import finalproj.battleship.model.OnePlayerBoard;
import finalproj.battleship.model.ships.Ship;
import finalproj.battleship.model.ships.ShipType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OnePlayerGameControllerTest {
    private OnePlayerGameController gameController;
    @Mock
    private OnePlayerBoard onePlayerBoard;
    @Mock
    private Ship mockShip;

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        when(onePlayerBoard.getShip(anyInt(), anyInt())).thenReturn(mockShip);
//        gameController = new OnePlayerGameController(onePlayerBoard);
//    }

    @Test
    void shootAtShouldDelegateToBoard() {
        when(onePlayerBoard.shootAt(1, 1)).thenReturn(true);
        assertTrue(gameController.shootAt(1, 1), "Shooting at (1,1) should return true if hit");
        verify(onePlayerBoard).shootAt(1, 1);
    }

    @Test
    void checkIfShipIsSunk() {
        when(mockShip.isSunk()).thenReturn(true);
        assertTrue(gameController.isSunk(1, 1), "Ship at (1,1) should be sunk");
        verify(onePlayerBoard).getShip(1, 1);
    }

    @Test
    void getShipTypeShouldReturnCorrectType() {
        when(mockShip.getShipType()).thenReturn(ShipType.BATTLESHIP);
        assertEquals(ShipType.BATTLESHIP, gameController.getShipType(1, 1), "Should return BATTLESHIP as the ship type at (1,1)");
        verify(onePlayerBoard).getShip(1, 1);
    }

    @Test
    void checkIfGameIsOver() {
        when(onePlayerBoard.isGameOver()).thenReturn(true);
        assertTrue(gameController.isGameOver(), "Game should be over when all ships are sunk");
    }

    @Test
    void getConclusionShouldReflectGameOutcome() {
        when(onePlayerBoard.getShotsFired()).thenReturn(15);
        assertEquals("You Win! You have shot 15 times.", gameController.getConclusion(), "Conclusion should reflect the number of shots fired in the game");
    }
}
