package battleship.server.model;
import java.util.HashSet;
import java.util.Set;


public abstract class Ship implements IShip {

    //Instance variables

    /**
     * The row that contains the bow(front part of the ship)
     */
    private int bowRow;

    /**
     * The column that contains the bow(front part of the ship)
     */
    private int bowColumn;

    /**
     * The length of the ship
     */
    private int length;

    /**
     * A boolean that represents whether the ship is going to be placed horizontally or vertically
     */
    private boolean horizontal;

    //private boolean isSunk

    /**
     * An set of number that indicate what parts of the ship has been hit
     */
    private Set<Integer> hit;


    //Constructor
    /**
     * This constructor sets the length property of the particular ship
     * And initializes the hit array
     * @param length for the length of the ship
     */
    public Ship(int length) {
        //Set length to the ship by type
        this.length = length;
        //Initialize boolean[] hit with all false
        this.hit = new HashSet<>();
    }

    /**
     * Returns the type of ship
     * All subclass should implement this method by returning the corresponding ship type
     * @return the type of the ship
     */
    public abstract ShipType getShipType();

    /**
     * “Puts” the ship in the ocean.
     * This involves giving values to the bowRow, bowColumn, and horizontal instance variables in the ship
     *
     * @param row for the given row to be checked
     * @param col for the given column checked
     * @param horizontal for the orientation
     */
    public void placeAt(int row, int col, boolean horizontal) {
        //Set the row, column, and horizontal information to the ship itself
        this.bowColumn = col;
        this.bowRow = row;
        this.horizontal = horizontal;
    }

    /**
     * If a part of the ship occupies the given row and column and the ship hasn’t been sunk
     * Mark that part of the ship as “hit” (in the hit array, index 0 indicates the bow) and return true;
     * Otherwise return false
     * @param row for the given place to be shoot
     * @param col for the given place to be shoot
     * @return whether successfully shoot at ship that is not sunk
     */
    public boolean getShotAt(int row, int col) {
        //Find the index of the given location in its ship
        if (this.isSunk()) return false;
        int inShipIndex = this.checkInShip(row, col);
        //If the location is in the ship, and the ship is not sunk
        //Add the according index indicating the shot
        if (inShipIndex == -1 || hit.contains(inShipIndex)) return false;
        hit.add(inShipIndex);
        return true;
    }

    /**
     * Check if the given location is in the ship
     * @param row for the given place to check
     * @param col for the given place to check
     * @return the index(bow will be 0) of the given location, return -1 if not in the ship
     */
    private int checkInShip(int row, int col) {
        //If horizontal, row is same as bowRow, and difference between column and bow column
        //is less than the ship's length, the location is in the ship
        if (this.horizontal) {
            return (row == this.bowRow && (col - this.bowColumn < this.length) && col >= this.bowColumn)? col - this.bowColumn : -1;
        } else {
            //If vertical, column is same as bowColumn, and difference between row and bowRow
            //is less than the ship's length, the location is in the ship
            return (col == this.bowColumn && (row - this.bowRow < this.length) && row >= this.bowRow)? row - this.bowRow  : -1;
        }
    }

    /**
     * Check if every part of the ship has been hit
     * If so, return true; otherwise return false
     * @return whether every part of the ship has been hit
     */
    public boolean isSunk() {
        //If exists a part not shot, the ship is not sunk
        return hit.size() == length;
    }

    /**
     * Returns a single-character String to use in the Board’s print method.
     * Return "s" if the ship has been sunk
     * Return "x" if it has not been sunk
     */
    @Override
    public String toString() {
        //If the ship is sunk, mark it as "s", otherwise as "x"
        if (this.isSunk()) return "s";
        else return "x";
    }


    //Getters and Setters
    /**
     * Get the value of bowRow
     *
     * @return Returns the row corresponding to the position of the bow
     */
    public int getBowRow() {
        return bowRow;
    }

    /**
     * Get the value of bowColumn
     *
     * @return Returns the bow column location
     */
    public int getBowColumn() {
        return bowColumn;
    }

    /**
     * Get the value of length
     *
     * @return Returns the ship length
     */
    public int getLength() {
        return length;
    }

    /**
     * Get the value of horizontal
     *
     * @return Returns whether the ship is horizontal or not
     */
    public boolean isHorizontal() {
        return horizontal;
    }

    /**
     * Get the value of hit
     *
     * @return Returns the hit array
     */
    public Set<Integer> getHit() {
        return hit;
    }

    /**
     * Set the value of bowRow
     *
     * @param bowRow for the row of bow
     */
    public void setBowRow(int bowRow) {
        this.bowRow = bowRow;
    }

    /**
     * Set the value of bowColumn
     *
     * @param bowColumn for the column of bow
     */
    public void setBowColumn(int bowColumn) {
        this.bowColumn = bowColumn;
    }

    /**
     * Set the value of the instance variable horizontal
     *
     * @param horizontal for the orientation
     */
    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    /**
     * Set the value of boolean[] hit for ShipTest
     *
     * @param hit for the status of being hit
     */
    public void setHit(Set<Integer> hit) {
        this.hit = hit;
    }
}
