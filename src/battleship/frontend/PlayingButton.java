package battleship.frontend;

import battleship.frontend.style.ButtonStyle;
import battleship.server.controler.GUIController;

import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;

public class PlayingButton extends GridButton{

    private final ButtonStyle buttonStyle;
    private final GUIController guiController;

    public PlayingButton(int row, int col, ButtonStyle buttonStyle, GUIController guiController) {
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
        if (!isClicked() && isEnabled()) { // Ensure action is performed only once
            setClicked(true);
            this.setEnabled(false); // Disable the button to prevent further clicks
            if (guiController.attack(this)) setBackground(buttonStyle.getHoverColor()); // Change color to indicate disabled state;
            else setBackground(buttonStyle.getDisabledColor()); // Change color to indicate disabled state
        }
    }

    public void getAttacked(boolean isHit) {
        setClicked(true);
        this.setEnabled(false);
        if (isHit) setBackground(buttonStyle.getHoverColor()); // Change color to indicate disabled state;
        else setBackground(buttonStyle.getDisabledColor());
    }
}
