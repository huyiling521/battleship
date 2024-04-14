package battleship.frontend.style;

import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

public enum ButtonStyle {
    PLAYER_SETTING1(new Color(240, 240, 240), Color.PINK, Color.RED, Color.GRAY),
    PLAYER_SETTING2(new Color(240, 240, 240), Color.CYAN, Color.BLUE, Color.GRAY),
    PLAYER_PLAYING1(new Color(240, 240, 240), Color.PINK, new Color(200, 100, 100), Color.lightGray),
    PLAYER_PLAYING2(new Color(240, 240, 240), new Color(173, 216, 230), new Color(100, 146, 170), Color.lightGray);

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
