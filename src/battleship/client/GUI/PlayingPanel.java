package battleship.client.GUI;

import battleship.client.GUI.components.PlayGridBoard;
import battleship.client.GUI.style.ButtonStyle;
import battleship.client.GUI.style.ComponentSize;
import battleship.client.controller.GUIController;

import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayingPanel extends JPanel {

    private final PlayGridBoard player1;
    private final PlayGridBoard player2;
    private static final ComponentSize playAreaSize = ComponentSize.GAME_PANEL;
    private static final ComponentSize windowSize = ComponentSize.WINDOW;
    private static final ComponentSize chatAreaSize = ComponentSize.CHAT_PANEL;
    private final JTextPane messageArea;
    private final JTextField textField;
//    private String systemMessage = "";
    private final Style system;
    private final Style myText;
    private final Style otherText;
    private final GUIController guiController;

    public PlayingPanel(GUIController guiController, String name, String opponentName) {
        super();
        this.guiController = guiController;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0,40,0,40);
        gbc.gridx = 0;
        gbc.gridy = 0;

        player1 = new PlayGridBoard(ButtonStyle.PLAYER_PLAYING1, name, guiController);
        player2 = new PlayGridBoard(ButtonStyle.PLAYER_PLAYING2, opponentName, guiController);

        add(player1, gbc);
        gbc.gridx++;
        add(player2, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        messageArea = new JTextPane();
        messageArea.setEditable(false);
        messageArea.setPreferredSize(new Dimension(300, 130));
        messageArea.setMaximumSize(new Dimension(300, 130));
        // Define a style and set colors

        system = messageArea.addStyle("system", null);
        myText = messageArea.addStyle("my", null);
        otherText = messageArea.addStyle("other", null);
        StyleConstants.setForeground(system, Color.RED);
        StyleConstants.setAlignment(system, StyleConstants.ALIGN_CENTER);
        StyleConstants.setForeground(myText, Color.BLUE);
        StyleConstants.setAlignment(myText, StyleConstants.ALIGN_RIGHT);
        StyleConstants.setForeground(otherText, Color.BLACK);
        StyleConstants.setAlignment(otherText, StyleConstants.ALIGN_LEFT);

        // Scroll Pane for the TextArea
        JScrollPane scrollPane = new JScrollPane(messageArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(300, 130));
        scrollPane.setMaximumSize(new Dimension(300, 130));// Adjust size as needed

        // Adding the scroll pane with message display area to the center
        add(scrollPane, gbc);

        textField = new JTextField();
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addMessage(textField.getText(), myText, guiController.getName() + ":\n");
                    guiController.sendOpponentMessage(textField.getText());
                    textField.setText("");
                } catch (Exception exception) {
                    addSystemMessage("Error: " + exception.getMessage());
                }
            }
        });
        // A panel for user input and send button
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(textField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        inputPanel.setPreferredSize(new Dimension(400, 50));
        inputPanel.setMaximumSize(new Dimension(400, 50));// Adjust size as needed
        gbc.gridy++;
        add(inputPanel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        JButton backButton = new JButton("Quit");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiController.endGame();
                guiController.toWelcomePanel();
            }
        });
        add(backButton, gbc);
        setBackground(Color.white);
    }

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

    public void addSystemMessage(String string) {
        addMessage(string, system, "");
    }

    public void addOpponentMessage(String string) {
        addMessage(string, otherText, guiController.getOpponentName()+":\n");
    }

    public PlayGridBoard getPlayer1() {
        return player1;
    }

    public PlayGridBoard getPlayer2() {
        return player2;
    }
}
