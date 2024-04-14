package battleship.frontend;

import battleship.frontend.style.ButtonStyle;
import battleship.frontend.style.ComponentSize;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

public class SettingPanel extends JPanel {
    private final GridButtonBoard player1;
    private static final ComponentSize playAreaSize = ComponentSize.GAME_PANEL;
    private static final ComponentSize windowSize = ComponentSize.WINDOW;
    private String currShip;
    private boolean isHorizontal;
    private String position;

    public SettingPanel() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        super();
        currShip = "";
        position = "";
        isHorizontal = true;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setPreferredSize(new Dimension(windowSize.getWidth(), windowSize.getHeight()));

        JPanel buttonPanel = new JPanel(new GridLayout(10, 1, 5, 5));


        JButton aircraftCarrier = new JButton("Aircraft Carrier \n(5 Units)");
        JButton battleship = new JButton("Battleship \n(4 Units)");
        JButton destroyer = new JButton("Destroyer \n(3 Units)");
        JButton submarine = new JButton("Submarine \n(3 Units)");
        JButton patrolBoat = new JButton("Patrol Boat \n(2 Units)");

        JButton horizontal = new JButton("Horizontal");
        JButton vertical = new JButton("Vertical");

        JLabel currShipLabel = new JLabel(String.format("Current Ship:   %s", currShip));
        currShipLabel.setForeground(ButtonStyle.PLAYER_SETTING.getPressedColor());
        JLabel currDirection = new JLabel(String.format("Direction:   %s", currShip, isHorizontal? "Horizontal" : "Vertical"));
        currShipLabel.setForeground(ButtonStyle.PLAYER_SETTING.getPressedColor());
        JLabel currPosition = new JLabel(String.format("Current Bow Position:   %s", position));
        currShipLabel.setForeground(ButtonStyle.PLAYER_SETTING.getPressedColor());

        buttonPanel.add(aircraftCarrier);
        buttonPanel.add(battleship);
        buttonPanel.add(destroyer);
        buttonPanel.add(submarine);
        buttonPanel.add(patrolBoat);
        buttonPanel.add(horizontal);
        buttonPanel.add(vertical);
        buttonPanel.add(currShipLabel);
        buttonPanel.add(currDirection);

        aircraftCarrier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currShip = "Aircraft Carrier";
                currShipLabel.setText(String.format("Current Ship: %s", currShip));
            }
        });

        battleship.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currShip = "Battleship";
                currShipLabel.setText(String.format("Current Ship: %s", currShip));
            }
        });

        destroyer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currShip = "Destroyer";
                currShipLabel.setText(String.format("Current Ship: %s", currShip));
            }
        });

        submarine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currShip = "Submarine";
                currShipLabel.setText(String.format("Current Ship: %s", currShip));
            }
        });

        patrolBoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currShip = "Patrol Boat";
                currShipLabel.setText(String.format("Current Ship: %s", currShip));
            }
        });

        horizontal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isHorizontal = true;
                currDirection.setText(String.format("Direction: %s", isHorizontal? "Horizontal" : "Vertical"));
            }
        });

        vertical.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isHorizontal = false;
                currDirection.setText(String.format("Direction: %s", isHorizontal? "Horizontal" : "Vertical"));
            }
        });
        add(buttonPanel);
        buttonPanel.setBackground(Color.white);
        buttonPanel.setOpaque(true);
        buttonPanel.setMaximumSize(new Dimension(200, 360));
//        JPanel playArea = new JPanel();
//        playArea.setLayout(new BoxLayout(playArea, BoxLayout.X_AXIS));
//        playArea.setBackground(null);
//        playArea.setPreferredSize(new Dimension(playAreaSize.getWidth(), 500));
//        playArea.setMaximumSize(playArea.getPreferredSize());
        player1 = new GridButtonBoard(SettingButton.class, ButtonStyle.PLAYER_SETTING, "Setting " + "name waiting");
//        playArea.add(player1);
//        playArea.add(Box.createHorizontalStrut(50));
//        add(playArea);
        add(player1);
        setBackground(Color.white);
        setOpaque(true);
    }

    public void setCurrShip(String currShip) {
        this.currShip = currShip;
    }

    public void setHorizontal(boolean horizontal) {
        isHorizontal = horizontal;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
