package PipesInTheDesert.Depracated;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import PipesInTheDesert.Constants;
import PipesInTheDesert.Depracated.Parsers.CommandsParser;
import PipesInTheDesert.GameEngine;

/**
 * Console entry point for the prototype command loop.
 * Supports two modes:
 * 1. Interactive: java Prototype (reads from stdin, outputs with [OUTPUT] prefix to stdout)
 * 2. File-based: java Prototype input.txt output.txt (reads from file, writes to file without prefix)
 */
public class Prototype {

    /**
     * Prevents instantiation of this static utility entry point.
     */
    private Prototype() {
        throw new AssertionError("No instantiation for static factory class");
    }

    /**
     * Starts the console loop in either interactive or file-based mode based on arguments.
     * Interactive mode (0 args): reads from stdin, prefixes output with [OUTPUT]
     * File mode (2 args): reads from inputFile, writes to outputFile without prefix
     *
     * @param args command-line arguments:
     *             - no args: interactive mode
     *             - inputFile outputFile: file-based mode
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            runInteractiveMode();
        } else if (args.length == 2) {
            runFileMode(args[0], args[1]);
        } else {
            System.err.println("Usage: java Prototype [inputFile outputFile]");
            System.exit(1);
        }
    }

    /**
     * Runs the game in interactive mode.
     * Reads commands from stdin and outputs results with [OUTPUT] prefix.
     */
    private static void runInteractiveMode() {
        GameEngine gameEngine = new GameEngine();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;

            boolean shouldExit = CommandsParser.handleCommand(gameEngine, line);
            if (shouldExit) {
                break;
            }
        }
        scanner.close();
    }

    /**
     * Runs the game in file-based mode.
     * Reads commands from inputFile and writes results to outputFile without prefix.
     *
     * @param inputFileName path to input file containing commands
     * @param outputFileName path to output file for command results
     */
    private static void runFileMode(String inputFileName, String outputFileName) {
        try {
            GameEngine gameEngine = new GameEngine();

            try (BufferedReader reader = new BufferedReader(new FileReader(Constants.TESTS_DIRECTORY + "/" + inputFileName));
                 PrintStream outputStream = new PrintStream(new File(Constants.TESTS_DIRECTORY + "/" + outputFileName))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty()) continue;

                    // File mode: no output prefix, output to file
                    boolean shouldExit = CommandsParser.handleCommand(gameEngine, line, outputStream, "");
                    if (shouldExit) {
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.println("IO error: " + e.getMessage());
            System.exit(1);
        }
    }
}
