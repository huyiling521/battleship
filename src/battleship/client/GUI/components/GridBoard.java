package battleship.client.GUI.components;

import battleship.client.GUI.style.ButtonStyle;
import battleship.client.GUI.style.ComponentSize;
import battleship.client.controller.GUIController;

import javax.swing.*;
import java.awt.*;

public abstract class GridBoard<T extends GridButton> extends JPanel {

    private static final ComponentSize gridButtonSize = ComponentSize.GRID_BUTTON;
    // private static final ComponentSize gridBoardSize = ComponentSize.GRID_BOARD;
    private static final int GRID_SIZE = 11; // Grid size constant

    private T[][] buttons = (T[][]) new GridButton[GRID_SIZE][GRID_SIZE]; // Create a grid of JButtons
    private T prevButton = null;
//    private String position;

    public GridBoard(ButtonStyle buttonStyle, String name, GUIController guiController) {
        super(new GridBagLayout());
//        position = "";
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(10, 0, 10, 0);
        // Populate the panel with buttons
        JButton nameBanner = new JButton(name + "'s Board");
        nameBanner.setOpaque(true);
        nameBanner.setBackground(buttonStyle.getHoverColor());
        nameBanner.setContentAreaFilled(true);
        nameBanner.setBorderPainted(true);
        nameBanner.setBorder(BorderFactory.createLineBorder(Color.white, 1, true));
        nameBanner.setHorizontalTextPosition(SwingConstants.CENTER);
        nameBanner.setVerticalTextPosition(SwingConstants.CENTER);
        nameBanner.setPreferredSize(
                new Dimension((GRID_SIZE - 1) * gridButtonSize.getWidth(), gridButtonSize.getHeight()));
        nameBanner.setMinimumSize(nameBanner.getPreferredSize()); // Set minimum size
        nameBanner.setMaximumSize(nameBanner.getPreferredSize());

        add(nameBanner, gbc);

        JPanel panel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE, 0, 0));
        panel.setBackground(null);
        // Set the size based on button count and size
        panel.setPreferredSize(
                new Dimension(GRID_SIZE * gridButtonSize.getWidth(), GRID_SIZE * gridButtonSize.getWidth()));
        panel.setMaximumSize(panel.getPreferredSize());
        // panel.setMinimumSize(panel.getPreferredSize());

        String[] rows = { "", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
        String[] cols = { "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (row == 0) {
                    JLabel colLabel = new JLabel(cols[col], SwingConstants.CENTER);
                    colLabel.setOpaque(true);
                    colLabel.setBackground(Color.white);
                    panel.add(colLabel);
                } else if (col == 0) {
                    JLabel rowLabel = new JLabel(rows[row], SwingConstants.CENTER);
                    rowLabel.setOpaque(true);
                    rowLabel.setBackground(Color.white);
                    panel.add(rowLabel);
                } else {
                    buttons[row][col] = createPlaceholderButton(row, col, buttonStyle,guiController);
                    panel.add(buttons[row][col]);
                }
            }
        }
        add(panel, gbc);
        setSize(GRID_SIZE * gridButtonSize.getWidth(), GRID_SIZE * gridButtonSize.getWidth() + 100);
        setBackground(Color.white);
    }

    public T[][] getButtons() {
        return buttons;
    }

    public T getButton(int row, int col) {
        if (row < 1 || row > 10 || col < 1 || col > 10) throw new IllegalArgumentException("The position is invalid.");
        return buttons[row][col];
    }

    protected abstract T createPlaceholderButton(int row, int col, ButtonStyle buttonStyle, GUIController guiController);
}
