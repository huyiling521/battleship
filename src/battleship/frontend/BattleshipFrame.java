package battleship.frontend;

import battleship.frontend.style.ComponentSize;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;


public class BattleshipFrame extends JFrame {
    private static final ComponentSize frameSize = ComponentSize.WINDOW;
    private final WelcomePanel welcomePage;
    private final SettingPanel settingPanel;

    private final PlayingPanel playingPanel;
    private final ChatPanel chatPanel;
    public BattleshipFrame() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        super();
        JPanel layout = new JPanel();
        layout.setLayout(new BoxLayout(layout, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(frameSize.getWidth(), frameSize.getHeight()));
        setResizable(false);
        setTitle("Battleship");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomePage = new WelcomePanel();
        settingPanel = new SettingPanel();
        playingPanel = new PlayingPanel();
        chatPanel = new ChatPanel();

//        setLayout(new FlowLayout());
        getContentPane().setBackground(Color.white);
        setLocationRelativeTo(null); // Center the window on the screen
        layout.add(welcomePage);
        layout.add(playingPanel);
        layout.add(settingPanel);
        welcomePage.setVisible(false);
        playingPanel.setVisible(false);
        settingPanel.setVisible(true);
        add(layout);
        layout.setBackground(Color.white);
        layout.setOpaque(true);
        setSize(frameSize.getWidth(), frameSize.getHeight());
//        pack();
    }
  }



