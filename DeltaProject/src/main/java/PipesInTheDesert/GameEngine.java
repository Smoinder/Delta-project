package PipesInTheDesert;

import PipesInTheDesert.Players.*;
import PipesInTheDesert.Elements.*;
import PipesInTheDesert.Connectors.Pipe;

import java.util.List;

public class GameEngine {
    public Player activePlayer;
    public List<Cistern> cisterns;
    public List<Pump> pumps;
    public List<Spring> springs;
    public List<Pipe> pipes;
    public List<Plumber> plumbers;
    public List<Saboteur> saboteurs;
    public int plumbersScore;
    public int saboteursScore;
    public int turnNumber;

    public void endGame() {
        System.out.println("endGame method of GameEngine class was called");
    }

    public void nextTurn() {
        System.out.println("nextTurn method of GameEngine class was called");
    }

    public void simulateWaterFlow() {
        System.out.println("simulateWaterFlow method of GameEngine class was called");
    }

    public void startGame() {
        System.out.println("startGame method of GameEngine class was called");
    }
}
