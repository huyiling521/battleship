package finalproj.battleship.testutils;

import javax.swing.*;
import java.awt.*;

/**
 * Utility class to help find components by their action command or name.
 */
public class TestUtils {
    /**
     * Recursively searches for a child component within a container by name.
     * @param parent the parent container to search within
     * @param name the name of the child component to find
     * @return the component if found, otherwise null
     */
    public static Component getChildNamed(Component parent, String name) {
        if (name.equals(parent.getName())) {
            return parent;
        }

        if (parent instanceof Container) {
            Component[] children = ((Container)parent).getComponents();
            for (Component child : children) {
                Component result = getChildNamed(child, name);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    public static JButton findButtonByText(Container container, String text) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                if (text.equals(button.getText())) {
                    return button;
                }
            }
            if (comp instanceof Container) {
                JButton button = findButtonByText((Container) comp, text);
                if (button != null) {
                    return button;
                }
            }
        }
        return null;
    }

    public static JLabel findLabelByText(Container container, String text) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JLabel) {
                JLabel label = (JLabel) comp;
                if (label.getText().startsWith(text)) {
                    return label;
                }
            }
            if (comp instanceof Container) {
                JLabel label = findLabelByText((Container) comp, text);
                if (label != null) {
                    return label;
                }
            }
        }
        return null;
    }
}
