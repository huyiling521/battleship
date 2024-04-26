package finalproj.battleship.GUI;

import finalproj.battleship.controller.GUIController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The WelcomePanel class extends JPanel and serves as the initial screen
 * for the Battleship game application. It presents the user with various options to navigate
 * through the game including starting a new single player or two player game, accessing the game guide,
 * or exiting the game.
 */
public class WelcomePanel extends JPanel {
    Image image;

    /**
     * Constructs a new WelcomePanel which sets up the GUI components and their layout within the panel.
     * It includes buttons that navigate to different parts of the game such as the single player game,
     * two player game settings, game guide, and an exit button to close the application.
     *
     * @param guiController the GUI controller to handle transitions between different panels based on user actions
     */
    public WelcomePanel(GUIController guiController) {
        // Use GridBagLayout for flexible layout management
        super(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(5, 0, 5, 0);

        // Setup the initial grid position for the title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        // Create and add the title label
        JLabel title = new JLabel("Welcome to Battleship Game!");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, gbc);

        gbc.gridx++;
        add(new Box.Filler(new Dimension(500,10), new Dimension(500,10), new Dimension(500,10)));

        // Create and add the credit label
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel credit = new JLabel("@author Yiling Hu | Haoteng Yang");
        credit.setFont(new Font("Arial", Font.BOLD, 12));
        credit.setForeground(Color.gray);
        credit.setHorizontalAlignment(SwingConstants.CENTER);
        credit.setPreferredSize(new Dimension(200, 20));
        credit.setMaximumSize(new Dimension(200, 20));
        add(credit, gbc);

        gbc.insets = new Insets(40, 0, 40, 0);
        // Create a panel to hold the buttons
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        buttonPanel.setBackground(Color.white);
        buttonPanel.setPreferredSize(new Dimension(200, 200));
        buttonPanel.setMaximumSize(buttonPanel.getPreferredSize());

        // Create the buttons
        JButton singlePlayerButton = new JButton("Single Player");
        JButton twoPlayersButton = new JButton("Two Player");
        JButton guideButton = new JButton("Guide");
        JButton exitButton = new JButton("Exit");

        // Add action listeners to buttons for navigating to different panels
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
                System.exit(0);
            }
        });

        // Add buttons to the panel
        buttonPanel.add(singlePlayerButton);
        buttonPanel.add(twoPlayersButton);
        buttonPanel.add(guideButton);
        buttonPanel.add(exitButton);

        gbc.gridy++;
        add(buttonPanel, gbc);

        ImageIcon icon = new ImageIcon("res/battleship-cover-img.png");
        image = icon.getImage();
        // Set the background color of the main panel to white
        setBackground(Color.WHITE);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
