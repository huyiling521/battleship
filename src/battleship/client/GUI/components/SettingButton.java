package battleship.client.GUI.components;

import battleship.client.GUI.style.ButtonStyle;
import battleship.client.controller.GUIController;

import java.awt.event.ActionEvent;

public class SettingButton extends GridButton{

    private final ButtonStyle buttonStyle;
    private final GUIController guiController;
    public SettingButton(int row, int col, ButtonStyle buttonStyle, GUIController guiController) {
        super(row, col, guiController);
        this.buttonStyle = buttonStyle;
        this.guiController = guiController;
        setOriginalColor(buttonStyle.getOriginalColor());
        setHoverColor(buttonStyle.getHoverColor());
        setDisabledColor(buttonStyle.getDisabledColor());
        setPressedColor(buttonStyle.getPressedColor());
        initStyle();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isClicked()) { // Ensure action is performed only once
            setClicked(true);
            setBackground(buttonStyle.getDisabledColor()); // Change color to indicate disabled state
            guiController.getClickedSettingButton(this);
        }
    }

    public void confirmClick() {
        setClicked(true);
        this.setEnabled(false);
        setBackground(buttonStyle.getDisabledColor()); // Disable the button to prevent further clicks
    }

    public void clearClick() {
        setClicked(false);
        this.setEnabled(true);
        setBackground(buttonStyle.getOriginalColor());// Disable the button to prevent further clicks
    }
}
