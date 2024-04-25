package battleship.server.controller;

import battleship.server.model.ships.ShipType;

public interface IGameController {

    boolean attack(int row, int col);

    boolean isSunk(int row, int col);

    ShipType getShipType(int row, int col);

    boolean isGameOver();

    String getConclusion();
}
