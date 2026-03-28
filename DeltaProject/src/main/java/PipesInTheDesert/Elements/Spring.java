package PipesInTheDesert.Elements;

import PipesInTheDesert.Connectors.*;

/**
 * Active source element where water enters the pipe system.
 */
public class Spring extends ActiveElement {
    /** Amount of water emitted during water-flow simulation. */
    public int throughput;

    /**
     * Checks whether the provided endpoint can connect to this spring.
     *
     * @param pipeEnd endpoint to validate
     * @return true when connection is allowed
     */
    public boolean canConnect(PipeEnd pipeEnd) {
        System.out.print("Spring.canConnect(PipeEnd): boolean");
        return true;
    }

    /**
     * Connects a pipe endpoint to this spring.
     *
     * @param pipeEnd endpoint to connect
     */
    public void connectEnd(PipeEnd pipeEnd) {
        System.out.println("Spring.connectEnd(PipeEnd)");
    }

    /**
     * Disconnects one endpoint from this spring.
     *
     * @return disconnected endpoint
     */
    public PipeEnd disconnectEnd() {
        System.out.println("Spring.disconnectEnd(): PipeEnd");
        return null;
    }

    /**
     * Returns a pipe related to this spring connection context.
     *
     * @return connected pipe reference
     */
    public Pipe getEnd() {
        System.out.println("Spring.getEnd(): Pipe");
        return null;
    }
}
