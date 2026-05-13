package PipesInTheDesert.GUI.View;

import PipesInTheDesert.Connectors.Pipe;
import PipesInTheDesert.Elements.MapObject;
import PipesInTheDesert.GUI.Observer.ChangeType;
import PipesInTheDesert.GUI.Observer.ModelObserver;
import PipesInTheDesert.GameEngine;

import java.awt.Graphics2D;

public class PipeView implements ModelObserver {
    private Pipe pipe;
    private MapPanel panel;

    public PipeView() {
        // Empty skeleton constructor.
    }

    @Override
    public void onStateChanged(MapObject source, ChangeType change) {
    }

    @Override
    public void draw(Graphics2D g) {
    }
}

