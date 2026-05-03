package PipesInTheDesert.Parsers;

import PipesInTheDesert.Commands.DebugModeCommands;
import PipesInTheDesert.Commands.PlayerModeCommands;
import PipesInTheDesert.Exceptions.GameException;
import PipesInTheDesert.GameEngine;
import PipesInTheDesert.MapType;

/**
 * Parses console commands and dispatches them to the command package.
 */
public final class CommandsParser {
    private CommandsParser() {
        throw new AssertionError("No instantiation for static factory class");
    }

    /**
     * Parses and executes a single console command.
     *
     * @param gameEngine active game engine instance
     * @param line raw command line
     * @return true when the console loop should terminate
     */
    public static boolean handleCommand(GameEngine gameEngine, String line) {
        String[] parts = line.trim().split("\\s+");
        String command = parts[0].toLowerCase();

        try {
            return switch (command) {
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
            System.out.println(e.getClass().getSimpleName());
        } catch (IllegalArgumentException e) {
            System.out.println("InvalidArgumentException");
        }

        return false;
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


