package finalproj.battleship.GUI;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class WelcomePanelTest {
    @Mock
    private WelcomePanel welcomePanel;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testWelcomeMessage() {
        // Test if the welcome message is displayed correctly
    }

    @Test
    public void testNavigation() {
        // Test if navigation from the welcome panel to other parts of the application works as expected
    }
}
