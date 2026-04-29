package PipesInTheDesert;

import java.util.Scanner;

import PipesInTheDesert.Parsers.CommandsParser;

/**
 * Console entry point for the prototype command loop.
 */
public class Prototype {

    /**
     * Prevents instantiation of this static utility entry point.
     */
    private Prototype() {
        throw new AssertionError("No instantiation for static factory class");
    }

    /**
     * Starts a simple console loop that dispatches a safe subset of commands.
     *
     * @param args ignored command-line arguments
     */
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            boolean shouldExit = CommandsParser.handleCommand(gameEngine, line);
            if (shouldExit) {
                break;
            }
        }
    }
}
