package battleship.client.model;

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
}
