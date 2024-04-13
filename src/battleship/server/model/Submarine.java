package battleship.server.model;

public class Submarine extends Ship {

    //Static variables
    /**
     * Name of the type of all Submarine
     */
    static final ShipType SHIPTYPE = ShipType.SUBMARINE;

    /**
     * Length of Submarine
     */
    static final int SHIPLENGTH = 3;


    //Constructor
    /**
     * Construct a Submarine with set length 1
     */
    public Submarine() {
        super(SHIPLENGTH);
    }

    /**
     * Return "Submarine" as the type of the ship
     */
    @Override
    public ShipType getShipType() {

        return SHIPTYPE;
    }
}
