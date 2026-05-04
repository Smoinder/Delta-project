package PipesInTheDesert.Connectors;

import PipesInTheDesert.Interfaces.IOccupiable;
import PipesInTheDesert.Players.Player;
import PipesInTheDesert.Interfaces.IConnectable;
import PipesInTheDesert.Exceptions.PipeAlreadyLeakingException;
import PipesInTheDesert.Exceptions.PipeAlreadyIntactException;
import PipesInTheDesert.Exceptions.PipeHasNoFreeEndsException;
import PipesInTheDesert.Exceptions.PlayerNotOnPipeException;
import PipesInTheDesert.Exceptions.InvalidArgumentException;
import PipesInTheDesert.Constants;

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
    private PipeEnd end1;
    /** Second endpoint of the pipe. */
    private PipeEnd end2;
    /** True when the pipe is punctured and leaking. */
    private boolean leaking;
    /** True when water is currently flowing in the pipe. */
    private boolean waterFlowing;
    /** Player currently occupying the pipe. */
    private Player occupant;
    /** Pipe length value used by game logic. */
    private int length;
    /** Amount of water in the pipe. */
    private int waterAmount = 0;

    /**
     * Constructor. Initializes pipe ends and sets them to belong to this pipe.
     */
    public Pipe() {
        this.end1 = new PipeEnd();
        this.end2 = new PipeEnd();
        this.end1.setPipe(this);
        this.end2.setPipe(this);
        this.leaking = false;
        this.waterFlowing = false;
        this.occupant = null;
        this.length = 1;
    }

    /**
     * Gets the first endpoint of the pipe.
     * 
     * @return the first endpoint
     */
    public PipeEnd getEnd1() {
        return end1;
    }

    /**
     * Sets the first endpoint of the pipe.
     * 
     * @param end1 the first endpoint to set
     */
    public void setEnd1(PipeEnd end1) {
        this.end1 = end1;
    }

    /**
     * Gets the second endpoint of the pipe.
     * 
     * @return the second endpoint
     */
    public PipeEnd getEnd2() {
        return end2;
    }

    /**
     * Sets the second endpoint of the pipe.
     * 
     * @param end2 the second endpoint to set
     */
    public void setEnd2(PipeEnd end2) {
        this.end2 = end2;
    }

    /**
     * Checks whether the pipe is leaking.
     * 
     * @return true if leaking, false otherwise
     */
    public boolean isLeaking() {
        return leaking;
    }

    /**
     * Sets the leaking state of the pipe.
     * 
     * @param leaking true to mark as leaking, false otherwise
     */
    public void setLeaking(boolean leaking) {
        this.leaking = leaking;
    }

    /**
     * Checks whether water is flowing through the pipe.
     * 
     * @return true if water is flowing, false otherwise
     */
    public boolean isWaterFlowing() {
        return waterFlowing;
    }

    /**
     * Sets the amount of water in the pipe and updates the flowing state
     * accordingly.
     * 
     * @return the amount of water
     */
    public void setWaterAmount(int amount) {
        if (amount > 0 && amount <= Constants.PIPE_CAPACITY) {
            this.waterAmount = amount;
            this.waterFlowing = true;
        } else if (amount <= 0) {
            this.waterAmount = 0;
            this.waterFlowing = false;
        }
    }

    /**
     * Adds the specified amount of water to the pipe, respecting capacity limits.
     * @param amount the amount of water to add (must be positive and not exceed capacity)
     */
    public void addWater(int amount) {
        if (amount > 0 && this.waterAmount + amount <= Constants.PIPE_CAPACITY) {
            this.waterAmount += amount;
            this.waterFlowing = true;
        }
    }

    /**
     * Gets the amount of water currently in the pipe.
     * 
     * @return the amount of water in the pipe
     */
    public int getWaterAmount() {
        return waterAmount;
    }

    /**
     * Gets the player currently occupying the pipe.
     * 
     * @return the occupant player, or null if unoccupied
     */
    public Player getOccupant() {
        return occupant;
    }

    /**
     * Sets the player occupying the pipe.
     * 
     * @param occupant the player to set as occupant
     */
    public void setOccupant(Player occupant) {
        this.occupant = occupant;
    }

    /**
     * Gets the length of the pipe.
     * 
     * @return the pipe length
     */
    public int getLength() {
        return length;
    }

    /**
     * Sets the length of the pipe.
     * 
     * @param length the length to set
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * Gets the unique ID of this pipe.
     *
     * @return pipe ID (1-indexed)
     */
    public int getId() {
        return this._id;
    }

    /**
     * Marks this pipe as leaking.
     * 
     * @throws PipeAlreadyLeakingException if the pipe is already leaking
     */
    public void puncture() throws PipeAlreadyLeakingException {
        if (leaking) {
            throw new PipeAlreadyLeakingException("Pipe is already leaking");
        }
        this.leaking = true;
    }

    /**
     * Repairs this pipe and restores non-leaking state.
     * 
     * @throws PipeAlreadyIntactException if the pipe is already intact (not
     *                                    leaking)
     */
    public void repair() throws PipeAlreadyIntactException {
        if (!leaking) {
            throw new PipeAlreadyIntactException("Pipe is already intact");
        }
        this.leaking = false;
    }

    /**
     * Returns the opposite endpoint relative to the provided endpoint.
     *
     * @param end endpoint already known on this pipe
     * @return the other endpoint of this pipe, or null if the end does not belong
     *         to this pipe
     */
    public PipeEnd getOtherEnd(PipeEnd end) {
        if (end == end1) {
            return end2;
        }
        if (end == end2) {
            return end1;
        }
        return null;
    }

    /**
     * Checks whether the given player can occupy this pipe.
     *
     * @param player player attempting to occupy the pipe
     * @return true if the pipe is free (no occupant), false otherwise
     */
    public boolean canAccept(Player player) {
        return occupant == null;
    }

    /**
     * Registers the given player as the current occupant of this pipe.
     *
     * @param player player to register
     */
    public void addOccupant(Player player) {
        this.occupant = player;
    }

    /**
     * Removes the given player from this pipe.
     *
     * @param player player to remove
     * @throws PlayerNotOnPipeException if the player is not the current occupant
     */
    public void removeOccupant(Player player) throws PlayerNotOnPipeException {
        if (this.occupant == player) {
            this.occupant = null;
        } else {
            throw new PlayerNotOnPipeException("Player is not on this pipe");
        }
    }

    /**
     * Connects a free end of this pipe to the specified element.
     * 
     * @param element the element to connect to (must implement IConnectable)
     * @throws PipeHasNoFreeEndsException if both ends are already connected
     * @throws InvalidArgumentException   if the connection is not possible
     */
    public void connectToElement(IConnectable element) throws PipeHasNoFreeEndsException, InvalidArgumentException {
        if (end1.isFree()) {
            end1.connect(element);
        } else if (end2.isFree()) {
            end2.connect(element);
        } else {
            throw new PipeHasNoFreeEndsException("Pipe has no free ends");
        }
    }

}
