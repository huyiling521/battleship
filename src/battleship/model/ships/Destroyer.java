package battleship.model.ships;

public class Destroyer extends Ship {

    //Static variables
    /**
     * Name of the type of all Destroyer
     */
    static final ShipType SHIPTYPE = ShipType.DESTROYER;

    /**
     * Length of Destroyer
     */
    static final int SHIPLENGTH = 3;


    //Constructor
    /**
     * Construct a Destroyer with set length 2
     */
    public Destroyer() {
        super(SHIPLENGTH);
    }


    /**
     * Return "Destroyer" as the type of the ship
     */
    @Override
    public ShipType getShipType() {
        return SHIPTYPE;
    }

}