package battleship.frontend;

import battleship.frontend.style.ButtonStyle;

import java.awt.event.ActionEvent;

public class PlayingButton extends GridButton{

    private final ButtonStyle buttonStyle;

    public PlayingButton(int row, int col, ButtonStyle buttonStyle) {
        super(row, col);
        this.buttonStyle = buttonStyle;
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
            this.setEnabled(false); // Disable the button to prevent further clicks
            System.out.println(e.getActionCommand() + "playing");
        }
    }
}
