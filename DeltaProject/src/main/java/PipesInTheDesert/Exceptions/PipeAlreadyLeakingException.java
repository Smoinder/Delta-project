package PipesInTheDesert.Exceptions;

public class PipeAlreadyLeakingException extends GameException {
    public PipeAlreadyLeakingException() {
        super("Pipe is already leaking");
    }
}