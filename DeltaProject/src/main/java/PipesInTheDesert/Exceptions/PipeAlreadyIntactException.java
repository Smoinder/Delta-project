package PipesInTheDesert.Exceptions;

public class PipeAlreadyIntactException extends GameException {
    public PipeAlreadyIntactException() {
        super("Pipe is already intact");
    }
}