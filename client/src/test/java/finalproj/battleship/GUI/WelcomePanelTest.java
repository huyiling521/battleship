package finalproj.battleship.GUI;

import finalproj.battleship.controller.GUIController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.*;
import java.awt.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WelcomePanelTest {
    @Mock
    private GUIController guiController;

    private WelcomePanel welcomePanel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        welcomePanel = new WelcomePanel(guiController);
    }

    @Test
    void componentsShouldBeInitializedCorrectly() {
        assertNotNull(findComponent(welcomePanel, JButton.class, "Single Player"), "Single Player button should be initialized");
        assertNotNull(findComponent(welcomePanel, JButton.class, "Two Player"), "Two Player button should be initialized");
        assertNotNull(findComponent(welcomePanel, JButton.class, "Guide"), "Guide button should be initialized");
        assertNotNull(findComponent(welcomePanel, JButton.class, "Exit"), "Exit button should be initialized");
    }

    @Test
    void singlePlayerButtonShouldTriggerController() {
        JButton singlePlayerButton = (JButton) findComponent(welcomePanel, JButton.class, "Single Player");
        assertNotNull(singlePlayerButton, "Single Player button should be initialized");
        singlePlayerButton.doClick();
        verify(guiController).toOnePlayerPanel();
    }

    @Test
    void twoPlayerButtonShouldTriggerController() {
        JButton twoPlayersButton = (JButton) findComponent(welcomePanel, JButton.class, "Two Player");
        assertNotNull(twoPlayersButton, "Two Player button should be initialized");
        twoPlayersButton.doClick();
        verify(guiController).toSettingPanel();
    }

    @Test
    void guideButtonShouldTriggerController() {
        JButton guideButton = (JButton) findComponent(welcomePanel, JButton.class, "Guide");
        assertNotNull(guideButton, "Guide button should be initialized");
        guideButton.doClick();
        verify(guiController).toGuidePanel();
    }

//    @Test
//    void exitButtonShouldExitApplication() {
//        JButton exitButton = (JButton) findComponent(welcomePanel, JButton.class, "Exit");
//        assertNotNull(exitButton, "Exit button should be initialized");
//        exitButton.doClick();
//        verifyStatic(System.class, times(1)); // Verify that System.exit() was called
//        System.exit(0);
//    }

    // Utility method to find components by their class and optionally by button text
    private Component findComponent(Container container, Class<?> componentClass, String buttonText) {
        for (Component comp : container.getComponents()) {
            if (componentClass.isInstance(comp)) {
                if (comp instanceof JButton && ((JButton) comp).getText().equals(buttonText)) {
                    return comp;
                }
            }
            if (comp instanceof Container) {
                Component found = findComponent((Container) comp, componentClass, buttonText);
                if (found != null) return found;
            }
        }
        return null;
    }
}
