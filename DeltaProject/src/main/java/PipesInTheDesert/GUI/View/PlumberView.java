package PipesInTheDesert.GUI.View;

import PipesInTheDesert.Elements.MapObject;
import PipesInTheDesert.GUI.Observer.ChangeType;
import PipesInTheDesert.GUI.Observer.ModelObserver;
import PipesInTheDesert.Players.Plumber;

import java.awt.Graphics2D;

public class PlumberView implements ModelObserver {
    private Plumber plumber;
    private MapPanel panel;

    public PlumberView() {
        // Empty skeleton constructor.
    }

    @Override
    public void onStateChanged(MapObject source, ChangeType change) {
    }

    @Override
    public void draw(Graphics2D g) {
    }
}

