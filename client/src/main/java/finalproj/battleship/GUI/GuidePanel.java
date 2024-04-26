package finalproj.battleship.GUI;

import finalproj.battleship.controller.GUIController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The GuidePanel class extends JPanel and provides a user interface
 * component that displays the rules and guidelines for playing the Battleship game.
 * It includes sections for general rules, gameplay mechanics, and a navigation button
 * to return to the welcome panel.
 */
public class GuidePanel extends JPanel {

    /**
     * Constructs a new GuidePanel with a reference to the GUIController
     * for handling navigation and interaction within the GUI.
     *
     * @param guiController the controller that manages GUI navigation and interactions
     */
    public GuidePanel(GUIController guiController) {
        super(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(20, 40, 20, 40);

        // Title label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel title = new JLabel("Guidance for the Battleship Game");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, gbc);  // Add title to the panel

        // Subtitle label for section heading
        gbc.gridy++;
        JLabel subtitle1 = new JLabel("General Rules");
        subtitle1.setFont(new Font("Arial", Font.BOLD, 18));
        add(subtitle1, gbc);

        // Text area for displaying game rules
        gbc.gridy++;
        JTextArea rulesText = new JTextArea();
        rulesText.setText("Detailed rules for playing Battleship, including single and multiplayer versions, " +
                "ship placement rules, and gameplay mechanics.");
        rulesText.setFont(new Font("Arial", Font.PLAIN, 14));
        rulesText.setEditable(false);
        rulesText.setLineWrap(true);
        rulesText.setWrapStyleWord(true);
        rulesText.setPreferredSize(new Dimension(450, 330));
        rulesText.setMaximumSize(rulesText.getPreferredSize());
        rulesText.setBackground(Color.WHITE);
        add(rulesText, gbc);

        // Back button for navigation
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiController.toWelcomePanel();
            }
        });

        // Panel for holding the back button
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.setBackground(Color.white);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        setBackground(Color.WHITE);
    }
}
