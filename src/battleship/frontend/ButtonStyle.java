package battleship.frontend;

import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

public enum ButtonStyle {
    PLAYER_SETTING1(Color.WHITE, Color.PINK, Color.RED, Color.GRAY),
    PLAYER_SETTING2(Color.WHITE, Color.CYAN, Color.BLUE, Color.GRAY),
    PLAYER_PLAYING1(Color.WHITE, Color.PINK, Color.RED, Color.GRAY),
    PLAYER_PLAYING2(Color.WHITE, Color.CYAN, Color.BLUE, Color.GRAY);

    private final Color originalColor;
    private final Color pressedColor;  // Color when button is pressed
    private final Color hoverColor;     // Color when mouse hovers
    private final Color disabledColor; // Color when button is disabled

    ButtonStyle(Color originalColor, Color pressedColor, Color hoverColor, Color disabledColor) {
        this.originalColor = originalColor;
        this.pressedColor = pressedColor;
        this.hoverColor = hoverColor;
        this.disabledColor = disabledColor;
    }

    public Color getOriginalColor() {
        return originalColor;
    }

    public Color getPressedColor() {
        return pressedColor;
    }

    public Color getHoverColor() {
        return hoverColor;
    }

    public Color getDisabledColor() {
        return disabledColor;
    }
}
