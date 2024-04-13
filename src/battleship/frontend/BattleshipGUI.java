package battleship.frontend;

import java.lang.reflect.InvocationTargetException;

public class BattleshipGUI {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        BattleshipFrame first =new BattleshipFrame();
        first.setVisible(true);
    }
}
