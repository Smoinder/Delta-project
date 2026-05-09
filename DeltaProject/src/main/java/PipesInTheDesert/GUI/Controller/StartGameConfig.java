package PipesInTheDesert.GUI.Controller;

public class StartGameConfig {
    public int numPlumbers;
    public int numSaboteurs;

    public boolean isValid() {
        return numPlumbers > 2 && numSaboteurs > 2;
    }
}

