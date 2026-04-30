package PipesInTheDesert.Elements;

import java.util.ArrayList;
import java.util.List;

import PipesInTheDesert.Connectors.*;
import PipesInTheDesert.Constants;
import PipesInTheDesert.Players.Player;
import PipesInTheDesert.Interfaces.IOccupiable;

/**
 * Active element that moves water from an input pipe to an output pipe and can
 * be occupied by players.
 *
 * <p>A pump enforces a "single input, single output" invariant: at most one
 * connected pipe end is the input and at most one is the output. Any other
 * connected pipes are <em>closed pipes</em> and do not transport water
 * (Glossary §2.5).
 *
 * <p>The list of connected pipe ends is inherited from {@link ActiveElement}
 * and accessed through its protected list-management methods.
 */
public class Pump extends ActiveElement implements IOccupiable {
    /** Counter for unique pump IDs (type-specific). */
    private static int _count = 0;
    /** Unique identifier of this pump (1-indexed). */
    private final int _id = ++_count;

    /** Maximum number of endpoints that may be connected. */
    private int _maxConnectedPipes;
    /** Endpoint currently selected as pump input. */
    private PipeEnd _inputPipe;
    /** Endpoint currently selected as pump output. */
    private PipeEnd _outputPipe;
    /** Functional state flag; false means the pump is out of order. */
    private boolean _isHealthy;
    /** Players currently occupying this pump. */
    private final List<Player> _occupants;
    /** Current stored water amount in the pump tank. */
    private double _waterTankLevel;

    /**
     * Creates a new healthy pump with default capacity, no occupants, an empty
     * tank, and no input/output assignments.
     */
    public Pump() {
        this._maxConnectedPipes = Constants.PUMP_DEFAULT_MAX_CONNECTED_PIPES;
        this._inputPipe = null;
        this._outputPipe = null;
        this._isHealthy = true;
        this._occupants = new ArrayList<>();
        this._waterTankLevel = 0.0;
    }

    /**
     * Gets the unique ID of this pump.
     *
     * @return pump ID (1-indexed)
     */
    public int getId() {
        return this._id;
    }

    /**
     * @return maximum number of pipe ends that may be connected to this pump
     */
    public int getMaxConnectedPipes() {
        return this._maxConnectedPipes;
    }

    /**
     * @param maxConnectedPipes new connection capacity (must be non-negative)
     */
    public void setMaxConnectedPipes(int maxConnectedPipes) {
        this._maxConnectedPipes = maxConnectedPipes;
    }

    /**
     * @return current input endpoint, or {@code null} if none assigned
     */
    public PipeEnd getInputPipe() {
        return this._inputPipe;
    }

    /**
     * @param inputPipe endpoint to use as input; the endpoint must already be
     *     connected to this pump or it is ignored
     */
    public void setInputPipe(PipeEnd inputPipe) {
        this.setInput(inputPipe);
    }

    /**
     * @return current output endpoint, or {@code null} if none assigned
     */
    public PipeEnd getOutputPipe() {
        return this._outputPipe;
    }

    /**
     * @param outputPipe endpoint to use as output; the endpoint must already
     *     be connected to this pump or it is ignored
     */
    public void setOutputPipe(PipeEnd outputPipe) {
        this.setOutput(outputPipe);
    }

    /**
     * @return {@code true} when the pump is functional (water flows through it)
     */
    public boolean isHealthy() {
        return this._isHealthy;
    }

    /**
     * @param healthy new functional state
     */
    public void setHealthy(boolean healthy) {
        this._isHealthy = healthy;
    }

    /**
     * @return mutable list of players currently on this pump
     */
    public List<Player> getOccupants() {
        return this._occupants;
    }

    /**
     * @return current water level in the internal tank
     */
    public double getWaterTankLevel() {
        return this._waterTankLevel;
    }

    /**
     * @param level new water-tank level (must be non-negative)
     */
    public void setWaterTankLevel(double level) {
        this._waterTankLevel = level;
    }

    /** Restores the pump to working state. */
    public void fix() {
        this._isHealthy = true;
    }

    /** Sets the pump to non-functional state. */
    public void goOutOfOrder() {
        this._isHealthy = false;
    }

    /**
     * Possibly breaks the pump based on a probability percentage.
     *
     * @param p probability in the range [0, 100] that the pump goes out of order
     */
    public void goOutOfOrderWithProbability(int p) {
        if (Math.random() * 100 < p) {
            this.goOutOfOrder();
        }
    }

    /**
     * Marks a connected endpoint as input. The endpoint must already be
     * connected to this pump; calls with unknown endpoints are ignored.
     *
     * @param p endpoint to use as input
     */
    public void setInput(PipeEnd p) {
        if (p == null || !this.hasConnectedPipe(p)) {
            return;
        }
        this._inputPipe = p;
    }

    /**
     * Marks a connected endpoint as output. The endpoint must already be
     * connected to this pump; calls with unknown endpoints are ignored.
     *
     * @param p endpoint to use as output
     */
    public void setOutput(PipeEnd p) {
        if (p == null || !this.hasConnectedPipe(p)) {
            return;
        }
        this._outputPipe = p;
    }

    /**
     * Reports whether the internal tank currently stores water.
     *
     * @return {@code true} when the tank is empty
     */
    public boolean isTankEmpty() {
        return this._waterTankLevel <= 0.0;
    }

    /**
     * Checks whether the provided endpoint can be connected to this pump.
     * The pump rejects null endpoints, endpoints already connected to it, and
     * any endpoint that would exceed {@link #getMaxConnectedPipes()}.
     *
     * @param pipeEnd endpoint to validate
     * @return {@code true} when connection is allowed
     */
    @Override
    public boolean canConnect(PipeEnd pipeEnd) {
        if (pipeEnd == null) {
            return false;
        }
        if (this.hasConnectedPipe(pipeEnd)) {
            return false;
        }
        return this.getConnectedPipes().size() < this._maxConnectedPipes;
    }

    /**
     * Connects a pipe endpoint to this pump. No-op when the endpoint is null
     * or already connected.
     *
     * @param pipeEnd endpoint to connect
     */
    @Override
    public void connectEnd(PipeEnd pipeEnd) {
        if (pipeEnd == null) {
            return;
        }
        this.addConnectedPipe(pipeEnd);
    }

    /**
     * Disconnects one currently connected endpoint from this pump. If the
     * disconnected endpoint is the current input or output, the corresponding
     * selection is cleared.
     *
     * @return the disconnected endpoint, or {@code null} if no pipe ends were
     *     connected
     */
    @Override
    public PipeEnd disconnectEnd() {
        List<PipeEnd> ends = this.getConnectedPipes();
        if (ends.isEmpty()) {
            return null;
        }
        PipeEnd end = ends.get(0);
        this.removeConnectedPipe(end);
        if (end == this._inputPipe) {
            this._inputPipe = null;
        }
        if (end == this._outputPipe) {
            this._outputPipe = null;
        }
        return end;
    }

    /**
     * Returns any pipe currently connected to this pump.
     *
     * @return the pipe owning the first connected endpoint, or {@code null}
     *     if no pipes are connected
     */
    @Override
    public Pipe getEnd() {
        List<PipeEnd> ends = this.getConnectedPipes();
        if (ends.isEmpty()) {
            return null;
        }
        return ends.get(0).pipe;
    }

    /**
     * Pumps allow multiple simultaneous occupants.
     *
     * @param player player attempting occupancy
     * @return {@code true} when the player is non-null and the pump is in a
     *     state that accepts occupancy
     */
    @Override
    public boolean canAccept(Player player) {
        return player != null;
    }

    /**
     * Registers a player as occupying this pump. Idempotent: re-adding the
     * same player has no effect.
     *
     * @param player player to add
     */
    @Override
    public void addOccupant(Player player) {
        if (player == null || this._occupants.contains(player)) {
            return;
        }
        this._occupants.add(player);
    }

    /**
     * Removes a player from this pump's occupancy set.
     *
     * @param player player to remove
     */
    @Override
    public void removeOccupant(Player player) {
        this._occupants.remove(player);
    }
}
