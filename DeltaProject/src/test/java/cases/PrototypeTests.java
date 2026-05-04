package cases;

import org.junit.jupiter.api.Test;
import PipesInTheDesert.GameEngine;
import PipesInTheDesert.Mode;
import PipesInTheDesert.Team;
import PipesInTheDesert.Exceptions.AlreadyOccupiedException;
import PipesInTheDesert.Exceptions.GameAlreadyStartedException;
import PipesInTheDesert.Exceptions.ElementNotConnectedException;
import PipesInTheDesert.Exceptions.GameException;
import PipesInTheDesert.Exceptions.WrongGameModeException;
import PipesInTheDesert.Exceptions.InvalidArgumentException;
import PipesInTheDesert.Exceptions.PlayerNotOnElementException;
import PipesInTheDesert.Commands.DebugModeCommands;
import PipesInTheDesert.Commands.PlayerModeCommands;
import PipesInTheDesert.Parsers.CommandsParser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PrototypeTests {
    private static GameEngine initiliazeEngine() {
        GameEngine ge = new GameEngine();
        try {
            ge.loadMap();
        } catch (GameException e) {
            System.out.println("Error loading map: " + e.getMessage());
        }
        return ge;
    }

    @Test
    public void startGame() {
        GameEngine ge = initiliazeEngine();
        try {
            ge.startGame(2, 2);
        } catch (GameException e) {
            System.out.println("Error starting game: " + e.getMessage());
        }
        assertEquals(2, ge.getPlumbers().size());
        assertEquals(2, ge.getSaboteurs().size());
    }

    @Test
    public void startGameCalledTwice() {
        GameEngine ge = initiliazeEngine();
        try {
            ge.startGame(2, 2);
        } catch (GameException e) {
            System.out.println("Error starting game: " + e.getMessage());
        }
        assertThrows(GameAlreadyStartedException.class, () -> ge.startGame(2, 2));
    }

    @Test
    public void initGameField() {
        GameEngine ge = initiliazeEngine();
        assertEquals(2, ge.getSprings().size());
        assertEquals(2, ge.getCisterns().size());
        assertEquals(1, ge.getPumps().size());
        assertEquals(2, ge.getPipes().size());
    }

    @Test
    public void moveOnPipe() {
        GameEngine ge = initiliazeEngine();
        try {
            ge.startGame(2, 2);
        } catch (GameException e) {
            System.out.println("Error starting game: " + e.getMessage());
        }
        try {
            ge.getActivePlayer().moveAlongPipe(ge.getPipes().get(0));
        } catch (GameException e) {
            System.out.println("Error moving on pipe: " + e.getMessage());
        }
        assertEquals(ge.getActivePlayer().getPosition(), ge.getPipes().get(0));
    }

    @Test
    public void moveOnOccupiedPipe() {
        GameEngine ge = initiliazeEngine();
        try {
            ge.startGame(2, 2);
        } catch (GameException e) {
            System.out.println("Error starting game: " + e.getMessage());
        }
        try {
            ge.getSaboteurs().get(0).occupy(ge.getPipes().get(0));
        } catch (GameException e) {
            System.out.println("Error occupying pipe: " + e.getMessage());
        }
        assertThrows(AlreadyOccupiedException.class, () -> ge.getActivePlayer().moveAlongPipe(ge.getPipes().get(0)));
    }

    @Test
    public void moveOnPump() {
        GameEngine ge = initiliazeEngine();
        try {
            ge.startGame(2, 2);
        } catch (GameException e) {
            System.out.println("Error starting game: " + e.getMessage());
        }
        try {
            ge.getActivePlayer().occupy(ge.getPipes().get(0));
            ge.getActivePlayer().moveToActiveElement(ge.getPumps().get(0));
        } catch (GameException e) {
            System.out.println("Error moving on pump: " + e.getMessage());
        }
        assertEquals(ge.getActivePlayer().getPosition(), ge.getPumps().get(0));
    }

    @Test
    public void switchFromDebugModeToPlayerMode() {
        GameEngine ge = initiliazeEngine();
        assertThrows(WrongGameModeException.class, () -> DebugModeCommands.playerMode(ge));
    }

    @Test
    public void breakHealthyPump() {
        GameEngine ge = initiliazeEngine();
        ge.setMode(Mode.DEBUG);
        try {
            DebugModeCommands.breakPump(ge, ge.getPumps().get(0));
        } catch (GameException e) {
            System.out.println("Error breaking pump: " + e.getMessage());
        }
        assertEquals(ge.getPumps().get(0).isHealthy(), false);
    }

    @Test
    public void breakAlreadyBrokenPump() {
        GameEngine ge = initiliazeEngine();
        ge.setMode(Mode.DEBUG);
        try {
            DebugModeCommands.breakPump(ge, ge.getPumps().get(0));
        } catch (GameException e) {
            System.out.println("Error breaking pump: " + e.getMessage());
        }
        InvalidArgumentException invalidArgument = assertThrows(InvalidArgumentException.class,
                () -> DebugModeCommands.breakPump(ge, ge.getPumps().get(0)));
        assertEquals("Pump is already broken", invalidArgument.getMessage());
    }

    @Test
    public void breakPumpCalledInPlayerMode() {
        GameEngine ge = initiliazeEngine();
        assertThrows(WrongGameModeException.class, () -> DebugModeCommands.breakPump(ge, ge.getPumps().get(0)));
    }

    @Test
    public void setRandomOff() {
        GameEngine ge = initiliazeEngine();
        ge.setMode(Mode.DEBUG);
        CommandsParser.handleCommand(ge, "setRandom false");
        assertEquals(false, ge.isRandomEnabled());
    }

    @Test
    public void setRandomOn() {
        GameEngine ge = initiliazeEngine();
        ge.setMode(Mode.DEBUG);
        CommandsParser.handleCommand(ge, "setRandom false");
        CommandsParser.handleCommand(ge, "setRandom true");
        assertEquals(true, ge.isRandomEnabled());
    }

    @Test
    public void setRandomCalledInPlayerMode() {
        GameEngine ge = initiliazeEngine();
        assertThrows(WrongGameModeException.class, () -> DebugModeCommands.setRandom(ge));
    }

    @Test
    public void setStaminaOfPlayer() {
        GameEngine ge = initiliazeEngine();
        try {
            ge.startGame(2, 2);
        } catch (GameException e) {
            System.out.println("Error starting game: " + e.getMessage());
        }
        ge.setMode(Mode.DEBUG);
        try {
            DebugModeCommands.setStamina(ge, ge.getPlumbers().get(0), 5);
        } catch (GameException e) {
            System.out.println("Error setting stamina: " + e.getMessage());
        }
        assertEquals(5, ge.getPlumbers().get(0).getStamina());
    }

    @Test
    public void setStaminaNegativeValue() {
        GameEngine ge = initiliazeEngine();
        try {
            ge.startGame(2, 2);
        } catch (GameException e) {
            System.out.println("Error starting game: " + e.getMessage());
        }
        ge.setMode(Mode.DEBUG);
        InvalidArgumentException invalidArgument = assertThrows(InvalidArgumentException.class,
                () -> DebugModeCommands.setStamina(ge, ge.getPlumbers().get(0), -5));
        assertEquals("Invalid stamina argument", invalidArgument.getMessage());
    }

    @Test
    public void setStaminaCalledInPlayerMode() {
        GameEngine ge = initiliazeEngine();
        try {
            ge.startGame(2, 2);
        } catch (GameException e) {
            System.out.println("Error starting game: " + e.getMessage());
        }
        assertThrows(WrongGameModeException.class,
                () -> DebugModeCommands.setStamina(ge, ge.getPlumbers().get(0), 5));
    }

    @Test
    public void setPlumbersScore() {
        GameEngine ge = initiliazeEngine();
        ge.setMode(Mode.DEBUG);
        try {
            DebugModeCommands.setScore(ge, Team.PLUMBERS, 10);
        } catch (GameException e) {
            System.out.println("Error setting plumbers score: " + e.getMessage());
        }
        assertEquals(10, ge.plumbersScore);
        assertEquals(0, ge.saboteursScore);
    }

    @Test
    public void setSaboteursScore() {
        GameEngine ge = initiliazeEngine();
        ge.setMode(Mode.DEBUG);
        try {
            DebugModeCommands.setScore(ge, Team.SABOTEURS, 7);
        } catch (GameException e) {
            System.out.println("Error setting saboteurs score: " + e.getMessage());
        }
        assertEquals(0, ge.plumbersScore);
        assertEquals(7, ge.saboteursScore);
    }

    @Test
    public void setNegativeScore() {
        GameEngine ge = initiliazeEngine();
        ge.setMode(Mode.DEBUG);
        InvalidArgumentException invalidArgument = assertThrows(InvalidArgumentException.class,
                () -> DebugModeCommands.setScore(ge, Team.PLUMBERS, -5));
        assertEquals("Invalid score argument", invalidArgument.getMessage());
    }

    @Test
    public void setScoreCalledInPlayerMode() {
        GameEngine ge = initiliazeEngine();
        assertThrows(WrongGameModeException.class,
                () -> DebugModeCommands.setScore(ge, Team.PLUMBERS, 10));
    }

    @Test
    public void changeActivePlayer() {
        GameEngine ge = initiliazeEngine();
        try {
            ge.startGame(2, 2);
        } catch (GameException e) {
            System.out.println("Error starting game: " + e.getMessage());
        }
        ge.setMode(Mode.DEBUG);
        try {
            DebugModeCommands.setActivePlayer(ge, ge.getPlumbers().get(0));
        } catch (GameException e) {
            System.out.println("Error changing active player: " + e.getMessage());
        }
        assertEquals(ge.getActivePlayer(), ge.getPlumbers().get(0));
    }

    @Test
    public void setPipeLeakToTrue() {
        GameEngine ge = initiliazeEngine();
        ge.setMode(Mode.DEBUG);
        try {
            DebugModeCommands.setPipeLeak(ge, ge.getPipes().get(0), true);
        } catch (GameException e) {
            System.out.println("Error setting pipe leak: " + e.getMessage());
        }
        assertEquals(true, ge.getPipes().get(0).isLeaking());
    }

    @Test
    public void setPipeLeakToFalse() {
        GameEngine ge = initiliazeEngine();
        ge.setMode(Mode.DEBUG);
        try {
            DebugModeCommands.setPipeLeak(ge, ge.getPipes().get(0), false);
        } catch (GameException e) {
            System.out.println("Error setting pipe leak: " + e.getMessage());
        }
        assertEquals(false, ge.getPipes().get(0).isLeaking());
    }

    @Test
    public void setNumberOfRounds() {
        GameEngine ge = initiliazeEngine();
        ge.setMode(Mode.DEBUG);
        try {
            DebugModeCommands.setRounds(ge, 10);
        } catch (GameException e) {
            System.out.println("Error setting number of rounds: " + e.getMessage());
        }
        assertEquals(10, ge.turnNumber);
    }

    @Test
    public void setNegativeNumberOfRounds() {
        GameEngine ge = initiliazeEngine();
        ge.setMode(Mode.DEBUG);
        InvalidArgumentException invalidArgument = assertThrows(InvalidArgumentException.class,
                () -> DebugModeCommands.setRounds(ge, -5));
        assertEquals("Round count must be positive", invalidArgument.getMessage());
    }

    @Test
    public void setRoundsZeroValue() {
        GameEngine ge = initiliazeEngine();
        ge.setMode(Mode.DEBUG);
        InvalidArgumentException invalidArgument = assertThrows(InvalidArgumentException.class,
                () -> DebugModeCommands.setRounds(ge, 0));
        assertEquals("Round count must be positive", invalidArgument.getMessage());
    }

    @Test
    public void addWaterToPump() {
        GameEngine ge = initiliazeEngine();
        ge.setMode(Mode.DEBUG);
        try {
            DebugModeCommands.addWater(ge, ge.getPumps().get(0), 50);
        } catch (GameException e) {
            System.out.println("Error adding water to pump: " + e.getMessage());
        }
        assertEquals(50, ge.getPumps().get(0).getWaterTankLevel());
    }

    @Test
    public void addWaterToPumpWithExistingWater() {
        GameEngine ge = initiliazeEngine();
        ge.setMode(Mode.DEBUG);
        try {
            DebugModeCommands.addWater(ge, ge.getPumps().get(0), 20);
            DebugModeCommands.addWater(ge, ge.getPumps().get(0), 20);
        } catch (GameException e) {
            System.out.println("Error adding water to pump: " + e.getMessage());
        }
        assertEquals(40, ge.getPumps().get(0).getWaterTankLevel());
    }

    @Test
    public void addWaterNegativeAmount() {
        GameEngine ge = initiliazeEngine();
        ge.setMode(Mode.DEBUG);
        InvalidArgumentException invalidArgument = assertThrows(InvalidArgumentException.class,
                () -> DebugModeCommands.addWater(ge, ge.getPumps().get(0), -10));
        assertEquals("Water amount cannot be negative", invalidArgument.getMessage());
    }

    @Test
    public void teleportPlayerMovePlayer() {
        GameEngine ge = initiliazeEngine();
        try {
            ge.startGame(2, 2);
        } catch (GameException e) {
            System.out.println("Error starting game: " + e.getMessage());
        }
        ge.setMode(Mode.DEBUG);
        try {
            DebugModeCommands.teleportPlayer(ge, ge.getPlumbers().get(0), ge.getCisterns().get(0));
        } catch (GameException e) {
            System.out.println("Error teleporting player: " + e.getMessage());
        }
        assertEquals(ge.getPlumbers().get(0).getPosition(), ge.getCisterns().get(0));
    }

    @Test
    public void disconnectValid() {
        GameEngine ge = initiliazeEngine();
        try {
            ge.startGame(2, 2);
        } catch (GameException e) {
            System.out.println("Error starting game: " + e.getMessage());
        }
        ge.setMode(Mode.DEBUG);
        try {
            DebugModeCommands.teleportPlayer(ge, ge.getPlumbers().get(0), ge.getPumps().get(0));
        } catch (GameException e) {
            System.out.println("Error teleporting player: " + e.getMessage());
        }
        ge.setMode(Mode.PLAYER);
        try {
            PlayerModeCommands.disconnect(ge, ge.getPlumbers().get(0), ge.getPipes().get(1), ge.getPumps().get(0));
        } catch (GameException e) {
            System.out.println("Error disconnecting pipe: " + e.getMessage());
        }
        assertEquals(false, ge.getPipes().get(1).getEnd1().getConnectedElement() != null
                && ge.getPipes().get(1).getEnd1().getConnectedElement().equals(ge.getPumps().get(0)));
        assertEquals(false, ge.getPipes().get(1).getEnd2().getConnectedElement() != null
                && ge.getPipes().get(1).getEnd2().getConnectedElement().equals(ge.getPumps().get(0)));
    }

    @Test
    public void disconnectNotOnLocation() {
        GameEngine ge = initiliazeEngine();
        try {
            ge.startGame(2, 2);
        } catch (GameException e) {
            System.out.println("Error starting game: " + e.getMessage());
        }
        ge.setMode(Mode.DEBUG);
        try {
            DebugModeCommands.teleportPlayer(ge, ge.getPlumbers().get(0), ge.getCisterns().get(1));
        } catch (GameException e) {
            System.out.println("Error teleporting player: " + e.getMessage());
        }
        ge.setMode(Mode.PLAYER);
        assertThrows(PlayerNotOnElementException.class, () -> PlayerModeCommands.disconnect(ge, ge.getPlumbers().get(0),
                ge.getPipes().get(1), ge.getPumps().get(0)));
    }

    @Test
    public void disconnectNotConnected() {
        GameEngine ge = initiliazeEngine();
        try {
            ge.startGame(2, 2);
        } catch (GameException e) {
            System.out.println("Error starting game: " + e.getMessage());
        }
        ge.setMode(Mode.DEBUG);
        try {
            DebugModeCommands.teleportPlayer(ge, ge.getPlumbers().get(0), ge.getCisterns().get(0));
        } catch (GameException e) {
            System.out.println("Error teleporting player: " + e.getMessage());
        }
        ge.setMode(Mode.PLAYER);
        ElementNotConnectedException exception = assertThrows(ElementNotConnectedException.class, () -> PlayerModeCommands.disconnect(ge, ge.getPlumbers().get(0),
                ge.getPipes().get(0), ge.getCisterns().get(0)));
        assertEquals("Pipe end not connected", exception.getMessage());
    }
}
