package finalproj.battleship;

import finalproj.battleship.GUI.BattleshipFrame;
import finalproj.battleship.controller.GUIController;

/**
 * The BattleshipClient class contains the main method to start the Battleship game application.
 * It initializes the game's GUI components and makes the main application window visible to the user.
 *
 * This class serves as the starting point for running the Battleship game, handling the initialization
 * of the main frame and its components through the GUIController.
 */
public class BattleshipClient {

    /**
     * Main method to launch the Battleship game application.
     * It creates an instance of  GUIController and  BattleshipFrame,
     * setting up the user interface and making it visible to the user.
     */
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
