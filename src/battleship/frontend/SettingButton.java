package battleship.frontend;

import battleship.frontend.style.ButtonStyle;
import battleship.server.controler.GUIController;

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
//            this.setEnabled(false); // Disable the button to prevent further clicks
            guiController.getClickedSettingButton(this);
        }
    }

    @Override
    public void confirmClick() {
        super.confirmClick();
        setBackground(buttonStyle.getDisabledColor()); // Change color to indicate disabled state
    }

    @Override
    public void clearClick() {
        super.clearClick();
        setBackground(buttonStyle.getOriginalColor()); // Change color to indicate disabled state
    }
}
