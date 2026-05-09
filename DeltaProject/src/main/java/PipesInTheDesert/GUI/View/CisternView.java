package PipesInTheDesert.GUI.View;

import PipesInTheDesert.Elements.Cistern;
import PipesInTheDesert.Elements.MapObject;
import PipesInTheDesert.GUI.Observer.ChangeType;
import PipesInTheDesert.GUI.Observer.ModelObserver;

import java.awt.Graphics2D;

public class CisternView implements ModelObserver {
    private Cistern cistern;
    private MapPanel panel;

    public CisternView() {
        // Empty skeleton constructor.
    }

    @Override
    public void onStateChanged(MapObject source, ChangeType change) {
    }

    @Override
    public void draw(Graphics2D g) {
    }
}

