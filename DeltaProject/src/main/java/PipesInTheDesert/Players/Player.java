package PipesInTheDesert.Players;

import PipesInTheDesert.MapObject;
import PipesInTheDesert.Interfaces.IOccupiable;
import PipesInTheDesert.Connectors.Pipe;
import PipesInTheDesert.Elements.Pump;

public abstract class Player extends MapObject {
    public int stamina;
    public int maxStamina;
    public IOccupiable position;
    public int playerId;
    public boolean isActive;

    public abstract boolean occupy(IOccupiable target);

    public abstract void moveAlongPipe(Pipe pipe);

    public abstract void endTurn();

    public abstract boolean hasEnoughStamina(int cost);

    public abstract void consumeStamina(int amount);
    
    public abstract void refreshStamina();

    public abstract void setIncomingPipe(Pump pump, Pipe incoming);

    public abstract void setOutgoingPipe(Pump pump, Pipe outgoing);
}
