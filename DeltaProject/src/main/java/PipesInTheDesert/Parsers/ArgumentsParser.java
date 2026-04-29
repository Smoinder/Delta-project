package PipesInTheDesert.Parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import PipesInTheDesert.Connectors.Pipe;
import PipesInTheDesert.Elements.Cistern;
import PipesInTheDesert.Elements.Pump;
import PipesInTheDesert.Elements.Spring;
import PipesInTheDesert.GameEngine;
import PipesInTheDesert.MapObject;
import PipesInTheDesert.MapType;
import PipesInTheDesert.Team;
import PipesInTheDesert.Interfaces.IConnectable;
import PipesInTheDesert.Interfaces.IOccupiable;
import PipesInTheDesert.Players.Player;
import PipesInTheDesert.Players.Plumber;
import PipesInTheDesert.Players.Saboteur;

/**
 * Parses raw command arguments into typed domain objects.
 */
public final class ArgumentsParser {
    private static final Pattern TYPE_ID_PATTERN = Pattern.compile("^([A-Za-z]+)(\\d+)$");

    private ArgumentsParser() {
        throw new AssertionError("No instantiation for static factory class");
    }

    /** Ensures a command received the required number of tokens. */
    public static void requireArgumentCount(String[] parts, int expected) {
        if (parts.length != expected) {
            throw new IllegalArgumentException("Unexpected argument count");
        }
    }

    /** Parses an integer command argument. */
    public static int parseInteger(String token) {
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid integer value", e);
        }
    }

    /** Parses a boolean command argument; accepts only true/false. */
    public static boolean parseBoolean(String token) {
        if (token == null) {
            throw new IllegalArgumentException("Invalid boolean value: null");
        }
        return switch (token.toLowerCase()) {
            case "true" -> true;
            case "false" -> false;
            default -> throw new IllegalArgumentException("Invalid boolean value: " + token);
        };
    }

    /** Converts text input to the corresponding map type. */
    public static MapType parseMapType(String token) {
        try {
            return MapType.valueOf(token.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown map type", e);
        }
    }

    /** Parses a team token and resolves to a Team constant. */
    public static Team parseTeam(String token) {
        try {
            return Team.valueOf(token.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown team: " + token, e);
        }
    }

    /** Resolves a player token like {@code plumber1} or {@code saboteur2}. */
    public static Player parsePlayer(GameEngine gameEngine, String token) {
        Object object = parseTypeIdToken(gameEngine, token);
        if (object instanceof Player player) {
            return player;
        }
        throw new IllegalArgumentException("Expected player, got: " + object.getClass().getSimpleName());
    }

    /** Resolves a plumber token like {@code plumber1}. */
    public static Plumber parsePlumber(GameEngine gameEngine, String token) {
        Object object = parseTypeIdToken(gameEngine, token);
        if (object instanceof Plumber plumber) {
            return plumber;
        }
        throw new IllegalArgumentException("Expected plumber, got: " + object.getClass().getSimpleName());
    }

    /** Resolves a saboteur token like {@code saboteur1}. */
    public static Saboteur parseSaboteur(GameEngine gameEngine, String token) {
        Object object = parseTypeIdToken(gameEngine, token);
        if (object instanceof Saboteur saboteur) {
            return saboteur;
        }
        throw new IllegalArgumentException("Expected saboteur, got: " + object.getClass().getSimpleName());
    }

    /** Resolves a pipe token like {@code pipe1}. */
    public static Pipe parsePipe(GameEngine gameEngine, String token) {
        Object object = parseTypeIdToken(gameEngine, token);
        if (object instanceof Pipe pipe) {
            return pipe;
        }
        throw new IllegalArgumentException("Expected pipe, got: " + object.getClass().getSimpleName());
    }

    /** Resolves a pump token like {@code pump1}. */
    public static Pump parsePump(GameEngine gameEngine, String token) {
        Object object = parseTypeIdToken(gameEngine, token);
        if (object instanceof Pump pump) {
            return pump;
        }
        throw new IllegalArgumentException("Expected pump, got: " + object.getClass().getSimpleName());
    }

    /** Resolves a cistern token like {@code cistern1}. */
    public static Cistern parseCistern(GameEngine gameEngine, String token) {
        Object object = parseTypeIdToken(gameEngine, token);
        if (object instanceof Cistern cistern) {
            return cistern;
        }
        throw new IllegalArgumentException("Expected cistern, got: " + object.getClass().getSimpleName());
    }

    /** Resolves a spring token like {@code spring1}. */
    public static Spring parseSpring(GameEngine gameEngine, String token) {
        Object object = parseTypeIdToken(gameEngine, token);
        if (object instanceof Spring spring) {
            return spring;
        }
        throw new IllegalArgumentException("Expected spring, got: " + object.getClass().getSimpleName());
    }

    /** Resolves a pipe or pump token into an IOccupiable. */
    public static IOccupiable parseIOccupiable(GameEngine gameEngine, String token) {
        Object object = parseTypeIdToken(gameEngine, token);
        if (object instanceof IOccupiable occupiable) {
            return occupiable;
        }
        throw new IllegalArgumentException("Expected occupiable element, got: " + object.getClass().getSimpleName());
    }

    /** Resolves a pump, cistern, or spring token into an IConnectable. */
    public static IConnectable parseIConnectable(GameEngine gameEngine, String token) {
        Object object = parseTypeIdToken(gameEngine, token);
        if (object instanceof IConnectable connectable) {
            return connectable;
        }
        throw new IllegalArgumentException("Expected connectable element, got: " + object.getClass().getSimpleName());
    }

    /** Resolves any supported typed token into a MapObject. */
    public static MapObject parseMapObject(GameEngine gameEngine, String token) {
        Object object = parseTypeIdToken(gameEngine, token);
        if (object instanceof MapObject mapObject) {
            return mapObject;
        }
        throw new IllegalArgumentException("Expected map object, got: " + object.getClass().getSimpleName());
    }

    private static Object parseTypeIdToken(GameEngine gameEngine, String token) {
        if (token == null || token.isEmpty()) {
            throw new IllegalArgumentException("Token cannot be empty");
        }

        Matcher matcher = TYPE_ID_PATTERN.matcher(token);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid token format: " + token);
        }

        String typePrefix = matcher.group(1).toLowerCase();
        int id = parseInteger(matcher.group(2));

        switch (typePrefix) {
            case "plumber" -> {
                for (Plumber plumber : gameEngine.getPlumbers()) {
                    if (plumber.getId() == id) {
                        return plumber;
                    }
                }
                throw new IllegalArgumentException("Plumber with ID " + id + " not found");
            }
            case "saboteur" -> {
                for (Saboteur saboteur : gameEngine.getSaboteurs()) {
                    if (saboteur.getId() == id) {
                        return saboteur;
                    }
                }
                throw new IllegalArgumentException("Saboteur with ID " + id + " not found");
            }
            case "pipe" -> {
                for (Pipe pipe : gameEngine.getPipes()) {
                    if (pipe.getId() == id) {
                        return pipe;
                    }
                }
                throw new IllegalArgumentException("Pipe with ID " + id + " not found");
            }
            case "pump" -> {
                for (Pump pump : gameEngine.getPumps()) {
                    if (pump.getId() == id) {
                        return pump;
                    }
                }
                throw new IllegalArgumentException("Pump with ID " + id + " not found");
            }
            case "cistern" -> {
                for (Cistern cistern : gameEngine.getCisterns()) {
                    if (cistern.getId() == id) {
                        return cistern;
                    }
                }
                throw new IllegalArgumentException("Cistern with ID " + id + " not found");
            }
            case "spring" -> {
                for (Spring spring : gameEngine.getSprings()) {
                    if (spring.getId() == id) {
                        return spring;
                    }
                }
                throw new IllegalArgumentException("Spring with ID " + id + " not found");
            }
            default -> throw new IllegalArgumentException("Unknown type: " + typePrefix);
        }
    }
}


