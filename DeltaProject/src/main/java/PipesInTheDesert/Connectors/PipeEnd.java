package PipesInTheDesert.Connectors;

import PipesInTheDesert.MapObject;
import PipesInTheDesert.Interfaces.IConnectable;
import PipesInTheDesert.Exceptions.InvalidArgumentException;
import PipesInTheDesert.Exceptions.PipeNotConnectedException;
import PipesInTheDesert.Elements.Pump;
/**
 * Endpoint of a pipe that can connect to active elements in the network.
 */
public class PipeEnd extends MapObject {
    /** Pipe that owns this endpoint. */
    private Pipe pipe;
    /** Element currently connected to this endpoint. */
    private IConnectable connectedElement;

    /**
     * Constructor. Initializes with no connections.
     */
    public PipeEnd() {
        super();
        this.pipe = null;
        this.connectedElement = null;
    }
    /**
     * Getters and Setters
     */
    public Pipe getPipe() { return pipe; }
    public void setPipe(Pipe pipe) { this.pipe = pipe; }
    public IConnectable getConnectedElement() { return connectedElement; }
    public void setConnectedElement(IConnectable element) { this.connectedElement = element; }

    /**
     * Reports whether this endpoint is currently connected.
     *
     * @return true when a connection exists
     */
    public boolean isConnected() {
        return connectedElement != null;
    }

    /**
     * Reports whether this endpoint is free.
     *
     * @return true when no element is connected
     */
    public boolean isFree() {
        return connectedElement == null;
    }

    /**
     * Connects this endpoint to the provided connectable element.
     *
     * @param element element to connect
     * @throws InvalidArgumentException if connection is not possible
     */
    public void connect(IConnectable element) throws InvalidArgumentException {
        if (!canConnect(element)) {
            throw new InvalidArgumentException("Connection not possible");
        }
        this.connectedElement = element;
    }

    /** Disconnects this endpoint from its current element.
     * @throws PipeNotConnectedException if already disconnected
     */
    public void disconnect() throws PipeNotConnectedException {
        if (!isConnected()) {
            throw new PipeNotConnectedException("Pipe end is not connected");
        }
        this.connectedElement = null;
    }

    /**
     * Checks whether this endpoint can connect to the provided element.
     *
     * @param element element to test
     * @return true when connection is allowed
     */
    public boolean canConnect(IConnectable element) {
        if (connectedElement != null) {
            return false;
        }
        if (element instanceof Pump) {
            Pump pump = (Pump) element;
            return pump.getConnectedPipes().size() < pump.getMaxConnectedPipes();
        }
        return true;
    }
}
