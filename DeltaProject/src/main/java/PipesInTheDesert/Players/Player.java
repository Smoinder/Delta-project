package PipesInTheDesert.Players;

import PipesInTheDesert.Connectors.Pipe;
import PipesInTheDesert.Elements.Pump;
import PipesInTheDesert.Interfaces.IOccupiable;
import PipesInTheDesert.MapObject;

/**
 * Abstract base type for all players that move in the pipe network.
 */
public abstract class Player extends MapObject {
    /** Current stamina available for actions. */
    public int stamina;
    /** Maximum stamina restored at turn start. */
    public int maxStamina;
    /** Current occupied map element, such as a pipe or a pump. */
    public IOccupiable position;
    /** Unique identifier of this player. */
    public int playerId;
    /** True when this player currently has the active turn. */
    public boolean isActive;

    /**
     * Tries to occupy a target map element.
     *
     * @param target occupiable target
     * @return true when occupation succeeds
     */
    public boolean occupy(IOccupiable target) {
        System.out.print("Plumber.occupy(IOccupiable): boolean");
        return true;
    };

    public abstract void moveAlongPipe(Pipe pipe);

    public abstract void endTurn();

    public abstract boolean hasEnoughStamina(int cost);

    public abstract void consumeStamina(int amount);

    public abstract void refreshStamina();

    public abstract void setIncomingPipe(Pump pump, Pipe incoming);

    public abstract void setOutgoingPipe(Pump pump, Pipe outgoing);
}
