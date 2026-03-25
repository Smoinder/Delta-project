package PipesInTheDesert.Elements;

import PipesInTheDesert.Connectors.*;

public class Spring extends ActiveElement {
    public int throughput;

    public boolean canConnect(PipeEnd pipeEnd) {
        System.out.println("canConnect method of Spring class was called with pipeEnd = " + pipeEnd);
        return true;
    }

    public void connectEnd(PipeEnd pipeEnd) {
        System.out.println("connectEnd method of Spring class was called with pipeEnd = " + pipeEnd);
    }

    public PipeEnd disconnectEnd() {
        System.out.println("disconnectEnd method of Spring class was called");
        return null;
    }

    public Pipe getEnd() {
        System.out.println("getEnd method of Spring class was called");
        return null;
    }
}
