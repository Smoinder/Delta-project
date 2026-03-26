package PipesInTheDesert.Connectors;

import PipesInTheDesert.MapObject;
import PipesInTheDesert.Interfaces.IConnectable;

public class PipeEnd extends MapObject {
    public Pipe pipe;
    public IConnectable connectedElement;

    public boolean isConnected() {
        System.out.println("isConnected method of PipeEnd class was called");
        return true;
    }

    public boolean isFree() {
        System.out.println("isFree method of PipeEnd class was called");
        return true;
    }

    public void connect(IConnectable element) {
        System.out.println("connect method of PipeEnd class was called with element = " + element);
    }

    public void disconnect(){
        System.out.println("disconnect method of PipeEnd class was called");
    }

    public boolean canConnect(IConnectable element) {
        System.out.println("canConnect method of PipeEnd class was called with element = " + element);
        return true;
    }
}
