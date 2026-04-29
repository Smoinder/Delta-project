package PipesInTheDesert.Exceptions;

public class PlayerNotOnPipeException extends GameException {
    public PlayerNotOnPipeException() {
        super("Player is not on this pipe");
    }
}