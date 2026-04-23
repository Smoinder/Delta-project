package PipesInTheDesert;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import PipesInTheDesert.Connectors.Pipe;
import PipesInTheDesert.Elements.Cistern;
import PipesInTheDesert.Elements.Pump;
import PipesInTheDesert.Elements.Spring;
import PipesInTheDesert.Exceptions.GameAlreadyStartedException;
import PipesInTheDesert.Exceptions.InvalidArgumentException;
import PipesInTheDesert.Exceptions.WrongGameModeException;
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

    private Mode _mode = Mode.PLAYER;

    private boolean _started = false;

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
        this._mode = mode;
    }

    public Mode getMode() {
        return this._mode;
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
    public void startGame(int numPlumbers, int numSaboteurs) throws WrongGameModeException, GameAlreadyStartedException, InvalidArgumentException {
        if (!this._started) {
            throw new GameAlreadyStartedException("Game already started");
        }
        if (this._mode != Mode.PLAYER) {
            throw new WrongGameModeException("Game mode should be 'PLAYER");
        }
        if (numPlumbers < 2)
            throw new InvalidArgumentException("Invalid input for number of plumber players. Should be at least 2.");
        if (numSaboteurs < 2)
            throw new InvalidArgumentException("Invalid input for number of saboteur players. Should be at least 2.");

        for (int i = 0; i < numPlumbers; i++) {
            Plumber p = new Plumber();
            this.addPlumber(p);
        }

        for (int i = 0; i < numSaboteurs; i++) {
            Saboteur p = new Saboteur();
            this.addSaboteur(p);
        }

        this._started = true;
    }

    /** Initializes the game field. */
    public void initGameField() {
        System.out.println("GameEngine.initGameField()");
    }
}
