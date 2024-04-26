package finalproj.battleship.GUI;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;

import finalproj.battleship.controller.GUIController;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.*;

@ExtendWith(MockitoExtension.class)
class PlayingPanelTest {

    @Mock
    private GUIController guiController;

    private PlayingPanel playingPanel;
    private JButton backButton;
    private JButton sendButton;
    private JTextField textField;
    private JTextPane messageArea;

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//        playingPanel = new PlayingPanel(guiController, "Player1", "Opponent");
//
//        backButton = (JButton) TestUtils.getChildNamed(playingPanel, "quitButton");
//        sendButton = (JButton) TestUtils.getChildNamed(playingPanel, "sendButton");
//        textField = (JTextField) TestUtils.getChildNamed(playingPanel, "textField");
//        messageArea = (JTextPane) TestUtils.getChildNamed(playingPanel, "messageArea");
//    }

    @Test
    void testQuitButtonAction() {
        assertNotNull(backButton, "Back button should not be null");
        backButton.doClick();
        verify(guiController).endGame();
        verify(guiController).toWelcomePanel();
    }

    @Test
    void testSendMessage() throws Exception {
        assertNotNull(sendButton, "Send button should not be null");
        assertNotNull(textField, "Text field should not be null");

        String testMessage = "Hello, Battleship!";
        textField.setText(testMessage);
        sendButton.doClick();

        try {
            verify(guiController).sendOpponentMessage(testMessage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertEquals("", textField.getText(), "Text field should be cleared after sending a message");
    }

    @Test
    void testMessageDisplay() {
        // Assuming `addSystemMessage` and other methods update `messageArea`
        String systemMessage = "System message test";
        playingPanel.addSystemMessage(systemMessage);

        String text = messageArea.getText();
        assertTrue(text.contains(systemMessage), "Message area should contain the system message");
    }
}

