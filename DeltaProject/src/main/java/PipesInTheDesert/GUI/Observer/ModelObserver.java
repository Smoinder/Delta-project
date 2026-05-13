package PipesInTheDesert.GUI.Observer;

import PipesInTheDesert.Elements.MapObject;

import java.awt.Graphics2D;

public interface ModelObserver {
    void onStateChanged(MapObject source, ChangeType change);

    void draw(Graphics2D g);
}

