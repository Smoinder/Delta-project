package PipesInTheDesert.Commands;

import PipesInTheDesert.GameEngine;
import PipesInTheDesert.MapObject;
import PipesInTheDesert.Elements.Pump;
import PipesInTheDesert.Exceptions.WrongGameModeException;
import PipesInTheDesert.Exceptions.AlreadyOccupiedException;
import PipesInTheDesert.Players.Player;
import PipesInTheDesert.Team;
import PipesInTheDesert.Connectors.Pipe;

public class DebugModeCommands {
    private DebugModeCommands() {
        throw new AssertionError("No instantiation for static factory class");
    }

    public static void playerMode(GameEngine ge) throws WrongGameModeException {

    }

    public static void breakPump(GameEngine ge, Pump p) throws WrongGameModeException {

    }

    public static void setRandom(GameEngine ge, boolean enabled) throws WrongGameModeException {

    }

    public static void setRandom(GameEngine ge) throws WrongGameModeException { // default value
        setRandom(ge, true);
    }

    public static void setStamina(GameEngine ge, Player p, int stamina) throws WrongGameModeException {

    }

    public static void setScore(GameEngine ge, Team t, int score) throws WrongGameModeException {

    }

    public static void setActivePlayer(GameEngine ge, Player player) throws WrongGameModeException {

    }

    public static void setPipeLeak(GameEngine ge, Pipe pipe, boolean leak) throws WrongGameModeException {

    }

    public static void setRounds(GameEngine ge, int value) throws WrongGameModeException {

    }

    public static void addWater(GameEngine ge, int amount) throws WrongGameModeException {

    }

    public static void teleportPlayer(GameEngine ge, Player p, MapObject object) throws WrongGameModeException, AlreadyOccupiedException {

    }
}
