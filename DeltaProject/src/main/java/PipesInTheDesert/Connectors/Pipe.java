package PipesInTheDesert.Connectors;

import PipesInTheDesert.Interfaces.IOccupiable;
import PipesInTheDesert.Players.Player;

/**
 * Connector that transports water between active elements through two
 * endpoints.
 */
public class Pipe implements IOccupiable {
    /** Counter for unique pipe IDs (type-specific). */
    private static int _count = 0;
    /** Unique identifier of this pipe (1-indexed). */
    private final int _id = ++_count;

    /** First endpoint of the pipe. */
    public PipeEnd end1;
    /** Second endpoint of the pipe. */
    public PipeEnd end2;
    /** True when the pipe is punctured and leaking. */
    public boolean leaking;
    /** True when water is currently flowing in the pipe. */
    public boolean waterFlowing;
    /** Player currently occupying the pipe. */
    public Player occupant;
    /** Pipe length value used by game logic. */
    public int length;


    /**
     * Gets the unique ID of this pipe.
     *
     * @return pipe ID (1-indexed)
     */
    public int getId() {
        return this._id;
    }

    /** Marks this pipe as leaking. */
    public void puncture() {
        System.out.println("Pipe.puncture()");
    }

    /** Repairs this pipe and restores non-leaking state. */
    public void repair() {
        System.out.println("Pipe.repair()");
    }

    /**
     * Returns the opposite endpoint relative to the provided endpoint.
     *
     * @param end endpoint already known on this pipe
     * @return the other endpoint of this pipe
     */
    public PipeEnd getOtherEnd(PipeEnd end) {
        System.out.println("Pipe.getOtherEnd(PipeEnd): PipeEnd");
        return null;
    }

    /**
     * Checks whether the given player can occupy this pipe.
     *
     * @param player player attempting to occupy the pipe
     * @return true when occupancy is allowed
     */
    public boolean canAccept(Player player) {
        System.out.print("Pipe.canAccept(Player): boolean ");
        return true;
    }

    /**
     * Registers the given player as the current occupant of this pipe.
     *
     * @param player player to register
     */
    public void addOccupant(Player player) {
        System.out.println("Pipe.addOccupant(Player)");
    }

    /**
     * Removes the given player from this pipe.
     *
     * @param player player to remove
     */
    public void removeOccupant(Player player) {
        System.out.println("Pipe.removeOccupant(Player)");
    }
}
