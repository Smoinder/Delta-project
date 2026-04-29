package PipesInTheDesert.Players;

import PipesInTheDesert.Connectors.Pipe;
import PipesInTheDesert.Connectors.PipeEnd;
import PipesInTheDesert.Constants;
import PipesInTheDesert.Elements.Cistern;
import PipesInTheDesert.Elements.Pump;
import PipesInTheDesert.Exceptions.NotEnoughStaminaException;
import PipesInTheDesert.Interfaces.IConnectable;
import PipesInTheDesert.Interfaces.IOccupiable;

/**
 * Player role focused on maintaining and extending the pipe system.
 */
public class Plumber extends Player {
    private static int _count = 0;
    private final int _id = ++_count;
    private boolean _holdingPump = false;
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
        return _holdingPump;
    }

    public void setHoldingPump(boolean holdingPump) {
        this._holdingPump = holdingPump;
    }

    public Pump getHeldPump() {
        return _heldPump;
    }

    public void setHeldPump(Pump heldPump) {
        this._heldPump = heldPump;
        this._holdingPump = heldPump != null;
    }

    public PipeEnd getHeldPipeEnd() {
        return _heldPipeEnd;
    }

    public void setHeldPipeEnd(PipeEnd heldPipeEnd) {
        this._heldPipeEnd = heldPipeEnd;
    }

    public void connectPipeEnd(IConnectable element) {
        if (_holdingPump) {
            throw new RuntimeException("PlayerHoldingPumpException");
        }
        if (_heldPipeEnd == null) {
            throw new RuntimeException("InvalidArgumentException");
        }
        if (!_heldPipeEnd.isFree()) {
            throw new RuntimeException("PipeNotFreeException");
        }
        if (!_heldPipeEnd.canConnect(element)) {
            throw new RuntimeException("InvalidArgumentException");
        }

        _heldPipeEnd.connect(element);
        _heldPipeEnd = null;
        consumeOrWrap(Constants.PLAYER_CHANGE_PUMP_INPUT_STAMINA);
    }

    public void disconnectPipeEnd(Pipe pipe, IConnectable element) {
        if (_holdingPump) {
            throw new RuntimeException("PlayerHoldingPumpException");
        }

        PipeEnd end = findPipeEndConnectedTo(pipe, element);
        if (end == null) {
            throw new RuntimeException("PipeNotConnectedException");
        }

        end.disconnect();
        _heldPipeEnd = end;
        consumeOrWrap(Constants.PLAYER_CHANGE_PUMP_INPUT_STAMINA);
    }

    public void fixPipe(Pipe pipe) {
        if (_holdingPump) {
            throw new RuntimeException("PlayerHoldingPumpException");
        }

        pipe.repair();
        consumeOrWrap(Constants.PLAYER_FIX_PIPE_STAMINA);
    }

    public void fixPump(Pump pump) {
        if (_holdingPump) {
            throw new RuntimeException("PlayerHoldingPumpException");
        }

        pump.fix();
        consumeOrWrap(Constants.PLAYER_FIX_PUMP_STAMINA);
    }

    public void getEnd(PipeEnd eop) {
        if (eop == null) {
            throw new RuntimeException("InvalidArgumentException");
        }

        _heldPipeEnd = eop;
    }

    public void insertPump(Pipe pipe, Pump pump) {
        if (!_holdingPump || _heldPump == null) {
            throw new RuntimeException("PlayerNotHoldingPumpException");
        }

        _heldPump = null;
        _holdingPump = false;
        consumeOrWrap(Constants.PLAYER_PLACE_PUMP_STAMINA);
    }

    public void pickUpPump(Cistern cistern) {
        if (_holdingPump) {
            throw new RuntimeException("PlayerHoldingPumpException");
        }
        if (cistern.generatedPumps == null || cistern.generatedPumps.isEmpty()) {
            throw new RuntimeException("InvalidArgumentException");
        }

        _heldPump = cistern.generatedPumps.remove(0);
        _holdingPump = true;
        consumeOrWrap(Constants.PLAYER_PICKUP_PUMP_STAMINA);
    }

    @Override
    public void setIncomingPipe(Pump pump, Pipe incoming) {
        PipeEnd end = findPipeEndConnectedTo(incoming, pump);
        if (end == null) {
            throw new RuntimeException("InvalidArgumentException");
        }

        pump.setInput(end);
    }

    @Override
    public void setOutgoingPipe(Pump pump, Pipe outgoing) {
        PipeEnd end = findPipeEndConnectedTo(outgoing, pump);
        if (end == null) {
            throw new RuntimeException("InvalidArgumentException");
        }

        pump.setOutput(end);
    }

    private PipeEnd findPipeEndConnectedTo(Pipe pipe, IConnectable element) {
        if (pipe == null) {
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

    private void consumeOrWrap(int cost) {
        try {
            consumeStamina(cost);
        } catch (NotEnoughStaminaException e) {
            throw new RuntimeException(e);
        }
    }
}