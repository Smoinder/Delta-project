package PipesInTheDesert;

public class Constants {
    private Constants () {};
    public final static int PLAYER_MAX_STAMINA = 32;
    public final static int PLAYER_WALK_ON_A_PIPE_STAMINA = 6;
    public final static int PLAYER_WALK_ON_A_PUMP_STAMINA = 6;
    public final static int PLAYER_PICKUP_PUMP_STAMINA = 12;
    public final static int PLAYER_PLACE_PUMP_STAMINA = 0;
    public final static double PLAYER_WALK_WITH_A_PUMP_STAMINA_MODIFIER = 1.5;
    public final static int PLAYER_PUNCTURE_PIPE_STAMINA = 8;
    public final static int PLAYER_FIX_PIPE_STAMINA = 8;
    public final static int PLAYER_FIX_PUMP_STAMINA = 12;
    public final static int PLAYER_CHANGE_PUMP_INPUT_STAMINA = 8;
    /** Stamina cost for a plumber to attach a free pipe end to an active element. */
    public final static int PLAYER_CONNECT_PIPE_STAMINA = 6;
    public final static double PUMP_BREAK_CHANCE = 0.1;

    /** Default maximum number of pipe ends a pump may have connected. */
    public final static int PUMP_DEFAULT_MAX_CONNECTED_PIPES = 4;
    /** Default pipes manufactured per turn at a cistern. */
    public final static double CISTERN_DEFAULT_PIPE_MANUFACTURE_SPEED = 0.5;
    /** Default pumps manufactured per turn at a cistern. */
    public final static double CISTERN_DEFAULT_PUMP_MANUFACTURE_SPEED = 0.5;
    /** Default amount of water emitted by a spring per turn. */
    public final static int SPRING_DEFAULT_THROUGHPUT = 10;

    public static final String NOT_IMPLEMENTED_YET = "NotImplementedYet";

    // I/O Mode Constants
    public static final String OUTPUT_PREFIX = "[OUTPUT] ";
    public static final String TESTS_DIRECTORY = "tests/cases";
}
