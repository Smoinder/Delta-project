package PipesInTheDesert.GUI.View;

import PipesInTheDesert.Elements.MapObject;
import PipesInTheDesert.GUI.Observer.ChangeType;
import PipesInTheDesert.GUI.Observer.ModelObserver;
import PipesInTheDesert.Players.Saboteur;

import java.awt.Graphics2D;

public class SaboteurView implements ModelObserver {
    private Saboteur saboteur;
    private MapPanel panel;

    public SaboteurView() {
        // Empty skeleton constructor.
    }

    @Override
    public void onStateChanged(MapObject source, ChangeType change) {
    }

    @Override
    public void draw(Graphics2D g) {
    }
}

