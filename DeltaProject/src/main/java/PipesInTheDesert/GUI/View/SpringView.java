package PipesInTheDesert.GUI.View;

import PipesInTheDesert.Elements.MapObject;
import PipesInTheDesert.Elements.Spring;
import PipesInTheDesert.GUI.Observer.ChangeType;
import PipesInTheDesert.GUI.Observer.ModelObserver;

import java.awt.Graphics2D;

public class SpringView implements ModelObserver {
    private Spring spring;
    private MapPanel panel;

    public SpringView() {
        // Empty skeleton constructor.
    }

    @Override
    public void onStateChanged(MapObject source, ChangeType change) {
    }

    @Override
    public void draw(Graphics2D g) {
    }
}

