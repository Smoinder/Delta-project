package PipesInTheDesert.Connectors;

import PipesInTheDesert.MapObject;
import PipesInTheDesert.Interfaces.IConnectable;

/**
 * Endpoint of a pipe that can connect to active elements in the network.
 */
public class PipeEnd extends MapObject {
    /** Pipe that owns this endpoint. */
    public Pipe pipe;
    /** Element currently connected to this endpoint. */
    public IConnectable connectedElement;

    /**
     * Reports whether this endpoint is currently connected.
     *
     * @return true when a connection exists
     */
    public boolean isConnected() {
        System.out.print("PipeEnd.isConnected(): boolean");
        return true;
    }

    /**
     * Reports whether this endpoint is free.
     *
     * @return true when no element is connected
     */
    public boolean isFree() {
        System.out.print("PipeEnd.isFree(): boolean");
        return true;
    }

    /**
     * Connects this endpoint to the provided connectable element.
     *
     * @param element element to connect
     */
    public void connect(IConnectable element) {
        System.out.println("PipeEnd.connect(IConnectable)");
    }

    /** Disconnects this endpoint from its current element. */
    public void disconnect(){
        System.out.println("PipeEnd.disconnect()");
    }

    /**
     * Checks whether this endpoint can connect to the provided element.
     *
     * @param element element to test
     * @return true when connection is allowed
     */
    public boolean canConnect(IConnectable element) {
        System.out.print("PipeEnd.canConnect(IConnectable): boolean");
        return true;
    }
}
