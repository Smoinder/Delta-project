package PipesInTheDesert.GUI.View;

import PipesInTheDesert.Elements.MapObject;
import PipesInTheDesert.GUI.Observer.ModelObserver;
import PipesInTheDesert.GameEngine;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapPanel extends JPanel {
    private GameEngine engine;
    private PipesInTheDesert.GUI.Controller.GameController controller;
    private Map<MapObject, ModelObserver> viewRegistry;
    private MapObject selectedElement;
    private MapObject hoveredElement;
    private List<MapObject> reachableElements;

    public MapPanel() {
        this.viewRegistry = new HashMap<>();
        this.reachableElements = new ArrayList<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void registerView(MapObject model, ModelObserver view) {
        viewRegistry.put(model, view);
    }

    public void unregisterView(MapObject model) {
        viewRegistry.remove(model);
    }

    public void setSelected(MapObject element) {
        selectedElement = element;
    }

    public void setReachable(List<MapObject> elements) {
        reachableElements = elements;
    }

    public void clearSelection() {
        selectedElement = null;
        hoveredElement = null;
        reachableElements.clear();
    }

    public ModelObserver getViewFor(MapObject model) {
        return viewRegistry.get(model);
    }
}

