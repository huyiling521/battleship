package finalproj.battleship.GUI;

import finalproj.battleship.controller.GUIController;
import finalproj.battleship.GUI.components.OnePlayerGridBoard;
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
class OnePlayerPanelTest {
    @Mock
    private GUIController guiController;
    @Mock
    private OnePlayerGridBoard player1;

    @Mock
    private OnePlayerPanel onePlayerPanel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void componentsInitializationTest() {
        onePlayerPanel = new OnePlayerPanel(guiController);
        assertNotNull(findComponent(onePlayerPanel, JButton.class, "Quit"), "Quit button should be initialized");
        assertNotNull(findComponent(onePlayerPanel, JButton.class, "Help"), "Help button should be initialized");
        assertNotNull(findComponent(onePlayerPanel, JTextArea.class), "Message area should be initialized");
    }

    @Test
    void backButtonShouldTriggerController() {
        JButton backButton = (JButton) findComponent(onePlayerPanel, JButton.class, "Quit");
        assertNotNull(backButton, "Back button should be initialized");
        backButton.doClick();
        verify(guiController).toWelcomePanel();
    }

    @Test
    void helpButtonShouldDisplayHelpMessage() {
        JButton helpButton = (JButton) findComponent(onePlayerPanel, JButton.class, "Help");
        assertNotNull(helpButton, "Help button should be initialized");
        helpButton.doClick();
        // Can't directly verify that JOptionPane was shown, but can check if method was called
        verify(onePlayerPanel).displayHelpMessage();
    }

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

    private Component findComponent(Container container, Class<?> componentClass) {
        for (Component comp : container.getComponents()) {
            if (componentClass.isInstance(comp)) {
                return comp;
            }
            if (comp instanceof Container) {
                Component found = findComponent((Container) comp, componentClass);
                if (found != null) return found;
            }
        }
        return null;
    }
}
