package battleship.frontend;

import battleship.frontend.style.ButtonStyle;
import battleship.frontend.style.ComponentSize;
import battleship.server.controler.GUIController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class SettingPanel extends JPanel implements PanelManage{
    private final GridButtonBoard player1;
    private static final ComponentSize playAreaSize = ComponentSize.GAME_PANEL;
    private static final ComponentSize windowSize = ComponentSize.WINDOW;
    private JLabel currPosition;
    private JLabel messageLabel;
    private JButton currShip;
    private boolean isHorizontal;
    private HashMap<String, Integer> ships;
    private int row = -1;
    private int col = -1;

    public SettingPanel(GUIController guiController) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        super();
        currShip = null;
        isHorizontal = true;
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0,40,0,40);
        setPreferredSize(new Dimension(windowSize.getWidth(), windowSize.getHeight()));

        JPanel buttonPanel = new JPanel(new GridLayout(14, 1, 1, 1));
        JButton aircraftCarrier = new JButton("Aircraft Carrier \n(5 Units)");
        aircraftCarrier.setActionCommand("aircraftCarrier,5");
        JButton battleship = new JButton("Battleship \n(4 Units)");
        battleship.setActionCommand("battleship,4");
        JButton destroyer = new JButton("Destroyer \n(3 Units)");
        destroyer.setActionCommand("destroyer,3");
        JButton submarine = new JButton("Submarine \n(3 Units)");
        submarine.setActionCommand("submarine,3");
        JButton patrolBoat = new JButton("Patrol Boat \n(2 Units)");
        patrolBoat.setActionCommand("patrolBoat,2");

        JButton horizontal = new JButton("Horizontal | Bow at west");
        JButton vertical = new JButton("Vertical | Bow at north");

        JLabel currShipLabel = new JLabel(String.format("Current Ship:   %s", currShip));
        JLabel currDirectionLabel = new JLabel(String.format("Direction:   %s", isHorizontal? "Horizontal" : "Vertical"));
        currPosition = new JLabel(String.format("Current Bow Position:"));

        JButton setShip = new JButton("Set The Ship");
        JButton quitGame = new JButton("Quit");
//        setShip.set(ButtonStyle.PLAYER_SETTING.getHoverColor());
        JButton startGame = new JButton("Start");
        startGame.setVisible(false);
//        startGame.setForeground(ButtonStyle.PLAYER_SETTING.getHoverColor());
//        startGame.setOpaque(true);

        buttonPanel.add(aircraftCarrier);
        buttonPanel.add(battleship);
        buttonPanel.add(destroyer);
        buttonPanel.add(submarine);
        buttonPanel.add(patrolBoat);
        buttonPanel.add(horizontal);
        buttonPanel.add(vertical);
        buttonPanel.add(currShipLabel);
        buttonPanel.add(currDirectionLabel);
        buttonPanel.add(currPosition);
        buttonPanel.add(new Box.Filler(new Dimension(0,20), new Dimension(0,20), new Dimension(0,20)));
        buttonPanel.add(setShip);
        buttonPanel.add(quitGame);
        buttonPanel.add(startGame);

        aircraftCarrier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currShip = aircraftCarrier;
                currShipLabel.setText(String.format("Current Ship: %s", "Aircraft Carrier"));
            }
        });

        battleship.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currShip = battleship;
                currShipLabel.setText(String.format("Current Ship: %s", "Battleship"));
            }
        });

        destroyer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currShip = destroyer;
                currShipLabel.setText(String.format("Current Ship: %s", "Destroyer"));
            }
        });

        submarine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currShip = submarine;
                currShipLabel.setText(String.format("Current Ship: %s", "Submarine"));
            }
        });

        patrolBoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currShip = patrolBoat;
                currShipLabel.setText(String.format("Current Ship: %s", "Patrol Boat"));
            }
        });

        horizontal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isHorizontal = true;
                currDirectionLabel.setText(String.format("Direction: %s", isHorizontal? "Horizontal" : "Vertical"));
            }
        });

        vertical.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isHorizontal = false;
                currDirectionLabel.setText(String.format("Direction: %s", isHorizontal? "Horizontal" : "Vertical"));
            }
        });

        setShip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (currShip == null
                            || currDirectionLabel.getText().length() <= 13
                            || row == -1 || col == -1) throw new IllegalArgumentException("Ship setting is invalid!");
                    int remain = guiController.setShip(row, col, isHorizontal, currShip.getActionCommand().split(",")[0]);
                    if (remain == 0) currShip.setEnabled(false);
                    if (!aircraftCarrier.isEnabled() && !battleship.isEnabled() && !destroyer.isEnabled()
                    && !submarine.isEnabled() && !patrolBoat.isEnabled()) {
                        startGame.setVisible(true);
                        setShip.setEnabled(false);
                    }
                    player1.setArrangedButtons(row, col, isHorizontal, Integer.parseInt(currShip.getActionCommand().split(",")[1]));
                } catch (Exception exception) {
                    messageLabel.setText(exception.getMessage());
                }
            }
        });

        quitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiController.toWelcomePanel();
            }
        });
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiController.startGame();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(buttonPanel, gbc);
        buttonPanel.setBackground(Color.white);
        buttonPanel.setOpaque(true);
        buttonPanel.setMaximumSize(new Dimension(200, 540));
        player1 = new GridButtonBoard(SettingButton.class, ButtonStyle.PLAYER_SETTING, "Setting " + "name waiting", guiController);

        gbc.gridx++;
        add(player1, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        messageLabel = new JLabel("");
        messageLabel.setForeground(Color.red);
        add(messageLabel, gbc);
        setBackground(Color.white);
        setOpaque(true);
    }

    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
        currPosition.setText("Current Bow Position:   " + (char)(row - 1 + 'A') + ", " + col);
    }

    @Override
    public void setMessageLabel(String message) {
    }

    public void panelClear(GUIController guiController) {
    }
}
