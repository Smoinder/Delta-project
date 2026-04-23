package PipesInTheDesert;

import java.util.ArrayList;
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
    private Player _activePlayer;
    /** All cisterns participating in the game. */
    private final List<Cistern> _cisterns = new ArrayList<>();
    /** All pumps participating in the game. */
    private final List<Pump> _pumps = new ArrayList<>();
    /** All springs participating in the game. */
    private final List<Spring> _springs = new ArrayList<>();
    /** All pipes participating in the game. */
    private final List<Pipe> _pipes = new ArrayList<>();
    /** Plumber players in turn order. Acts as FIFO */
    private final List<Plumber> _plumbers = new ArrayList<>();
    /** Saboteur players in turn order. Acts as FIFO */
    private final List<Saboteur> _saboteurs = new ArrayList<>();
    /** Total score accumulated by plumbers. */
    public int plumbersScore;
    /** Total score accumulated by saboteurs. */
    public int saboteursScore;
    /** Current turn index of the game. */
    public int turnNumber;

    private Mode mode;

    public void setActivePlayer(Player p) {
        this._activePlayer = p;
    }

    public Player getActivePlayer() {
        return this._activePlayer;
    }

    public void addCistern(Cistern c) {
        this._cisterns.add(c);
    }

    public List<Cistern> getCisterns() {
        return  this._cisterns;
    }

    public void addPump(Pump p) {
        this._pumps.add(p);
    }

    public List<Pump> getPumps() {
        return this._pumps;
    }

    public void addSpring(Spring s) {
        this._springs.add(s);
    }

    public List<Spring> getSprings() {
        return this._springs;
    }

    public void addPipe(Pipe p) {
        this._pipes.add(p);
    }

    public List<Pipe> getPipes() {
        return this._pipes;
    }

    public void addPlumber(Plumber p) {
        this._plumbers.add(p);
    }

    public List<Plumber> getPlumbers() {
        return this._plumbers;
    }

    public void addSaboteur(Saboteur s) {
        this._saboteurs.add(s);
    }

    public List<Saboteur> getSaboteurs() {
        return this._saboteurs;
    }

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
