package finalproj.battleship.GUI.components;

import finalproj.battleship.GUI.style.ButtonStyle;
import finalproj.battleship.controller.GUIController;

import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;

public class OnePlayerButton extends GridButton{

    private final ButtonStyle buttonStyle;
    private final GUIController guiController;

    public OnePlayerButton(int row, int col, ButtonStyle buttonStyle, GUIController guiController) {
        super(row, col, guiController);
        this.buttonStyle = buttonStyle;
        this.guiController = guiController;
        setHoverColor(buttonStyle.getHoverColor());
        setOriginalColor(buttonStyle.getOriginalColor());
        setDisabledColor(buttonStyle.getDisabledColor());
        setPressedColor(buttonStyle.getPressedColor());
        initStyle();
        if (buttonStyle.equals(ButtonStyle.PLAYER_PLAYING2)) {
            for (MouseListener m : this.getMouseListeners()) {
                this.removeMouseListener(m);
            }
            this.setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (!isClicked() && isEnabled()) {
                setClicked(true);
                this.setEnabled(false);
                if (guiController.attackComputer(this))
                    setBackground(buttonStyle.getHoverColor());
                else setBackground(buttonStyle.getDisabledColor());
            }
        } catch (Exception exception) {
            guiController.throwWholeErrorMessage(exception.getMessage());
        }
    }
}
