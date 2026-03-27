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
        System.out.println("Cistern.generatePipes()");
    }

    public void generatePumps() {
        System.out.println("Cistern.generatePumps()");
    }

    public boolean canConnect(PipeEnd pipeEnd) {
        System.out.print("Cistern.canConnect(): boolean");
        return true;
    }

    public void connectEnd(PipeEnd pipeEnd) {
        System.out.println("Cistern.connectEnd()");
    }

    public PipeEnd disconnectEnd() {
        System.out.println("Cistern.disconnectEnd(): PipeEnd");
        return null;
    }

    public Pipe getEnd() {
        System.out.println("Cistern.getEnd(): Pipe");
        return null;
    }
}
