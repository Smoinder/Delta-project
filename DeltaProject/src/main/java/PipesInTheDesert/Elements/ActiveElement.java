package PipesInTheDesert.Elements;

import PipesInTheDesert.Connectors.PipeEnd;
import PipesInTheDesert.Interfaces.IConnectable;

import java.util.List;
import PipesInTheDesert.MapObject;

/**
 * Abstract base for field elements that act as connection nodes for pipe ends.
 */
public abstract class ActiveElement extends MapObject implements IConnectable {
    /** Pipe endpoints currently connected to this element. */
    public List<PipeEnd> connectedPipes;
    /** True when the element is in a functional state. */
    public boolean works;
}
