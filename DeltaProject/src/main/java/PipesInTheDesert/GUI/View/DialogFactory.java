package PipesInTheDesert.GUI.View;

import PipesInTheDesert.MapType;
import PipesInTheDesert.Team;
import PipesInTheDesert.GUI.Controller.StartGameConfig;

import javax.swing.JFrame;

public final class DialogFactory {
    private DialogFactory() {
    }

    public static StartGameConfig showStartGameDialog(JFrame parent) {
        return null;
    }

    public static MapType showLoadMapDialog(JFrame parent) {
        return null;
    }

    public static void showGameOverDialog(JFrame parent, Team winner) {
    }

    public static String showDebugValueDialog(JFrame parent, String prompt) {
        return null;
    }
}

