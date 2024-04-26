package controller;


import model.ships.ShipType;

public interface IGameController {

    boolean shootAt(int row, int col);

    boolean isSunk(int row, int col);

    ShipType getShipType(int row, int col);

    boolean isGameOver();

    String getConclusion();
}
