package battleship.frontend;

import battleship.frontend.style.ComponentSize;
import battleship.server.controler.GUIController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatPanel extends JPanel {

    private static final ComponentSize chatAreaSize = ComponentSize.CHAT_PANEL;
    private JTextArea messageArea;
    private JTextField textField;
    public ChatPanel(GUIController guiController) {
        super();
        setLayout(new BorderLayout());

        messageArea = new JTextArea();
        messageArea.setPreferredSize(new Dimension(300, 130));
        messageArea.setMaximumSize(new Dimension(300, 130));
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setEditable(false);  // Ensure the area is not editable

// Scroll Pane for the TextArea
        JScrollPane scrollPane = new JScrollPane(messageArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(300, 130));
        scrollPane.setMaximumSize(new Dimension(300, 130));// Adjust size as needed

// Adding the scroll pane with message display area to the center
        add(scrollPane, BorderLayout.NORTH);

        textField = new JTextField();
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMessage(textField.getText(), Color.GREEN);
                textField.setText("");
            }
        });
// A panel for user input and send button
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(textField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        inputPanel.setPreferredSize(new Dimension(400, 50));
        inputPanel.setMaximumSize(new Dimension(400, 50));// Adjust size as needed

// Adding the input panel to the bottom
        add(inputPanel, BorderLayout.SOUTH);
        setPreferredSize(new Dimension(chatAreaSize.getWidth(), chatAreaSize.getHeight()));
        setMaximumSize(getPreferredSize());
    }

    public void addMessage(String string, Color color) {
        this.messageArea.setText(messageArea.getText() + string + "\n");
    }
}
