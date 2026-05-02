package PipesInTheDesert.Elements;

import java.util.ArrayList;
import java.util.List;

import PipesInTheDesert.Connectors.*;
import PipesInTheDesert.Constants;
import PipesInTheDesert.Interfaces.IOccupiable;
import PipesInTheDesert.Players.Player;

/**
 * Destination active element where water is collected and where new pipes and
 * pumps are manufactured for plumbers to extend the network.
 *
 * <p>This class implements {@link IOccupiable} so plumbers can be located on
 * a cistern when picking up a freshly manufactured pump (use case 6). The
 * §4 design originally omitted this; the inconsistency is acknowledged in
 * §4.6 Task 7 of the team docs and is resolved here.
 */
public class Cistern extends ActiveElement implements IOccupiable {
    /** Counter for unique cistern IDs (type-specific). */
    private static int _count = 0;
    /** Unique identifier of this cistern (1-indexed). */
    private final int _id = ++_count;

    /** Current accumulated progress toward creating new pipes. */
    private double _pipeGenerationState;
    /** Pipe production speed applied at the end of each turn. */
    private double _pipeManufactureSpeed;
    /** Current accumulated progress toward creating new pumps. */
    private double _pumpGenerationState;
    /** Pump production speed applied at the end of each turn. */
    private double _pumpManufactureSpeed;
    /** Pipes currently manufactured and stored by this cistern. */
    private final List<Pipe> _generatedPipes;
    /** Pumps currently manufactured and stored by this cistern. */
    private final List<Pump> _generatedPumps;
    /** Players currently standing on this cistern. */
    private final List<Player> _occupants;

    /**
     * Creates a new cistern with default manufacture speeds, empty production
     * progress, and no stored pipes, pumps, or occupants.
     */
    public Cistern() {
        this._pipeGenerationState = 0.0;
        this._pipeManufactureSpeed = Constants.CISTERN_DEFAULT_PIPE_MANUFACTURE_SPEED;
        this._pumpGenerationState = 0.0;
        this._pumpManufactureSpeed = Constants.CISTERN_DEFAULT_PUMP_MANUFACTURE_SPEED;
        this._generatedPipes = new ArrayList<>();
        this._generatedPumps = new ArrayList<>();
        this._occupants = new ArrayList<>();
    }

    /**
     * Gets the unique ID of this cistern.
     *
     * @return cistern ID (1-indexed)
     */
    public int getId() {
        return this._id;
    }

    /**
     * @return current accumulated pipe-generation progress
     */
    public double getPipeGenerationState() {
        return this._pipeGenerationState;
    }

    /**
     * @param state new pipe-generation progress
     */
    public void setPipeGenerationState(double state) {
        this._pipeGenerationState = state;
    }

    /**
     * @return pipes manufactured per turn
     */
    public double getPipeManufactureSpeed() {
        return this._pipeManufactureSpeed;
    }

    /**
     * @param speed new pipes-per-turn manufacture speed (must be non-negative)
     */
    public void setPipeManufactureSpeed(double speed) {
        this._pipeManufactureSpeed = speed;
    }

    /**
     * @return current accumulated pump-generation progress
     */
    public double getPumpGenerationState() {
        return this._pumpGenerationState;
    }

    /**
     * @param state new pump-generation progress
     */
    public void setPumpGenerationState(double state) {
        this._pumpGenerationState = state;
    }

    /**
     * @return pumps manufactured per turn
     */
    public double getPumpManufactureSpeed() {
        return this._pumpManufactureSpeed;
    }

    /**
     * @param speed new pumps-per-turn manufacture speed (must be non-negative)
     */
    public void setPumpManufactureSpeed(double speed) {
        this._pumpManufactureSpeed = speed;
    }

    /**
     * @return mutable list of pipes currently stored at this cistern
     */
    public List<Pipe> getGeneratedPipes() {
        return this._generatedPipes;
    }

    /**
     * @return mutable list of pumps currently stored at this cistern
     */
    public List<Pump> getGeneratedPumps() {
        return this._generatedPumps;
    }

    /**
     * @return mutable list of players currently on this cistern
     */
    public List<Player> getOccupants() {
        return this._occupants;
    }

    /**
     * Advances pipe manufacturing by {@link #getPipeManufactureSpeed()}. While
     * the accumulated progress is at least one full unit, instantiates a new
     * {@link Pipe} whose first endpoint is connected to this cistern and adds
     * it to {@link #getGeneratedPipes()}.
     */
    public void generatePipes() {
        this._pipeGenerationState += this._pipeManufactureSpeed;
        while (this._pipeGenerationState >= 1.0) {
            Pipe pipe = new Pipe();
            PipeEnd cisternSide = new PipeEnd();
            cisternSide.setPipe(pipe);
            cisternSide.setConnectedElement(this);
            PipeEnd freeSide = new PipeEnd();
            freeSide.setPipe(pipe);
            pipe.setEnd1(cisternSide);
            pipe.setEnd2(freeSide);
            this.addConnectedPipe(cisternSide);
            this._generatedPipes.add(pipe);
            this._pipeGenerationState -= 1.0;
        }
    }

    /**
     * Advances pump manufacturing by {@link #getPumpManufactureSpeed()}. While
     * the accumulated progress is at least one full unit, instantiates a new
     * {@link Pump} and adds it to {@link #getGeneratedPumps()} so plumbers
     * can later pick it up.
     */
    public void generatePumps() {
        this._pumpGenerationState += this._pumpManufactureSpeed;
        while (this._pumpGenerationState >= 1.0) {
            this._generatedPumps.add(new Pump());
            this._pumpGenerationState -= 1.0;
        }
    }

    /**
     * @param pipeEnd endpoint to validate
     * @return {@code true} when the endpoint is non-null and not already
     *     connected to this cistern
     */
    @Override
    public boolean canConnect(PipeEnd pipeEnd) {
        if (pipeEnd == null) {
            return false;
        }
        return !this.hasConnectedPipe(pipeEnd);
    }

    /**
     * Connects a pipe endpoint to this cistern.
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
     * Disconnects one currently connected pipe endpoint.
     *
     * @return the disconnected endpoint, or {@code null} if none were connected
     */
    @Override
    public PipeEnd disconnectEnd() {
        List<PipeEnd> ends = this.getConnectedPipes();
        if (ends.isEmpty()) {
            return null;
        }
        PipeEnd end = ends.get(0);
        this.removeConnectedPipe(end);
        return end;
    }

    /**
     * Returns any pipe currently connected to this cistern.
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
        return ends.get(0).getPipe();
    }

    /**
     * Cisterns allow multiple simultaneous occupants.
     *
     * @param player player attempting occupancy
     * @return {@code true} when the player is non-null
     */
    @Override
    public boolean canAccept(Player player) {
        return player != null;
    }

    /**
     * Registers a player as occupying this cistern. Idempotent: re-adding the
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
     * Removes a player from this cistern's occupancy set.
     *
     * @param player player to remove
     */
    @Override
    public void removeOccupant(Player player) {
        this._occupants.remove(player);
    }
}
