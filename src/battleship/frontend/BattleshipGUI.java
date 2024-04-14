package battleship.frontend;

import battleship.frontend.BattleshipFrame;

import java.lang.reflect.InvocationTargetException;

public class BattleshipGUI {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        BattleshipFrame first =new BattleshipFrame();
        first.setVisible(true);
    }
}
