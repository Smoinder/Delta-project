package PipesInTheDesert.Elements;

import java.awt.*;
import java.util.List;

import PipesInTheDesert.Connectors.*;
import PipesInTheDesert.Constants;

/**
 * Active source element where water enters the pipe system. During water-flow
 * simulation, pipes connected to a spring receive water from it according to
 * the spring's throughput.
 */
public class Spring extends ActiveElement {
    /** Counter for unique spring IDs (type-specific). */
    private static int _count = 0;
    /** Unique identifier of this spring (1-indexed). */
    private final int _id = ++_count;

    /** Amount of water emitted during water-flow simulation. */
    private int _throughput;

    /**
     * Creates a new spring with the default throughput configured in
     * {@link Constants#SPRING_DEFAULT_THROUGHPUT}.
     */
    public Spring() {
        this._throughput = Constants.SPRING_DEFAULT_THROUGHPUT;
    }

    /**
     * Gets the unique ID of this spring.
     *
     * @return spring ID (1-indexed)
     */
    public int getId() {
        return this._id;
    }

    /**
     * @return amount of water emitted by this spring during water-flow
     *     simulation
     */
    public int getThroughput() {
        return this._throughput;
    }

    /**
     * @param throughput new throughput value (must be non-negative)
     */
    public void setThroughput(int throughput) {
        this._throughput = throughput;
    }

    /**
     * @param pipeEnd endpoint to validate
     * @return {@code true} when the endpoint is non-null and not already
     *     connected to this spring
     */
    @Override
    public boolean canConnect(PipeEnd pipeEnd) {
        if (pipeEnd == null) {
            return false;
        }
        return !this.hasConnectedPipe(pipeEnd);
    }

    /**
     * Connects a pipe endpoint to this spring.
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
     * Disconnects one currently connected endpoint.
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
     * Returns any pipe currently connected to this spring.
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

    @Override
    public Rectangle getBounds(){
        return super.getBounds();
    }
}
