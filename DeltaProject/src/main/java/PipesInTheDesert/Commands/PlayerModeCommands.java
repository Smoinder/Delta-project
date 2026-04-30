package PipesInTheDesert.Commands;

import PipesInTheDesert.Constants;
import PipesInTheDesert.GameEngine;
import PipesInTheDesert.Interfaces.IConnectable;
import PipesInTheDesert.Interfaces.IOccupiable;
import PipesInTheDesert.MapType;
import PipesInTheDesert.Mode;
import PipesInTheDesert.Players.Player;
import PipesInTheDesert.Players.Plumber;
import PipesInTheDesert.Players.Saboteur;
import PipesInTheDesert.Connectors.Pipe;
import PipesInTheDesert.Connectors.PipeEnd;
import PipesInTheDesert.Elements.Cistern;
import PipesInTheDesert.Elements.Pump;
import PipesInTheDesert.Exceptions.*;

public class PlayerModeCommands {
    private PlayerModeCommands() {
        throw new AssertionError("No instantiation for static factory class");
    }

    public static void disconnect(GameEngine ge, Plumber pl, Pipe p, IConnectable elem)
            throws PlayerNotOnElementException, ElementNotConnectedException, WrongGameModeException,
            WrongTeamOfActivePlayerException {
        notImplemented();
    }

    public static void endTurn(GameEngine ge) throws WrongGameModeException {
        notImplemented();
    }

    public static void printState(GameEngine ge) throws WrongGameModeException { // Return a string, don't print it
                                                                                   // here
        notImplemented();
    }

    public static void debugMode(GameEngine ge) throws WrongGameModeException {
        if(ge.getMode() == Mode.DEBUG)
            throw new WrongGameModeException("MODE:DEBUG is already the current mode");
        ge.setMode(Mode.DEBUG);
        System.out.println("DebugMode OK");
    }

    public static void startGame(GameEngine ge, int numPlumbers, int numSaboteurs) throws WrongGameModeException, GameAlreadyStartedException, InvalidArgumentException {
        ge.startGame(numPlumbers, numSaboteurs);
        System.out.println("StartGame OK");
    }

    public static void loadMap(GameEngine ge, MapType mapType) throws WrongGameModeException, MapNotEmptyException {
        ge.loadMap(mapType);
        System.out.println("LoadMap OK");
    }

    public static void move(GameEngine ge, IOccupiable elem)
            throws WrongGameModeException, ElementNotReachableException, NotEnoughStaminaException {
        notImplemented();
    }

    public static void fix(GameEngine ge, Pipe p)
            throws WrongGameModeException, PlayerNotOnElementException, NotEnoughStaminaException,
            WrongTeamOfActivePlayerException {
        notImplemented();
    }

    public static void fixPump(GameEngine ge, Pump p)
            throws WrongGameModeException, PlayerNotOnElementException, NotEnoughStaminaException,
            WrongTeamOfActivePlayerException {
        notImplemented();
    }

    /**
     * Saboteur action: punctures a pipe so it leaks water into the desert
     * (use case 12, §5.2.2.12 of the skeleton plan).
     *
     * <p>Conditions checked, in order:
     * <ol>
     *   <li>Game mode is {@link Mode#PLAYER}.</li>
     *   <li>The active player is a {@link Saboteur} (puncturing is a saboteur
     *       action).</li>
     *   <li>The active player is currently positioned on the target pipe.</li>
     *   <li>The active player has at least
     *       {@link Constants#PLAYER_PUNCTURE_PIPE_STAMINA} stamina available.</li>
     * </ol>
     *
     * <p>On success, the pipe is marked leaking ({@link Pipe#puncture()},
     * idempotent) and the saboteur's stamina is consumed.
     *
     * @param ge active game engine
     * @param p  pipe to puncture
     * @throws WrongGameModeException           if the game is not in PLAYER mode
     * @throws WrongTeamOfActivePlayerException if the active player is not a
     *                                          saboteur
     * @throws PlayerNotOnElementException      if the active player is not on
     *                                          the target pipe
     * @throws NotEnoughStaminaException        if the saboteur has insufficient
     *                                          stamina
     */
    public static void puncture(GameEngine ge, Pipe p)
            throws WrongGameModeException, PlayerNotOnElementException, NotEnoughStaminaException,
            WrongTeamOfActivePlayerException {
        if (ge.getMode() != Mode.PLAYER) {
            throw new WrongGameModeException("Game mode should be 'PLAYER'");
        }
        Player active = ge.getActivePlayer();
        if (!(active instanceof Saboteur)) {
            throw new WrongTeamOfActivePlayerException("Puncture is a saboteur action");
        }
        if (active.getPosition() != p) {
            throw new PlayerNotOnElementException("Saboteur is not on the target pipe");
        }
        active.consumeStamina(Constants.PLAYER_PUNCTURE_PIPE_STAMINA);
        p.puncture();
        System.out.println("Puncture OK");
    }

    public static void setPumpDirection(GameEngine ge, Pump pump, Pipe inputPipe, Pipe outputPipe)
            throws WrongGameModeException, PlayerNotOnElementException, NotEnoughStaminaException {
        notImplemented();
    }

    public static void pickUpPump(GameEngine ge) throws WrongGameModeException, WrongTeamOfActivePlayerException,
            NoFreePumpsException, AlredayHoldingPumpException, NotEnoughStaminaException, PlayerNotOnElementException {
        notImplemented();
    }

    public static void installPump(GameEngine ge, Pipe p) throws WrongGameModeException,
            WrongTeamOfActivePlayerException, PlayerNotOnElementException, NotEnoughStaminaException {
        notImplemented();
    }

    public static void connect(GameEngine ge, Pipe p, IConnectable elem)
            throws WrongGameModeException, WrongTeamOfActivePlayerException, PlayerNotOnElementException,
            NotEnoughStaminaException, PipeHasNoFreeEndsException {
        notImplemented();
    }

    private static void notImplemented() {
        System.out.println(Constants.NOT_IMPLEMENTED_YET);
    }
}
