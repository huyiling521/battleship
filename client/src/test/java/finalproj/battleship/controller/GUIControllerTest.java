package finalproj.battleship.controller;

import static org.junit.jupiter.api.Assertions.*;

import finalproj.battleship.network.GameClient;
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
public class GUIControllerTest {

    private GUIController controller;
    @Mock
    private GameClient gameClient;

    @BeforeEach
    public void setUp() {
        controller = new GUIController();
        controller.setGameClient(gameClient);
    }

    @Test
    public void testFunctionalityOne() {
        // Test some functionality of GUIController
    }

    @Test
    public void testFunctionalityTwo() {
        // Test another functionality of GUIController;
    }

    // Additional tests can be added here
}
