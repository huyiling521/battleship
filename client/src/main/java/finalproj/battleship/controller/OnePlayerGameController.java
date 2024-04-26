package finalproj.battleship.controller;

import finalproj.battleship.model.IGameController;
import finalproj.battleship.model.OnePlayerBoard;
import finalproj.battleship.model.ships.ShipType;

public class OnePlayerGameController implements IGameController {
    private OnePlayerBoard onePlayerBoard;

    public OnePlayerGameController() {
        this.onePlayerBoard = new OnePlayerBoard();
    }
    public boolean shootAt(int row, int col) {
        return onePlayerBoard.shootAt(row, col);
    }

    public boolean isSunk(int row, int col) {
        return onePlayerBoard.getShip(row, col).isSunk();
    }

    public ShipType getShipType(int row, int col) {
        return onePlayerBoard.getShip(row, col).getShipType();
    }

    public boolean isGameOver() {
        return onePlayerBoard.isGameOver();
    }

    public String getConclusion() {
        return "You Win! You have shot " + onePlayerBoard.getShotsFired() + " times.";
    }
}
