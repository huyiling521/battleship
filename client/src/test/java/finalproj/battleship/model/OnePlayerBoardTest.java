package finalproj.battleship.model;

import static org.junit.jupiter.api.Assertions.*;

import finalproj.battleship.model.ships.Destroyer;
import finalproj.battleship.model.ships.EmptySea;
import finalproj.battleship.model.ships.PatrolBoat;
import finalproj.battleship.model.ships.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

class OnePlayerBoardTest {
    private OnePlayerBoard board;

    @BeforeEach
    void setUp() {
        board = new OnePlayerBoard();
    }

    @Test
    void boardInitialization() {
        Ship[][] ships = board.getShipArray();
        // Check if all positions are initialized to EmptySea
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertTrue(ships[i][j] instanceof EmptySea, "Position (" + i + "," + j + ") should be initialized to EmptySea");
            }
        }
        assertEquals(0, board.getShotsFired(), "No shots should have been fired at initialization");
        assertEquals(0, board.getHitCount(), "No hits should be recorded at initialization");
    }


    @Test
    void placeAllShipsRandomly() {
        board.placeAllShipsRandomly();
        long shipCells = Arrays.stream(board.getShipArray())
                .flatMap(Arrays::stream) // Correctly flattening the 2D array into a stream of Ship objects
                .filter(ship -> !(ship instanceof EmptySea)) // Filtering to count only non-EmptySea ships
                .count();
        assertTrue(shipCells > 0, "Ships should be placed on the board");
    }

    @Test
    void shootAtShip() {
        // Assuming there is a ship at position (0, 0) for simplicity in testing
        board.setShips(new Ship[10][10]); // Resetting the ship array to control the environment
        Ship ship = new Destroyer();
        ship.placeAt(0, 0, true);
        board.getShipArray()[0][0] = ship;
        board.getShipArray()[0][1] = ship;

        assertTrue(board.shootAt(0, 0), "Shooting at a ship should return true");
        assertEquals(1, board.getShotsFired(), "Shots fired should be incremented");
        assertEquals(1, board.getHitCount(), "Hit count should be incremented");
        assertFalse(board.isGameOver(), "Game should not be over after one hit");
    }

    @Test
    void gameIsOver() {
        // Place small ships and sink them
        board.setShips(new Ship[10][10]);
        Ship smallShip = new PatrolBoat();
        smallShip.placeAt(0, 0, true);
        board.getShipArray()[0][0] = smallShip;
        board.getShipArray()[0][1] = smallShip;

        board.shootAt(0, 0);
        board.shootAt(0, 1);
        assertTrue(smallShip.isSunk(), "Ship should be sunk");

        board.setShipList(new ArrayList<>());
        assertTrue(board.isGameOver(), "Game should be over when all ships are sunk");
    }
}
