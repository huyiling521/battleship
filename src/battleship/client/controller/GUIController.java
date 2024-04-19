package battleship.client.controller;

import battleship.client.GUI.*;
import battleship.server.controller.GameController;
import battleship.server.model.TwoPlayerBoard;

import javax.swing.*;
import java.awt.*;

public class GUIController {
//    private int row;
//    private int col;
    private GridButton prevButton;
    private SettingPanel settingPanel;
    private WelcomePanel welcomePanel;
    private PlayingPanel playingPanel;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private BattleshipFrame battleshipFrame;
    private GameController gameController;
    public GUIController() {
//        row = -1;
//        col = -1;
        prevButton = null;
        welcomePanel = new WelcomePanel(this);
        settingPanel = null;
        playingPanel = null;
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add(welcomePanel, "welcome");;
        cardLayout.show(cardPanel, "welcome");
    }

    public void getClickedSettingButton(GridButton newButton) {
        String[] pos = newButton.getActionCommand().split(",");
        int row = Integer.parseInt(pos[0]);
        int col = Integer.parseInt(pos[1]);
        settingPanel.setPosition(row, col);
        if (prevButton != null && prevButton.isEnabled()) prevButton.clearClick();
        prevButton = newButton;
    }

    public void toSettingPanel() {
        try {
            settingPanel = new SettingPanel(this);
            this.gameController = new GameController(new TwoPlayerBoard());
            cardPanel.add(settingPanel, "setting");
            cardLayout.show(cardPanel, "setting");
        } catch (Exception exception) {
            welcomePanel.setMessageLabel("Sorry, something went wrong...");
        }
    }
    public void toPlayingPanel() {
        try {
            playingPanel = new PlayingPanel(this);
            this.gameController = new GameController(new TwoPlayerBoard());
            cardPanel.add(playingPanel, "playing");
            cardLayout.show(cardPanel, "playing");
        } catch (Exception exception) {
            welcomePanel.setMessageLabel("Sorry, something went wrong...");
        }
    }
    public void toWelcomePanel() {
        this.gameController = null;
        settingPanel = null;
        playingPanel = null;
        cardLayout.show(cardPanel, "welcome");
    }

    public int setShip(int row, int col, boolean isHorizontal, String shipType) {
        return gameController.placeOneShip(row - 1, col - 1, isHorizontal, shipType);
    }

    public void startGame() {
        toPlayingPanel();
        // TODO waiting for two player to come into the playing room
    }

    public void receiveOpponentAttack(){
        // TODO
        int row = 1;
        int col = 1;
        boolean isHit = gameController.receiveOpponentAttack(row, col);
        playingPanel.getPlayer2().attackedButton(row, col, isHit);
    }

    public void receiveOpponentMassage(){
        // TODO
    }

    public boolean attack(GridButton newButton) {
        String[] pos = newButton.getActionCommand().split(",");
        int row = Integer.parseInt(pos[0]);
        int col = Integer.parseInt(pos[1]);
//        currentPanel.setPosition(row, col);
        String systemMessage = "You attacked (" + (char)(row - 1 + 'A') + ", " + col + "): ";
        if (gameController.attack(row, col)) {
            playingPanel.addSystemMessage(systemMessage + "HIT");
            return true;
        } else {
            playingPanel.addSystemMessage(systemMessage + "MISS");
            return false;
        }
    }

    public void sendMessage(String req){
        // TODO
    }

    public void setCardPanel(JPanel cardPanel) {
        this.cardPanel = cardPanel;
    }

    public void setCardLayout(CardLayout cardLayout) {
        this.cardLayout = cardLayout;
    }

    public WelcomePanel getWelcomePanel() {
        return welcomePanel;
    }

    public JPanel getCardPanel() {
        return cardPanel;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public void setBattleshipFrame(BattleshipFrame battleshipFrame) {
        this.battleshipFrame = battleshipFrame;
    }
}
