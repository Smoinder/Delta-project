package PipesInTheDesert.Interfaces;

import PipesInTheDesert.Players.Player;

public interface IOccupiable {
    public boolean canAccept(Player player);
    public void addOccupant(Player player);
    public void removeOccupant(Player player);
}
