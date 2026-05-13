package PipesInTheDesert.GUI.View;

import PipesInTheDesert.Mode;
import PipesInTheDesert.Players.Player;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel {
    private JLabel plumbersScoreLabel;
    private JLabel saboteursScoreLabel;
    private JLabel turnLabel;
    private JLabel activePlayerLabel;
    private JLabel modeLabel;

    public ScorePanel() {
        // Empty skeleton constructor.
    }

    public void updateScores(int plumbers, int saboteurs) {
    }

    public void updateTurn(int turn, Player activePlayer) {
    }

    public void updateMode(Mode mode) {
    }
}

