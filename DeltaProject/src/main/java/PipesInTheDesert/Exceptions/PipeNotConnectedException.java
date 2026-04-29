package PipesInTheDesert.Exceptions;

public class PipeNotConnectedException extends GameException {
    public PipeNotConnectedException() {
        super("Pipe end is not connected");
    }
}