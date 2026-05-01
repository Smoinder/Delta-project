package PipesInTheDesert.Interfaces;

import PipesInTheDesert.Players.Player;
import PipesInTheDesert.Exceptions.PlayerNotOnPipeException;

public interface IOccupiable {
    public boolean canAccept(Player player);
    public void addOccupant(Player player);
    public void removeOccupant(Player player) throws PlayerNotOnPipeException;
}
