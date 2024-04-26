package model.ships;

public class AircraftCarrier extends Ship {

    //Static variables
    /**
     * Name of the type of all AircraftCarrier
     */
    static final ShipType SHIPTYPE = ShipType.AIRCRAFT_CARRIER;

    /**
     * Length of AircraftCarrier
     */
    static final int SHIPLENGTH = 5;

    //Constructor

    /**
     * Construct a Battleship with set length 3
     */
    public AircraftCarrier() {
        super(SHIPLENGTH);
    }

    /**
     * Get the shiptype
     *
     * @return ship type of the ship
     */
    @Override
    public ShipType getShipType() {
        return SHIPTYPE;
    }
}
