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
import PipesInTheDesert.Interfaces.IOccupiable;

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
    /**
     * Changes the active player directly, overriding turn order.
     * @param ge GameEngine instance
     * @param player player to set as active
     * @throws WrongGameModeException if not in Debug Mode
     * @throws InvalidArgumentException if player is null
     */
    public static void setActivePlayer(GameEngine ge, Player player) throws WrongGameModeException, InvalidArgumentException {
        if (ge.getMode() != Mode.DEBUG) {
            throw new WrongGameModeException("Cannot set active player: not in Debug Mode");
        }
        if (player == null) {
            throw new InvalidArgumentException("Player cannot be null");
        }
        ge.setActivePlayer(player);
    }
    /**
     * Directly sets the leaking state of a pipe.
     * @param ge GameEngine instance
     * @param pipe pipe to modify
     * @param leak true to set leaking, false otherwise
     * @throws WrongGameModeException if not in Debug Mode
     * @throws InvalidArgumentException if pipe is null
     */
    public static void setPipeLeak(GameEngine ge, Pipe pipe, boolean leak) throws WrongGameModeException, InvalidArgumentException {
        if (ge.getMode() != Mode.DEBUG) {
            throw new WrongGameModeException("Cannot set pipe leak: not in Debug Mode");
        }
        if (pipe == null) {
            throw new InvalidArgumentException("Pipe cannot be null");
        }
        pipe.setLeaking(leak);
    }
    /**
     * Sets the total number of rounds for the game.
     * @param ge GameEngine instance
     * @param value number of rounds (must be >= 1)
     * @throws WrongGameModeException if not in Debug Mode
     * @throws InvalidArgumentException if value <= 0
     */
    public static void setRounds(GameEngine ge, int value) throws WrongGameModeException, InvalidArgumentException {
        if (ge.getMode() != Mode.DEBUG) {
            throw new WrongGameModeException("Cannot set rounds: not in Debug Mode");
        }
        if (value <= 0) {
            throw new InvalidArgumentException("Round count must be positive");
        }
        ge.turnNumber = value;
    }

    public static void addWater(GameEngine ge, int amount) throws WrongGameModeException {
        notImplemented();
    }
    public static void teleportPlayer(GameEngine ge, Player p, MapObject object)
            throws WrongGameModeException, AlreadyOccupiedException, InvalidArgumentException {
        if (ge.getMode() != Mode.DEBUG) {
            throw new WrongGameModeException("Cannot teleport player: not in Debug Mode");
        }
        if (p == null || object == null) {
            throw new InvalidArgumentException("Player or destination cannot be null");
        }
        if (!(object instanceof IOccupiable)) {
            throw new InvalidArgumentException("Destination is not occupiable");
        }

        if (object instanceof Pipe) {
            Pipe pipe = (Pipe) object;
            if (pipe.getOccupant() != null) {
                throw new AlreadyOccupiedException();
            }
        }

        p.occupy((IOccupiable) object);
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