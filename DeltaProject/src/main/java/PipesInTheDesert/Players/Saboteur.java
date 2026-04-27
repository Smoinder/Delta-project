package PipesInTheDesert.Players;

import PipesInTheDesert.Connectors.Pipe;
import PipesInTheDesert.Elements.Pump;

/**
 * Player role focused on disrupting water delivery by damaging pipe-system
 * elements.
 */
public class Saboteur extends Player {
    /**
     * Creates a leak in a target pipe.
     *
     * @param pipe pipe to puncture
     */
    public void puncturePipe(Pipe pipe) {
        System.out.println("Saboteur.puncturePipe(Pipe)");
    }

    /**
     * Moves the saboteur through a pipe.
     *
     * @param pipe pipe to move on
     */
    public void moveAlongPipe(Pipe pipe) {
        System.out.println("Saboteur.moveAlongPipe(Pipe)");
    }

    /** Ends the saboteur turn. */
    public void endTurn() {
        System.out.println("Saboteur.endTurn()");
    }

    /**
     * Checks stamina availability for an action.
     *
     * @param cost required stamina cost
     * @return true when enough stamina is available
     */
    public boolean hasEnoughStamina(int cost) {
        System.out.print("Saboteur.hasEnoughStamina(int): boolean");
        return true;
    }

    /**
     * Deducts stamina after an action.
     *
     * @param amount stamina amount to consume
     */
    public void consumeStamina(int amount) {
        System.out.println("Saboteur.consumeStamina(int)");
    }

    /** Restores stamina at turn refresh. */
    public void refreshStamina() {
        System.out.println("Saboteur.refreshStamina()");
    }

    /**
     * Selects incoming pipe on a pump.
     *
     * @param pump     target pump
     * @param incoming selected incoming pipe
     */
    public void setIncomingPipe(Pump pump, Pipe incoming) {
        System.out.println("Saboteur.setIncomingPipe(Pump, Pipe)");
    }

    /**
     * Selects outgoing pipe on a pump.
     *
     * @param pump     target pump
     * @param outgoing selected outgoing pipe
     */
    public void setOutgoingPipe(Pump pump, Pipe outgoing) {
        System.out.println("Saboteur.setOutgoingPipe(Pump, Pipe)");
    }
}
