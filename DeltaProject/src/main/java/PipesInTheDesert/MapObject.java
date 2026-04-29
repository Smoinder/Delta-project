package PipesInTheDesert;

/**
 * Abstract base for elements that have a fixed position on the game field canvas.
 */
public abstract class MapObject {
    /** X coordinate on the game field canvas. */
    private double x;
    /** Y coordinate on the game field canvas. */
    private double y;

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
}
