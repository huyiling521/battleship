package model.ships;

public class EmptySea extends Ship {

    //Static variables
    /**
     * Name of the type of EmptySea
     */
    static final ShipType SHIPTYPE = ShipType.EMPTY;

    /**
     * Length of EmptySea
     */
    static final int SHIPLENGTH = 1;


    //Constructor
    /**
     * Construct a EmptySea with set length 1
     */
    public EmptySea() {
        super(SHIPLENGTH);
    }


    /**
     * Return false to indicate nothing was hit
     */
    @Override
    public boolean getShotAt(int row, int column) {
        return false;
    }

    @Override
    public boolean isSunk() {
        return false;
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
