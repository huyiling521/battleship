package finalproj.battleship.model.ships;

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
     * Get the shiptype
     *
     * @return ship type of the ship
     */
    @Override
    public ShipType getShipType() {
        return SHIPTYPE;
    }
}
