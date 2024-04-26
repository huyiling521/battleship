package finalproj.battleship.GUI;

import finalproj.battleship.GUI.style.ComponentSize;
import finalproj.battleship.controller.GUIController;


import javax.swing.*;
import java.awt.*;


public class BattleshipFrame extends JFrame {
    private static final ComponentSize frameSize = ComponentSize.WINDOW;

    public BattleshipFrame(GUIController guiController) {
        super();

        setPreferredSize(new Dimension(frameSize.getWidth(), frameSize.getHeight()));
        setResizable(false);
        setTitle("Battleship");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(guiController.getCardPanel());
        getContentPane().setBackground(Color.white);
        setLocationRelativeTo(null);

        guiController.setBattleshipFrame(this);
        setSize(frameSize.getWidth(), frameSize.getHeight());
    }
}



