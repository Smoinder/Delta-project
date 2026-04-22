package PipesInTheDesert.Exceptions;

public class AlreadyOccupiedException extends Exception {
    public AlreadyOccupiedException() {
        super("This object is already occupied by another player.");
    }
}
