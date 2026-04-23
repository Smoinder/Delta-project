package PipesInTheDesert.Commands;

import PipesInTheDesert.GameEngine;
import PipesInTheDesert.Interfaces.IConnectable;
import PipesInTheDesert.Interfaces.IOccupiable;
import PipesInTheDesert.Players.Plumber;
import PipesInTheDesert.Connectors.Pipe;
import PipesInTheDesert.Elements.Pump;
import PipesInTheDesert.Exceptions.*;

public class PlayerModeCommands {
    private PlayerModeCommands() {
        throw new AssertionError("No instantiation for static factory class");
    }

    public static void disconnect(GameEngine ge, Plumber pl, Pipe p, IConnectable elem)
            throws PlayerNotOnElementException, ElementNotConnectedException, WrongGameModeException,
            WrongTeamOfActivePlayerException {

    }

    public static void endTurn(GameEngine ge) throws WrongGameModeException {

    }

    public static String printState(GameEngine ge) throws WrongGameModeException { // Return a string, don't print it
                                                                                   // here
        return "";
    }

    public static void debugMode(GameEngine ge) throws WrongGameModeException {

    }

    public static void startGame(GameEngine ge, int numPlumbers, int numSaboteurs) throws WrongGameModeException, GameAlreadyStartedException, InvalidArgumentException {
        ge.startGame(numPlumbers, numSaboteurs);
    }

    public static void loadMap(GameEngine ge) throws WrongGameModeException, MapNotEmptyException {

    }

    public static void move(GameEngine ge, IOccupiable elem)
            throws WrongGameModeException, ElementNotReachableException, NotEnoughStaminaException {

    }

    public static void fix(GameEngine ge, Pipe p)
            throws WrongGameModeException, PlayerNotOnElementException, NotEnoughStaminaException,
            WrongTeamOfActivePlayerException {

    }

    public static void fixPump(GameEngine ge, Pump p)
            throws WrongGameModeException, PlayerNotOnElementException, NotEnoughStaminaException,
            WrongTeamOfActivePlayerException {

    }

    public static void puncture(GameEngine ge, Pipe p)
            throws WrongGameModeException, PlayerNotOnElementException, NotEnoughStaminaException,
            WrongTeamOfActivePlayerException {

    }

    public static void setPumpDirection(GameEngine ge, Pump pump, Pipe inputPipe, Pipe outputPipe)
            throws WrongGameModeException, PlayerNotOnElementException, NotEnoughStaminaException {

    }

    public static void pickUpPump(GameEngine ge) throws WrongGameModeException, WrongTeamOfActivePlayerException,
            NoFreePumpsException, AlredayHoldingPumpException, NotEnoughStaminaException, PlayerNotOnElementException {

    }

    public static void installPump(GameEngine ge, Pipe p) throws WrongGameModeException,
            WrongTeamOfActivePlayerException, PlayerNotOnElementException, NotEnoughStaminaException {

    }

    public static void connect(GameEngine ge, Pipe p, IConnectable elem)
            throws WrongGameModeException, WrongTeamOfActivePlayerException, PlayerNotOnElementException,
            NotEnoughStaminaException, PipeHasNoFreeEndsException {
    }
}
