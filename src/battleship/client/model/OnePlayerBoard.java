package battleship.client.model;

import common.model.IBoard;
import common.model.model.IBoard;
import common.model.ships.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


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
	  * Ten ships that will be put in the ocean
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
		//for each ship in the ship lists, place it randomly
		for (Ship s: shipList) {
			this.placeOneShipRandomly(s);
		}
	}
	
	/**
	 * Place one given type of ship randomly on ocean
	 * @param ship for the ship to be placed
	 */
	private void placeOneShipRandomly(Ship ship) {
		while (true) {
			//Randomly assign a number from 1-10 to the row of the location on the ocean
			int row = random.nextInt(10);
			//Randomly assign a number from 1-10 to the column of the location on the ocean
			int column = random.nextInt(10);
			//Randomly set the ship to be placed horizontal or not
			boolean isHorizontal = random.nextBoolean();
			//Check if the ship can be put in this randomly created location
			if (this.okToPlaceShipAt(row, column, isHorizontal, ship)) {
				//if it can be placed, then just place it. 
				this.placeShipAt(row, column, isHorizontal, ship);
				//Then exit the loop
				break;
			}	
		}
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
		//Determine if the number of shipsSunk has been 10
		return shipList.isEmpty();
	}

	/**
	 * Print the OnePlayerBoard with row and column numbers(0-9) displayed
	 * "x" indicates a location that you have fired upon and hit a (real) ship.
	 * "-" indicates a location that you have fired upon and found nothing there.
	 * "s" indicates a location containing a sunken ship.
	 * "." indicates a location that has never been fired upon
	 */
	public void print() {
		//First print a column for view
		System.out.print("  0 1 2 3 4 5 6 7 8 9");
		//Then print a row for view
		for (int i = 0; i < 10; i++) {
			System.out.print("\n"+ i +" ");
			//Print each point on ocean
			for(int j = 0; j < 10; j++) {
				//If the point has been shoot
				if (this.shootPoint[i][j]){
					//Determine if there is a real ship, a empty sea or sunk ship
					System.out.print(this.ships[i][j].toString() + " ");
				}
				//If the point has never been shot
				else {
					//Just print a "."
					System.out.print("."+" ");
				}
			}
		}
	}
	
	//Getters and Setters

	public Ship getShip(int row, int col) {
		return ships[row][col];
	}
	/**
	 * @return the 10x10 array of Ships(indicating the ocean)
	 */
	public Ship[][] getShipArray() {
		return ships;
	}

	/**
	 * @return the number of shots fired (in the game)
	 */
	public int getShotsFired() {
		return shotsFired;
	}

	/**
	 * @return Returns the number of hits recorded (in the game)
	 */
	public int getHitCount() {
		return hitCount;
	}

	/**
	 * @return Returns the list of ten ships in the ocean
	 */
	public List<Ship> getShipList() {
		return shipList;
	}

	public void setShips(Ship[][] ships) {
		this.ships = ships;
	}

	public void setShotsFired(int shotsFired) {
		this.shotsFired = shotsFired;
	}

	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}

	public void setShipList(List<Ship> shipList) {
		this.shipList = shipList;
	}

	public void setShootPoint(boolean[][] shootPoint) {
		this.shootPoint = shootPoint;
	}
}
