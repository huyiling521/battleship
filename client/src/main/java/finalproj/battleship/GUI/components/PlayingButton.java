package finalproj.battleship.GUI.components;

import finalproj.battleship.GUI.style.ButtonStyle;
import finalproj.battleship.controller.GUIController;

import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;

public class PlayingButton extends GridButton{

    private final GUIController guiController;

    public PlayingButton(int row, int col, ButtonStyle buttonStyle, GUIController guiController) {
        super(row, col, guiController);
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
                if (guiController.attack(this))
                    setBackground(getHoverColor());
                else setBackground(getDisabledColor());
            }
        } catch (Exception exception) {
            clearClick();
            guiController.throwWholeErrorMessage(exception.getMessage());
        }
    }

    public void opponentAttacked(boolean isHit) {
        setClicked(true);
        if (isHit) setBackground(getHoverColor()); // Change color to indicate disabled state;
        else setBackground(getDisabledColor());
    }
}
