package finalproj.battleship.model;
import finalproj.battleship.model.ships.ShipType;

public interface IGameController {

    /**
     * Check if the given location contains a ”real” ship which is still afloat
     * Return true if yes
     *
     * @param row for the given location
     * @param col for the given location
     * @return Return true if exists a "real" ship, otherwise false
     */
    boolean shootAt(int row, int col);

    /**
     * Check if the ship at the given location is sunk
     *
     * @param row
     * @param col
     * @return true is the ship is sunk
     */
    boolean isSunk(int row, int col);

    /**
     * Get the shiptype of the ship at given location
     * @param row
     * @param col
     * @return shiptype of the ship
     */
    ShipType getShipType(int row, int col);

    /**
     * Check if all ships are sunk
     * @return true if all ships aresunk, otherwise false
     */
    boolean isGameOver();

    /**
     * Get a conclusion for the game shots.
     * @return the conclusion of shots fired
     */
    String getConclusion();
}
