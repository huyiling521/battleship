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
class OnePlayerPanelTest {

    @Mock
    private GUIController guiController;
    private OnePlayerPanel onePlayerPanel;

    @BeforeEach
    void setUp() {
        onePlayerPanel = new OnePlayerPanel(guiController);
    }

    @Test
    void testQuitButton(){
        JButton back = TestUtils.findButtonByText(onePlayerPanel, "Quit");
        assertNotNull(back, "Aircraft Carrier button should not be null");

        back.doClick();
        verify(guiController).toWelcomePanel();
    }
}
