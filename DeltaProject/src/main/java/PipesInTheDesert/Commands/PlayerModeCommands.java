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
import PipesInTheDesert.Elements.ActiveElement;
import PipesInTheDesert.Elements.Cistern;
import PipesInTheDesert.Elements.Pump;
import PipesInTheDesert.Exceptions.*;
import java.util.List;

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

    public static void printState(GameEngine ge) throws WrongGameModeException {

        notImplemented();
    }

    public static void debugMode(GameEngine ge) throws WrongGameModeException {
        if (ge.getMode() == Mode.DEBUG)
            throw new WrongGameModeException("MODE:DEBUG is already the current mode");
        ge.setMode(Mode.DEBUG);
        System.out.println("DebugMode OK");
    }

    public static void startGame(GameEngine ge, int numPlumbers, int numSaboteurs)
            throws WrongGameModeException, GameAlreadyStartedException, InvalidArgumentException {
        ge.startGame(numPlumbers, numSaboteurs);
        printState(ge);
        System.out.println("StartGame OK");
    }

    public static void loadMap(GameEngine ge, MapType mapType) throws WrongGameModeException, MapNotEmptyException {
        ge.loadMap(mapType);
        printState(ge);
        System.out.println("LoadMap OK");
    }

    public static void move(GameEngine ge, IOccupiable elem)
            throws WrongGameModeException, ElementNotReachableException, AlreadyOccupiedException,
            NotEnoughStaminaException, PlayerNotOnPipeException, InvalidArgumentException {
        if (ge.getMode() != Mode.PLAYER) {
            throw new WrongGameModeException("Game mode should be 'PLAYER'");
        }
        Player active = ge.getActivePlayer();
        if (active == null) {
            throw new InvalidArgumentException("No active player");
        }
        if (elem instanceof Pipe pipe) {
            active.moveAlongPipe(pipe);
        } else if (elem instanceof ActiveElement activeElement) {
            active.moveToActiveElement(activeElement);
        } else {
            throw new ElementNotReachableException("Target element is not a pipe or pump");
        }
        System.out.println("Move OK");
    }

    public static void fix(GameEngine ge, Pipe p)
            throws WrongGameModeException, PlayerNotOnElementException, NotEnoughStaminaException,
            WrongTeamOfActivePlayerException, InvalidArgumentException, PipeAlreadyIntactException {
        if (ge.getMode() != Mode.PLAYER) {
            throw new WrongGameModeException("Game mode should be 'PLAYER'");
        }
        Player active = ge.getActivePlayer();
        if (!(active instanceof Plumber plumber)) {
            throw new WrongTeamOfActivePlayerException("Fix is a plumber action");
        }
        if (plumber.getPosition() != p) {
            throw new PlayerNotOnElementException("Plumber is not on the target pipe");
        }
        plumber.fixPipe(p);
        System.out.println("Fix OK");
    }

    public static void fixPump(GameEngine ge, Pump p)
            throws WrongGameModeException, PlayerNotOnElementException, NotEnoughStaminaException,
            WrongTeamOfActivePlayerException, InvalidArgumentException {
        if (ge.getMode() != Mode.PLAYER) {
            throw new WrongGameModeException("Game mode should be 'PLAYER'");
        }
        Player active = ge.getActivePlayer();
        if (!(active instanceof Plumber plumber)) {
            throw new WrongTeamOfActivePlayerException("FixPump is a plumber action");
        }
        if (plumber.getPosition() != p) {
            throw new PlayerNotOnElementException("Plumber is not on the target pump");
        }
        plumber.fixPump(p);
        System.out.println("FixPump OK");
    }

    /**
     * Saboteur action: punctures a pipe so it leaks water into the desert
     * (use case 12, §5.2.2.12 of the skeleton plan).
     *
     * <p>
     * Conditions checked, in order:
     * <ol>
     * <li>Game mode is {@link Mode#PLAYER}.</li>
     * <li>The active player is a {@link Saboteur} (puncturing is a saboteur
     * action).</li>
     * <li>The active player is currently positioned on the target pipe.</li>
     * <li>The active player has at least
     * {@link Constants#PLAYER_PUNCTURE_PIPE_STAMINA} stamina available.</li>
     * </ol>
     *
     * <p>
     * On success, the pipe is marked leaking ({@link Pipe#puncture()},
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
            WrongTeamOfActivePlayerException, PipeAlreadyLeakingException {
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

    /**
     * Player action: changes a pump's direction by selecting which connected
     * pipe is the input and which is the output (use case 4, §5.2.2.4 of the
     * skeleton plan). Both Plumbers and Saboteurs are allowed to perform this
     * action; only one pipe may be the input and only one the output, and any
     * other connected pipes implicitly become "closed pipes".
     *
     * <p>
     * Conditions checked, in order:
     * <ol>
     * <li>Game mode is {@link Mode#PLAYER}.</li>
     * <li>The active player is currently positioned on the target pump.</li>
     * <li>The two selected pipes are different.</li>
     * <li>Each selected pipe has an endpoint currently connected to the pump.</li>
     * <li>The active player has at least
     * {@link Constants#PLAYER_CHANGE_PUMP_INPUT_STAMINA} stamina available.</li>
     * </ol>
     *
     * @param ge         active game engine
     * @param pump       pump whose direction is being changed
     * @param inputPipe  pipe to mark as the new input
     * @param outputPipe pipe to mark as the new output
     * @throws WrongGameModeException       if the game is not in PLAYER mode
     * @throws PlayerNotOnElementException  if the active player is not on the
     *                                      target pump
     * @throws InvalidArgumentException     if the input and output pipes are the
     *                                      same instance
     * @throws ElementNotConnectedException if either selected pipe is not
     *                                      connected to the pump
     * @throws NotEnoughStaminaException    if the player has insufficient stamina
     */
    public static void setPumpDirection(GameEngine ge, Pump pump, Pipe inputPipe, Pipe outputPipe)
            throws WrongGameModeException, PlayerNotOnElementException, NotEnoughStaminaException,
            InvalidArgumentException, ElementNotConnectedException {
        if (ge.getMode() != Mode.PLAYER) {
            throw new WrongGameModeException("Game mode should be 'PLAYER'");
        }
        Player active = ge.getActivePlayer();
        if (active == null || active.getPosition() != pump) {
            throw new PlayerNotOnElementException("Active player is not on the target pump");
        }
        if (inputPipe == outputPipe) {
            throw new InvalidArgumentException("Input and output pipes must differ");
        }
        if (endConnectedTo(inputPipe, pump) == null) {
            throw new ElementNotConnectedException("Input pipe is not connected to the pump");
        }
        if (endConnectedTo(outputPipe, pump) == null) {
            throw new ElementNotConnectedException("Output pipe is not connected to the pump");
        }
        active.setIncomingPipe(pump, inputPipe);
        active.setOutgoingPipe(pump, outputPipe);
        System.out.println("SetPumpDirection OK");
    }

    /**
     * Returns the endpoint of {@code pipe} that is currently connected to
     * {@code pump}, or {@code null} if neither end is.
     */
    private static PipeEnd endConnectedTo(Pipe pipe, Pump pump) {
        if (pipe == null) {
            return null;
        }
        if (pipe.getEnd1() != null && pipe.getEnd1().getConnectedElement() == pump) {
            return pipe.getEnd1();
        }
        if (pipe.getEnd2() != null && pipe.getEnd2().getConnectedElement() == pump) {
            return pipe.getEnd2();
        }
        return null;
    }

    /**
     * Plumber action: picks up a manufactured pump from the cistern the
     * plumber is standing on (use case 6, §5.2.2.6 of the skeleton plan).
     * The plumber may carry at most one pump at a time.
     *
     * <p>
     * Conditions checked, in order:
     * <ol>
     * <li>Game mode is {@link Mode#PLAYER}.</li>
     * <li>The active player is a {@link Plumber}.</li>
     * <li>The plumber is currently positioned on a {@link Cistern}.</li>
     * <li>The plumber is not already holding a pump.</li>
     * <li>The cistern has at least one manufactured pump available.</li>
     * <li>The plumber has at least
     * {@link Constants#PLAYER_PICKUP_PUMP_STAMINA} stamina available.</li>
     * </ol>
     *
     * <p>
     * On success, one pump is removed from the cistern's generated-pump
     * inventory and assigned to the plumber.
     *
     * @param ge active game engine
     * @throws WrongGameModeException           if the game is not in PLAYER mode
     * @throws WrongTeamOfActivePlayerException if the active player is not a
     *                                          plumber
     * @throws PlayerNotOnElementException      if the plumber is not on a cistern
     * @throws AlredayHoldingPumpException      if the plumber already holds a pump
     * @throws NoFreePumpsException             if the cistern has no available
     *                                          pumps
     * @throws NotEnoughStaminaException        if the plumber has insufficient
     *                                          stamina
     */
    public static void pickUpPump(GameEngine ge) throws WrongGameModeException, WrongTeamOfActivePlayerException,
            NoFreePumpsException, AlredayHoldingPumpException, NotEnoughStaminaException, PlayerNotOnElementException,
            InvalidArgumentException {
        if (ge.getMode() != Mode.PLAYER) {
            throw new WrongGameModeException("Game mode should be 'PLAYER'");
        }
        Player active = ge.getActivePlayer();
        if (!(active instanceof Plumber plumber)) {
            throw new WrongTeamOfActivePlayerException("PickUpPump is a plumber action");
        }
        if (!(plumber.getPosition() instanceof Cistern cistern)) {
            throw new PlayerNotOnElementException("Plumber must be on a cistern to pick up a pump");
        }
        if (plumber.isHoldingPump()) {
            throw new AlredayHoldingPumpException("Plumber is already holding a pump");
        }
        List<Pump> available = cistern.getGeneratedPumps();
        if (available.isEmpty()) {
            throw new NoFreePumpsException("Cistern has no free pumps to pick up");
        }
        plumber.pickUpPump(cistern);
        System.out.println("PickUpPump OK");
    }

    /**
     * Plumber action: installs the pump the plumber is carrying on the pipe
     * the plumber is standing on (use case 7, §5.2.2.7 of the skeleton plan).
     *
     * <p>
     * Conditions checked, in order:
     * <ol>
     * <li>Game mode is {@link Mode#PLAYER}.</li>
     * <li>The active player is a {@link Plumber}.</li>
     * <li>The plumber is currently positioned on the target pipe.</li>
     * <li>The plumber is currently holding a pump.</li>
     * <li>The pipe has at least one initialized endpoint.</li>
     * <li>The plumber has at least
     * {@link Constants#PLAYER_PLACE_PUMP_STAMINA} stamina available.</li>
     * </ol>
     *
     * <p>
     * On success, the held pump is inserted on one end of the pipe per the
     * skeleton flow (PipeEnd.disconnect; PipeEnd.connect(pump)): the chosen
     * pipe end is detached from its previous element (if any) and attached to
     * the pump. The plumber's heldPump slot is cleared and stamina is consumed.
     *
     * @param ge active game engine
     * @param p  pipe on which to install the pump
     * @throws WrongGameModeException           if the game is not in PLAYER mode
     * @throws WrongTeamOfActivePlayerException if the active player is not a
     *                                          plumber
     * @throws PlayerNotOnElementException      if the plumber is not on the
     *                                          target pipe
     * @throws InvalidArgumentException         if the plumber is not holding a
     *                                          pump or the pipe has no usable
     *                                          endpoint
     * @throws NotEnoughStaminaException        if the plumber has insufficient
     *                                          stamina
     */
    public static void installPump(GameEngine ge, Pipe p) throws WrongGameModeException,
            WrongTeamOfActivePlayerException, PlayerNotOnElementException, NotEnoughStaminaException,
            InvalidArgumentException {
        if (ge.getMode() != Mode.PLAYER) {
            throw new WrongGameModeException("Game mode should be 'PLAYER'");
        }
        Player active = ge.getActivePlayer();
        if (!(active instanceof Plumber plumber)) {
            throw new WrongTeamOfActivePlayerException("InstallPump is a plumber action");
        }
        if (plumber.getPosition() != p) {
            throw new PlayerNotOnElementException("Plumber is not on the target pipe");
        }
        if (!plumber.isHoldingPump() || plumber.getHeldPump() == null) {
            throw new InvalidArgumentException("Plumber is not holding a pump");
        }
        PipeEnd target = (p.getEnd1() != null) ? p.getEnd1() : p.getEnd2();
        if (target == null) {
            throw new InvalidArgumentException("Pipe has no usable endpoints");
        }
        plumber.consumeStamina(Constants.PLAYER_PLACE_PUMP_STAMINA);
        Pump pump = plumber.getHeldPump();
        IConnectable previous = target.getConnectedElement();
        if (previous instanceof ActiveElement prev) {
            prev.removeConnectedPipe(target);
        }
        target.setConnectedElement(pump);
        pump.connectEnd(target);
        plumber.setHeldPump(null);
        System.out.println("InstallPump OK");
    }

    /**
     * Plumber action: connects a free pipe end of the given pipe to the
     * specified active element (use case 10, §5.2.2.10 of the skeleton plan).
     *
     * <p>
     * Conditions checked, in order:
     * <ol>
     * <li>Game mode is {@link Mode#PLAYER}.</li>
     * <li>The active player is a {@link Plumber}.</li>
     * <li>The plumber is currently positioned on the target element.</li>
     * <li>Both arguments are non-null.</li>
     * <li>The pipe has a free endpoint (not already connected to an element).</li>
     * <li>The element accepts the chosen pipe end
     * ({@link IConnectable#canConnect(PipeEnd)}).</li>
     * <li>The plumber has at least
     * {@link Constants#PLAYER_CONNECT_PIPE_STAMINA} stamina available.</li>
     * </ol>
     *
     * <p>
     * On success, the free pipe end's {@code connectedElement} is set to
     * the target element, the element registers the pipe end via
     * {@link IConnectable#connectEnd(PipeEnd)}, the plumber's
     * {@link Plumber#heldPipeEnd held pipe end} reference is cleared, and
     * stamina is consumed.
     *
     * @param ge   active game engine
     * @param p    pipe with a free endpoint
     * @param elem active element to attach the free endpoint to
     * @throws WrongGameModeException           if the game is not in PLAYER mode
     * @throws WrongTeamOfActivePlayerException if the active player is not a
     *                                          plumber
     * @throws PlayerNotOnElementException      if the plumber is not on the
     *                                          target element
     * @throws InvalidArgumentException         if either argument is null or the
     *                                          element refuses the connection
     * @throws PipeHasNoFreeEndsException       if the pipe has no free endpoints
     * @throws NotEnoughStaminaException        if the plumber has insufficient
     *                                          stamina
     */
    public static void connect(GameEngine ge, Pipe p, IConnectable elem)
            throws WrongGameModeException, WrongTeamOfActivePlayerException, PlayerNotOnElementException,
            NotEnoughStaminaException, PipeHasNoFreeEndsException, InvalidArgumentException {
        if (ge.getMode() != Mode.PLAYER) {
            throw new WrongGameModeException("Game mode should be 'PLAYER'");
        }
        Player active = ge.getActivePlayer();
        if (!(active instanceof Plumber plumber)) {
            throw new WrongTeamOfActivePlayerException("Connect is a plumber action");
        }
        if (p == null || elem == null) {
            throw new InvalidArgumentException("Pipe and element must be non-null");
        }
        if (plumber.getPosition() != elem) {
            throw new PlayerNotOnElementException("Plumber is not on the target element");
        }
        PipeEnd freeEnd = freeEndOf(p);
        if (freeEnd == null) {
            throw new PipeHasNoFreeEndsException("Pipe has no free ends to connect");
        }
        if (!elem.canConnect(freeEnd)) {
            throw new InvalidArgumentException("Element cannot accept this pipe end");
        }
        plumber.consumeStamina(Constants.PLAYER_CONNECT_PIPE_STAMINA);
        freeEnd.setConnectedElement(elem);
        elem.connectEnd(freeEnd);
        plumber.setHeldPipeEnd(null);
        System.out.println("Connect OK");
    }

    /**
     * Returns the first free endpoint of {@code pipe}, or {@code null} if both
     * endpoints are already attached to elements.
     */
    private static PipeEnd freeEndOf(Pipe pipe) {
        if (pipe.getEnd1() != null && pipe.getEnd1().getConnectedElement() == null) {
            return pipe.getEnd1();
        }
        if (pipe.getEnd2() != null && pipe.getEnd2().getConnectedElement() == null) {
            return pipe.getEnd2();
        }
        return null;
    }

    private static void notImplemented() {
        System.out.println(Constants.NOT_IMPLEMENTED_YET);
    }
}
