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

public class DebugModeCommands {
    private DebugModeCommands() {
        throw new AssertionError("No instantiation for static factory class");
    }

    public static void playerMode(GameEngine ge) throws WrongGameModeException {
        notImplemented();
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
    /**
     * Moves a player directly to any element, bypassing movement rules.
     * @param ge GameEngine instance
     * @param p player to move
     * @param object destination element
     * @throws WrongGameModeException if not in Debug Mode
     * @throws InvalidArgumentException if player or object is null, or destination not occupiable
     * @throws AlreadyOccupiedException if target pipe is already occupied
     */
    public static void teleportPlayer(GameEngine ge, Player p, MapObject object) throws WrongGameModeException, AlreadyOccupiedException, InvalidArgumentException {
        if (ge.getMode() != Mode.DEBUG) {
            throw new WrongGameModeException("Cannot teleport player: not in Debug Mode");
        }
        if (p == null || object == null) {
            throw new InvalidArgumentException("Player or destination cannot be null");
        }
        if (!(object instanceof IOccupiable)) {
            throw new InvalidArgumentException("Destination is not occupiable");
        }

        IOccupiable target = (IOccupiable) object;
        if (object instanceof Pipe && ((Pipe) object).getOccupant() != null) {
            throw new AlreadyOccupiedException();
        }
        p.occupy(target);
    }

    private static void notImplemented() {
        System.out.println(Constants.NOT_IMPLEMENTED_YET);
    }
}
