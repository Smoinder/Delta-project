package PipesInTheDesert.Elements;

import PipesInTheDesert.Connectors.PipeEnd;
import PipesInTheDesert.Interfaces.IConnectable;

import java.util.List;
import PipesInTheDesert.MapObject;

public abstract class ActiveElement extends MapObject implements IConnectable {
    public List<PipeEnd> connectedPipes;
    public boolean works;
}
