package finalproj.battleship.GUI;

import finalproj.battleship.GUI.style.ComponentSize;
import finalproj.battleship.controller.GUIController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.*;

import java.awt.*;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BattleshipFrameTest {
    @Mock
    private GUIController guiController;
    @Mock
    private JPanel cardPanel;

    private BattleshipFrame frame;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(guiController.getCardPanel()).thenReturn(cardPanel);
        frame = new BattleshipFrame(guiController);
    }

    @Test
    void frameInitializationTest() {
        assertEquals(JFrame.EXIT_ON_CLOSE, frame.getDefaultCloseOperation(), "Check default close operation");
        assertFalse(frame.isResizable(), "Frame should be non-resizable");
        assertEquals("Battleship", frame.getTitle(), "Check frame title");
        assertEquals(new Dimension(ComponentSize.WINDOW.getWidth(), ComponentSize.WINDOW.getHeight()), frame.getPreferredSize(), "Check frame size");
    }

    @Test
    void componentsAddedTest() {
        // Verify that the card panel is added to the frame
        verify(guiController).getCardPanel();
        assertTrue(Arrays.asList(frame.getContentPane().getComponents()).contains(cardPanel), "Card panel should be added to the frame");
    }

    @Test
    void guiControllerSetInFrameTest() {
        // Verify that the GUIController has set this frame as its BattleshipFrame
        verify(guiController).setBattleshipFrame(frame);
    }
}
