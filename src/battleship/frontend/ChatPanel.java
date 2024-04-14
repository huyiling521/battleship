package battleship.frontend;

import javax.swing.*;
import java.awt.*;

public class ChatPanel extends JPanel {

    /**
     * Creates a new <code>JPanel</code> with a double buffer
     * and a flow layout.
     */
    public ChatPanel() {
        super();
        setLayout(new BorderLayout());

        JTextArea messageArea = new JTextArea();
        messageArea.setPreferredSize(new Dimension(300, 100));
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setEditable(false);  // Ensure the area is not editable

        JTextField textField = new JTextField();
        JButton sendButton = new JButton("Send");

// Scroll Pane for the TextArea
        JScrollPane scrollPane = new JScrollPane(messageArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(300, 150));  // Adjust size as needed


// Adding the scroll pane with message display area to the center
        add(scrollPane, BorderLayout.CENTER);

// A panel for user input and send button
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(textField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        inputPanel.setPreferredSize(new Dimension(400, 50));  // Adjust size as needed

// Adding the input panel to the bottom
        add(inputPanel, BorderLayout.SOUTH);
        setPreferredSize(new Dimension(400, 100));

    }
}
