package model.ships;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import model.ships.AircraftCarrier;
import model.ships.ShipType;

public class AircraftCarrierTest {

    private AircraftCarrier aircraftCarrier;

    @Before
    public void setUp() {
        aircraftCarrier = new AircraftCarrier();
    }

    @Test
    public void testShipLength() {
        assertEquals("Length of the AircraftCarrier should be 5", 5, aircraftCarrier.getLength());
    }

    @Test
    public void testGetShipType() {
        assertEquals("Ship type should be AIRCRAFT_CARRIER", ShipType.AIRCRAFT_CARRIER, aircraftCarrier.getShipType());
    }
}
