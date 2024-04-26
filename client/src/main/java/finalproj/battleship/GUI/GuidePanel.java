package finalproj.battleship.GUI;

import finalproj.battleship.controller.GUIController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuidePanel extends JPanel {

    public GuidePanel(GUIController guiController) {
        super(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(20, 40, 20, 40);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JLabel title = new JLabel("Guidance for the Battleship Game");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, gbc);

        gbc.gridy++;
        gbc.gridwidth = 2;
        JLabel subtitle1 = new JLabel("General Rules");
        subtitle1.setFont(new Font("Arial", Font.BOLD, 18));
        add(subtitle1, gbc);

        gbc.gridy++;
        JTextArea rulesText = new JTextArea();
        rulesText.setText("- There are 2 versions of Battleship game:\n" +
                "   >> Single Player Version allows you to play with a computer\n" +
                "   >> Two Player Version allows you to play with another player online\n\n" +
                "- Their rules are slightly different, please refer to their pages for detail.\n" +
                "- Here are some common rules:\n" +
                "   >> 11 ships in total, including:  \n" +
                "           1 aircraft of size 5,\n" +
                "           2 battleship of size 4,\n" +
                "           2 submarines of size 3,\n" +
                "           2 destroyers of size 3,\n" +
                "           4 patrol boats of size 2.\n" +
                "   >> A hit is marked, and a miss is also marked.\n" +
                "   >> The game continues until one player sinks all ships.\n" +
                "   >> Each ship must occupy the specified number of squares\n" +
                "         and must be placed on consecutive squares on the grid,\n" +
                "         arranged either horizontally or vertically.\n" +
                "   >> The ships cannot overlap.\n" +
                "          (i.e., only one ship can occupy any given square in the grid). \n");
        rulesText.setFont(new Font("Arial", Font.PLAIN, 14));
        rulesText.setEditable(false);
        rulesText.setLineWrap(true);
        rulesText.setWrapStyleWord(true);
        rulesText.setPreferredSize(new Dimension(450, 330));
        rulesText.setMaximumSize(rulesText.getPreferredSize());
        rulesText.setBackground(Color.WHITE);
        add(rulesText, gbc);

//        gbc.gridy++;
//        JTextArea rulesText = new JTextArea();
//        rulesText.setText("There are 2 versions of Battleship game: Single Player Version allows you to play with" +
//                "- Each player places their ships on a grid.\n" +
//                "- Players take turns guessing the location of the other's ships.\n" +
//                "- A hit is marked, and a miss is also marked.\n" +
//                "- The game continues until one player sinks all ships of the other.\n" +
//                "- The first player to sink all of the opponent's ships wins.");
//        rulesText.setFont(new Font("Arial", Font.PLAIN, 14));
//        rulesText.setEditable(false);
//        rulesText.setLineWrap(true);
//        rulesText.setWrapStyleWord(true);
//        rulesText.setPreferredSize(new Dimension(250, 200));
//        rulesText.setMaximumSize(rulesText.getPreferredSize());
//        rulesText.setBackground(Color.WHITE);
//
//        add(rulesText, gbc);
//
//        gbc.gridx++;
//        gbc.gridy = 1;
//        JLabel subtitle2 = new JLabel("Setting Ships");
//        subtitle2.setFont(new Font("Arial", Font.BOLD, 18));
//        add(subtitle2, gbc);
//
//        gbc.gridy++;
//        JTextArea rulesText2 = new JTextArea();
//        rulesText2.setText("- You have 11 ships in total, including:  \n" +
//                "   1 aircraft of size 5,\n" +
//                "   2 battleship of size 4,\n" +
//                "   2 submarines of size 3,\n" +
//                "   2 destroyers of size 3,\n" +
//                "   4 patrol boats of size 2.\n" +
//                "- Each ship must occupy the specified number of squares\n" +
//                "  and must be placed on consecutive squares on the grid,\n" +
//                "  arranged either horizontally or vertically.\n" +
//                "- The ships cannot overlap (i.e., only one ship can occupy any given square in the grid). \n" +
//                "- The position you are selecting is the bow of the ship.\n" +
//                "- Horizontal ships are facing west(the bow is at the west grid)\n" +
//                "- Vertical ships are facing north(the bow is at the north grid)");
//
//        rulesText2.setFont(new Font("Arial", Font.PLAIN, 14));
//        rulesText2.setEditable(false);
//        rulesText2.setLineWrap(true);
//        rulesText2.setWrapStyleWord(true);
//        rulesText2.setPreferredSize(new Dimension(250, 200));
//        rulesText2.setMaximumSize(rulesText.getPreferredSize());
//        rulesText2.setBackground(Color.WHITE);
//
//        add(rulesText2, gbc);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiController.toWelcomePanel();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.setBackground(Color.white);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        setBackground(Color.WHITE);

    }
}

