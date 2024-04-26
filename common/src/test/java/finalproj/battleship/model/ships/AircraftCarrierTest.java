package finalproj.battleship.model.ships;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AircraftCarrierTest {

    private AircraftCarrier aircraftCarrier;

    @BeforeEach
    public void setUp() {
        aircraftCarrier = new AircraftCarrier();
    }

    @Test
    public void testShipLength() {
        assertEquals(5, aircraftCarrier.getLength());
    }

    @Test
    public void testGetShipType() {
        assertEquals(ShipType.AIRCRAFT_CARRIER, aircraftCarrier.getShipType());
    }
}
