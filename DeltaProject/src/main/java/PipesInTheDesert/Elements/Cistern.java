package PipesInTheDesert.Elements;

import java.util.List;
import PipesInTheDesert.Connectors.*;

/**
 * Destination active element where water is collected and where new pipes and pumps are manufactured.
 */
public class Cistern extends ActiveElement {
    /** Current accumulated progress toward creating new pipes. */
    public double pipeGenerationState;
    /** Pipe production speed applied at the end of each turn. */
    public double pipeManufactureSpeed;
    /** Current accumulated progress toward creating new pumps. */
    public double pumpGenerationState;
    /** Pump production speed applied at the end of each turn. */
    public double pumpManufactureSpeed;
    /** Pipes currently manufactured and stored by this cistern. */
    public List<Pipe> generatedPipes;
    /** Pumps currently manufactured and stored by this cistern. */
    public List<Pump> generatedPumps;

    /** Advances pipe manufacturing and creates new pipe instances when progress reaches full units. */
    public void generatePipes() {
        System.out.println("Cistern.generatePipes()");
    }

    /** Advances pump manufacturing and creates new pump instances when progress reaches full units. */
    public void generatePumps() {
        System.out.println("Cistern.generatePumps()");
    }

    /**
     * Checks whether the provided endpoint can be connected to this cistern.
     *
     * @param pipeEnd endpoint to validate
     * @return true when the connection is allowed
     */
    public boolean canConnect(PipeEnd pipeEnd) {
        System.out.print("Cistern.canConnect(PipeEnd): boolean");
        return true;
    }

    /**
     * Connects a pipe endpoint to this cistern.
     *
     * @param pipeEnd endpoint to connect
     */
    public void connectEnd(PipeEnd pipeEnd) {
        System.out.println("Cistern.connectEnd(PipeEnd)");
    }

    /**
     * Disconnects one currently connected pipe endpoint.
     *
     * @return a disconnected endpoint
     */
    public PipeEnd disconnectEnd() {
        System.out.println("Cistern.disconnectEnd(): PipeEnd");
        return null;
    }

    /**
     * Returns a pipe related to this cistern connection context.
     *
     * @return a connected pipe reference
     */
    public Pipe getEnd() {
        System.out.println("Cistern.getEnd(): Pipe");
        return null;
    }
}
