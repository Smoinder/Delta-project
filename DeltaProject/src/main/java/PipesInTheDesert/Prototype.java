package PipesInTheDesert;

import java.util.Scanner;

import PipesInTheDesert.Exceptions.GameException;

/**
 * Console entry point for the prototype command loop.
 */
public class Prototype {

    /**
     * Prevents instantiation of this static utility entry point.
     */
    private Prototype() {

        throw new AssertionError("No instantiation for static factory class");
    }

    /**
     * Starts a simple console loop that dispatches a safe subset of commands.
     *
     * @param args ignored command-line arguments
     */
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            boolean shouldExit = handleCommand(gameEngine, line);
            if (shouldExit)
                break;
        }
    }

    /**
     * Parses and executes a single console command.
     *
     * @param gameEngine active game engine instance
     * @param line raw command line
     * @return true when the console loop should terminate
     */
    private static boolean handleCommand(GameEngine gameEngine, String line) {
        String[] parts = line.split("\\s+");
        String command = parts[0].toLowerCase();

        try {
            return switch (command) {
                case "exit" -> commandExit();
                case "startgame" -> commandStartGame(gameEngine, parts);
                case "loadmap" -> commandLoadMap(gameEngine, parts);
                case "debugmode" -> commandDebugMode(gameEngine, parts);
                case "playermode" -> commandPlayerMode(gameEngine, parts);
                case "printstate" -> commandPrintState(gameEngine, parts);
                case "disconnect" -> commandDisconnect(gameEngine, parts);
                case "endturn" -> commandEndTurn(gameEngine, parts);
                case "move" -> commandMove(gameEngine, parts);
                case "fix" -> commandFix(gameEngine, parts);
                case "fixpump" -> commandFixPump(gameEngine, parts);
                case "puncture" -> commandPuncture(gameEngine, parts);
                case "setpumpdirection" -> commandSetPumpDirection(gameEngine, parts);
                case "pickuppump" -> commandPickUpPump(gameEngine, parts);
                case "installpump" -> commandInstallPump(gameEngine, parts);
                case "connect" -> commandConnect(gameEngine, parts);
                case "breakpump" -> commandBreakPump(gameEngine, parts);
                case "setrandom" -> commandSetRandom(gameEngine, parts);
                case "setstamina" -> commandSetStamina(gameEngine, parts);
                case "setscore" -> commandSetScore(gameEngine, parts);
                case "setactiveplayer" -> commandSetActivePlayer(gameEngine, parts);
                case "setpipeleak" -> commandSetPipeLeak(gameEngine, parts);
                case "setrounds" -> commandSetRounds(gameEngine, parts);
                case "addwater" -> commandAddWater(gameEngine, parts);
                case "teleportplayer" -> commandTeleportPlayer(gameEngine, parts);
                default -> {
                    System.out.println("Invalid Command");
                    yield false;
                }
            };
        } catch (GameException e) {
            System.out.println(e.getClass().getSimpleName());
        } catch (IllegalArgumentException e) {
            System.out.println("InvalidArgumentException");
        }

        return false;
    }

    /**
     * Handles the exit command.
     *
     * @return true to terminate the console loop
     */
    private static boolean commandExit() {
        return true;
    }

    /**
     * Handles the startgame command.
     *
     * @param gameEngine active game engine instance
     * @param parts parsed command tokens
     * @return false to continue the console loop
     */
    private static boolean commandStartGame(GameEngine gameEngine, String[] parts) throws GameException {
        requireArgumentCount(parts, 3);
        int plumbers = parseInteger(parts[1]);
        int saboteurs = parseInteger(parts[2]);
        gameEngine.startGame(plumbers, saboteurs);
        System.out.println("StartGame OK");
        return false;
    }

    /**
     * Handles the loadmap command.
     *
     * @param gameEngine active game engine instance
     * @param parts parsed command tokens
     * @return false to continue the console loop
     */
    private static boolean commandLoadMap(GameEngine gameEngine, String[] parts) throws GameException {
        if (parts.length == 1) {
            gameEngine.loadMap();
        } else if (parts.length == 2) {
            gameEngine.loadMap(parseMapType(parts[1]));
        } else {
            throw new IllegalArgumentException("LoadMap expects zero or one argument");
        }
        System.out.println("LoadMap OK");
        return false;
    }

    /**
     * Handles the debugmode command.
     *
     * @param gameEngine active game engine instance
     * @param parts parsed command tokens
     * @return false to continue the console loop
     */
    private static boolean commandDebugMode(GameEngine gameEngine, String[] parts) {
        requireArgumentCount(parts, 1);
        gameEngine.setMode(Mode.DEBUG);
        System.out.println("DebugMode OK");
        return false;
    }

    /**
     * Handles the playermode command.
     *
     * @param gameEngine active game engine instance
     * @param parts parsed command tokens
     * @return false to continue the console loop
     */
    private static boolean commandPlayerMode(GameEngine gameEngine, String[] parts) {
        requireArgumentCount(parts, 1);
        gameEngine.setMode(Mode.PLAYER);
        System.out.println("PlayerMode OK");
        return false;
    }

    /**
     * Handles the printstate command.
     *
     * @return false to continue the console loop
     */
    private static boolean commandPrintState(GameEngine gameEngine, String[] parts) {
        System.out.println(Constants.NOT_IMPLEMENTED_YET);
        return false;
    }

    /**
     * Handles the disconnect command.
     *
     * @return false to continue the console loop
     */
    private static boolean commandDisconnect(GameEngine gameEngine, String[] parts) {
        System.out.println(Constants.NOT_IMPLEMENTED_YET);
        return false;
    }

    /**
     * Handles the endturn command.
     *
     * @return false to continue the console loop
     */
    private static boolean commandEndTurn(GameEngine gameEngine, String[] parts) {
        System.out.println(Constants.NOT_IMPLEMENTED_YET);
        return false;
    }

    /**
     * Handles the move command.
     *
     * @return false to continue the console loop
     */
    private static boolean commandMove(GameEngine gameEngine, String[] parts) {
        System.out.println(Constants.NOT_IMPLEMENTED_YET);
        return false;
    }

    /**
     * Handles the fix command.
     *
     * @return false to continue the console loop
     */
    private static boolean commandFix(GameEngine gameEngine, String[] parts) {
        System.out.println(Constants.NOT_IMPLEMENTED_YET);
        return false;
    }

    /**
     * Handles the fixpump command.
     *
     * @return false to continue the console loop
     */
    private static boolean commandFixPump(GameEngine gameEngine, String[] parts) {
        System.out.println(Constants.NOT_IMPLEMENTED_YET);
        return false;
    }

    /**
     * Handles the puncture command.
     *
     * @return false to continue the console loop
     */
    private static boolean commandPuncture(GameEngine gameEngine, String[] parts) {
        System.out.println(Constants.NOT_IMPLEMENTED_YET);
        return false;
    }

    /**
     * Handles the setpumpdirection command.
     *
     * @return false to continue the console loop
     */
    private static boolean commandSetPumpDirection(GameEngine gameEngine, String[] parts) {
        System.out.println(Constants.NOT_IMPLEMENTED_YET);
        return false;
    }

    /**
     * Handles the pickuppump command.
     *
     * @return false to continue the console loop
     */
    private static boolean commandPickUpPump(GameEngine gameEngine, String[] parts) {
        System.out.println(Constants.NOT_IMPLEMENTED_YET);
        return false;
    }

    /**
     * Handles the installpump command.
     *
     * @return false to continue the console loop
     */
    private static boolean commandInstallPump(GameEngine gameEngine, String[] parts) {
        System.out.println(Constants.NOT_IMPLEMENTED_YET);
        return false;
    }

    /**
     * Handles the connect command.
     *
     * @return false to continue the console loop
     */
    private static boolean commandConnect(GameEngine gameEngine, String[] parts) {
        System.out.println(Constants.NOT_IMPLEMENTED_YET);
        return false;
    }

    /**
     * Handles the breakpump command.
     *
     * @return false to continue the console loop
     */
    private static boolean commandBreakPump(GameEngine gameEngine, String[] parts) {
        System.out.println(Constants.NOT_IMPLEMENTED_YET);
        return false;
    }

    /**
     * Handles the setrandom command.
     *
     * @return false to continue the console loop
     */
    private static boolean commandSetRandom(GameEngine gameEngine, String[] parts) {
        System.out.println(Constants.NOT_IMPLEMENTED_YET);
        return false;
    }

    /**
     * Handles the setstamina command.
     *
     * @return false to continue the console loop
     */
    private static boolean commandSetStamina(GameEngine gameEngine, String[] parts) {
        System.out.println(Constants.NOT_IMPLEMENTED_YET);
        return false;
    }

    /**
     * Handles the setscore command.
     *
     * @return false to continue the console loop
     */
    private static boolean commandSetScore(GameEngine gameEngine, String[] parts) {
        System.out.println(Constants.NOT_IMPLEMENTED_YET);
        return false;
    }

    /**
     * Handles the setactiveplayer command.
     *
     * @return false to continue the console loop
     */
    private static boolean commandSetActivePlayer(GameEngine gameEngine, String[] parts) {
        System.out.println(Constants.NOT_IMPLEMENTED_YET);
        return false;
    }

    /**
     * Handles the setpipeleak command.
     *
     * @return false to continue the console loop
     */
    private static boolean commandSetPipeLeak(GameEngine gameEngine, String[] parts) {
        System.out.println(Constants.NOT_IMPLEMENTED_YET);
        return false;
    }

    /**
     * Handles the setrounds command.
     *
     * @return false to continue the console loop
     */
    private static boolean commandSetRounds(GameEngine gameEngine, String[] parts) {
        System.out.println(Constants.NOT_IMPLEMENTED_YET);
        return false;
    }

    /**
     * Handles the addwater command.
     *
     * @return false to continue the console loop
     */
    private static boolean commandAddWater(GameEngine gameEngine, String[] parts) {
        System.out.println(Constants.NOT_IMPLEMENTED_YET);
        return false;
    }

    /**
     * Handles the teleportplayer command.
     *
     * @return false to continue the console loop
     */
    private static boolean commandTeleportPlayer(GameEngine gameEngine, String[] parts) {
        System.out.println(Constants.NOT_IMPLEMENTED_YET);
        return false;
    }

    /**
     * Ensures a command received the required number of tokens.
     *
     * @param parts parsed command tokens
     * @param expected expected token count
     */
    private static void requireArgumentCount(String[] parts, int expected) {
        if (parts.length != expected) {
            throw new IllegalArgumentException("Unexpected argument count");
        }
    }

    /**
     * Parses an integer command argument.
     *
     * @param token raw numeric token
     * @return parsed integer value
     */
    private static int parseInteger(String token) {
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid integer value", e);
        }
    }

    /**
     * Converts text input to the corresponding map type.
     *
     * @param token textual map name
     * @return parsed map type
     */
    private static MapType parseMapType(String token) {
        try {
            return MapType.valueOf(token.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown map type", e);
        }
    }
}
