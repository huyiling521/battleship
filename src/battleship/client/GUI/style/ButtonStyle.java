package battleship.client.GUI.style;

import java.awt.Color;

public enum ButtonStyle {
    PLAYER_SETTING(new Color(240, 240, 240), new Color(220, 160, 160), Color.PINK, Color.lightGray),
    PLAYER_PLAYING1(new Color(240, 240, 240), new Color(220, 160, 160), Color.PINK, Color.lightGray),
    PLAYER_PLAYING2(new Color(240, 240, 240), new Color(130, 176, 200), new Color(173, 216, 230), Color.lightGray);

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
