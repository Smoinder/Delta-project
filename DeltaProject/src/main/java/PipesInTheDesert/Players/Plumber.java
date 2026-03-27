package PipesInTheDesert.Players;

import PipesInTheDesert.Interfaces.IConnectable;
import PipesInTheDesert.Interfaces.IOccupiable;
import PipesInTheDesert.Connectors.*;
import PipesInTheDesert.Elements.Cistern;
import PipesInTheDesert.Elements.Pump;

public class Plumber extends Player {
    public boolean holdingPump;
    public Pump heldPump;
    public PipeEnd heldPipeEnd;
    
    public void connectPipeEnd(IConnectable element) {
        System.out.println("Plumber.connectPipeEnd(IConnectable)");
    }

    public void disconnectPipeEnd(Pipe pipe, IConnectable element) {
        System.out.println("Plumber.disconnectPipeEnd(Pipe, IConnectable)");
    }

    public void fixPipe(Pipe pipe) {
        System.out.println("Plumber.fixPipe(Pipe)");
    }

    public void fixPump(Pump pump) {
        System.out.println("Plumber.fixPump(Pump)");
    }

    public void getEnd(PipeEnd eop) {
        System.out.println("Plumber.getEnd(PipeEnd)");
    }

    public void insertPump(Pipe pipe, Pump pump) {
        System.out.println("Plumber.insertPump(Pipe, Pump)");
    }

    public void pickUpPump(Cistern cistern) {
        System.out.println("Plumber.pickUpPump(Cistern)");
    }

    public boolean occupy(IOccupiable target) {
        System.out.print("Plumber.occupy(IOccupiable): boolean");
        return true;
    }

    public void moveAlongPipe(Pipe pipe) {
        System.out.println("Plumber.moveAlongPipe(Pipe)");
    }

    public void endTurn() {
        System.out.println("Plumber.endTurn()");
    }

    public boolean hasEnoughStamina(int cost) {
        System.out.print("Plumber.hasEnoughStamina(int): boolean");
        return true;
    }

    public void consumeStamina(int amount) {
        System.out.println("Plumber.consumeStamina(int)");
    }

    public void refreshStamina() {
        System.out.println("Plumber.refreshStamina()");
    }

    public void setIncomingPipe(Pump pump, Pipe incoming) {
        System.out.println("Plumber.setIncomingPipe(Pump, Pipe)");
    }

    public void setOutgoingPipe(Pump pump, Pipe outgoing) {
        System.out.println("Plumber.setOutgoingPipe(Pump, Pipe)");
    }
}
