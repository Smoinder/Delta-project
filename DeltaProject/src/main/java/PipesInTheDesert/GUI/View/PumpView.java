package PipesInTheDesert.GUI.View;

import PipesInTheDesert.Elements.MapObject;
import PipesInTheDesert.Elements.Pump;
import PipesInTheDesert.GUI.Observer.ChangeType;
import PipesInTheDesert.GUI.Observer.ModelObserver;

import java.awt.Graphics2D;

public class PumpView implements ModelObserver {
    private Pump pump;
    private MapPanel panel;

    public PumpView() {
        // Empty skeleton constructor.
    }

    @Override
    public void onStateChanged(MapObject source, ChangeType change) {
    }

    @Override
    public void draw(Graphics2D g) {
    }
}

