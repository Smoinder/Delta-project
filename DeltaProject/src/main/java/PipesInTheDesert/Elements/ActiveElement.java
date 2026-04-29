package PipesInTheDesert.Elements;

import PipesInTheDesert.Connectors.PipeEnd;
import PipesInTheDesert.Interfaces.IConnectable;

import java.util.ArrayList;
import java.util.List;
import PipesInTheDesert.MapObject;

/**
 * Abstract base for field elements that act as connection nodes for pipe ends.
 */
public abstract class ActiveElement extends MapObject implements IConnectable {
    /** Pipe endpoints currently connected to this element. */
    private List<PipeEnd> connectedPipes = new ArrayList<>();
    /** True when the element is in a functional state. */
    private boolean works = true;

    /**
     * Returns every pipe end currently tracked as connected to this element.
     *
     * @return mutable list of connected pipe ends
     */
    public List<PipeEnd> getConnectedPipes() {
        return connectedPipes;
    }

    /**
     * Replaces the tracked connection list for this element.
     *
     * @param connectedPipes new list of connected pipe ends; null resets to empty
     */
    public void setConnectedPipes(List<PipeEnd> connectedPipes) {
        if (connectedPipes == null) {
            this.connectedPipes = new ArrayList<>();
            return;
        }
        this.connectedPipes = connectedPipes;
    }

    /**
     * Returns whether the element is currently functional.
     *
     * @return true when the element works
     */
    public boolean isWorking() {
        return works;
    }

    /**
     * Updates the functional state of this element.
     *
     * @param works new working-state flag
     */
    public void setWorking(boolean works) {
        this.works = works;
    }

    /**
     * Adds a pipe end to the tracked connection list if it is not already present.
     *
     * @param pipeEnd pipe end to add
     * @return true when the pipe end was added
     */
    public boolean addConnectedPipe(PipeEnd pipeEnd) {
        if (pipeEnd == null || connectedPipes.contains(pipeEnd)) {
            return false;
        }
        connectedPipes.add(pipeEnd);
        return true;
    }

    /**
     * Removes a pipe end from the tracked connection list.
     *
     * @param pipeEnd pipe end to remove
     * @return true when the pipe end was present and removed
     */
    public boolean removeConnectedPipe(PipeEnd pipeEnd) {
        if (pipeEnd == null) {
            return false;
        }
        return connectedPipes.remove(pipeEnd);
    }

    /**
     * Checks whether the given pipe end is currently tracked on this element.
     *
     * @param pipeEnd pipe end to look for
     * @return true when the pipe end is present
     */
    public boolean hasConnectedPipe(PipeEnd pipeEnd) {
        return pipeEnd != null && connectedPipes.contains(pipeEnd);
    }
}
