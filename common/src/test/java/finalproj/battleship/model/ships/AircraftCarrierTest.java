package finalproj.battleship.model.ships;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AircraftCarrierTest {

    private AircraftCarrier aircraftCarrier;

    @BeforeEach
    public void setUp() {
        aircraftCarrier = new AircraftCarrier();
        aircraftCarrier.placeAt(0, 0, true);
    }

    @Test
    public void testShipLength() {
        assertEquals(5, aircraftCarrier.getLength());
    }

    @Test
    public void testLength() {
        assertEquals(5, aircraftCarrier.getLength()); // Check if length is correctly set to 4
    }

    @Test
    public void testGetShotAt() {
        assertTrue(aircraftCarrier.getShotAt(0, 0)); // Assume (0, 0) is a valid position to hit
        assertFalse(aircraftCarrier.getShotAt(0, 0)); // Attempt to hit the same position again
    }

    @Test
    public void testIsSunk() {
        aircraftCarrier.getShotAt(0, 0);
        aircraftCarrier.getShotAt(0, 1);
        aircraftCarrier.getShotAt(0, 2);
        aircraftCarrier.getShotAt(0, 3);
        aircraftCarrier.getShotAt(0, 4);
        assertTrue(aircraftCarrier.isSunk()); // Check if the ship is sunk after all parts are hit
    }

    @Test
    public void testGetShipType() {
        assertEquals(ShipType.AIRCRAFT_CARRIER, aircraftCarrier.getShipType());
    }
}
