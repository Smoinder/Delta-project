package PipesInTheDesert.GUI.Controller;

import PipesInTheDesert.Elements.MapObject;
import PipesInTheDesert.GameEngine;
import PipesInTheDesert.MapType;
import PipesInTheDesert.GUI.View.GameWindow;
import PipesInTheDesert.GUI.View.MapPanel;

public class GameController {
    private GameEngine engine;
    private GameWindow window;
    private MapPanel mapPanel;
    private ActionMode actionMode;
    private ActionType pendingActionType;

    public GameController() {
        // Empty skeleton constructor.
    }

    public void initialize() {
    }

    public void handleMapClick(int x, int y) {
    }

    public void handleMapRightClick(int x, int y) {
    }

    public void handleMapHover(int x, int y) {
    }

    public void handleActionButton(ActionType action) {
    }

    public void handleTargetClick(MapObject target) {
    }

    public void loadMap(MapType mapType) {
    }

    public void startGame(int plumbers, int saboteurs) {
    }

    public void endTurn() {
    }

    public void toggleDebugMode() {
    }

    public void resetToActiveTurn() {
    }

    private MapObject findElementAt(int x, int y) {
        return null;
    }

    private void createViewsForMap() {
    }

    private void createPlayerViews() {
    }

    private void resolveReachableTargets() {
    }

    private void executeCommand(ActionType action, MapObject target) {
    }
}

