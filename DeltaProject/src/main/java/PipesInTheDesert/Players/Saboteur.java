package PipesInTheDesert.Players;

import PipesInTheDesert.Connectors.Pipe;
import PipesInTheDesert.Connectors.PipeEnd;
import PipesInTheDesert.Constants;
import PipesInTheDesert.Elements.Pump;
import PipesInTheDesert.Exceptions.InvalidArgumentException;
import PipesInTheDesert.Exceptions.NotEnoughStaminaException;
import PipesInTheDesert.Interfaces.IConnectable;
import PipesInTheDesert.Interfaces.IOccupiable;

/**
 * Player role focused on disrupting water delivery.
 */
public class Saboteur extends Player {
    private static int _count = 0;
    private final int _id = ++_count;

    public Saboteur(IOccupiable position, int maxStamina, boolean isActive) {
        super(position, maxStamina, isActive);
    }

    public Saboteur(IOccupiable position, int maxStamina) {
        super(position, maxStamina);
    }

    public Saboteur(IOccupiable position) {
        super(position);
    }

    public int getId() {
        return _id;
    }

    public void puncturePipe(Pipe pipe)
            throws InvalidArgumentException, NotEnoughStaminaException {

        if (pipe == null) {
            throw new InvalidArgumentException("Pipe cannot be null");
        }
        if (pipe.leaking) {
            throw new InvalidArgumentException("Pipe already leaking");
        }
        consumeStamina(Constants.PLAYER_PUNCTURE_PIPE_STAMINA);
        pipe.puncture();
    }

    @Override
    public void setIncomingPipe(Pump pump, Pipe incoming)
            throws InvalidArgumentException, NotEnoughStaminaException {

        PipeEnd end = findPipeEndConnectedTo(incoming, pump);
        if (end == null) {
            throw new InvalidArgumentException("Pipe not connected to pump");
        }

        consumeStamina(Constants.PLAYER_CHANGE_PUMP_INPUT_STAMINA);

        pump.setInput(end);
    }

    @Override
    public void setOutgoingPipe(Pump pump, Pipe outgoing)
            throws InvalidArgumentException, NotEnoughStaminaException {

        PipeEnd end = findPipeEndConnectedTo(outgoing, pump);
        if (end == null) {
            throw new InvalidArgumentException("Pipe not connected to pump");
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