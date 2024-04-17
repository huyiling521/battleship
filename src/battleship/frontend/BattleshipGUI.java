package battleship.frontend;

import battleship.frontend.BattleshipFrame;
import battleship.server.controler.GUIController;
import battleship.server.controler.GameController;
import battleship.server.model.TwoPlayerBoard;

import java.lang.reflect.InvocationTargetException;

public class BattleshipGUI {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        GUIController guiController = new GUIController();
        BattleshipFrame first = new BattleshipFrame(guiController);
        first.setVisible(true);
    }
}
