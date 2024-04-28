package finalproj.battleship.GUI;

import finalproj.battleship.GUI.style.ComponentSize;
import finalproj.battleship.controller.GUIController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.*;

import java.awt.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BattleshipFrameTest {

    @Mock
    private GUIController guiController;

    private BattleshipFrame battleshipFrame;

    @BeforeEach
    void setUp() {
        when(guiController.getCardPanel()).thenReturn(new JPanel());
        battleshipFrame = new BattleshipFrame(guiController);
    }

    @Test
    void framePropertiesTest() {
        assertEquals(ComponentSize.WINDOW.getWidth(), battleshipFrame.getWidth());
        assertEquals(ComponentSize.WINDOW.getHeight(), battleshipFrame.getHeight());
        assertFalse(battleshipFrame.isResizable());
        assertEquals("Battleship", battleshipFrame.getTitle());
        assertEquals(JFrame.EXIT_ON_CLOSE, battleshipFrame.getDefaultCloseOperation());

        verify(guiController, times(1)).getCardPanel();
        verify(guiController, times(1)).setBattleshipFrame(battleshipFrame);
    }
}
