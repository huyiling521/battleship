package battleship.frontend;

import battleship.frontend.style.ButtonStyle;
import battleship.frontend.style.ComponentSize;
import battleship.server.controler.GUIController;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class GridButtonBoard extends JPanel{

    private static final ComponentSize gridButtonSize = ComponentSize.GRID_BUTTON;
//    private static final ComponentSize gridBoardSize = ComponentSize.GRID_BOARD;
    private static final int GRID_SIZE = 11; // Grid size constant

    private GridButton[][] buttons = new GridButton[GRID_SIZE][GRID_SIZE]; // Create a grid of JButtons
    private GridButton prevButton = null;
    private String position;

    public GridButtonBoard(Class<?> classof, ButtonStyle buttonStyle, String name, GUIController guiController) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        super(new GridBagLayout());
        position = "";
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
        nameBanner.setPreferredSize(new Dimension((GRID_SIZE - 1) * gridButtonSize.getWidth(), gridButtonSize.getHeight()));  // Set preferred size
        nameBanner.setMinimumSize(nameBanner.getPreferredSize());    // Set minimum size
        nameBanner.setMaximumSize(nameBanner.getPreferredSize());
        //nameBanner.setPreferredSize(GRID_SIZE * gridButtonSize.getWidth(), gridButtonSize.getWidth());
        add(nameBanner, gbc);

        JPanel panel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE, 0, 0));
        panel.setBackground(null);
        // Set the size based on button count and size
        panel.setPreferredSize(new Dimension(GRID_SIZE * gridButtonSize.getWidth(), GRID_SIZE * gridButtonSize.getWidth()));
        panel.setMaximumSize(panel.getPreferredSize());
//        panel.setMinimumSize(panel.getPreferredSize());

        String[] rows = {"", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        String[] cols = {"", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

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
                    Constructor<?> constructor = classof.getConstructor(int.class, int.class, ButtonStyle.class, GUIController.class);
                    buttons[row][col] = (GridButton) constructor.newInstance(row, col, buttonStyle, guiController);
                    panel.add(buttons[row][col]);
                }
            }
        }
        add(panel, gbc);
        setSize(GRID_SIZE * gridButtonSize.getWidth(), GRID_SIZE * gridButtonSize.getWidth() + 100);
        setBackground(Color.white);
    }

    public void setArrangedButtons(int row, int col, boolean isHorizontal, int length) {
        if (isHorizontal) {
            for (int i = col; i < col + length; i++) buttons[row][i].confirmClick();
          } else {
            for (int i = row; i < row + length; i++) buttons[i][col].confirmClick();
        }
    }

    public void setPrevButton(int row, int col) {
        this.prevButton = buttons[row][col];
    }
    public GridButton getPrevButton() {
        return prevButton;
    }

}
