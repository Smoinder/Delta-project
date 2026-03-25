package PipesInTheDesert.Elements;

import java.util.List;
import PipesInTheDesert.Connectors.*;
import PipesInTheDesert.Players.Player;
import PipesInTheDesert.Interfaces.IOccupiable;

public class Pump extends ActiveElement implements IOccupiable {
    public List<PipeEnd> connectedPipes;
    public int maxConnectedPipes;
    public PipeEnd inputPipe;
    public PipeEnd outputPipe;
    public boolean isHealthy;
    public List<Player> occupants;
    public double waterTankLevel;

    public void fix() {
        System.out.println("fix method of Pump class was called");
    }

    public void goOutOfOrder() {
        System.out.println("goOutOfOrder method of Pump class was called");
    }

    public void goOutOfOrderWithProbability(int p) {
        System.out.println("goOutOfOrderWithProbability method of Pump class was called with p = " + p);
    }

    public void setInput(PipeEnd p) {
        System.out.println("setInput method of Pump class was called with p = " + p);
    }

    public void setOutput(PipeEnd p) {
        System.out.println("setOutput method of Pump class was called with p = " + p);
    }

    public boolean isTankEmpty() {
        System.out.println("isTankEmpty method of Pump class was called");
        return true;
    }

    public boolean canConnect(PipeEnd pipeEnd) {
        System.out.println("canConnect method of Pump class was called with pipeEnd = " + pipeEnd);
        return true;
    }

    public void connectEnd(PipeEnd pipeEnd) {
        System.out.println("connectEnd method of Pump class was called with pipeEnd = " + pipeEnd);
    }

    public PipeEnd disconnectEnd() {
        System.out.println("disconnectEnd method of Pump class was called");
        return null;
    }

    public Pipe getEnd() {
        System.out.println("getEnd method of Pump class was called");
        return null;
    }

    public boolean canAccept(Player player) {
        System.out.println("canAccept method of Pump class was called with player = " + player);
        return true;
    }

    public void addOccupant(Player player) {
        System.out.println("addOccupant method of Pump class was called with player = " + player);
    }

    public void removeOccupant(Player player) {
        System.out.println("removeOccupant method of Pump class was called with player = " + player);
    }
}
