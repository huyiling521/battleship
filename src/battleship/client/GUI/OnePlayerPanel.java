package battleship.client.GUI;

import battleship.client.GUI.components.OnePlayerGridBoard;
import battleship.client.GUI.components.PlayGridBoard;
import battleship.client.GUI.style.ButtonStyle;
import battleship.client.GUI.style.ComponentSize;
import battleship.client.controller.GUIController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OnePlayerPanel extends JPanel {

    private final OnePlayerGridBoard player1;
    private static final ComponentSize playAreaSize = ComponentSize.GAME_PANEL;
    private static final ComponentSize windowSize = ComponentSize.WINDOW;
    private final JTextArea messageArea;

    private final JScrollPane scrollPane;
    private final GUIController guiController;

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

        // 将文本区域放入滚动窗格
        scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(550, 450));
        scrollPane.setMaximumSize(new Dimension(550, 450));

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

        gbc.gridx = 0;
        gbc.gridy++;
        player1 = new OnePlayerGridBoard(ButtonStyle.PLAYER_PLAYING1, "Your", guiController);
        add(player1, gbc);

        gbc.gridx++;
        messageArea = new JTextArea();
        messageArea.setEditable(false);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);

        // Scroll Pane for the TextArea
        JScrollPane scrollPane = new JScrollPane(messageArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(250, 450));
        scrollPane.setMaximumSize(new Dimension(250, 450));// Adjust size as needed

        // Adding the scroll pane with message display area to the center
        add(scrollPane, gbc);
        setBackground(Color.white);

    }


    public void addSystemMessage(String string) {
        messageArea.setText(messageArea.getText() + string + "\n");
    }

    public void displayHelpMessage() {
        JOptionPane.showMessageDialog(OnePlayerPanel.this, scrollPane, "Help Information", JOptionPane.INFORMATION_MESSAGE);
    }
}
