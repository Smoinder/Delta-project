package PipesInTheDesert.Connectors;

import PipesInTheDesert.Interfaces.IOccupiable;
import PipesInTheDesert.Players.Player;

public class Pipe implements IOccupiable{
    public PipeEnd end1;
    public PipeEnd end2;
    public boolean leaking;
    public boolean waterFlowing;
    public Player occupant;
    public int length;

    public void puncture() {
        System.out.println("puncture method of Pipe class was called");
    }

    public void repair() {
        System.out.println("repair method of Pipe class was called");
    }

    public PipeEnd getOtherEnd(PipeEnd end) {
        System.out.println("getOtherEnd method of Pipe class was called with end = " + end);
        return null;
    }

    public boolean canAccept(Player player) {
        System.out.println("canAccept method of Pipe class was called with player = " + player);
        return true;
    }

    public void addOccupant(Player player) {
        System.out.println("addOccupant method of Pipe class was called with player = " + player);
    }

    public void removeOccupant(Player player) {
        System.out.println("removeOccupant method of Pipe class was called with player = " + player);
    }
}
