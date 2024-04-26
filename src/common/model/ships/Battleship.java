package battleship.server.model.ships;

public class Battleship extends Ship {

    //Static variables
    /**
     * Name of the type of all Battleship
     */
    static final ShipType SHIPTYPE = ShipType.BATTLESHIP;

    /**
     * Length of Battleship
     */
    static final int SHIPLENGTH = 4;


    //Constructor
    /**
     * Construct a Battleship with set length 4
     */
    public Battleship() {
        super(SHIPLENGTH);
    }


    /**
     * Return "Battleship" as the type of the ship
     */
    @Override
    public ShipType getShipType() {
        return SHIPTYPE;
    }

}

