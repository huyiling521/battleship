package battleship.client.GUI.components;

import battleship.client.GUI.style.ButtonStyle;
import battleship.client.controller.GUIController;

import java.awt.event.ActionEvent;

public class SettingButton extends GridButton{

    private final GUIController guiController;
    public SettingButton(int row, int col, ButtonStyle buttonStyle, GUIController guiController) {
        super(row, col, guiController);
        this.guiController = guiController;
        setOriginalColor(buttonStyle.getOriginalColor());
        setHoverColor(buttonStyle.getHoverColor());
        setDisabledColor(buttonStyle.getDisabledColor());
        setPressedColor(buttonStyle.getPressedColor());
        initStyle();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isClicked()) {
            setClicked(true);
            setBackground(getDisabledColor());
            guiController.getClickedSettingButton(this);
        }
    }

    public void confirmClick() {
        setClicked(true);
        this.setEnabled(false);
        setBackground(getDisabledColor());
    }
}
