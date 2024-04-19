package battleship.client.GUI;

import battleship.client.controller.GUIController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePanel extends JPanel{

    private JLabel messageLabel;
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

        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        buttonPanel.setBackground(Color.white);
        buttonPanel.setPreferredSize(new Dimension(200, 200));
        buttonPanel.setMaximumSize(buttonPanel.getPreferredSize());
        // Create buttons
        JButton singlePlayerButton = new JButton("Single Player");
        JButton twoPlayersButton = new JButton("Two Players");
        JButton playOnlineButton = new JButton("Play Online");
        JButton exitButton = new JButton("Exit");

        // Add action listeners to buttons
        singlePlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiController.toPlayingPanel();
            }
        });

        twoPlayersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiController.toSettingPanel();
            }
        });

        playOnlineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiController.toSettingPanel();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exit the application
                System.exit(0);
            }
        });

        messageLabel = new JLabel("");
        messageLabel.setForeground(Color.red);
        // Create a panel with a grid layout to hold buttons
        // Set background color of the panel to white
        buttonPanel.add(singlePlayerButton);
        buttonPanel.add(twoPlayersButton);
        buttonPanel.add(playOnlineButton);
        buttonPanel.add(exitButton);
        buttonPanel.add(messageLabel);

        setBackground(Color.WHITE);
        gbc.gridy++;
        add(buttonPanel, gbc);
    }

    public void setMessageLabel(String message) {
        this.messageLabel.setText(message);
    }
}
