package finalproj.battleship.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import finalproj.battleship.GUI.components.*;

class GUIControllerTest {
    @Mock private GUIController guiController; // Mock the concrete implementation
    @Mock private PlayingButton playingButton;
    @Mock private OnePlayerButton onePlayerButton;
    @Mock private SettingButton settingButton;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        guiController = mock(GUIController.class);
    }

    @Test
    void testNavigationToWelcome() {
        guiController.toWelcomePanel();
        verify(guiController).toWelcomePanel();
    }

    @Test
    void testNavigationToOnePlayerPanel() {
        guiController.toOnePlayerPanel();
        verify(guiController).toOnePlayerPanel();
    }

    @Test
    void testNavigationToGuidePanel() {
        guiController.toGuidePanel();
        verify(guiController).toGuidePanel();
    }

    @Test
    void testNavigationToSettingPanel() {
        guiController.toSettingPanel();
        verify(guiController).toSettingPanel();
    }

    @Test
    void testNavigationToPlayingPanel() {
        guiController.toPlayingPanel();
        verify(guiController).toPlayingPanel();
    }

    @Test
    void testEndGame() {
        guiController.endGame();
        verify(guiController).endGame();
    }

    @Test
    void testSetShip() throws Exception {
        when(guiController.setShip(1, 1, true, "Destroyer")).thenReturn(1);
        int result = guiController.setShip(1, 1, true, "Destroyer");
        assertEquals(1, result);
        verify(guiController).setShip(1, 1, true, "Destroyer");
    }

    @Test
    void testAttack() throws Exception {
        when(guiController.attack(playingButton)).thenReturn(true);
        assertTrue(guiController.attack(playingButton));
        verify(guiController).attack(playingButton);
    }

    @Test
    void testGetClickedSettingButton() {
        doNothing().when(guiController).getClickedSettingButton(settingButton);
        guiController.getClickedSettingButton(settingButton);
        verify(guiController).getClickedSettingButton(settingButton);
    }

    @Test
    void testAttackComputer() {
        when(guiController.attackComputer(onePlayerButton)).thenReturn(true);
        assertTrue(guiController.attackComputer(onePlayerButton));
        verify(guiController).attackComputer(onePlayerButton);
    }
}
