package battleship.frontend;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class GridButtonBoard extends JPanel{
    private static final int GRID_SIZE = 11; // Grid size constant
    private static final int BUTTON_SIZE = 50; // Button size constant
    private JButton[][] buttons = new JButton[GRID_SIZE][GRID_SIZE]; // Create a grid of JButtons

    public GridButtonBoard(Class<?> classof, ButtonStyle buttonStyle) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        setSize(GRID_SIZE * BUTTON_SIZE, GRID_SIZE * BUTTON_SIZE); // Set the size based on button count and size
        JPanel panel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE, 0, 0)); // No gaps between buttons
        panel.setPreferredSize(new Dimension(GRID_SIZE * BUTTON_SIZE, GRID_SIZE * BUTTON_SIZE)); // Fixed size
        // Populate the panel with buttons

        String[] rows = {"", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        String[] cols = {"", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (row == 0) {
                    panel.add(new JLabel(cols[col], SwingConstants.CENTER));
                } else if (col == 0) {
                    panel.add(new JLabel(rows[row], SwingConstants.CENTER));
                } else {
                    Constructor<?> constructor = classof.getConstructor(int.class, int.class, ButtonStyle.class);
                    buttons[row][col] = (JButton) constructor.newInstance(row, col, buttonStyle);
                    panel.add(buttons[row][col]);
                }
            }
        }
        add(panel);
    }
}
