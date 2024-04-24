package battleship.client.GUI;

import battleship.client.controller.GUIController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePanel extends JPanel{

    public WelcomePanel(GUIController guiController) {
        super(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(40,0,40,0);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JLabel title = new JLabel("Welcome to Battleship Game!");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, gbc);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        buttonPanel.setBackground(Color.white);
        buttonPanel.setPreferredSize(new Dimension(200, 200));
        buttonPanel.setMaximumSize(buttonPanel.getPreferredSize());
        // Create buttons
        JButton singlePlayerButton = new JButton("Single Player");
        JButton twoPlayersButton = new JButton("Two Players");
        JButton guideButton = new JButton("Guide");
        JButton exitButton = new JButton("Exit");

        // Add action listeners to buttons
        singlePlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiController.toOnePlayerPanel();
            }
        });

        twoPlayersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiController.toSettingPanel();
            }
        });

        guideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiController.toGuidePanel();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exit the application
                System.exit(0);
            }
        });

        buttonPanel.add(singlePlayerButton);
        buttonPanel.add(twoPlayersButton);
        buttonPanel.add(guideButton);
        buttonPanel.add(exitButton);

        setBackground(Color.WHITE);
        gbc.gridy++;
        add(buttonPanel, gbc);
    }
}
