package battleship.frontend;

import battleship.frontend.style.ButtonStyle;
import battleship.frontend.style.ComponentSize;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class PlayingPanel extends JPanel {

    private final GridButtonBoard player1;
    private final GridButtonBoard player2;
    private final ChatPanel chatPanel;

    private static final ComponentSize playAreaSize = ComponentSize.GAME_PANEL;
    private static final ComponentSize windowSize = ComponentSize.WINDOW;

    public PlayingPanel() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(windowSize.getWidth(), windowSize.getHeight()));

        JPanel playArea = new JPanel();
        playArea.setLayout(new BoxLayout(playArea, BoxLayout.X_AXIS));
        playArea.setBackground(null);
        playArea.setPreferredSize(new Dimension(playAreaSize.getWidth(), 400));
        playArea.setMaximumSize(playArea.getPreferredSize());
        player1 = new GridButtonBoard(PlayingButton.class, ButtonStyle.PLAYER_PLAYING1, "name waiting");
        player2 = new GridButtonBoard(PlayingButton.class, ButtonStyle.PLAYER_PLAYING2, "name waiting");
//        player1.setVisible(true);
//        player2.setVisible(true);
        playArea.add(player1);
//        playArea.add(Box.createHorizontalStrut(50));
        playArea.add(player2);
        add(playArea);

        chatPanel = new ChatPanel();
        add(chatPanel);
        setBackground(Color.white);
    }
}
