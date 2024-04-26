package battleship.client.controller;

import battleship.client.GUI.*;
import battleship.client.GUI.components.OnePlayerButton;
import battleship.client.GUI.components.PlayingButton;
import battleship.client.GUI.components.SettingButton;
import battleship.client.network.GameClient;

import javax.swing.*;
import java.awt.*;

public class GUIController implements IGUIController {

    private BattleshipFrame battleshipFrame;
    private OnePlayerGameController onePlayerGameController;
    private SettingPanel settingPanel;
    private PlayingPanel playingPanel;
    private OnePlayerPanel onePlayerPanel;
    private final JPanel cardPanel;
    private final CardLayout cardLayout;
    private GameClient gameClient;
    private String name;
    private String opponentName;
    public GUIController() {
        WelcomePanel welcomePanel = new WelcomePanel(this);
        settingPanel = null;
        playingPanel = null;
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add(welcomePanel, "welcome");;
        cardPanel.add(new GuidePanel(this), "guide");
        cardLayout.show(cardPanel, "welcome");
    }

    public void toWelcomePanel() {
        settingPanel = null;
        playingPanel = null;
        onePlayerPanel = null;
        gameClient = null;
        cardLayout.show(cardPanel, "welcome");
    }

    public void toOnePlayerPanel() {
        onePlayerPanel = new OnePlayerPanel(this);
        onePlayerGameController = new OnePlayerGameController();
        cardPanel.add(onePlayerPanel, "oneplayer");
        cardLayout.show(cardPanel, "oneplayer");
        onePlayerPanel.displayHelpMessage();
    }

    public void toGuidePanel() {
        cardLayout.show(cardPanel, "guide");
    }

    public void toSettingPanel() {
        try {
            name = JOptionPane.showInputDialog(battleshipFrame, "Please Enter Your Name: ", "Set Name", JOptionPane.QUESTION_MESSAGE);
            if (name == null) return;
            while (name.isEmpty()) {
                name = JOptionPane.showInputDialog(battleshipFrame, "Name can't be empty, \nPlease Enter Your Name: ", "Set Name", JOptionPane.QUESTION_MESSAGE);
                if (name == null) return;
            }
            connectToServer();
            settingPanel = new SettingPanel(this);
            cardPanel.add(settingPanel, "setting");
            cardLayout.show(cardPanel, "setting");
            throwInformationMessage("Successfully connected!\nWait for another player...", "Connecting");
            String res = gameClient.sendGameMessage(MessageConstant.CONNECTION);
            if (res.startsWith(MessageConstant.GAME_QUIT)) {
                throwWholeErrorMessage(res.substring(res.lastIndexOf(":") + 1));
                gameClient.close();
                toWelcomePanel();
            } else {
                opponentName = res.substring(res.lastIndexOf(":") + 1);
                throwInformationMessage("Player matched!\nGame start.", "Connected");
            }
        } catch (Exception exception) {
            throwWholeErrorMessage(exception.getMessage());
        }
    }

    public void toPlayingPanel() {
        try {
            playingPanel = new PlayingPanel(this, name, opponentName);
            cardPanel.add(playingPanel, "playing");
            cardLayout.show(cardPanel, "playing");
            throwInformationMessage("Wait for another player to complete setting...", "Connecting");
            startGame();
            throwInformationMessage("Game start!", "Connected");
        } catch (Exception exception) {
            throwWholeErrorMessage(exception.getMessage());
        }
    }

    private void connectToServer() throws Exception {
        gameClient = new GameClient("localhost", 1235, this);
        String res = gameClient.sendGameMessage(MessageConstant.NAME_SETTING + name);
    }

    private void startGame(){
        try {
            String res = gameClient.sendGameMessage(MessageConstant.GAME_START);
        } catch (Exception e) {
            throwWholeErrorMessage(e.getMessage());
        }
    }

    public void endGame(){
        try {
            String res = gameClient.sendGameMessage(MessageConstant.GAME_QUIT);
        } catch (Exception e) {
            throwWholeErrorMessage(e.getMessage());
        }
    }

    public void sendOpponentMessage(String message) throws Exception {
        gameClient.sendOpponentMessage(message);
    }

    public void receiveOpponentAttack(int row, int col, boolean isHit){
        playingPanel.getPlayer2().opponentAttackedButton(row, col, isHit);
    }

    public void receiveOpponentMessage(String message){
        playingPanel.addOpponentMessage(message);
    }
    public void receiveSystemMessage(String message){
        playingPanel.addSystemMessage(message);
    }

    public int setShip(int row, int col, boolean isHorizontal, String shipType) throws Exception {
        return gameClient.setShip((row - 1) + "," + (col - 1) + "," + isHorizontal + "," + shipType);
    }

    public boolean attack(PlayingButton newButton) throws Exception {
        String[] pos = newButton.getActionCommand().split(",");
        int row = Integer.parseInt(pos[0]);
        int col = Integer.parseInt(pos[1]);
        String systemMessage = name + " attacked (" + (char)(row - 1 + 'A') + ", " + col + "): ";
        boolean isHit = gameClient.attack((row - 1) + "," + (col - 1));
        if (isHit) playingPanel.addSystemMessage(systemMessage + "HIT");
        else playingPanel.addSystemMessage(systemMessage + "MISS");
        return isHit;
    }

    public void throwWholeErrorMessage(String message) {
        JOptionPane.showMessageDialog(battleshipFrame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void throwInformationMessage(String message, String title) {
        JOptionPane.showMessageDialog(battleshipFrame, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public void getClickedSettingButton(SettingButton newButton) {
        String[] pos = newButton.getActionCommand().split(",");
        int row = Integer.parseInt(pos[0]);
        int col = Integer.parseInt(pos[1]);
        settingPanel.setPosition(row, col);
        settingPanel.checkPrevButtonStatus(newButton);
    }

    public String getName() {
        return name;
    }

    public String getOpponentName() {
        return opponentName;
    }

    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }

    public JPanel getCardPanel() {
        return cardPanel;
    }

    public void setBattleshipFrame(BattleshipFrame battleshipFrame) {
        this.battleshipFrame = battleshipFrame;
    }

    //the following is for single player

    public boolean attackComputer(OnePlayerButton onePlayerButton) {
        String[] pos = onePlayerButton.getActionCommand().split(",");
        int row = Integer.parseInt(pos[0]);
        int col = Integer.parseInt(pos[1]);
        String systemMessage = "You" + " attacked (" + (char)(row - 1 + 'A') + ", " + col + "): ";
        boolean isHit = onePlayerGameController.shootAt(row - 1,col - 1);
        if (isHit) {
            onePlayerPanel.addSystemMessage(systemMessage + "HIT");
            if (onePlayerGameController.isSunk(row - 1, col - 1)) onePlayerPanel.addSystemMessage("You sunk a " + onePlayerGameController.getShipType(row - 1, col - 1));
            if (onePlayerGameController.isGameOver()) {
                String conclusion = onePlayerGameController.getConclusion();
                throwInformationMessage(conclusion, "Game Over");
                int response = JOptionPane.showConfirmDialog(battleshipFrame, "Play again?", "No, thanks", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    toOnePlayerPanel();
                } else {
                    toWelcomePanel();
                }
            }
        }
        else onePlayerPanel.addSystemMessage(systemMessage + "MISS");
        return isHit;
    }
}
