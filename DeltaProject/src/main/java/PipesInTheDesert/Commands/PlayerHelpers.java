package PipesInTheDesert.Commands;

import PipesInTheDesert.Constants;
import PipesInTheDesert.GameEngine;
import PipesInTheDesert.Interfaces.IConnectable;
import PipesInTheDesert.Players.Player;
import PipesInTheDesert.Players.Plumber;
import PipesInTheDesert.Players.Saboteur;
import PipesInTheDesert.Connectors.Pipe;
import PipesInTheDesert.Connectors.PipeEnd;
import PipesInTheDesert.Elements.Cistern;
import PipesInTheDesert.Elements.Pump;
import PipesInTheDesert.Elements.Spring;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class PlayerHelpers {
    private PlayerHelpers() {
        throw new AssertionError("No instantiation for static helper class");
    }

    public static PipeEnd endConnectedTo(Pipe pipe, IConnectable element) {
        if (pipe == null || element == null) {
            return null;
        }
        if (pipe.getEnd1() != null && pipe.getEnd1().getConnectedElement() == element) {
            return pipe.getEnd1();
        }
        if (pipe.getEnd2() != null && pipe.getEnd2().getConnectedElement() == element) {
            return pipe.getEnd2();
        }
        return null;
    }

    public static PipeEnd freeEndOf(Pipe pipe) {
        if (pipe.getEnd1() != null && pipe.getEnd1().getConnectedElement() == null) {
            return pipe.getEnd1();
        }
        if (pipe.getEnd2() != null && pipe.getEnd2().getConnectedElement() == null) {
            return pipe.getEnd2();
        }
        return null;
    }

    public static String formatTurnLine(GameEngine ge) {
        Player activePlayer = ge.getActivePlayer();
        return "Turn: " + ge.turnNumber + " Active: " + nameOf(activePlayer) + "Team: "+activePlayer.getPlayerTeam();
    }

    public static String formatScoreLine(GameEngine ge) {
        return "Score: plumbers=" + ge.plumbersScore + " saboteurs=" + ge.saboteursScore;
    }

    public static String formatSpringLine(Spring spring) {
        return nameOf(spring) + " throughput=" + spring.getThroughput() + " connected=" + pipeList(spring.getConnectedPipes());
    }

    public static String formatCisternLine(Cistern cistern) {
        return nameOf(cistern) + " pipes=" + cistern.getGeneratedPipes().size() +
                " pumps=" + cistern.getGeneratedPumps().size() +
                " connected=" + pipeList(cistern.getConnectedPipes()) +
                " occupants=" + playerList(cistern.getOccupants());
    }

    public static String formatPumpLine(Pump pump) {
        String input = pipeNameFromEnd(pump.getInputPipe());
        String output = pipeNameFromEnd(pump.getOutputPipe());
        return nameOf(pump) + " healthy=" + pump.isHealthy() +
                " input=" + input +
                " output=" + output +
                " tank=" + pump.getWaterTankLevel() +
                " connected=" + pipeList(pump.getConnectedPipes()) +
                " occupants=" + playerList(pump.getOccupants());
    }

    public static String formatPipeLine(Pipe pipe) {
        String end1 = connectedElementName(pipe.getEnd1());
        String end2 = connectedElementName(pipe.getEnd2());
        String occupant = nameOf(pipe.getOccupant());
        return nameOf(pipe) + " leaking=" + pipe.isLeaking() +
                " flowing=" + pipe.isWaterFlowing() +
                " end1=" + end1 +
                " end2=" + end2 +
                " occupant=" + occupant;
    }

    public static String formatPlumberLine(Plumber plumber) {
        String position = nameOf(plumber.getPosition());
        String heldPump = nameOf(plumber.getHeldPump());
        String heldEnd = pipeNameFromEnd(plumber.getHeldPipeEnd());
        return nameOf(plumber) + " stamina=" + plumber.getStamina() +
                " position=" + position +
                " holdingPump=" + heldPump +
                " holdingPipeEnd=" + heldEnd;
    }

    public static String formatSaboteurLine(Saboteur saboteur) {
        String position = nameOf(saboteur.getPosition());
        return nameOf(saboteur) + " stamina=" + saboteur.getStamina() +
                " position=" + position;
    }

    public static String pipeNameFromEnd(PipeEnd end) {
        if (end == null || end.getPipe() == null) {
            return "none";
        }
        return nameOf(end.getPipe());
    }

    public static String connectedElementName(PipeEnd end) {
        if (end == null || end.getConnectedElement() == null) {
            return "none";
        }
        return nameOf(end.getConnectedElement());
    }

    public static String pipeList(List<PipeEnd> ends) {
        List<String> names = new ArrayList<>();
        for (PipeEnd end : ends) {
            if (end != null && end.getPipe() != null) {
                names.add(nameOf(end.getPipe()));
            }
        }
        names.sort(String::compareTo);
        return "[" + String.join(",", names) + "]";
    }

    public static String playerList(List<Player> players) {
        List<String> names = new ArrayList<>();
        for (Player player : players) {
            names.add(nameOf(player));
        }
        names.sort(String::compareTo);
        return "[" + String.join(",", names) + "]";
    }

    public static <T> List<T> sortedByName(List<T> items) {
        List<T> sorted = new ArrayList<>(items);
        sorted.sort(Comparator.comparing(PlayerHelpers::nameOf));
        return sorted;
    }

    public static String nameOf(Object obj) {
        if (obj == null) {
            return "none";
        }
        if (obj instanceof Spring spring) {
            return "spring" + spring.getId();
        }
        if (obj instanceof Cistern cistern) {
            return "cistern" + cistern.getId();
        }
        if (obj instanceof Pump pump) {
            return "pump" + pump.getId();
        }
        if (obj instanceof Pipe pipe) {
            return "pipe" + pipe.getId();
        }
        if (obj instanceof Plumber plumber) {
            return "plumber" + plumber.getId();
        }
        if (obj instanceof Saboteur saboteur) {
            return "saboteur" + saboteur.getId();
        }
        return obj.getClass().getSimpleName();
    }


    public static void notImplemented() {
        System.out.println(Constants.NOT_IMPLEMENTED_YET);
    }
}

