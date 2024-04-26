package battleship.server.controller;

import battleship.model.ships.ShipType;
import battleship.server.model.TwoPlayerBoard;

public class TwoPlayerGameController implements IGameController{
    private TwoPlayerBoard twoPlayerBoard;

    public TwoPlayerGameController(TwoPlayerBoard twoPlayerBoard) {
        this.twoPlayerBoard = twoPlayerBoard;
    }

    public int placeOneShip(int row, int col, boolean isHorizontal, String shipType) {
        return twoPlayerBoard.placeOneShip(row, col, isHorizontal, shipType);
    }

    public boolean shootAt(int row, int col) {
        return twoPlayerBoard.shootAt(row, col);
    }
    public boolean isSunk(int row, int col) {
        return twoPlayerBoard.getShip(row, col).isSunk();
    }
    public boolean isGameOver() {
        return twoPlayerBoard.isGameOver();
    }

    public String getConclusion() {
        return "You have shot " + twoPlayerBoard.getShotsFired() + " times.";
    }
    public ShipType getShipType(int row, int col) {
        return twoPlayerBoard.getShip(row, col).getShipType();
    }


}
