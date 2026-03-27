package PipesInTheDesert.Players;

import PipesInTheDesert.Connectors.Pipe;
import PipesInTheDesert.Elements.Pump;
import PipesInTheDesert.Interfaces.IOccupiable;

public class Saboteur extends Player {
    public void puncturePipe(Pipe pipe) {
        System.out.println("Saboteur.puncturePipe(Pipe)");
    }

    public boolean occupy(IOccupiable target) {
        System.out.print("Saboteur.occupy(IOccupiable): boolean");
        return true;
    }

    public void moveAlongPipe(Pipe pipe) {
        System.out.println("Saboteur.moveAlongPipe(Pipe)");
    }

    public void endTurn() {
        System.out.println("Saboteur.endTurn()");
    }

    public boolean hasEnoughStamina(int cost) {
        System.out.print("Saboteur.hasEnoughStamina(int): boolean");
        return true;
    }

    public void consumeStamina(int amount) {
        System.out.println("Saboteur.consumeStamina(int)");
    }

    public void refreshStamina() {
        System.out.println("Saboteur.refreshStamina()");
    }

    public void setIncomingPipe(Pump pump, Pipe incoming) {
        System.out.println("Saboteur.setIncomingPipe(Pump, Pipe)");
    }

    public void setOutgoingPipe(Pump pump, Pipe outgoing) {
        System.out.println("Saboteur.setOutgoingPipe(Pump, Pipe)");
    }
}
