package finalproj.battleship.controller;

import static org.junit.jupiter.api.Assertions.*;

import finalproj.battleship.model.OnePlayerBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OnePlayerGameControllerTest {

    private OnePlayerGameController controller;

    @BeforeEach
    public void setUp() {
        controller = new OnePlayerGameController();
    }

    @Test
    public void testShootAt() {
        boolean shot = controller.shootAt(0,0);
        if (shot == true) {
            Exception e = assertThrows(IllegalArgumentException.class, () -> {
                controller.shootAt(0, 0);
            });
            assertEquals("The position has been shot. Please choose another one.", e.getMessage());
        }
    }

    @Test
    public void testIsSunk() {
        assertFalse(controller.isSunk(0,0));
    }

    @Test
    public void testIsGameOver() {
        assertFalse(controller.isGameOver());

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                controller.shootAt(i, j);
            }
        }
        assertTrue(controller.isGameOver());
    }
}
