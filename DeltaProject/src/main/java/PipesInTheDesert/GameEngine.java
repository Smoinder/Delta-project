package PipesInTheDesert;

import java.util.List;

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
    private Mode mode;

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Mode getMode() {
        return mode;
    }

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
        System.out.println("GameEngine.startGame()");
    }

    /** Initializes the game field. */
    public void initGameField() {
        System.out.println("GameEngine.initGameField()");
    }
}
