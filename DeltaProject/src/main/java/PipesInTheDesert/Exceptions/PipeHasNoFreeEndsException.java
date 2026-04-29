package PipesInTheDesert.Exceptions;

public class PipeHasNoFreeEndsException extends GameException {
    public PipeHasNoFreeEndsException() {
        super("Pipe has no free ends");
    }
}
