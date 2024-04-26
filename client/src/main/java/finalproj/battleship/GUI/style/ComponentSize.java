package finalproj.battleship.GUI.style;

/**
 * The ComponentSize enum defines standard sizes for various GUI components in the Battleship game.
 * Each enum constant represents a different component, specifying its width and height in pixels. This
 * ensures a consistent layout and appearance across the game interface.
 */
public enum ComponentSize {
    WINDOW(1200, 700),
    WELCOME_PANEL(400, 400),
    GAME_PANEL(800, 800),
    CHAT_PANEL(770, 180),
    GRID_BUTTON(35); // Assuming GRID_BUTTON uses the same value for both width and height

    private int width;
    private int height;

    /**
     * Constructs a component size with specified width and height.
     *
     * @param width the width of the component in pixels
     * @param height the height of the component in pixels
     */
    ComponentSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Constructs a component size where width and height are the same.
     *
     * @param length the width and height of the component in pixels
     */
    ComponentSize(int length) {
        this.width = length;
        this.height = length;
    }

    /**
     * Returns the width of the component.
     * @return the width in pixels
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of the component.
     * @return the height in pixels
     */
    public int getHeight() {
        return height;
    }
}
