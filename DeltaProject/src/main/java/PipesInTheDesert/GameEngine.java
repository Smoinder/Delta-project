package PipesInTheDesert;

import java.util.List;
import java.util.Scanner;

import PipesInTheDesert.Connectors.Pipe;
import PipesInTheDesert.Elements.Cistern;
import PipesInTheDesert.Elements.Pump;
import PipesInTheDesert.Elements.Spring;
import PipesInTheDesert.Players.Player;
import PipesInTheDesert.Players.Plumber;
import PipesInTheDesert.Players.Saboteur;

/**
 * Manages overall game state, turn progression, and water-flow simulation.
 */
public class GameEngine {
    /** Player whose turn is currently active. */
    public Player activePlayer;
    /** All cisterns participating in the game. */
    public List<Cistern> cisterns;
    /** All pumps participating in the game. */
    public List<Pump> pumps;
    /** All springs participating in the game. */
    public List<Spring> springs;
    /** All pipes participating in the game. */
    public List<Pipe> pipes;
    /** Plumber players in turn order. Acts as FIFO */
    public List<Plumber> plumbers;
    /** Saboteur players in turn order. Acts as FIFO */
    public List<Saboteur> saboteurs;
    /** Total score accumulated by plumbers. */
    public int plumbersScore;
    /** Total score accumulated by saboteurs. */
    public int saboteursScore;
    /** Current turn index of the game. */
    public int turnNumber;

    /** Ends the game and finalizes the result. */
    public void endGame() {
        System.out.println("GameEngine.endGame()");
    }

    /** Advances the game to the next player's turn. */
    public void nextTurn() {
        System.out.println("GameEngine.nextTurn()");
    }

    /** Simulates water movement through springs, pipes, pumps, and cisterns. */
    public void simulateWaterFlow() {
        System.out.println("GameEngine.simulateWaterFlow()");
    }

    /** Initializes the game session, configures players. */
    public void startGame() {
        Scanner sc = new Scanner(System.in);

        System.out.println("GameEngine.startGame()");

        System.out.print("Enter number of plumber players: ");
        String input = sc.nextLine();
        if (!input.matches("\\d+")) {
            System.out.println("Invalid input for number of plumber players. Please enter a valid integer.");
            System.err.println("Invalid input for number of plumber players.");
            return;
        }
        int numPlumbers = Integer.parseInt(input);

        if (numPlumbers < 2) {
            System.err.println("Condition Check: check failed, plumbers < 2.");
            return;
        }

        System.out.println("Condition Check: check passed, plumbers >= 2");

        System.out.print("Enter number of saboteur players: ");
        input = sc.nextLine();
        if (!input.matches("\\d+")) {
            System.err.println(
                    "Invalid input for number of saboteur players. Please enter a valid integer.");
            return;
        }
        int numSaboteurs = Integer.parseInt(input);

        if (numSaboteurs < 2) {
            System.err.println("Condition Check: check failed, saboteurs < 2.");
            return;
        }

        System.out.println("Condition Check: check passed, saboteurs >= 2");
    }

    /** Initializes the game field. */
    public void initGameField() {
        System.out.println("GameEngine.initGameField()");

        System.out.println("GameEngine.addSpring()");
        System.out.println("GameEngine.addPipe()");
        System.out.println("GameEngine.addPump()");
        System.out.println("GameEngine.addCistern()");
        System.out.println("GameEngine.setInitialScores()");
    }
}
