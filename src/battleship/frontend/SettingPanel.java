package battleship.frontend;

import battleship.frontend.style.ButtonStyle;
import battleship.frontend.style.ComponentSize;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class SettingPanel extends JPanel {
    private final GridButtonBoard player1;
    private final GridButtonBoard player2;
    private static final ComponentSize panelSize = ComponentSize.GAME_PANEL;

    public SettingPanel() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        super();
        player1 = new GridButtonBoard(PlayingButton.class, ButtonStyle.PLAYER_SETTING1, "name waiting");
        player2 = new GridButtonBoard(PlayingButton.class, ButtonStyle.PLAYER_SETTING2, "name waiting");
        setPreferredSize(new Dimension(panelSize.getWidth(), panelSize.getHeight()));
    }
}
