package battleship.client.GUI.components;

import battleship.client.GUI.style.ButtonStyle;
import battleship.client.controller.GUIController;

public class SettingGridBoard extends GridBoard<SettingButton> {

    private SettingButton prevSettingButton;

    public SettingGridBoard(ButtonStyle buttonStyle, String name, GUIController guiController) {
        super(buttonStyle, name, guiController);
        this.prevSettingButton = null;
    }

    public void setArrangedButtons(int row, int col, boolean isHorizontal, int length) {
        if (isHorizontal) {
            for (int i = col; i < col + length; i++)
                getButton(row, i).confirmClick();
        } else {
            for (int i = row; i < row + length; i++)
                getButton(i, col).confirmClick();
        }
    }


    @Override
    protected SettingButton createPlaceholderButton(int row, int col, ButtonStyle buttonStyle, GUIController guiController) {
        return new SettingButton(row, col, buttonStyle, guiController);
    }

    public void checkPrevButtonStatus(SettingButton settingButton) {
        if (prevSettingButton != null && prevSettingButton.isEnabled()) prevSettingButton.clearClick();
        prevSettingButton = settingButton;
    }

    public void setPrevSettingButton(int row, int col) {
        this.prevSettingButton = getButton(row, col);
    }

    public SettingButton getPrevSettingButton() {
        return prevSettingButton;
    }
}
