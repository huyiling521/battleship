package battleship.client.GUI.components;

import battleship.client.controller.GUIController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public abstract class GridButton extends JButton implements ActionListener {
    private Color originalColor;
    private Color pressedColor;  // Color when button is pressed
    private Color hoverColor;     // Color when mouse hovers
    private Color disabledColor; // Color when button is disabled
    private boolean isButtonPressed = false;
    private boolean isClicked = false;

    public GridButton(int row, int col, GUIController guiController) {
        super();
        this.addActionListener(this);
        this.setActionCommand(row + "," + col);
    }

    protected void initStyle() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Check if the button hasn't been clicked
                if (!isButtonPressed && !isClicked) {
                    setBackground(hoverColor);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Only reset color if not clicked
                if (!isClicked) {
                    setBackground(originalColor);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // Check if the button hasn't been clicked
                if (!isClicked) {
                    isButtonPressed = true;
                    setBackground(pressedColor);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Check if the button hasn't been clicked set to hover color
                isButtonPressed = false;
                if (!isClicked && contains(e.getPoint())) {
                    setBackground(hoverColor);
                }
            }
        });

        this.setBackground(originalColor);
        this.setFocusPainted(true);
        this.setContentAreaFilled(true);
        this.setBorderPainted(true);
        this.setOpaque(true);
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setBorder(BorderFactory.createLineBorder(Color.white, 1, true));
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void clearClick() {
        setClicked(false);
        this.setEnabled(true);
        setBackground(getOriginalColor());// Disable the button to prevent further clicks
    }

    public void setButtonPressed(boolean buttonPressed) {
        isButtonPressed = buttonPressed;
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

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public void setOriginalColor(Color originalColor) {
        this.originalColor = originalColor;
    }

    public void setPressedColor(Color pressedColor) {
        this.pressedColor = pressedColor;
    }

    public void setHoverColor(Color hoverColor) {
        this.hoverColor = hoverColor;
    }

    public void setDisabledColor(Color disabledColor) {
        this.disabledColor = disabledColor;
    }

}
