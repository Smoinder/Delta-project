package PipesInTheDesert;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import PipesInTheDesert.Connectors.Pipe;
import PipesInTheDesert.Connectors.PipeEnd;
import PipesInTheDesert.Elements.Cistern;
import PipesInTheDesert.Elements.Pump;
import PipesInTheDesert.Elements.Spring;
import PipesInTheDesert.Exceptions.GameAlreadyStartedException;
import PipesInTheDesert.Exceptions.InvalidArgumentException;
import PipesInTheDesert.Exceptions.MapNotEmptyException;
import PipesInTheDesert.Exceptions.WrongGameModeException;
import PipesInTheDesert.Interfaces.IOccupiable;
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
    /// Index of the last active plumber player in the plumbers list, used for turn
    /// progression.
    private int lastActivePlumberIndex = -1;
    /// Index of the last active saboteur player in the saboteurs list, used for
    /// turn progression.
    private int lastActiveSaboteurIndex = -1;

    private Mode _mode = Mode.PLAYER;

    private boolean _started = false;

    private boolean _mapLoaded = false;

    private boolean _randomEnabled = true;

    public boolean isRandomEnabled() {
        return _randomEnabled;
    }

    public void setRandomEnabled(boolean randomEnabled) {
        this._randomEnabled = randomEnabled;
    }

    public void setPlumbersScore(int score) {
        this.plumbersScore = score;
    }

    public void setSaboteursScore(int score) {
        this.saboteursScore = score;
    }

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
        return this._cisterns;
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

    /**
     * Ends the game and finalizes the result.
     * 
     * @return the winning team
     */
    public Team endGame() {
        this._started = false;
        return plumbersScore > saboteursScore ? Team.PLUMBERS : Team.SABOTEURS;
    }

    /**
     * Advances the game to the next player's turn.
     * 
     * @return the winning team if the game ended after this turn, or null
     *         otherwise.
     */
    public Team nextTurn() {
        simulateWaterFlow();

        for(Cistern c : this._cisterns) {
            c.generatePipes();
            c.generatePumps();
        }

        for(Pump p : this._pumps) {
            if(Math.random() <= Constants.PUMP_BREAK_CHANCE) {
                p.goOutOfOrder();
            }
        }

        for (Plumber p : this._plumbers) {
            p.setStamina(Constants.PLAYER_MAX_STAMINA);
        }
        for (Saboteur s : this._saboteurs) {
            s.setStamina(Constants.PLAYER_MAX_STAMINA);
        }

        if (this._activePlayer instanceof Plumber pl) {
            lastActivePlumberIndex = this._plumbers.indexOf(pl);
            if (lastActiveSaboteurIndex == this._saboteurs.size() - 1) {
                this._activePlayer = this._saboteurs.get(0);
            } else {
                this._activePlayer = this._saboteurs.get(lastActiveSaboteurIndex + 1);
            }
        } else if (this._activePlayer instanceof Saboteur s) {
            lastActiveSaboteurIndex = this._saboteurs.indexOf(s);
            if (lastActivePlumberIndex == this._plumbers.size() - 1) {
                this._activePlayer = this._plumbers.get(0);
            } else {
                this._activePlayer = this._plumbers.get(lastActivePlumberIndex + 1);
            }
        }

        this.turnNumber++;

        if (this.turnNumber >= Constants.GAME_TURNS_NUMBER_TO_END) {
            return endGame();
        }
        return null;
    }

    /** Simulates water movement through springs, pipes, pumps, and cisterns. */
    public void simulateWaterFlow() {
        for (Pipe p : this._pipes) {
            if (p.isLeaking()) {
                saboteursScore += p.getWaterAmount();
                p.setWaterAmount(0);
            } else if (p.isWaterFlowing()) {
                if (p.getEnd1().getConnectedElement() instanceof Cistern) {
                    plumbersScore += p.getWaterAmount();
                    p.setWaterAmount(0);
                } else if (p.getEnd1().getConnectedElement() instanceof Pump pump) {
                    pump.addWater(p.getWaterAmount());
                    p.setWaterAmount(0);
                } else if (p.getEnd2().getConnectedElement() instanceof Cistern) {
                    plumbersScore += p.getWaterAmount();
                    p.setWaterAmount(0);
                } else if (p.getEnd2().getConnectedElement() instanceof Pump pump) {
                    pump.addWater(p.getWaterAmount());
                    p.setWaterAmount(0);
                }
            }
        }
        for (Pump p : this._pumps) {
            if (p.isHealthy()) {
                if (p.getOutputPipe() != null) {
                    p.getOutputPipe().getPipe().addWater(p.getWaterTankLevel());
                }
                p.setWaterTankLevel(0);
            }
        }
        for (Spring s : this._springs) {
            for (PipeEnd p : s.getConnectedPipes()) {
                p.getPipe().addWater(s.getThroughput() / s.getConnectedPipes().size());
            }
        }
    }

    /**
     * Initializes the game session, just prints that it was called. Used in
     * skeleton
     * 
     * @deprecated use {@link #startGame(int, int)} instead
     */
    @Deprecated
    public void startGame() {
        System.out.println("GameEngine.startGame()");
    }

    /**
     * Initializes the game field, just prints that it was called. Used in skeleton
     * 
     * @deprecated use {@link #loadMap()} instead
     */
    @Deprecated
    public void initGameField() {
        System.out.println("GameEngine.initGameField()");
    }

    /** Initializes the game session, configures players. */
    public void startGame(int numPlumbers, int numSaboteurs)
            throws WrongGameModeException, GameAlreadyStartedException, InvalidArgumentException {
        if (this._started) {
            throw new GameAlreadyStartedException("Game already started");
        }
        if (this._mode != Mode.PLAYER) {
            throw new WrongGameModeException("Game mode should be 'PLAYER'");
        }
        if (numPlumbers < 2)
            throw new InvalidArgumentException("Invalid input for number of plumber players. Should be at least 2.");
        if (numSaboteurs < 2)
            throw new InvalidArgumentException("Invalid input for number of saboteur players. Should be at least 2.");

        for (int i = 0; i < numPlumbers; i++) {
            Pump randomPump = this._pumps.get((int) (Math.random() * this._pumps.size()));
            Plumber p = new Plumber(randomPump);
            randomPump.addOccupant(p);
            this.addPlumber(p);
        }

        for (int i = 0; i < numSaboteurs; i++) {
            Pump randomPump = this._pumps.get((int) (Math.random() * this._pumps.size()));
            Saboteur s = new Saboteur(randomPump);
            randomPump.addOccupant(s);
            this.addSaboteur(s);
        }

        this.turnNumber = 0;

        this._activePlayer = this._plumbers.get(0);

        this._started = true;
    }

    /**
     * Initializes small map, containing one spring and one cistern
     */
    private void _loadSmallMap() {
        // Create a spring
        this.addSpring(new Spring());
        // Create a cistern
        this.addCistern(new Cistern());
    }

    /**
     * Initializes default map, containing two springs, two cisterns, and a
     * pump connected to one cistern and one spring.
     * 
     * <pre>
     * spring1      spring2
     *   |
     * pump1
     *   |
     * cistern1     cistern2
     * </pre>
     */
    private void _loadDefaultMap() {
        // Create springs
        Spring s1 = new Spring();
        this.addSpring(s1);
        this.addSpring(new Spring());
        // Create cisterns
        Cistern c1 = new Cistern();
        this.addCistern(c1);
        this.addCistern(new Cistern());

        // Create pump
        Pump p = new Pump();
        this.addPump(p);

        // Connect all elements with pipes
        Pipe p1 = new Pipe();
        p1.getEnd1().setConnectedElement(s1);
        p1.getEnd2().setConnectedElement(p);
        this.addPipe(p1);
        Pipe p2 = new Pipe();
        p2.getEnd1().setConnectedElement(p);
        p2.getEnd2().setConnectedElement(c1);
        this.addPipe(p2);
    }

    /**
     * Initializes large map, containing two springs, two cisterns, and
     * two pumps, in sequence connecting one cistern to one spring.
     * 
     * <pre>
     * spring1      spring2
     *   |
     * pump1
     *   |
     * pump2
     *   |
     * cistern1     cistern2
     * </pre>
     */
    private void _loadLargeMap() {
        // Create springs
        Spring s1 = new Spring();
        this.addSpring(s1);
        this.addSpring(new Spring());
        // Create cisterns
        Cistern c1 = new Cistern();
        this.addCistern(c1);
        this.addCistern(new Cistern());

        // Create pumps
        Pump pump1 = new Pump();
        this.addPump(pump1);
        Pump pump2 = new Pump();
        this.addPump(pump2);

        // Connect all elements with pipes
        Pipe p1 = new Pipe();
        p1.getEnd1().setConnectedElement(s1);
        p1.getEnd2().setConnectedElement(pump1);
        this.addPipe(p1);
        Pipe p2 = new Pipe();
        p2.getEnd1().setConnectedElement(pump1);
        p2.getEnd2().setConnectedElement(pump2);
        this.addPipe(p2);
        Pipe p3 = new Pipe();
        p3.getEnd1().setConnectedElement(pump2);
        p3.getEnd2().setConnectedElement(c1);
        this.addPipe(p3);
    }

    /** Initializes the game field. */
    public void loadMap(MapType mapType) throws MapNotEmptyException, WrongGameModeException {
        if (this._mapLoaded)
            throw new MapNotEmptyException("Map already loaded");
        if (this._mode != Mode.PLAYER) {
            throw new WrongGameModeException("Game mode should be 'PLAYER'");
        }
        switch (mapType) {
            case SMALL -> this._loadSmallMap();
            case DEFAULT -> this._loadDefaultMap();
            case LARGE -> this._loadLargeMap();
        }
        this._mapLoaded = true;
    }

    /** Initializes the game field with the default map. */
    public void loadMap() throws MapNotEmptyException, WrongGameModeException {
        this.loadMap(MapType.DEFAULT);
    }
}
