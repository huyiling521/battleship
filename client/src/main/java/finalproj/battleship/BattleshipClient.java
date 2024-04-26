package finalproj.battleship;

import finalproj.battleship.GUI.BattleshipFrame;
import finalproj.battleship.controller.GUIController;

public class BattleshipClient {
    public static void main(String[] args) {
        try {
            GUIController guiController = new GUIController();
            BattleshipFrame first = new BattleshipFrame(guiController);
            first.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}