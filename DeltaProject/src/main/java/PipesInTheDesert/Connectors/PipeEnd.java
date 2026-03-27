package PipesInTheDesert.Connectors;

import PipesInTheDesert.MapObject;
import PipesInTheDesert.Interfaces.IConnectable;

public class PipeEnd extends MapObject {
    public Pipe pipe;
    public IConnectable connectedElement;

    public boolean isConnected() {
        System.out.print("PipeEnd.isConnected(): boolean");
        return true;
    }

    public boolean isFree() {
        System.out.print("PipeEnd.isFree(): boolean");
        return true;
    }

    public void connect(IConnectable element) {
        System.out.println("PipeEnd.connect()");
    }

    public void disconnect(){
        System.out.println("PipeEnd.disconnect()");
    }

    public boolean canConnect(IConnectable element) {
        System.out.print("PipeEnd.canConnect(): boolean");
        return true;
    }
}
