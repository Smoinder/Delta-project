package PipesInTheDesert.GUI.View;

import PipesInTheDesert.GUI.Controller.Action;
import PipesInTheDesert.GUI.Controller.GameController;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.util.List;

public class ActionPanel extends JPanel {
    private JPanel buttonPanel;
    private List<JButton> actionButtons;
    private GameController controller;

    public ActionPanel() {
        // Empty skeleton constructor.
    }

    public void updateActions(List<Action> actions) {
    }

    public void clearActions() {
    }
}

