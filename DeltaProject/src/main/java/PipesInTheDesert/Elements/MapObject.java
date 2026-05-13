package PipesInTheDesert.Elements;

import PipesInTheDesert.GUI.Observer.ChangeType;
import PipesInTheDesert.GUI.Observer.ModelObserver;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract base for elements that have a fixed position on the game field canvas.
 */
public abstract class MapObject {
    /** X coordinate on the game field canvas. */
    private double x;
    /** Y coordinate on the game field canvas. */
    private double y;

    private final List<ModelObserver> observers = new ArrayList<>();

    /**
     * Returns the horizontal position of this object.
     *
     * @return x coordinate on the game field canvas
     */
    public double getX() {
        return x;
    }

    /**
     * Updates the horizontal position of this object.
     *
     * @param x new x coordinate on the game field canvas
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Returns the vertical position of this object.
     *
     * @return y coordinate on the game field canvas
     */
    public double getY() {
        return y;
    }

    /**
     * Updates the vertical position of this object.
     *
     * @param y new y coordinate on the game field canvas
     */
    public void setY(double y) {
        this.y = y;
    }

    public void addObserver(ModelObserver obs){

    }

    public void removeObserver(ModelObserver obs){

    }

    protected void notifyObservers(ChangeType change){

    }

    public Rectangle getBounds(){
        return new Rectangle(40,40);
    }

    public boolean contains(int px, int py){
        return false;
    }

}
