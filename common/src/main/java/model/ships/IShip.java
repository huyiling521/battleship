package model.ships;

public interface IShip {
    /**
     * “Puts” the ship in the ocean.
     * This involves giving values to the bowRow, bowColumn, and horizontal instance variables in the ship
     *
     * @param row for the given row to be checked
     * @param col for the given column checked
     * @param horizontal for the orientation
     */
    void placeAt(int row, int col, boolean horizontal);

    /**
     * If a part of the ship occupies the given row and column and the ship hasn’t been sunk
     * Mark that part of the ship as “hit” (in the hit array, index 0 indicates the bow) and return true;
     * Otherwise return false
     *
     * @param row for the given place to be shoot
     * @param col for the given place to be shoot
     * @return whether successfully shoot at ship that is not sunk
     */
    boolean getShotAt(int row, int col);

    /**
     * Check if every part of the ship has been hit
     * If so, return true; otherwise return false
     *
     * @return whether every part of the ship has been hit
     */
    boolean isSunk();
}
