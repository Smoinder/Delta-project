package PipesInTheDesert.Elements;

import PipesInTheDesert.Connectors.*;

public class Spring extends ActiveElement {
    public int throughput;

    public boolean canConnect(PipeEnd pipeEnd) {
        System.out.print("Spring.canConnect(PipeEnd): boolean");
        return true;
    }

    public void connectEnd(PipeEnd pipeEnd) {
        System.out.println("Spring.connectEnd(PipeEnd)");
    }

    public PipeEnd disconnectEnd() {
        System.out.println("Spring.disconnectEnd(): PipeEnd");
        return null;
    }

    public Pipe getEnd() {
        System.out.println("Spring.getEnd(): Pipe");
        return null;
    }
}
