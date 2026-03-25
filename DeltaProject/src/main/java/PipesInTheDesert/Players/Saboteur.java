package PipesInTheDesert.Players;

import PipesInTheDesert.Connectors.Pipe;
import PipesInTheDesert.Elements.Pump;
import PipesInTheDesert.Interfaces.IOccupiable;

public class Saboteur extends Player {
    public void puncturePipe(Pipe pipe) {
        System.out.println("puncturePipe method of Saboteur class was called with pipe = " + pipe);
    }

    public boolean occupy(IOccupiable target) {
        System.out.println("occupy method of Saboteur class was called with target = " + target);
        return true;
    }

    public void moveAlongPipe(Pipe pipe) {
        System.out.println("moveAlongPipe method of Saboteur class was called with pipe = " + pipe);
    }

    public void endTurn() {
        System.out.println("endTurn method of Saboteur class was called");
    }

    public boolean hasEnoughStamina(int cost) {
        System.out.println("hasEnoughStamina method of Saboteur class was called with cost = " + cost);
        return true;
    }

    public void consumeStamina(int amount) {
        System.out.println("consumeStamina method of Saboteur class was called with amount = " + amount);
    }

    public void refreshStamina() {
        System.out.println("refreshStamina method of Saboteur class was called");
    }

    public void setIncomingPipe(Pump pump, Pipe incoming) {
        System.out.println("setIncomingPipe method of Saboteur class was called with pump = " + pump
                + " and incoming = " + incoming);
    }

    public void setOutgoingPipe(Pump pump, Pipe outgoing) {
        System.out.println("setOutgoingPipe method of Saboteur class was called with pump = " + pump
                + " and outgoing = " + outgoing);
    }
}
