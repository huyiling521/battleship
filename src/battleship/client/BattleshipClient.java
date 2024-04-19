package battleship.client;

import battleship.client.GUI.BattleshipFrame;
import battleship.client.controller.GUIController;

import java.lang.reflect.InvocationTargetException;

public class BattleshipClient {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        GUIController guiController = new GUIController();
        BattleshipFrame first = new BattleshipFrame(guiController);
        first.setVisible(true);
    }
}
