package PipesInTheDesert.GUI.Controller;

import PipesInTheDesert.Elements.MapObject;
import PipesInTheDesert.GameEngine;
import PipesInTheDesert.Players.Player;

import java.util.Collections;
import java.util.List;

public final class ActionResolver {
    private ActionResolver() {
    }

    public static List<Action> getAvailableActions(Player player, GameEngine engine) {
        return Collections.emptyList();
    }

    public static List<MapObject> getReachableTargets(Player player, GameEngine engine) {
        return Collections.emptyList();
    }

    public static boolean isActionValid(ActionType action, Player player, MapObject target, GameEngine engine) {
        return false;
    }
}

