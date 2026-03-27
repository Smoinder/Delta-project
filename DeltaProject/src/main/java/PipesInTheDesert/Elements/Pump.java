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
        System.out.println("Pump.fix()");
    }

    public void goOutOfOrder() {
        System.out.println("Pump.goOutOfOrder()");
    }

    public void goOutOfOrderWithProbability(int p) {
        System.out.println("Pump.goOutOfOrderWithProbability(int)");
    }

    public void setInput(PipeEnd p) {
        System.out.println("Pump.setInput(PipeEnd)");
    }

    public void setOutput(PipeEnd p) {
        System.out.println("Pump.setOutput(PipeEnd)");
    }

    public boolean isTankEmpty() {
        System.out.print("Pump.isTankEmpty(): boolean");
        return true;
    }

    public boolean canConnect(PipeEnd pipeEnd) {
        System.out.print("Pump.canConnect(PipeEnd): boolean");
        return true;
    }

    public void connectEnd(PipeEnd pipeEnd) {
        System.out.println("Pump.connectEnd(PipeEnd)");
    }

    public PipeEnd disconnectEnd() {
        System.out.println("Pump.disconnectEnd(): PipeEnd");
        return null;
    }

    public Pipe getEnd() {
        System.out.println("Pump.getEnd(): Pipe");
        return null;
    }

    public boolean canAccept(Player player) {
        System.out.print("Pump.canAccept(Player): boolean");
        return true;
    }

    public void addOccupant(Player player) {
        System.out.println("Pump.addOccupant(Player)");
    }

    public void removeOccupant(Player player) {
        System.out.println("Pump.removeOccupant(Player)");
    }
}
