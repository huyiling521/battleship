package finalproj.battleship.GUI.style;

import java.awt.Color;

/**
 * The ButtonStyle enum defines various styles for buttons used in the Battleship game GUI.
 * Each style is associated with specific colors for different states of the button: original, pressed,
 * hovered, and disabled. This allows for a consistent and visually appealing user interface across
 * different parts of the game.
 */
public enum ButtonStyle {
    PLAYER_SETTING(new Color(240, 240, 240), new Color(220, 160, 160), Color.PINK, Color.lightGray),
    PLAYER_PLAYING1(new Color(240, 240, 240), new Color(220, 160, 160), Color.PINK, Color.lightGray),
    PLAYER_PLAYING2(new Color(240, 240, 240), new Color(130, 176, 200), new Color(173, 216, 230), Color.lightGray);

    private final Color originalColor; // Default color of the button
    private final Color pressedColor;  // Color when the button is pressed
    private final Color hoverColor;    // Color when the mouse hovers over the button
    private final Color disabledColor; // Color when the button is disabled

    /**
     * Constructs a style with specified colors for different button states.
     *
     * @param originalColor the default color of the button
     * @param pressedColor the color of the button when it is pressed
     * @param hoverColor the color of the button when the mouse hovers over it
     * @param disabledColor the color of the button when it is disabled
     */
    ButtonStyle(Color originalColor, Color pressedColor, Color hoverColor, Color disabledColor) {
        this.originalColor = originalColor;
        this.pressedColor = pressedColor;
        this.hoverColor = hoverColor;
        this.disabledColor = disabledColor;
    }

    /**
     * Returns the default color of the button.
     * @return the default color
     */
    public Color getOriginalColor() {
        return originalColor;
    }

    /**
     * Returns the color of the button when it is pressed.
     * @return the pressed color
     */
    public Color getPressedColor() {
        return pressedColor;
    }

    /**
     * Returns the color of the button when the mouse hovers over it.
     * @return the hover color
     */
    public Color getHoverColor() {
        return hoverColor;
    }

    /**
     * Returns the color of the button when it is disabled.
     * @return the disabled color
     */
    public Color getDisabledColor() {
        return disabledColor;
    }
}
