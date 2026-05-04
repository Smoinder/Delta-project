package PipesInTheDesert.Players;

import PipesInTheDesert.Commands.PlayerHelpers;
import PipesInTheDesert.Connectors.Pipe;
import PipesInTheDesert.Connectors.PipeEnd;
import PipesInTheDesert.Constants;
import PipesInTheDesert.Elements.Pump;
import PipesInTheDesert.Exceptions.InvalidArgumentException;
import PipesInTheDesert.Exceptions.NotEnoughStaminaException;
import PipesInTheDesert.Exceptions.PipeAlreadyLeakingException;
import PipesInTheDesert.Exceptions.PipeNotConnectedException;
import PipesInTheDesert.Interfaces.IConnectable;
import PipesInTheDesert.Interfaces.IOccupiable;
import PipesInTheDesert.Team;

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
            throws InvalidArgumentException, NotEnoughStaminaException, PipeAlreadyLeakingException {

        if (pipe == null) {
            throw new InvalidArgumentException("Pipe cannot be null");
        }
        if (pipe.isLeaking()) {
            throw new PipeAlreadyLeakingException("Pipe already leaking");
        }
        consumeStamina(Constants.PLAYER_PUNCTURE_PIPE_STAMINA);
        pipe.puncture();
    }

    @Override
    public void setIncomingPipe(Pump pump, Pipe incoming)
            throws PipeNotConnectedException, NotEnoughStaminaException {

        PipeEnd end = PlayerHelpers.endConnectedTo(incoming, pump);
        if (end == null) {
            throw new PipeNotConnectedException("Pipe not connected to pump");
        }

        consumeStamina(Constants.PLAYER_CHANGE_PUMP_INPUT_STAMINA);

        pump.setInput(end);
    }

    @Override
    public void setOutgoingPipe(Pump pump, Pipe outgoing)
            throws PipeNotConnectedException, NotEnoughStaminaException {

        PipeEnd end = PlayerHelpers.endConnectedTo(outgoing, pump);
        if (end == null) {
            throw new PipeNotConnectedException("Pipe not connected to pump");
        }

        consumeStamina(Constants.PLAYER_CHANGE_PUMP_INPUT_STAMINA);

        pump.setOutput(end);
    }

    @Override
    public Team getPlayerTeam() {
        return Team.SABOTEURS;
    }

}