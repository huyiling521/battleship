package battleship.client.GUI.style;

public enum ComponentSize {
    WINDOW(1200, 800),
    WELCOME_PANEL(400, 400),
    GAME_PANEL(800, 800),
//    GRID_BOARD(440, 600),
    CHAT_PANEL(770, 180),
    GRID_BUTTON(35);

    private int width;
    private int height;
    ComponentSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    ComponentSize(int length) {
        this.width = length;
        this.height = length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
