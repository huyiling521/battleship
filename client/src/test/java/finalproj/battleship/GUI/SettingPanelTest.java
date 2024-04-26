package finalproj.battleship.GUI;

import finalproj.battleship.controller.GUIController;
import finalproj.battleship.GUI.components.SettingGridBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SettingPanelTest {
    @Mock
    private GUIController guiController;
    @Mock
    private SettingGridBoard settingGridBoard;

    private SettingPanel settingPanel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(guiController.getName()).thenReturn("Player1");
        settingPanel = new SettingPanel(guiController);
    }

    @Test
    void componentsShouldBeInitializedCorrectly() {
        assertNotNull(findComponent(settingPanel, JButton.class, "Quit"), "Quit button should be initialized");
        assertNotNull(findComponent(settingPanel, JButton.class, "Start"), "Start button should be initialized");
        assertNotNull(findComponent(settingPanel, JButton.class, "Set The Ship"), "Set Ship button should be initialized");
    }

    @Test
    void quitButtonShouldTriggerController() {
        JButton quitButton = (JButton) findComponent(settingPanel, JButton.class, "Quit");
        assertNotNull(quitButton, "Quit button should be initialized");
        quitButton.doClick();
        verify(guiController).toWelcomePanel();
    }

    @Test
    void setShipButtonShouldChangeGameState() {
        JButton setShipButton = (JButton) findComponent(settingPanel, JButton.class, "Set The Ship");
        assertNotNull(setShipButton, "Set Ship button should be initialized");
        setShipButton.doClick();
        try {
            verify(guiController, atLeastOnce()).setShip(anyInt(), anyInt(), anyBoolean(), anyString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void startGameButtonShouldTriggerController() {
        JButton startGameButton = (JButton) findComponent(settingPanel, JButton.class, "Start");
        assertNotNull(startGameButton, "Start button should be initialized");
        startGameButton.doClick();
        verify(guiController).toPlayingPanel();
    }

    // Utility method to find components by their class and optionally by button text
    private Component findComponent(Container container, Class<?> componentClass, String buttonText) {
        for (Component comp : container.getComponents()) {
            if (componentClass.isInstance(comp)) {
                if (comp instanceof JButton && ((JButton) comp).getText().contains(buttonText)) {
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
