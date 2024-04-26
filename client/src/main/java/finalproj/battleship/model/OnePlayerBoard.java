package finalproj.battleship.model;

import model.IBoard;
import model.ships.EmptySea;
import model.ships.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * The OnePlayerBoard class represents the playing board for a single player in a game of Battleship.
 * It implements the IBoard} interface, providing all the necessary functionality for managing
 * the player's ships and interactions with them during the game.
 */
public class OnePlayerBoard implements IBoard {
	
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
	  * 11 ships that will be put in the ocean
	  */

	 private Ship aircraftCarrier;
	 private Ship battleship1;
	 private Ship battleship2;
	 private Ship destroyer1;
	 private Ship destroyer2;
	 private Ship submarine1;
	 private Ship submarine2;
	 private Ship patrolBoat1;
	 private Ship patrolBoat2;
	 private Ship patrolBoat3;
	 private Ship patrolBoat4;
	 /**
	  * A list to store ten ships
	  */
	 List<Ship> shipList;

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
	public OnePlayerBoard() {
		//Initialize the number of shots been fired to 0
		this.shotsFired = 0;
		//Initialize the number of hit to 0
		this.hitCount = 0;
		//Initialize ships
		this.newShips();
		//Create a new empty ocean
		this.newOcean();
		shipList = new ArrayList<>(Arrays.asList(aircraftCarrier, battleship1, battleship2, destroyer1, destroyer2, submarine1, submarine2, patrolBoat1, patrolBoat2, patrolBoat3, patrolBoat4));
		placeAllShipsRandomly();
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
		aircraftCarrier = new AircraftCarrier();
		battleship1 = new Battleship();
		battleship2 = new Battleship();
		destroyer1 = new Destroyer();
		destroyer2 = new Destroyer();
		submarine1 = new Submarine();
		submarine2 = new Submarine();
		patrolBoat1 = new PatrolBoat();
		patrolBoat2 = new PatrolBoat();
		patrolBoat3 = new PatrolBoat();
		patrolBoat4 = new PatrolBoat();
	}
	
	/**
	 * Place all ten ships randomly on the (initially empty) ocean. 
	 * Place larger ships before smaller ones
	 */
	public void placeAllShipsRandomly() {
		for (Ship s: shipList) {
			this.placeOneShipRandomly(s);
		}
	}
	
	/**
	 * Place one given type of ship randomly on ocean
	 *
	 * @param ship for the ship to be placed
	 */
	private void placeOneShipRandomly(Ship ship) {
		while (true) {
			//Randomly assign a number from 1-10 to the row and col of the location on the ocean
			int row = random.nextInt(10);
			int column = random.nextInt(10);
			//Randomly set the ship to be placed horizontal or not
			boolean isHorizontal = random.nextBoolean();
			//Check if the ship can be put in this randomly created location
			if (this.okToPlaceShipAt(row, column, isHorizontal, ship)) {
				this.placeShipAt(row, column, isHorizontal, ship);
				break;
			}	
		}
	}

	/**
	 * Returns true if the given location contains a ship, false if it does not
	 *
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
		//Increase the number of shots been fire
		shotsFired++;
		//records this location has been shot
		shootPoint[row][col] = true;
		Ship ship = ships[row][col];
		if (!ship.getShotAt(row, col)) return false;
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

		//Horizontally place a battleship
		if (horizontal) {
			for (int i = col; i < col + ship.getLength(); i++) {
				this.ships[row][i] = ship;
			}		
		}
		//Vertically place a battleship
		else {
			for (int i = row; i < row + ship.getLength(); i++) {
				this.ships[i][col] = ship;
			}		
		}
	}

	/**
	 * Determines whether it is possible to place a given ship at the specified location with the specified orientation.
	 *
	 * @param row row of ship's bow.
	 * @param col col of ship's bow
	 * @param horizontal true if ship is horizontally paces, false if vertically placed
	 * @param ship The Ship object to be placed.
	 * @return true if the ship can be placed at the specified location
	 */
	private boolean okToPlaceShipAt(int row, int col, boolean horizontal, Ship ship) {
		//Check if the location is valid
		if (row > 9 || row < 0 || col > 9 || col < 0) return false;

		//Check if the space for the ship planned to place is enough
		int length = ship.getLength();
		if (horizontal &&  col + length > 10 || !horizontal && row + length > 10) return false;
		//Check places need to be occupied are free
		if (horizontal) {
			for (int i = col; i < col + length; i++) {
				if (this.isOccupied(row, i)) return false;
			}
		} else {
			for (int i = row; i < row + length; i++) {
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
	 * @param ship for the Ship to be placed
	 */
	private void placeShipAt(int row, int col, boolean horizontal, Ship ship) {
		//Set the row, column, and horizontal information to the ship itself
		ship.placeAt(row, col, horizontal);
		//Set the ship to the ocean accordingly
		this.placeOneShip(ship);
	}
	
    /**
     * Determine if all ships have been sunk
     * @return true if all ships have been sunk, otherwise false
     */
	public boolean isGameOver() {
		return shipList.isEmpty();
	}

	//Getters and Setters
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
}
