package battleship.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePanel extends JPanel {

    public WelcomePanel() {
        super(new GridLayout(4, 1, 10, 10));

        // Create buttons
        JButton singlePlayerButton = new JButton("Single Player");
        JButton twoPlayersButton = new JButton("Two Players");
        JButton playOnlineButton = new JButton("Play Online");
        JButton exitButton = new JButton("Exit");

        // Add action listeners to buttons
        singlePlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Placeholder for starting a single player game
                System.out.println("Starting Single Player Game...");
            }
        });

        twoPlayersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Placeholder for starting a two player game
                System.out.println("Starting Two Player Game...");
            }
        });

        playOnlineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Placeholder for starting an online game
                System.out.println("Connecting to Online Game...");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exit the application
                System.exit(0);
            }
        });

        // Create a panel with a grid layout to hold buttons
        setBackground(Color.WHITE); // Set background color of the panel to white
        add(singlePlayerButton);
        add(twoPlayersButton);
        add(playOnlineButton);
        add(exitButton);
    }
}
