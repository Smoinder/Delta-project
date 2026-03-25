package PipesInTheDesert.Interfaces;

import PipesInTheDesert.Connectors.*;

public interface IConnectable {
    public boolean canConnect(PipeEnd pipeEnd);
    public void connectEnd(PipeEnd pipeEnd);
    public PipeEnd disconnectEnd();
    public Pipe getEnd();
}
