package finalproj.battleship.GUI;

import finalproj.battleship.testutils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import finalproj.battleship.controller.GUIController;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PlayingPanelTest {

    @Mock
    private GUIController guiController;
    private PlayingPanel playingPanel;

    @BeforeEach
    void setUp() {
        playingPanel = new PlayingPanel(guiController, "my", "other");
    }

    @Test
    void testQuitButton(){
        JButton back = TestUtils.findButtonByText(playingPanel, "Quit");
        assertNotNull(back, "quit button should not be null");

        back.doClick();
        verify(guiController).endGame();
    }

    @Test
    void testSendButton() throws Exception {
        JButton send = TestUtils.findButtonByText(playingPanel, "Send");
        assertNotNull(send, "send button should not be null");

        send.doClick();
        verify(guiController).sendOpponentMessage(anyString());
    }
}
