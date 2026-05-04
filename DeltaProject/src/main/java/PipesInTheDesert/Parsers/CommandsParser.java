package PipesInTheDesert.Parsers;

import PipesInTheDesert.Commands.DebugModeCommands;
import PipesInTheDesert.Commands.PlayerModeCommands;
import PipesInTheDesert.Constants;
import PipesInTheDesert.Exceptions.GameException;
import PipesInTheDesert.GameEngine;
import PipesInTheDesert.MapType;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Parses console commands and dispatches them to the command package.
 */
public final class CommandsParser {
    private CommandsParser() {
        throw new AssertionError("No instantiation for static factory class");
    }

    /**
     * Parses and executes a single console command in interactive mode.
     * Output is prefixed with [OUTPUT] and sent to System.out.
     *
     * @param gameEngine active game engine instance
     * @param line       raw command line
     * @return true when the console loop should terminate
     */
    public static boolean handleCommand(GameEngine gameEngine, String line) {
        return handleCommand(gameEngine, line, System.out, Constants.OUTPUT_PREFIX);
    }

    /**
     * Parses and executes a single console command with output routing.
     * Captures all System.out output, prefixes it, and sends to the provided
     * stream.
     *
     * @param gameEngine   active game engine instance
     * @param line         raw command line
     * @param outputStream destination for command output
     * @param outputPrefix prefix to prepend to each line (e.g., "[OUTPUT] " or "")
     * @return true when the console loop should terminate
     */
    public static boolean handleCommand(GameEngine gameEngine, String line, PrintStream outputStream,
            String outputPrefix) {
        String[] parts = line.trim().split("\\s+");
        String command = parts[0].toLowerCase();

        PrintStream originalOut = System.out;
        ByteArrayOutputStream captureBuffer = new ByteArrayOutputStream();
        PrintStream captureStream = new PrintStream(captureBuffer);
        boolean shouldExit = false;

        try {
            System.setOut(captureStream);

            shouldExit = switch (command) {
                case "exit" -> true;
                case "startgame" -> handleStartGame(gameEngine, parts);
                case "loadmap" -> handleLoadMap(gameEngine, parts);
                case "debugmode" -> handleDebugMode(gameEngine, parts);
                case "playermode" -> handlePlayerMode(gameEngine, parts);
                case "printstate" -> handlePrintState(gameEngine, parts);
                case "disconnect" -> handleDisconnect(gameEngine, parts);
                case "endturn" -> handleEndTurn(gameEngine, parts);
                case "move" -> handleMove(gameEngine, parts);
                case "fix" -> handleFix(gameEngine, parts);
                case "fixpump" -> handleFixPump(gameEngine, parts);
                case "puncture" -> handlePuncture(gameEngine, parts);
                case "setpumpdirection" -> handleSetPumpDirection(gameEngine, parts);
                case "pickuppump" -> handlePickUpPump(gameEngine, parts);
                case "installpump" -> handleInstallPump(gameEngine, parts);
                case "pickuppipe" -> handlePickUpPipe(gameEngine, parts);
                case "installpipe" -> handleInstallPipe(gameEngine, parts);
                case "connect" -> handleConnect(gameEngine, parts);
                case "breakpump" -> handleBreakPump(gameEngine, parts);
                case "setrandom" -> handleSetRandom(gameEngine, parts);
                case "setstamina" -> handleSetStamina(gameEngine, parts);
                case "setscore" -> handleSetScore(gameEngine, parts);
                case "setactiveplayer" -> handleSetActivePlayer(gameEngine, parts);
                case "setpipeleak" -> handleSetPipeLeak(gameEngine, parts);
                case "setrounds" -> handleSetRounds(gameEngine, parts);
                case "addwater" -> handleAddWater(gameEngine, parts);
                case "teleportplayer" -> handleTeleportPlayer(gameEngine, parts);
                default -> {
                    System.out.println("Invalid Command");
                    yield false;
                }
            };
        } catch (GameException e) {
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("InvalidArgumentException");
        } finally {
            System.out.flush();
            System.setOut(originalOut);
            captureStream.close();
            emitOutput(outputStream, captureBuffer.toString(), outputPrefix);
        }

        return shouldExit;
    }

    /**
     * Emits output to the provided stream with optional prefix.
     * Each non-empty line gets the prefix prepended.
     *
     * @param outputStream destination stream
     * @param content      text content to emit
     * @param prefix       prefix for each line (e.g., "[OUTPUT] " or "")
     */
    private static void emitOutput(PrintStream outputStream, String content, String prefix) {
        String[] lines = content.split("\n");
        for (String line : lines) {
            // Remove trailing \r for Windows compatibility because byte stream has \r\n
            line = line.replaceAll("\r$", "");
            if (!line.isEmpty())
                outputStream.println(prefix + line);
        }
    }

    private static boolean handleStartGame(GameEngine gameEngine, String[] parts) throws GameException {
        ArgumentsParser.requireArgumentCount(parts, 3);
        PlayerModeCommands.startGame(gameEngine,
                ArgumentsParser.parseInteger(parts[1]),
                ArgumentsParser.parseInteger(parts[2]));
        return false;
    }

    private static boolean handleLoadMap(GameEngine gameEngine, String[] parts) throws GameException {
        if (parts.length == 1) {
            PlayerModeCommands.loadMap(gameEngine, MapType.DEFAULT);
        } else if (parts.length == 2) {
            PlayerModeCommands.loadMap(gameEngine, ArgumentsParser.parseMapType(parts[1]));
        } else {
            throw new IllegalArgumentException("LoadMap expects zero or one argument");
        }
        return false;
    }

    private static boolean handleDebugMode(GameEngine gameEngine, String[] parts) throws GameException {
        ArgumentsParser.requireArgumentCount(parts, 1);
        PlayerModeCommands.debugMode(gameEngine);
        return false;
    }

    private static boolean handlePlayerMode(GameEngine gameEngine, String[] parts) throws GameException {
        ArgumentsParser.requireArgumentCount(parts, 1);
        DebugModeCommands.playerMode(gameEngine);
        return false;
    }

    private static boolean handlePrintState(GameEngine gameEngine, String[] parts) throws GameException {
        ArgumentsParser.requireArgumentCount(parts, 1);
        PlayerModeCommands.printState(gameEngine);
        return false;
    }

    private static boolean handleDisconnect(GameEngine gameEngine, String[] parts) throws GameException {
        ArgumentsParser.requireArgumentCount(parts, 4);
        PlayerModeCommands.disconnect(gameEngine,
                ArgumentsParser.parsePlumber(gameEngine, parts[1]),
                ArgumentsParser.parsePipe(gameEngine, parts[2]),
                ArgumentsParser.parseIConnectable(gameEngine, parts[3]));
        return false;
    }

    private static boolean handleEndTurn(GameEngine gameEngine, String[] parts) throws GameException {
        ArgumentsParser.requireArgumentCount(parts, 1);
        PlayerModeCommands.endTurn(gameEngine);
        return false;
    }

    private static boolean handleMove(GameEngine gameEngine, String[] parts) throws GameException {
        ArgumentsParser.requireArgumentCount(parts, 2);
        PlayerModeCommands.move(gameEngine, ArgumentsParser.parseIOccupiable(gameEngine, parts[1]));
        return false;
    }

    private static boolean handleFix(GameEngine gameEngine, String[] parts) throws GameException {
        ArgumentsParser.requireArgumentCount(parts, 2);
        PlayerModeCommands.fix(gameEngine, ArgumentsParser.parsePipe(gameEngine, parts[1]));
        return false;
    }

    private static boolean handleFixPump(GameEngine gameEngine, String[] parts) throws GameException {
        ArgumentsParser.requireArgumentCount(parts, 2);
        PlayerModeCommands.fixPump(gameEngine, ArgumentsParser.parsePump(gameEngine, parts[1]));
        return false;
    }

    private static boolean handlePuncture(GameEngine gameEngine, String[] parts) throws GameException {
        ArgumentsParser.requireArgumentCount(parts, 2);
        PlayerModeCommands.puncture(gameEngine, ArgumentsParser.parsePipe(gameEngine, parts[1]));
        return false;
    }

    private static boolean handleSetPumpDirection(GameEngine gameEngine, String[] parts) throws GameException {
        ArgumentsParser.requireArgumentCount(parts, 4);
        PlayerModeCommands.setPumpDirection(gameEngine,
                ArgumentsParser.parsePump(gameEngine, parts[1]),
                ArgumentsParser.parsePipe(gameEngine, parts[2]),
                ArgumentsParser.parsePipe(gameEngine, parts[3]));
        return false;
    }

    private static boolean handlePickUpPump(GameEngine gameEngine, String[] parts) throws GameException {
        ArgumentsParser.requireArgumentCount(parts, 1);
        PlayerModeCommands.pickUpPump(gameEngine);
        return false;
    }

    private static boolean handleInstallPump(GameEngine gameEngine, String[] parts) throws GameException {
        ArgumentsParser.requireArgumentCount(parts, 2);
        PlayerModeCommands.installPump(gameEngine, ArgumentsParser.parsePipe(gameEngine, parts[1]));
        return false;
    }

    private static boolean handlePickUpPipe(GameEngine gameEngine, String[] parts) throws GameException {
        ArgumentsParser.requireArgumentCount(parts, 1);
        PlayerModeCommands.pickUpPipe(gameEngine);
        return false;
    }

    private static boolean handleInstallPipe(GameEngine gameEngine, String[] parts) throws GameException {
        ArgumentsParser.requireArgumentCount(parts, 2);
        PlayerModeCommands.installPipe(gameEngine,
                ArgumentsParser.parsePump(gameEngine, parts[1]));
        return false;
    }

    private static boolean handleConnect(GameEngine gameEngine, String[] parts) throws GameException {
        ArgumentsParser.requireArgumentCount(parts, 3);
        PlayerModeCommands.connect(gameEngine,
                ArgumentsParser.parsePipe(gameEngine, parts[1]),
                ArgumentsParser.parseIConnectable(gameEngine, parts[2]));
        return false;
    }

    private static boolean handleBreakPump(GameEngine gameEngine, String[] parts) throws GameException {
        ArgumentsParser.requireArgumentCount(parts, 2);
        DebugModeCommands.breakPump(gameEngine, ArgumentsParser.parsePump(gameEngine, parts[1]));
        return false;
    }

    private static boolean handleSetRandom(GameEngine gameEngine, String[] parts) throws GameException {
        if (parts.length == 1) {
            DebugModeCommands.setRandom(gameEngine);
        } else if (parts.length == 2) {
            DebugModeCommands.setRandom(gameEngine, ArgumentsParser.parseBoolean(parts[1]));
        } else {
            throw new IllegalArgumentException("SetRandom expects zero or one argument");
        }
        return false;
    }

    private static boolean handleSetStamina(GameEngine gameEngine, String[] parts) throws GameException {
        ArgumentsParser.requireArgumentCount(parts, 3);
        DebugModeCommands.setStamina(gameEngine,
                ArgumentsParser.parsePlayer(gameEngine, parts[1]),
                ArgumentsParser.parseInteger(parts[2]));
        return false;
    }

    private static boolean handleSetScore(GameEngine gameEngine, String[] parts) throws GameException {
        ArgumentsParser.requireArgumentCount(parts, 3);
        DebugModeCommands.setScore(gameEngine,
                ArgumentsParser.parseTeam(parts[1]),
                ArgumentsParser.parseInteger(parts[2]));
        return false;
    }

    private static boolean handleSetActivePlayer(GameEngine gameEngine, String[] parts) throws GameException {
        ArgumentsParser.requireArgumentCount(parts, 2);
        DebugModeCommands.setActivePlayer(gameEngine, ArgumentsParser.parsePlayer(gameEngine, parts[1]));
        return false;
    }

    private static boolean handleSetPipeLeak(GameEngine gameEngine, String[] parts) throws GameException {
        ArgumentsParser.requireArgumentCount(parts, 3);
        DebugModeCommands.setPipeLeak(gameEngine,
                ArgumentsParser.parsePipe(gameEngine, parts[1]),
                ArgumentsParser.parseBoolean(parts[2]));
        return false;
    }

    private static boolean handleSetRounds(GameEngine gameEngine, String[] parts) throws GameException {
        ArgumentsParser.requireArgumentCount(parts, 2);
        DebugModeCommands.setRounds(gameEngine, ArgumentsParser.parseInteger(parts[1]));
        return false;
    }

    private static boolean handleAddWater(GameEngine gameEngine, String[] parts) throws GameException {
        ArgumentsParser.requireArgumentCount(parts, 3);
        DebugModeCommands.addWater(gameEngine,
                ArgumentsParser.parsePump(gameEngine, parts[1]),
                ArgumentsParser.parseInteger(parts[2]));
        return false;
    }

    private static boolean handleTeleportPlayer(GameEngine gameEngine, String[] parts) throws GameException {
        ArgumentsParser.requireArgumentCount(parts, 3);
        DebugModeCommands.teleportPlayer(gameEngine,
                ArgumentsParser.parsePlayer(gameEngine, parts[1]),
                ArgumentsParser.parseMapObject(gameEngine, parts[2]));
        return false;
    }
}
