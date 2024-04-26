package finalproj.battleship.model;

import finalproj.battleship.model.IBoard;
import finalproj.battleship.model.ships.*;

import java.util.*;


public class TwoPlayerBoard implements IBoard {

	//Instance variables

	/**
	 * A variable used to quickly determine which ship is in any given location
	 */
	private Ship[][] ships = new Ship[10][10];

	/**
	 * The total number of shots fired by the user
	 */
	private int shotsFired;

	/**
	 * The number of times a shot hit a ship.
	 * If the user shoots the same part of a ship more than once, every hit is counted
	 */
	private int hitCount;

	/**
	 * A random number generator to be used for returning random "row" and "column" for a ship.
	 */
	 Random random = new Random();

	 /**
	  * A list to store ten ships
	  */
	 List<Ship> shipList;
	 HashMap<String, List<Ship>> settingList;

	 /**
	  * A list to help determine whether a point is shot.
	  */
	 boolean[][] shootPoint = new boolean[10][10];


	//Constructor
	/**
	 * Creates an 'empty' ocean
	 * Fills the ships array with EmptySea objects
	 * Also initializes any game variables
	 */
	public TwoPlayerBoard() {
		//Initialize the number of shots been fired to 0
		this.shotsFired = 0;
		//Initialize the number of hit to 0
		this.hitCount = 0;
		this.settingList = new HashMap<>();
		this.shipList = new ArrayList<>();
		//Initialize ships
		this.newShips();
		//Create a new empty ocean
		this.newOcean();
	}
	
	/**
	 * A helper to create an 'empty' ocean
	 * Fills the ships array with EmptySea objects.
	 */
	private void newOcean() {
		//for each location in the ocean, fill it with an empty ship
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				this.ships[i][j] = new EmptySea();
			}
		}
	}

	/**
	 * A helper to create 10 ships to be placed
	 */
	private void newShips() {
		settingList.put("aircraftCarrier", new ArrayList<>(Arrays.asList(new AircraftCarrier())));
		settingList.put("battleship", new ArrayList<>(Arrays.asList(new Battleship(), new Battleship())));
		settingList.put("destroyer", new ArrayList<>(Arrays.asList(new Destroyer(), new Destroyer())));
		settingList.put("submarine", new ArrayList<>(Arrays.asList(new Submarine(), new Submarine())));
		settingList.put("patrolBoat", new ArrayList<>(Arrays.asList(new PatrolBoat(), new PatrolBoat(), new PatrolBoat(), new PatrolBoat())));
	}

	/**
	 * Returns true if the given location contains a ship, false if it does not
	 * @param row for the given location
	 * @param column for the given location
	 * @return whether the location is occupied
	 */
	private boolean isOccupied(int row, int column) {
		//decide if the type of the ship is empty
		return !this.ships[row][column].getShipType().equals(ShipType.EMPTY);
	}
	
	/**
	 * Check if the given location contains a ”real” ship which is still afloat
	 * Return true if yes, otherwise return false
	 * Updates the number of shots that have been fired and the number of hits
	 * Shoot at every location in the ocean to make sure each part of the ship that contains the given location
	 * has been marked in correct index in its boolean[] hit
	 *
	 * @param row for the given location
	 * @param col for the given location
	 * @return Return true if exists a "real" ship, otherwise false
	 */
	public boolean shootAt(int row, int col) {

		if (shootPoint[row][col]) throw new IllegalArgumentException("The position has been shot. Please choose another one.");
		//Increase the number of shots been fire to 1
		shotsFired++;
		//records this location has been shot
		shootPoint[row][col] = true;
		Ship ship = ships[row][col];
		//if the shot is not valid return false
		if (!ship.getShotAt(row, col)) return false;
		//otherwise increase hit counts
		hitCount++;
		if (ship.isSunk()) this.shipList.remove(ship);
		return true;
	}
	
	/**
	 * Place one ship at a given row, column and orientation
	 *
	 * @param ship for the ship to be placed
	 */
	private void placeOneShip(Ship ship) {
		int row = ship.getBowRow();
		int col = ship.getBowColumn();
		boolean horizontal = ship.isHorizontal();
		//First find out if the ship is going to be placed horizontally

		if (horizontal) {
			//The points on same ship will have same number of row but different number of columns
			for (int i = col; i < col + ship.getLength(); i++) {
				//Horizontally place a battleship
				this.ships[row][i] = ship;
			}		
		}
		//If the ship is not going to be placed horizontally
		else {
			//The points on same ship will have same number of column but different number of rows
			for (int i = row; i < row + ship.getLength(); i++) {
				//Vertically place a battleship
				this.ships[i][col] = ship;
			}		
		}
	}

	private boolean okToPlaceShipAt(int row, int col, boolean horizontal, Ship ship) {
		//Check if the location the ship is planned to place to has enough space(not beyond ocean)
		if (row > 9 || row < 0 || col > 9 || col < 0) return false;

		int length = ship.getLength();
		if (horizontal &&  col + length > 10 || !horizontal && row + length > 10) return false;
		if (horizontal) {
			for (int i = col; i < col + length; i++) {
					// Only if all spaces are free, return true
				if (this.isOccupied(row, i)) return false;
			}
		} else {
			for (int i = row; i < row + length; i++) {
				// Only if all spaces are free, return true
				if (this.isOccupied(i, col)) return false;
			}
		}
		return true;
	}

	/**
	 * “Puts” the ship in the ocean.
	 * This involves giving values to the bowRow, bowColumn, and horizontal instance variables in the ship
	 * And it also involves putting a reference to the ship in each of 1 or more locations (up to 4) in the ships array in the OnePlayerBoard object.
	 * @param row for the given row to be checked
	 * @param col for the given column checked
	 * @param horizontal for the orientation
	 * @param shipType for the type of Ship to be placed
	 */

	public int placeOneShip(int row, int col, boolean horizontal, String shipType) {
		List<Ship> currShipList = settingList.get(shipType);
		if (currShipList.isEmpty()) throw new IllegalArgumentException("You have already placed all " + shipType);
		Ship ship = currShipList.get(0);
		if (!okToPlaceShipAt(row, col, horizontal, ship)) throw new IllegalArgumentException("The place is invalid to place the ship.");
		ship.placeAt(row, col, horizontal);
		this.placeOneShip(ship);
		shipList.add(currShipList.get(0));
		currShipList.remove(0);
		return currShipList.size();
	}
	
    /**
     * Determine if all ships have been sunk
     * @return true if all ships have been sunk, otherwise false
     */
	public boolean isGameOver() {
		//Determine if the number of shipsSunk has been 10
		return shipList.isEmpty();
	}
	
	//Getters and Setters
	/**
	 * Gets the 10x10 array representing the ships on the board.
	 *
	 * @return the 10x10 array of Ships(indicating the ocean)
	 */
	public Ship[][] getShipArray() {
		return ships;
	}

	/**
	 * Gets the total number of shots fired by the player in the game.
	 *
	 * @return the number of shots fired (in the game)
	 */
	public int getShotsFired() {
		return shotsFired;
	}

	/**
	 * Gets the total number of hits recorded in the game.
	 *
	 * @return Returns the number of hits recorded (in the game)
	 */
	public int getHitCount() {
		return hitCount;
	}

	/**
	 * Gets the list of ships placed on the ocean.
	 *
	 * @return Returns the list of ten ships in the ocean
	 */
	public List<Ship> getShipList() {
		return shipList;
	}

	/**
	 * Sets the array of ships on the board.
	 *
	 * @param ships a 10x10 array of {@link Ship} objects representing the new state of the ocean
	 */
	public void setShips(Ship[][] ships) {
		this.ships = ships;
	}

	/**
	 * Sets the total number of shots that have been fired in the game.
	 *
	 * @param shotsFired the new number of shots fired
	 */
	public void setShotsFired(int shotsFired) {
		this.shotsFired = shotsFired;
	}

	/**
	 * Sets the total number of hits recorded in the game.
	 *
	 * @param hitCount the new number of hits recorded
	 */
	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}

	/**
	 * Sets the list of ships in the ocean.
	 *
	 * @param shipList a list of {@link Ship} objects representing all the ships in the ocean
	 */
	public void setShipList(List<Ship> shipList) {
		this.shipList = shipList;
	}

	/**
	 * Sets the array indicating whether each point on the board has been shot.
	 *
	 * @param shootPoint a 10x10 boolean array where true indicates a shot has been fired at the corresponding location
	 */
	public void setShootPoint(boolean[][] shootPoint) {
		this.shootPoint = shootPoint;
	}

	/**
	 * Retrieves a ship at the specified location on the board.
	 *
	 * @param row the row index of the ship to retrieve
	 * @param col the column index of the ship to retrieve
	 * @return the ship located at the specified row and column
	 */
	public Ship getShip(int row, int col) {
		return ships[row][col];
	}
}
