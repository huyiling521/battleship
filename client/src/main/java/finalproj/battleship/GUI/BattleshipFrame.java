package finalproj.battleship.GUI;

import finalproj.battleship.GUI.style.ComponentSize;
import finalproj.battleship.controller.GUIController;
import javax.swing.*;
import java.awt.*;

/**
 * The BattleshipFrame class extends JFrame to create the main application window
 * for the Battleship game. It configures the window's properties such as size, title, and behavior
 * upon closure, and initializes the game's GUI components based on the GUIController.
 *
 * This frame is designed to display the various game panels managed by the GUIController,
 * providing a user interface for the Battleship game.
 */

public class BattleshipFrame extends JFrame {
    private static final ComponentSize frameSize = ComponentSize.WINDOW;
    private GUIController guiController;

    /**
     * Constructs a new BattleshipFrame} that sets up the main window of the Battleship game.
     * This constructor initializes the frame with a specific size, sets it to be non-resizable,
     * and establishes the window title. It also sets the default close operation to exit the
     * application when the window is closed.
     *
     * @param guiController the GUI controller that manages the game's panels and interaction logic.
     */
    public BattleshipFrame(GUIController guiController) {
        super();

        this.guiController = guiController;

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
