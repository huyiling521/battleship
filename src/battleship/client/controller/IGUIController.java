package battleship.client.controller;

import battleship.client.GUI.components.OnePlayerButton;
import battleship.client.GUI.components.PlayingButton;
import battleship.client.GUI.components.SettingButton;


interface IGUIController {
    void toWelcomePanel();

    void toOnePlayerPanel();

    void toGuidePanel();

    void toSettingPanel();

    void toPlayingPanel();
    void endGame();
    
    int setShip(int row, int col, boolean isHorizontal, String shipType) throws Exception;

    boolean attack(PlayingButton newButton) throws Exception;
    
    void getClickedSettingButton(SettingButton newButton);

    String getName();

    String getOpponentName();


    //the following is for single player
    boolean attackComputer(OnePlayerButton onePlayerButton);
}
