package finalproj.battleship.GUI;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SettingPanelTest {
    @Mock
    private SettingPanel settingPanel;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSettingsAdjustment() {
        // Test if the settings can be adjusted correctly, such as changing difficulty or game modes
    }

    @Test
    public void testSettingsPersistence() {
        // Test if the changes in settings are persisted across sessions or restarts
    }
}
