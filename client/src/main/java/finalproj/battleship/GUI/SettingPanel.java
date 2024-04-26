package finalproj.battleship.GUI;

import finalproj.battleship.GUI.components.SettingButton;
import finalproj.battleship.GUI.components.SettingGridBoard;
import finalproj.battleship.GUI.style.ButtonStyle;
import finalproj.battleship.GUI.style.ComponentSize;
import finalproj.battleship.controller.GUIController;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * The SettingPanel class is a JPanel that provides the user interface
 * for setting up the game, including placing ships and starting the game.
 * It allows the user to select ships, set their orientation, and position them on the grid.
 */
public class SettingPanel extends JPanel{
    private final SettingGridBoard player1;
    private static final ComponentSize playAreaSize = ComponentSize.GAME_PANEL;
    private static final ComponentSize windowSize = ComponentSize.WINDOW;
    private JLabel currPosition;
    private JLabel messageLabel;
    private JButton currShip;
    private boolean isHorizontal;
    private int row = -1;
    private int col = -1;

    /**
     * Constructs a SettingPanel with all necessary UI components
     * to interact with the game setup.
     *
     * @param guiController The GUI controller to manage actions within the panel.
     */
    public SettingPanel(GUIController guiController){
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

        // Initialize buttons with ship names
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

        // Initialize buttons with orientation
        JButton horizontal = new JButton("Horizontal | Bow at west");
        JButton vertical = new JButton("Vertical | Bow at north");

        // Initialize label for current ship setting
        JLabel currShipLabel = new JLabel(String.format("Current Ship:   %s", currShip));
        JLabel currDirectionLabel = new JLabel(String.format("Direction:   %s", isHorizontal? "Horizontal" : "Vertical"));
        currPosition = new JLabel(String.format("Current Bow Position:"));

        // Initialize buttons for game action
        JButton setShip = new JButton("Set The Ship");
        JButton quitGame = new JButton("Quit");
        JButton startGame = new JButton("Start");
        startGame.setVisible(false);

        // Add buttons and labels to panel
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

        // Add action listeners
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
                    player1.checkPrevButtonStatus(null);
                }
            }
        });

        quitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiController.endGame();
                guiController.toWelcomePanel();
            }
        });
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiController.toPlayingPanel();
            }
        });

        //add button panel to the setting panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(buttonPanel, gbc);
        buttonPanel.setBackground(Color.white);
        buttonPanel.setOpaque(true);
        buttonPanel.setMaximumSize(new Dimension(200, 540));

        // Add setting board
        player1 = new SettingGridBoard(ButtonStyle.PLAYER_SETTING, guiController.getName(), guiController);

        gbc.gridx++;
        add(player1, gbc);

        // Add message label
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

    public void checkPrevButtonStatus(SettingButton settingButton) {
        player1.checkPrevButtonStatus(settingButton);
    }
    /**
     * Displays a help message in a dialog box using the main scroll pane of the panel.
     * The dialog provides game instructions and information to the user.
     */
    public void displayHelpMessage() {
        // Set up for helper information
        JTextArea textArea = new JTextArea(10, 25);
        textArea.setText("---------- WELCOME TO ONLINE BATTLESHIP----------\n\n" +
                "- Here are some guidelines for this Online Two Player Version:\n" +
                "   >> Each player places their ships on a grid.\n" +
                "   >> Players take turns guessing the location of the other's ships.\n" +
                "   >> A hit is marked, and a miss is also marked.\n" +
                "   >> The game continues until one player sinks all ships of the other.\n" +
                "   >> The first player to sink all of the opponent's ships wins.\n\n" +
                "- You have 11 ships in total, including:  \n" +
                "   >> 1 aircraft of size 5,\n" +
                "   >> 2 battleship of size 4,\n" +
                "   >> 2 submarines of size 3,\n" +
                "   >> 2 destroyers of size 3,\n" +
                "   >> 4 patrol boats of size 2.\n" +
                "- Each ship must occupy the specified number of squares\n" +
                "  and must be placed on consecutive squares on the grid,\n" +
                "  arranged either horizontally or vertically.\n" +
                "- The ships cannot overlap (i.e., only one ship can occupy any given square in the grid). \n" +
                "- The position you are selecting is the bow of the ship.\n" +
                "- Horizontal ships are facing west(the bow is at the west grid)\n" +
                "- Vertical ships are facing north(the bow is at the north grid)");
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(550, 450));
        scrollPane.setMaximumSize(new Dimension(550, 450));
        JOptionPane.showMessageDialog(this, scrollPane, "Help Information", JOptionPane.INFORMATION_MESSAGE);
    }
}
