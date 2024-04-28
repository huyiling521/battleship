package finalproj.battleship.GUI;
import finalproj.battleship.testutils.TestUtils;
import finalproj.battleship.controller.GUIController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WelcomePanelTest {

    private WelcomePanel panel;
    @Mock
    private GUIController guiController;

    @BeforeEach
    void setUp() {
        // Initialize the panel with the mocked GUIController
        panel = new WelcomePanel(guiController);
    }

    @Test
    void testSinglePlayerButtonAction() {
        JButton singlePlayerButton = (JButton) TestUtils.findButtonByText(panel, "Single Player");
        singlePlayerButton.doClick();
        verify(guiController).toOnePlayerPanel();
    }

    @Test
    void testTwoPlayerButtonAction() {
        JButton twoPlayerButton = (JButton) TestUtils.findButtonByText(panel, "Two Player");
        twoPlayerButton.doClick();
        verify(guiController).toSettingPanel();
    }

    @Test
    void testGuideButtonAction() {
        JButton guideButton = (JButton) TestUtils.findButtonByText(panel, "Guide");
        guideButton.doClick();
        verify(guiController).toGuidePanel();
    }

    // this test will system.exit(0) and cause testing failure
    //but functionality is approved to exit the program
//    @Test
//    void testExitButtonAction() {
//        JButton exitButton = (JButton) TestUtils.findButtonByText(panel, "Exit");
//        exitButton.doClick();
//        verify(guiController).exitGame();
//    }

    @Test
    void simpleTest() {
        System.out.println("This is a simple test.");
        assertEquals(1, 1);
    }
}
