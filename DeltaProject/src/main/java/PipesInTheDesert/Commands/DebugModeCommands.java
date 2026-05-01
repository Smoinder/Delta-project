package PipesInTheDesert.Commands;

import PipesInTheDesert.Constants;
import PipesInTheDesert.GameEngine;
import PipesInTheDesert.MapObject;
import PipesInTheDesert.Elements.Pump;
import PipesInTheDesert.Mode;
import PipesInTheDesert.Exceptions.WrongGameModeException;
import PipesInTheDesert.Exceptions.AlreadyOccupiedException;
import PipesInTheDesert.Exceptions.InvalidArgumentException;
import PipesInTheDesert.Players.Player;
import PipesInTheDesert.Team;
import PipesInTheDesert.Connectors.Pipe;

/**
 * Static command handlers that are only available in Debug Mode.
 * These methods directly modify game state for deterministic testing.
 */
public class DebugModeCommands {
    private DebugModeCommands() {
        throw new AssertionError("No instantiation for static factory class");
    }

    /**
     * Switches the game from Debug Mode back to Player Mode.
     *
     * @param ge active game engine
     * @throws WrongGameModeException if the game is not currently in Debug Mode
     */
    public static void playerMode(GameEngine ge) throws WrongGameModeException {
        requireDebugMode(ge);
        ge.setMode(Mode.PLAYER);
    }

    /**
     * Breaks a pump manually for testing random pump failure.
     *
     * @param ge active game engine
     * @param p pump to break
     * @throws WrongGameModeException if the game is not in Debug Mode
     */
    public static void breakPump(GameEngine ge, Pump p) throws WrongGameModeException, InvalidArgumentException {
        requireDebugMode(ge);

        if (p == null) {
            throw new InvalidArgumentException("Pump cannot be null");
        }

        if (!p.isHealthy) {
            throw new InvalidArgumentException("Pump is already broken");
        }

        p.goOutOfOrder();
    }

    /**
     * Enables or disables random behavior in the game.
     *
     * @param ge active game engine
     * @param enabled true to enable random events, false to disable them
     * @throws WrongGameModeException if the game is not in Debug Mode
     */
    public static void setRandom(GameEngine ge, boolean enabled) throws WrongGameModeException {
        requireDebugMode(ge);
        ge.setRandomEnabled(enabled);
    }

    /**
     * Enables random behavior by default.
     *
     * @param ge active game engine
     * @throws WrongGameModeException if the game is not in Debug Mode
     */
    public static void setRandom(GameEngine ge) throws WrongGameModeException {
        setRandom(ge, true);
    }

    /**
     * Sets a player's stamina directly.
     *
     * @param ge active game engine
     * @param p target player
     * @param stamina new stamina value
     * @throws WrongGameModeException if the game is not in Debug Mode
     */
    public static void setStamina(GameEngine ge, Player p, int stamina) throws WrongGameModeException, InvalidArgumentException {
        requireDebugMode(ge);

        if (p == null || stamina < 0) {
            throw new InvalidArgumentException("Invalid stamina argument");
        }

        p.setStamina(stamina);
    }

    /**
     * Sets the score of one team directly.
     *
     * @param ge active game engine
     * @param t target team
     * @param score new score value
     * @throws WrongGameModeException if the game is not in Debug Mode
     */
    public static void setScore(GameEngine ge, Team t, int score) throws WrongGameModeException, InvalidArgumentException {
        requireDebugMode(ge);

        if (t == null || score < 0) {
            throw new InvalidArgumentException("Invalid score argument");
        }

        switch (t) {
            case PLUMBERS -> ge.setPlumbersScore(score);
            case SABOTEURS -> ge.setSaboteursScore(score);
            default -> throw new InvalidArgumentException("Invalid team");
        }
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

    public static void teleportPlayer(GameEngine ge, Player p, MapObject object)
            throws WrongGameModeException, AlreadyOccupiedException {
        notImplemented();
    }

    /**
     * Validates that the current game mode is Debug Mode.
     *
     * @param ge active game engine
     * @throws WrongGameModeException if current mode is not Debug
     */
    private static void requireDebugMode(GameEngine ge) throws WrongGameModeException {
        if (ge == null || ge.getMode() != Mode.DEBUG) {
            throw new WrongGameModeException("Command is only available in Debug Mode");
        }
    }

    private static void notImplemented() {
        System.out.println(Constants.NOT_IMPLEMENTED_YET);
    }
}