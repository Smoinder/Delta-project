package PipesInTheDesert.Commands;

import PipesInTheDesert.Constants;
import PipesInTheDesert.GameEngine;
import PipesInTheDesert.MapObject;
import PipesInTheDesert.Elements.Pump;
import PipesInTheDesert.Mode;
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
        ge.setMode(Mode.PLAYER);
        System.out.println("PlayerMode OK");
    }

    public static void breakPump(GameEngine ge, Pump p) throws WrongGameModeException {
        notImplemented();
    }

    public static void setRandom(GameEngine ge, boolean enabled) throws WrongGameModeException {
        notImplemented();
    }

    public static void setRandom(GameEngine ge) throws WrongGameModeException { // default value
        setRandom(ge, true);
    }

    public static void setStamina(GameEngine ge, Player p, int stamina) throws WrongGameModeException {
        notImplemented();
    }

    public static void setScore(GameEngine ge, Team t, int score) throws WrongGameModeException {
        notImplemented();
    }

    public static void setActivePlayer(GameEngine ge, Player player) throws WrongGameModeException {
        notImplemented();
    }

    public static void setPipeLeak(GameEngine ge, Pipe pipe, boolean leak) throws WrongGameModeException {
        notImplemented();
    }

    public static void setRounds(GameEngine ge, int value) throws WrongGameModeException {
        notImplemented();
    }

    public static void addWater(GameEngine ge, int amount) throws WrongGameModeException {
        notImplemented();
    }

    public static void teleportPlayer(GameEngine ge, Player p, MapObject object) throws WrongGameModeException, AlreadyOccupiedException {
        notImplemented();
    }

    private static void notImplemented() {
        System.out.println(Constants.NOT_IMPLEMENTED_YET);
    }
}
