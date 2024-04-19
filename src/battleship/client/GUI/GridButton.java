package battleship.client.GUI;

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
                if (!isButtonPressed && !isClicked) { // Check if the button hasn't been clicked
                    setBackground(hoverColor);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!isClicked) { // Only reset color if not clicked
                    setBackground(originalColor);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (!isClicked) { // Check if the button hasn't been clicked
                    isButtonPressed = true;
                    setBackground(pressedColor);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isButtonPressed = false;
                if (!isClicked && contains(e.getPoint())) {
                    setBackground(hoverColor);
                }
            }
        });

        // Adjust the visual settings to preserve default animation
        this.setBackground(originalColor);
        this.setFocusPainted(true);
        this.setContentAreaFilled(true);
        this.setBorderPainted(true);
        this.setOpaque(true);
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setBorder(BorderFactory.createLineBorder(Color.white, 1, true));
//        this.setBorder(BorderFactory.createLineBorder(Color.white));
    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (!isClicked) { // Ensure action is performed only once
//            isClicked = true;
//            setBackground(disabledColor); // Change color to indicate disabled state
//            this.setEnabled(false); // Disable the button to prevent further clicks
//            System.out.println(e.getActionCommand());
//        }
//    }
    public void confirmClick() {
        setClicked(true);
        this.setEnabled(false); // Disable the button to prevent further clicks
    }

    public void clearClick() {
        setClicked(false);
        this.setEnabled(true); // Disable the button to prevent further clicks
    }

    public void setButtonHoverColor() {
        this.setBackground(hoverColor);
    }

    public void setButtonPressedColor() {
        this.setBackground(pressedColor);
    }

    public boolean isButtonPressed() {
        return isButtonPressed;
    }

    public boolean isClicked() {
        return isClicked;
    }


    public void setButtonPressed(boolean buttonPressed) {
        isButtonPressed = buttonPressed;
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
