package PipesInTheDesert.Players;

import PipesInTheDesert.Connectors.Pipe;
import PipesInTheDesert.Connectors.PipeEnd;
import PipesInTheDesert.Constants;
import PipesInTheDesert.Elements.Cistern;
import PipesInTheDesert.Elements.Pump;
import PipesInTheDesert.Exceptions.*;
import PipesInTheDesert.Interfaces.IConnectable;
import PipesInTheDesert.Interfaces.IOccupiable;

/**
 * Player role focused on maintaining and extending the pipe system.
 */
public class Plumber extends Player {
    private static int _count = 0;
    private final int _id = ++_count;

    private Pump _heldPump = null;
    private PipeEnd _heldPipeEnd = null;

    public Plumber(IOccupiable position, int maxStamina, boolean isActive) {
        super(position, maxStamina, isActive);
    }

    public Plumber(IOccupiable position, int maxStamina) {
        super(position, maxStamina);
    }

    public Plumber(IOccupiable position) {
        super(position);
    }

    public int getId() {
        return _id;
    }

    public boolean isHoldingPump() {
        return _heldPump != null;
    }

    public Pump getHeldPump() {
        return _heldPump;
    }

    public void setHeldPump(Pump heldPump) {
        this._heldPump = heldPump;
    }

    public PipeEnd getHeldPipeEnd() {
        return _heldPipeEnd;
    }

    public void setHeldPipeEnd(PipeEnd heldPipeEnd) {
        this._heldPipeEnd = heldPipeEnd;
    }

    public void connectPipeEnd(IConnectable element)
            throws InvalidArgumentException,
            PipeHasNoFreeEndsException, NotEnoughStaminaException {

        if (isHoldingPump()) {
            throw new InvalidArgumentException("Already holding pump");
        }
        if (_heldPipeEnd == null || element == null) {
            throw new InvalidArgumentException("Invalid pipe end or element");
        }
        if (!_heldPipeEnd.isFree()) {
            throw new PipeHasNoFreeEndsException("Pipe doesnt have free end");
        }
        if (!_heldPipeEnd.canConnect(element)) {
            throw new InvalidArgumentException("Element cannot accept pipe end");
        }

        consumeStamina(Constants.PLAYER_CHANGE_PUMP_INPUT_STAMINA);
        _heldPipeEnd.connect(element);
        _heldPipeEnd = null;
    }

    public void disconnectPipeEnd(Pipe pipe, IConnectable element)
            throws ElementNotConnectedException,
            InvalidArgumentException, NotEnoughStaminaException {

        if (isHoldingPump()) {
            throw new InvalidArgumentException("Already holding pump");
        }

        PipeEnd end = findPipeEndConnectedTo(pipe, element);
        if (end == null) {
            throw new ElementNotConnectedException("Pipe end not connected");
        }

        consumeStamina(Constants.PLAYER_CHANGE_PUMP_INPUT_STAMINA);
        end.disconnect();
        _heldPipeEnd = end;
    }

    public void fixPipe(Pipe pipe)
            throws InvalidArgumentException,
            NotEnoughStaminaException {

        if (isHoldingPump()) {
            throw new InvalidArgumentException("Already holding pump");
        }
        if (pipe == null) {
            throw new InvalidArgumentException("Pipe cannot be null");
        }
        if (!pipe.leaking) {
            throw new InvalidArgumentException("Pipe is not leaking");
        }

        consumeStamina(Constants.PLAYER_FIX_PIPE_STAMINA);
        pipe.repair();
    }

    public void fixPump(Pump pump)
            throws InvalidArgumentException,
            NotEnoughStaminaException {

        if (isHoldingPump()) {
            throw new InvalidArgumentException("Already holding pump");
        }
        if (pump == null) {
            throw new InvalidArgumentException("Pump cannot be null");
        }
        if (pump.isHealthy) {
            throw new InvalidArgumentException("Pump is already healthy");
        }

        consumeStamina(Constants.PLAYER_FIX_PUMP_STAMINA);
        pump.fix();
    }

    public void setEnd(PipeEnd eop) throws InvalidArgumentException {
        if (eop == null) {
            throw new InvalidArgumentException("Pipe end cannot be null");
        }

        _heldPipeEnd = eop;
    }

    public void insertPump(Pipe pipe, Pump pump)
            throws InvalidArgumentException, NoFreePumpsException,
            NotEnoughStaminaException {

        if (_heldPump == null) {
            throw new NoFreePumpsException("No free pumps");
        }
        if (pipe == null || pump == null) {
            throw new InvalidArgumentException("Pipe or pump cannot be null");
        }

        consumeStamina(Constants.PLAYER_PLACE_PUMP_STAMINA);
        _heldPump = null;
    }

    public void pickUpPump(Cistern cistern)
            throws InvalidArgumentException,
            NoFreePumpsException, NotEnoughStaminaException {

        if (isHoldingPump()) {
            throw new InvalidArgumentException("Already holding pump");
        }
        if (cistern == null) {
            throw new InvalidArgumentException("Cistern cannot be null");
        }
        if (cistern.generatedPumps == null || cistern.generatedPumps.isEmpty()) {
            throw new NoFreePumpsException("No free pumps");
        }

        consumeStamina(Constants.PLAYER_PICKUP_PUMP_STAMINA);
        _heldPump = cistern.generatedPumps.remove(0);
    }

    @Override
    public void setIncomingPipe(Pump pump, Pipe incoming)
            throws InvalidArgumentException, NotEnoughStaminaException {

        PipeEnd end = findPipeEndConnectedTo(incoming, pump);
        if (end == null) {
            throw new InvalidArgumentException("Pipe is not connected to pump");
        }
        consumeStamina(Constants.PLAYER_CHANGE_PUMP_INPUT_STAMINA);
        pump.setInput(end);
    }

    @Override
    public void setOutgoingPipe(Pump pump, Pipe outgoing)
            throws InvalidArgumentException, NotEnoughStaminaException {

        PipeEnd end = findPipeEndConnectedTo(outgoing, pump);
        if (end == null) {
            throw new InvalidArgumentException("Pipe is not connected to pump");
        }
        consumeStamina(Constants.PLAYER_CHANGE_PUMP_INPUT_STAMINA);
        pump.setOutput(end);
    }

    private PipeEnd findPipeEndConnectedTo(Pipe pipe, IConnectable element) {
        if (pipe == null || element == null) {
            return null;
        }
        if (pipe.end1 != null && pipe.end1.connectedElement == element) {
            return pipe.end1;
        }
        if (pipe.end2 != null && pipe.end2.connectedElement == element) {
            return pipe.end2;
        }
        return null;
    }
}