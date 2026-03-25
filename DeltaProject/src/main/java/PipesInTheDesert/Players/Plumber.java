package PipesInTheDesert.Players;

import PipesInTheDesert.Interfaces.IConnectable;
import PipesInTheDesert.Interfaces.IOccupiable;
import PipesInTheDesert.Connectors.*;
import PipesInTheDesert.Elements.Cistern;
import PipesInTheDesert.Elements.Pump;

public class Plumber extends Player {
    public boolean holdingPump;

    public void connectPipeEnd(Pipe pipe, IConnectable element) {
        System.out.println(
                "connectPipeEnd method of Plumber class was called with pipe = " + pipe + " and element = " + element);
    }

    public void disconnectPipeEnd(Pipe pipe, IConnectable element) {
        System.out.println("disconnectPipeEnd method of Plumber class was called with pipe = " + pipe
                + " and element = " + element);
    }

    public void fixPipe(Pipe pipe) {
        System.out.println("fixPipe method of Plumber class was called with pipe = " + pipe);
    }

    public void fixPump(Pump pump) {
        System.out.println("fixPump method of Plumber class was called with pump = " + pump);
    }

    public void getEnd(PipeEnd eop) {
        System.out.println("getEnd method of Plumber class was called with eop = " + eop);
    }

    public void insertPump(Pipe pipe, Pump pump) {
        System.out.println("insertPump method of Plumber class was called with pipe = " + pipe + " and pump = " + pump);
    }

    public void pickUpPump(Cistern cistern) {
        System.out.println("pickUpPump method of Plumber class was called with cistern = " + cistern);
    }

    public boolean occupy(IOccupiable target) {
        System.out.println("occupy method of Plumber class was called with target = " + target);
        return true;
    }

    public void moveAlongPipe(Pipe pipe) {
        System.out.println("moveAlongPipe method of Plumber class was called with pipe = " + pipe);
    }

    public void endTurn() {
        System.out.println("endTurn method of Plumber class was called");
    }

    public boolean hasEnoughStamina(int cost) {
        System.out.println("hasEnoughStamina method of Plumber class was called with cost = " + cost);
        return true;
    }

    public void consumeStamina(int amount) {
        System.out.println("consumeStamina method of Plumber class was called with amount = " + amount);
    }

    public void refreshStamina() {
        System.out.println("refreshStamina method of Plumber class was called");
    }

    public void setIncomingPipe(Pump pump, Pipe incoming) {
        System.out.println("setIncomingPipe method of Plumber class was called with pump = " + pump + " and incoming = "
                + incoming);
    }

    public void setOutgoingPipe(Pump pump, Pipe outgoing) {
        System.out.println("setOutgoingPipe method of Plumber class was called with pump = " + pump + " and outgoing = "
                + outgoing);
    }
}
