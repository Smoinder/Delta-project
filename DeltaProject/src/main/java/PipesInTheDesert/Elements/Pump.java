package PipesInTheDesert.Elements;

import java.util.List;
import PipesInTheDesert.Connectors.*;
import PipesInTheDesert.Players.Player;
import PipesInTheDesert.Interfaces.IOccupiable;

/**
 * Active element that moves water from an input pipe to an output pipe and can be occupied by players.
 */
public class Pump extends ActiveElement implements IOccupiable {
    /** Endpoints currently connected to this pump. */
    public List<PipeEnd> connectedPipes;
    /** Maximum number of endpoints that may be connected. */
    public int maxConnectedPipes;
    /** Endpoint currently selected as pump input. */
    public PipeEnd inputPipe;
    /** Endpoint currently selected as pump output. */
    public PipeEnd outputPipe;
    /** Functional state flag; false means the pump is out of order. */
    public boolean isHealthy;
    /** Players currently occupying this pump. */
    public List<Player> occupants;
    /** Current stored water amount in the pump tank. */
    public double waterTankLevel;

    /** Restores the pump to working state. */
    public void fix() {
        System.out.println("Pump.fix()");
    }

    /** Sets the pump to non-functional state. */
    public void goOutOfOrder() {
        System.out.println("Pump.goOutOfOrder()");
    }

    /**
     * Attempts to break the pump based on a probability value.
     *
     * @param p probability parameter used by game logic
     */
    public void goOutOfOrderWithProbability(int p) {
        System.out.println("Pump.goOutOfOrderWithProbability(int)");
    }

    /**
     * Marks a connected endpoint as input.
     *
     * @param p endpoint to use as input
     */
    public void setInput(PipeEnd p) {
        System.out.println("Pump.setInput(PipeEnd)");
    }

    /**
     * Marks a connected endpoint as output.
     *
     * @param p endpoint to use as output
     */
    public void setOutput(PipeEnd p) {
        System.out.println("Pump.setOutput(PipeEnd)");
    }

    /**
     * Reports whether the internal tank currently stores water.
     *
     * @return true when the tank is empty
     */
    public boolean isTankEmpty() {
        System.out.print("Pump.isTankEmpty(): boolean");
        return true;
    }

    /**
     * Checks whether the provided endpoint can be connected to this pump.
     *
     * @param pipeEnd endpoint to validate
     * @return true when connection is allowed
     */
    public boolean canConnect(PipeEnd pipeEnd) {
        System.out.print("Pump.canConnect(PipeEnd): boolean");
        return true;
    }

    /**
     * Connects a pipe endpoint to this pump.
     *
     * @param pipeEnd endpoint to connect
     */
    public void connectEnd(PipeEnd pipeEnd) {
        System.out.println("Pump.connectEnd(PipeEnd)");
    }

    /**
     * Disconnects one connected endpoint from this pump.
     *
     * @return disconnected endpoint
     */
    public PipeEnd disconnectEnd() {
        System.out.println("Pump.disconnectEnd(): PipeEnd");
        return null;
    }

    /**
     * Returns a pipe related to this pump connection context.
     *
     * @return connected pipe reference
     */
    public Pipe getEnd() {
        System.out.println("Pump.getEnd(): Pipe");
        return null;
    }

    /**
     * Checks whether a player may occupy this pump.
     *
     * @param player player attempting occupancy
     * @return true when occupancy is allowed
     */
    public boolean canAccept(Player player) {
        System.out.print("Pump.canAccept(Player): boolean");
        return true;
    }

    /**
     * Registers a player as occupying this pump.
     *
     * @param player player to add
     */
    public void addOccupant(Player player) {
        System.out.println("Pump.addOccupant(Player)");
    }

    /**
     * Removes a player from this pump occupancy set.
     *
     * @param player player to remove
     */
    public void removeOccupant(Player player) {
        System.out.println("Pump.removeOccupant(Player)");
    }
}
