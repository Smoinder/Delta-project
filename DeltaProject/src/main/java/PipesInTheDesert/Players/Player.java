package PipesInTheDesert.Players;

import PipesInTheDesert.Connectors.Pipe;
import PipesInTheDesert.Constants;
import PipesInTheDesert.Elements.Pump;
import PipesInTheDesert.Exceptions.AlreadyOccupiedException;
import PipesInTheDesert.Exceptions.ElementNotReachableException;
import PipesInTheDesert.Exceptions.NotEnoughStaminaException;
import PipesInTheDesert.Exceptions.PlayerNotOnPipeException;
import PipesInTheDesert.Interfaces.IOccupiable;
import PipesInTheDesert.MapObject;
import PipesInTheDesert.Exceptions.InvalidArgumentException;
import PipesInTheDesert.Exceptions.NotEnoughStaminaException;

/**
 * Abstract base type for all players that move in the pipe network.
 */
public abstract class Player extends MapObject {
    /** Current stamina available for actions. */
    protected int _stamina;
    /** Maximum stamina restored at turn start. */
    protected final int _maxStamina;
    /** Current occupied map element, such as a pipe or a pump. */
    private IOccupiable _position;
    /** Last known player id. Used to have unique player ids for new players */
    private static int _lastPlayerId = 0;
    /** Unique identifier of this player. */
    private final int _playerId = ++Player._lastPlayerId;
    /** True when this player currently has the active turn. */
    private boolean _isActive;

    Player(IOccupiable position, int maxStamina, boolean isActive) {
        this._maxStamina = maxStamina;
        this._stamina = maxStamina;
        this._position = position;
        this._isActive = isActive;
    }

    Player(IOccupiable position, int maxStamina) {
        this(position, maxStamina, false);
    }

    Player(IOccupiable position) {
        this(position, Constants.PLAYER_MAX_STAMINA);
    }

    Player() {
        // TODO: Remove default constructor when Plumbers and Saboteurs are concretely
        // defined
        _maxStamina = Constants.PLAYER_MAX_STAMINA;
    }

    /**
     * Gets the unique ID of this player.
     *
     * @return player ID (1-indexed)
     */
    public int getPlayerId() {
        return this._playerId;
    }

    public void setStamina(int stamina) {
        this._stamina = stamina;
    }

    public int getStamina() {
        return this._stamina;
    }

    /**
     * Tries to occupy a target map element.
     *
     * @param target occupiable target
     * @return true when occupation succeeds
     */
    public boolean occupy(IOccupiable target) throws AlreadyOccupiedException {
        if (!target.canAccept(this))
            throw new AlreadyOccupiedException("Target is already occupied by another player");
        target.addOccupant(this);
        this._position = target;
        return true;
    };

    /**
     * Move to the pipe, connected to the current {@code _position}
     * 
     * @param pipe destination
     * @throws AlreadyOccupiedException     if pipe already has another player on it
     * @throws ElementNotReachableException if the pipe is not connected to the
     *                                      current {@code _position}
     * @throws NotEnoughStaminaException    if player does not have enough stamina
     *                                      to move
     * @throws PlayerNotOnPipeException     if the player is not currently on a pipe
     * 
     */
    public void moveAlongPipe(Pipe pipe) throws AlreadyOccupiedException, ElementNotReachableException,
            NotEnoughStaminaException, PlayerNotOnPipeException {
        // do nothing if already on the target
        if (pipe == this._position)
            return;
        if (!pipe.canAccept(this))
            throw new AlreadyOccupiedException("Target pipe is already occupied by another player");
        // pipes cannot be connected with each other -> no option to go directly to any
        // other pipe
        if (this._position instanceof Pipe)
            throw new ElementNotReachableException("Pipe not reachable");

        // TODO: add check if current element is connected to target pipe
        this.consumeStamina(Constants.PLAYER_WALK_ON_A_PIPE_STAMINA);
        this._position.removeOccupant(this);
        pipe.addOccupant(this);
        this._position = pipe;
    }

    /**
     * Starts player's turn, refreshing the stamina and activating the player
     */
    public void startTurn() {
        this.refreshStamina();
        this._isActive = false;
    }

    /**
     * Ends player's turn, refreshing the stamina and disable the player
     */
    public void endTurn() {
        this.refreshStamina();
        this._isActive = false;
    };

    /**
     * Determine if the player can take action with corresponding {@code cost}
     * 
     * @param cost
     * @return {@code true} if player has enough stamina; {@code false} otherwise
     */
    public boolean hasEnoughStamina(int cost) {
        return this._stamina >= cost;
    };

    /**
     * Consumes stamina for some action
     * 
     * @param amount - amount of stamina to consume
     * @throws NotEnoughStaminaException if current stamina level is below
     *                                   {@code amount}
     */
    public void consumeStamina(int amount) throws NotEnoughStaminaException {
        if (!this.hasEnoughStamina(amount))
            throw new NotEnoughStaminaException("Player " + this._playerId + " has not enough stamina");
        this._stamina -= amount;
    };

    /**
     * Refills {@code stamina} level, returning it to the {@code maxStamina}
     */
    public void refreshStamina() {
        this._stamina = this._maxStamina;
    };

    public abstract void setIncomingPipe(Pump pump, Pipe incoming)
            throws InvalidArgumentException, NotEnoughStaminaException;

    public abstract void setOutgoingPipe(Pump pump, Pipe outgoing)
            throws InvalidArgumentException, NotEnoughStaminaException;



}
