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
class GuidePanelTest {
    @Mock
    private GUIController guiController;

    private GuidePanel guidePanel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        guidePanel = new GuidePanel(guiController);
    }

    @Test
    void panelShouldContainTitleLabel() {
        JLabel titleLabel = (JLabel) findComponent(guidePanel, JLabel.class, "Guidance for the Battleship Game");
        assertNotNull(titleLabel, "Title label should be initialized");
        assertEquals("Guidance for the Battleship Game", titleLabel.getText(), "Title text should match");
    }

    @Test
    void panelShouldContainRulesText() {
        JTextArea rulesText = (JTextArea) findComponent(guidePanel, JTextArea.class, "- There are 2 versions of Battleship game:");
        assertNotNull(rulesText, "Rules text area should be initialized");
        assertTrue(rulesText.getText().contains("11 ships in total"), "Rules should contain specific game rules");
        assertFalse(rulesText.isEditable(), "Rules text area should not be editable");
    }

    @Test
    void backButtonShouldTriggerController() {
        JButton backButton = (JButton) findComponentByActionCommand(guidePanel, "Back");
        assertNotNull(backButton, "Back button should be initialized");
        backButton.doClick();
        verify(guiController).toWelcomePanel();
    }

    // Utility method to find components by their text content
    private Component findComponent(Container container, Class<?> componentClass, String text) {
        for (Component comp : container.getComponents()) {
            if (componentClass.isInstance(comp) && ((JLabel) comp).getText().contains(text)) {
                return comp;
            }
            if (comp instanceof Container) {
                Component found = findComponent((Container) comp, componentClass, text);
                if (found != null) return found;
            }
        }
        return null;
    }

    // Utility method to find buttons by action command
    private Component findComponentByActionCommand(Container container, String command) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JButton && ((JButton) comp).getActionCommand().equals(command)) {
                return comp;
            }
            if (comp instanceof Container) {
                Component found = findComponentByActionCommand((Container) comp, command);
                if (found != null) return found;
            }
        }
        return null;
    }
}
