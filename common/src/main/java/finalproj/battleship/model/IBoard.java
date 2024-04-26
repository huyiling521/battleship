package finalproj.battleship.model;

import finalproj.battleship.model.ships.Ship;

import java.util.List;

public interface IBoard {

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
    boolean shootAt(int row, int col);

    /**
     * Determine if all ships have been sunk
     * @return true if all ships have been sunk, otherwise false
     */
    boolean isGameOver();

    //Getters and Setters
    /**
     * Retrieves a ship at the specified location on the board.
     *
     * @param row the row index of the ship to retrieve
     * @param col the column index of the ship to retrieve
     * @return the ship located at the specified row and column
     */
    Ship getShip(int row, int col);
    /**
     * Gets the 10x10 array representing the ships on the board.
     *
     * @return the 10x10 array of Ships(indicating the ocean)
     */
    Ship[][] getShipArray();

    /**
     * Gets the total number of shots fired by the player in the game.
     *
     * @return the number of shots fired (in the game)
     */
    int getShotsFired();

    /**
     * Gets the total number of hits recorded in the game.
     *
     * @return Returns the number of hits recorded (in the game)
     */
    int getHitCount();

    /**
     * Gets the list of ships placed on the ocean.
     *
     * @return Returns the list of ten ships in the ocean
     */
    List<Ship> getShipList();

    /**
     * Sets the array of ships on the board.
     *
     * @param ships a 10x10 array of {@link Ship} objects representing the new state of the ocean
     */
    void setShips(Ship[][] ships);

    /**
     * Sets the total number of shots that have been fired in the game.
     *
     * @param shotsFired the new number of shots fired
     */
    void setShotsFired(int shotsFired);

    /**
     * Sets the total number of hits recorded in the game.
     *
     * @param hitCount the new number of hits recorded
     */
    void setHitCount(int hitCount);

    /**
     * Sets the list of ships in the ocean.
     *
     * @param shipList a list of {@link Ship} objects representing all the ships in the ocean
     */
    void setShipList(List<Ship> shipList);

    /**
     * Sets the array indicating whether each point on the board has been shot.
     *
     * @param shootPoint a 10x10 boolean array where true indicates a shot has been fired at the corresponding location
     */
    void setShootPoint(boolean[][] shootPoint);
}
