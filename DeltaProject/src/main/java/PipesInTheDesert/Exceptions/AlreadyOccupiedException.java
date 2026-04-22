package PipesInTheDesert.Exceptions;

public class AlreadyOccupiedException extends GameException {
    public AlreadyOccupiedException() {
        super("This object is already occupied by another player.");
    }
}
