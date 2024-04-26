package battleship.server.model.ships;

public class PatrolBoat extends Ship {

	//Static variables
	/**
	 * Name of the type of all Patrol Boat
	 */
	static final ShipType SHIPTYPE = ShipType.PATROL_BOAT;

	/**
	 * Length of Patrol Boat
	 */
	static final int SHIPLENGTH = 2;


	//Constructor
	/**
	 * Construct a Patrol Boat with set length 1
	 */
	public PatrolBoat() {
		super(SHIPLENGTH);
	}
		
	/**
	 * Return "Patrol Boat" as the type of the ship
	 */
	@Override
	public ShipType getShipType() {
		
		return SHIPTYPE;
	}
}
