package finalproj.battleship.GUI.components;

import finalproj.battleship.GUI.style.ButtonStyle;
import finalproj.battleship.controller.GUIController;

public class OnePlayerGridBoard extends GridBoard<OnePlayerButton> {

    public OnePlayerGridBoard(ButtonStyle buttonStyle, String name, GUIController guiController) {
        super(buttonStyle, name, guiController);
    }

    @Override
    protected OnePlayerButton createPlaceholderButton(int row, int col, ButtonStyle buttonStyle, GUIController guiController) {
        return new OnePlayerButton(row, col, buttonStyle, guiController);
    }
}
