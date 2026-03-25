package PipesInTheDesert.Elements;

import java.util.List;
import PipesInTheDesert.Connectors.*;

public class Cistern extends ActiveElement {
    public double pipeGenerationState;
    public double pipeManufactureSpeed;
    public double pumpGenerationState;
    public double pumpManufactureSpeed;
    public List<Pipe> generatedPipes;
    public List<Pump> generatedPumps;

    public void generatePipes() {
        System.out.println("generatePipes method of Cistern class was called");
    }

    public void generatePumps() {
        System.out.println("generatePumps method of Cistern class was called");
    }

    public boolean canConnect(PipeEnd pipeEnd) {
        System.out.println("canConnect method of Cistern class was called with pipeEnd = " + pipeEnd);
        return true;
    }

    public void connectEnd(PipeEnd pipeEnd) {
        System.out.println("connectEnd method of Cistern class was called with pipeEnd = " + pipeEnd);
    }

    public PipeEnd disconnectEnd() {
        System.out.println("disconnectEnd method of Cistern class was called");
        return null;
    }

    public Pipe getEnd() {
        System.out.println("getEnd method of Cistern class was called");
        return null;
    }
}
