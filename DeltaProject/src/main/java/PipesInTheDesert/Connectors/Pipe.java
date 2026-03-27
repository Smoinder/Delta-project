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
        System.out.println("Pipe.puncture()");
    }

    public void repair() {
        System.out.println("Pipe.repair()");
    }

    public PipeEnd getOtherEnd(PipeEnd end) {
        System.out.println("Pipe.getOtherEnd(PipeEnd): PipeEnd");
        return null;
    }

    public boolean canAccept(Player player) {
        System.out.print("Pipe.canAccept(Player): boolean ");
        return true;
    }

    public void addOccupant(Player player) {
        System.out.println("Pipe.addOccupant(Player)");
    }

    public void removeOccupant(Player player) {
        System.out.println("Pipe.removeOccupant(Player)");
    }
}
