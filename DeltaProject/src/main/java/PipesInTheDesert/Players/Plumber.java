package PipesInTheDesert.Players;

import PipesInTheDesert.Interfaces.IConnectable;
import PipesInTheDesert.Interfaces.IOccupiable;
import PipesInTheDesert.Connectors.*;
import PipesInTheDesert.Elements.Cistern;
import PipesInTheDesert.Elements.Pump;

/**
 * Player role focused on maintaining and extending the pipe system.
 */
public class Plumber extends Player {
    /** True when the plumber currently carries a pump. */
    public boolean holdingPump;
    /** Pump currently carried by this plumber. Null if not holding */
    public Pump heldPump;
    /** Pipe end currently held by this plumber. Null if not holding */
    public PipeEnd heldPipeEnd;
    
    /**
     * Connects a held free pipe end to a target connectable element.
     *
     * @param element target element to connect
     */
    public void connectPipeEnd(IConnectable element) {
        System.out.println("Plumber.connectPipeEnd(IConnectable)");
    }

    /**
     * Disconnects an end of a pipe from a target element.
     *
     * @param pipe pipe whose end is disconnected
     * @param element element currently connected to the pipe end
     */
    public void disconnectPipeEnd(Pipe pipe, IConnectable element) {
        System.out.println("Plumber.disconnectPipeEnd(Pipe, IConnectable)");
    }

    /**
     * Repairs a leaking pipe.
     *
     * @param pipe target pipe
     */
    public void fixPipe(Pipe pipe) {
        System.out.println("Plumber.fixPipe(Pipe)");
    }

    /**
     * Repairs a broken pump.
     *
     * @param pump target pump
     */
    public void fixPump(Pump pump) {
        System.out.println("Plumber.fixPump(Pump)");
    }

    /**
     * Picks a specific pipe end for later connect or disconnect operations.
     *
     * @param eop selected pipe end
     */
    public void getEnd(PipeEnd eop) {
        System.out.println("Plumber.getEnd(PipeEnd)");
    }

    /**
     * Inserts a pump into an existing pipe segment.
     *
     * @param pipe pipe being split
     * @param pump pump inserted between resulting ends
     */
    public void insertPump(Pipe pipe, Pump pump) {
        System.out.println("Plumber.insertPump(Pipe, Pump)");
    }

    /**
     * Picks up a manufactured pump from a cistern.
     *
     * @param cistern source cistern
     */
    public void pickUpPump(Cistern cistern) {
        System.out.println("Plumber.pickUpPump(Cistern)");
    }

    /**
     * Tries to occupy a target map element.
     *
     * @param target occupiable target
     * @return true when occupation succeeds
     */
    public boolean occupy(IOccupiable target) {
        System.out.print("Plumber.occupy(IOccupiable): boolean");
        return true;
    }

    /**
     * Moves the plumber through a pipe.
     *
     * @param pipe pipe to move on
     */
    public void moveAlongPipe(Pipe pipe) {
        System.out.println("Plumber.moveAlongPipe(Pipe)");
    }

    /** Ends the plumber turn. */
    public void endTurn() {
        System.out.println("Plumber.endTurn()");
    }

    /**
     * Checks stamina availability for an action.
     *
     * @param cost required stamina cost
     * @return true when enough stamina is available
     */
    public boolean hasEnoughStamina(int cost) {
        System.out.print("Plumber.hasEnoughStamina(int): boolean");
        return true;
    }

    /**
     * Deducts stamina after an action.
     *
     * @param amount stamina amount to consume
     */
    public void consumeStamina(int amount) {
        System.out.println("Plumber.consumeStamina(int)");
    }

    /** Restores stamina at turn refresh. */
    public void refreshStamina() {
        System.out.println("Plumber.refreshStamina()");
    }

    /**
     * Selects incoming pipe on a pump.
     *
     * @param pump target pump
     * @param incoming selected incoming pipe
     */
    public void setIncomingPipe(Pump pump, Pipe incoming) {
        System.out.println("Plumber.setIncomingPipe(Pump, Pipe)");
    }

    /**
     * Selects outgoing pipe on a pump.
     *
     * @param pump target pump
     * @param outgoing selected outgoing pipe
     */
    public void setOutgoingPipe(Pump pump, Pipe outgoing) {
        System.out.println("Plumber.setOutgoingPipe(Pump, Pipe)");
    }
}
