package battleship.frontend;

import battleship.frontend.style.ComponentSize;
import battleship.server.controler.GUIController;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;


public class BattleshipFrame extends JFrame {
    private static final ComponentSize frameSize = ComponentSize.WINDOW;
    private final WelcomePanel welcomePage;
    private SettingPanel settingPanel;

    private PlayingPanel playingPanel;
    private final  CardLayout cardLayout;
    private final JPanel cardPanel;

    public BattleshipFrame(GUIController guiController) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        super();
        JPanel layout = new JPanel();

        setPreferredSize(new Dimension(frameSize.getWidth(), frameSize.getHeight()));
        setResizable(false);
        setTitle("Battleship");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        welcomePage = new WelcomePanel(guiController);
        //settingPanel = new SettingPanel(guiController);
        playingPanel = new PlayingPanel(guiController);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        guiController.setCurrentPanel(welcomePage);
        cardPanel.add(welcomePage, "welcome");
//        cardPanel.add(settingPanel, "setting");
//        cardPanel.add(playingPanel, "playing");

        cardLayout.show(cardPanel, "welcome");
        add(cardPanel);
        getContentPane().setBackground(Color.white);
        setLocationRelativeTo(null); // Center the window on the screen

        guiController.setCardLayout(cardLayout);
        guiController.setCardPanel(cardPanel);
        guiController.setBattleshipFrame(this);
        setSize(frameSize.getWidth(), frameSize.getHeight());
    }

    public WelcomePanel getWelcomePage() {
        return welcomePage;
    }

    public SettingPanel getSettingPanel() {
        return settingPanel;
    }

    public PlayingPanel getPlayingPanel() {
        return playingPanel;
    }

    public void setSettingPanel(SettingPanel settingPanel) {
        this.settingPanel = settingPanel;
    }

    public void setPlayingPanel(PlayingPanel playingPanel) {
        this.playingPanel = playingPanel;
    }
}



