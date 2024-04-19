package battleship.server.controller;

import battleship.server.model.TwoPlayerBoard;

public class GameController {
    private TwoPlayerBoard twoPlayerBoard;
    public GameController(TwoPlayerBoard twoPlayerBoard) {
        this.twoPlayerBoard = twoPlayerBoard;
    }

    public int placeOneShip(int row, int col, boolean isHorizontal, String shipType) {
        return twoPlayerBoard.placeOneShip(row, col, isHorizontal, shipType);
    }

    public boolean receiveOpponentAttack(int row, int col){
        boolean isHit = twoPlayerBoard.shootAt(row, col);
        return null;
    }

    public boolean attack(int row, int col){
        return true;
    }


}
