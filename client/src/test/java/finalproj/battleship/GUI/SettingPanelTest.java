package finalproj.battleship.GUI;

import finalproj.battleship.testutils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import finalproj.battleship.controller.GUIController;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SettingPanelTest {

    @Mock
    private GUIController guiController;

    private JLabel currLabel;

    private SettingPanel settingPanel;

    @BeforeEach
    void setUp() {
        settingPanel = new SettingPanel(guiController);
    }

    @Test
    void testAircraftCarrierButtonAction(){
        currLabel = TestUtils.findLabelByText(settingPanel, "Current Ship:");
        JButton aircraftCarrierButton = TestUtils.findButtonByText(settingPanel, "Aircraft Carrier \n(5 Units)");
        assertNotNull(aircraftCarrierButton, "Aircraft Carrier button should not be null");

        aircraftCarrierButton.doClick();
        assertEquals("Current Ship: Aircraft Carrier", currLabel.getText());
    }

    @Test
    void testBattleshipButtonAction() {
        currLabel = TestUtils.findLabelByText(settingPanel, "Current Ship:");
        JButton battleshipButton = TestUtils.findButtonByText(settingPanel, "Battleship \n(4 Units)");
        assertNotNull(battleshipButton, "Battleship button should not be null");

        battleshipButton.doClick();
        assertEquals("Current Ship: Battleship", currLabel.getText());
    }

    @Test
    void testDestroyerButtonAction() {
        currLabel = TestUtils.findLabelByText(settingPanel, "Current Ship:");
        JButton destroyerButton = TestUtils.findButtonByText(settingPanel, "Destroyer \n(3 Units)");
        assertNotNull(destroyerButton, "Destroyer button should not be null");

        destroyerButton.doClick();
        assertEquals("Current Ship: Destroyer", currLabel.getText());
    }

    @Test
    void testSubmarineButtonAction() {
        currLabel = TestUtils.findLabelByText(settingPanel, "Current Ship:");
        JButton submarineButton = TestUtils.findButtonByText(settingPanel, "Submarine \n(3 Units)");
        assertNotNull(submarineButton, "Submarine button should not be null");

        submarineButton.doClick();
        assertEquals("Current Ship: Submarine", currLabel.getText());
    }
}
