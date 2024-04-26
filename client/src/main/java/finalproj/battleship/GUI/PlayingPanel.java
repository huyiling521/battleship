package finalproj.battleship.GUI;

import finalproj.battleship.GUI.components.PlayGridBoard;
import finalproj.battleship.GUI.style.ButtonStyle;
import finalproj.battleship.GUI.style.ComponentSize;
import finalproj.battleship.controller.GUIController;


import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 /** The PlayingPanel class represents the main gameplay interface in the Battleship game.
  * It includes two game boards for each player and a chat system to communicate with the opponent.
  */
public class PlayingPanel extends JPanel {

    private final PlayGridBoard player1;
    private final PlayGridBoard player2;
    private static final ComponentSize playAreaSize = ComponentSize.GAME_PANEL;
    private static final ComponentSize windowSize = ComponentSize.WINDOW;
    private static final ComponentSize chatAreaSize = ComponentSize.CHAT_PANEL;
    private final JTextPane messageArea;
    private final JTextField textField;
    private final Style system;
    private final Style myText;
    private final Style otherText;
    private final GUIController guiController;

     /**
      * Constructs a PlayingPanel with all the necessary UI components for gameplay.
      * This includes two grid boards for each player, a chat area, and a quit button.
      *
      * @param guiController The GUI controller used for handling user actions and navigation.
      * @param name The name of the player using this panel.
      * @param opponentName The name of the opponent player.
      */
    public PlayingPanel(GUIController guiController, String name, String opponentName) {
        super();
        this.guiController = guiController;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0,40,0,40);
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Setup the quit button
        JButton backButton = new JButton("Quit");
        backButton.setPreferredSize(new Dimension(100,40));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiController.endGame();
                guiController.toWelcomePanel();
            }
        });
        add(backButton, gbc);

        // Initialize player game boards
        gbc.anchor = GridBagConstraints.CENTER;
        player1 = new PlayGridBoard(ButtonStyle.PLAYER_PLAYING1, name, guiController);
        player2 = new PlayGridBoard(ButtonStyle.PLAYER_PLAYING2, opponentName, guiController);

        gbc.gridy++;
        add(player1, gbc);
        gbc.gridx++;
        add(player2, gbc);

        // Setup the chat area
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        messageArea = new JTextPane();
        messageArea.setEditable(false);
        messageArea.setPreferredSize(new Dimension(300, 130));
        messageArea.setMaximumSize(new Dimension(300, 130));

        // Setup chat meassage display formats
        system = messageArea.addStyle("system", null);
        myText = messageArea.addStyle("my", null);
        otherText = messageArea.addStyle("other", null);
        StyleConstants.setForeground(system, Color.RED);
        StyleConstants.setAlignment(system, StyleConstants.ALIGN_CENTER);
        StyleConstants.setForeground(myText, Color.BLUE);
        StyleConstants.setAlignment(myText, StyleConstants.ALIGN_RIGHT);
        StyleConstants.setForeground(otherText, Color.BLACK);
        StyleConstants.setAlignment(otherText, StyleConstants.ALIGN_LEFT);

        //Add scrollpanel to the message display area
        JScrollPane scrollPane = new JScrollPane(messageArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(300, 130));
        scrollPane.setMaximumSize(new Dimension(300, 130));// Adjust size as needed

        add(scrollPane, gbc);

        //Add text input component
        textField = new JTextField();
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addMessage(textField.getText(), myText, (name == null? "You" : name) + ":\n");
                    guiController.sendOpponentMessage(textField.getText());
                    textField.setText("");
                } catch (Exception exception) {
                    addSystemMessage("Error: " + exception.getMessage());
                }
            }
        });

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(textField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        inputPanel.setPreferredSize(new Dimension(400, 50));
        inputPanel.setMaximumSize(new Dimension(400, 50));
        gbc.gridy++;
        add(inputPanel, gbc);

        setBackground(Color.white);
    }

     /**
      * Adds a system message to the message area.
      * System messages are typically informational or error messages from the game system.
      *
      * @param string the system message to add
      */
     public void addSystemMessage(String string) {
         addMessage(string, system, "");
     }

     /**
      * Adds a message from the opponent to the message area.
      * These messages are prefixed with the opponent's name for clarity.
      *
      * @param string the opponent's message to add
      */
     public void addOpponentMessage(String string) {
         addMessage(string, otherText, guiController.getOpponentName()+":\n");
     }

     /**
      * Adds a styled message to the message area.
      * This method inserts the specified text with a given style into the message area's document.
      *
      * @param string the message text to be displayed
      * @param style the style to be applied to the message text
      * @param tag a prefix string, often containing the sender's name or a label, which may also be styled
      */
    private void addMessage(String string, Style style, String tag) {
        try {
            StyledDocument doc = messageArea.getStyledDocument();
            doc.setParagraphAttributes(doc.getLength(), 1, style, false);
            doc.insertString(doc.getLength(), tag , style);
            doc.insertString(doc.getLength(), string + "\n", style);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

     /**
      * Returns the PlayGridBoard object representing player 1.
      *
      * @return the PlayGridBoard object representing player 1
      */
     public PlayGridBoard getPlayer1() {
         return player1;
     }

     /**
      * Returns the PlayGridBoard object representing player 2.
      *
      * @return the PlayGridBoard object representing player 2
      */
     public PlayGridBoard getPlayer2() {
         return player2;
     }

}
