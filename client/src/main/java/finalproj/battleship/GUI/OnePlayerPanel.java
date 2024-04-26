package finalproj.battleship.GUI;

import finalproj.battleship.GUI.components.OnePlayerGridBoard;
import finalproj.battleship.GUI.style.ButtonStyle;
import finalproj.battleship.GUI.style.ComponentSize;
import finalproj.battleship.controller.GUIController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents the single-player game panel in a Battleship game.
 * This panel contains the game grid for the human player, and it provides interaction
 * with the game through a GUIController. It also displays game information and help instructions.
 *
 * The panel is divided into sections for the game grid, a message display area, and buttons for additional actions like 'Quit' and 'Help'.
 */
public class OnePlayerPanel extends JPanel {

    private final OnePlayerGridBoard player1;
    private static final ComponentSize playAreaSize = ComponentSize.GAME_PANEL;
    private static final ComponentSize windowSize = ComponentSize.WINDOW;
    private final JTextArea messageArea;

    private final JScrollPane scrollPane;
    private final GUIController guiController;

    /**
     * Constructs a new OnePlayerPanel with the specified GUIController.
     * Initializes the game environment, sets up the GUI components and configures action listeners.
     *
     * @param guiController The GUIController that handles actions within this panel.
     */
    public OnePlayerPanel(GUIController guiController) {
        super();
        this.guiController = guiController;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10,40,20,10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Set up for helper information
        JTextArea textArea = new JTextArea(10, 25);
        textArea.setText("---------- WELCOME TO BATTLESHIP----------\n\n" +
                "This is a Computer vs. Human version of game Battleship.\n" +
                "Here's basic rules for this game!\n\n" +
                ">>The computer places the ten ships on the 10 x 10 board.\n" +
                "     1 aircraft carrier(cover 5 units), \n" +
                "     2 battleship(cover 4 units), \n" +
                "     2 destroyer(cover 3 units), \n" +
                "     2 submarine(cover 3 unit), \n" +
                "     4 patrol boats(cover 2 unit)\n\n" +
                ">>The human player tries to hit the ships, by indicating a specific row and column number (r,c). \n" +
                ">>The computer responds with one bit of information saying, “hit” or “miss”.\n\n" +
                ">>When a ship is hit and sinks, the program prints out a message “You just sank a ship - (type).\n" +
                ">>It takes four hits (in four different places) to sink a battleship, three to sink a cruiser, two for a destroyer, and one for a submarine.\n\n" +

                ">>When all ship are sunk, the game is over.\n" +
                ">>The score will be the number of shots the human player takes in total.");
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(550, 450));
        scrollPane.setMaximumSize(new Dimension(550, 450));

        // Set up for quit button
        gbc.anchor = GridBagConstraints.LINE_START;
        JButton backButton = new JButton("Quit");
        backButton.setPreferredSize(new Dimension(100, 40));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiController.toWelcomePanel();
            }
        });
        add(backButton, gbc);

        // Set up for helper button
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.LINE_END;
        JButton helpButton = new JButton("Help");
        helpButton.setPreferredSize(new Dimension(100, 40));
        add(helpButton, gbc);
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OnePlayerPanel.this.displayHelpMessage();
            }
        });

        // Set up for playing board
        gbc.gridx = 0;
        gbc.gridy++;
        player1 = new OnePlayerGridBoard(ButtonStyle.PLAYER_PLAYING1, "Your", guiController);
        add(player1, gbc);

        //Set up for message area
        gbc.gridx++;
        messageArea = new JTextArea();
        messageArea.setEditable(false);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(messageArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(250, 450));
        scrollPane.setMaximumSize(new Dimension(250, 450));// Adjust size as needed

        add(scrollPane, gbc);
        setBackground(Color.white);
    }

    /**
     * Adds a system message to the message area.
     * Each message added will appear on a new line.
     *
     * @param string The message to be added.
     */
    public void addSystemMessage(String string) {
        messageArea.setText(messageArea.getText() + string + "\n");
    }

    /**
     * Displays a help message in a dialog box using the main scroll pane of the panel.
     * The dialog provides game instructions and information to the user.
     */
    public void displayHelpMessage() {
        JOptionPane.showMessageDialog(OnePlayerPanel.this, scrollPane, "Help Information", JOptionPane.INFORMATION_MESSAGE);
    }
}
