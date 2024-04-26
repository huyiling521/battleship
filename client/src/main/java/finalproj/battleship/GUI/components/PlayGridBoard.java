package finalproj.battleship.GUI.components;

import finalproj.battleship.GUI.style.ButtonStyle;
import finalproj.battleship.controller.GUIController;

public class PlayGridBoard extends GridBoard<PlayingButton> {

    public PlayGridBoard(ButtonStyle buttonStyle, String name, GUIController guiController) {
        super(buttonStyle, name, guiController);
    }

    public void opponentAttackedButton(int row, int col, boolean isHit) {
        getButton(row, col).opponentAttacked(isHit);
    }

    @Override
    protected PlayingButton createPlaceholderButton(int row, int col, ButtonStyle buttonStyle, GUIController guiController) {
        return new PlayingButton(row, col, buttonStyle, guiController);
    }
}
