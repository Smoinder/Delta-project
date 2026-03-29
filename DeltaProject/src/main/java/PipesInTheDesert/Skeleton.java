package PipesInTheDesert;

import java.util.Scanner;

import PipesInTheDesert.Connectors.Pipe;
import PipesInTheDesert.Connectors.PipeEnd;
import PipesInTheDesert.Elements.Cistern;
import PipesInTheDesert.Elements.Pump;
import PipesInTheDesert.Interfaces.IConnectable;
import PipesInTheDesert.Players.Plumber;
import PipesInTheDesert.Players.Saboteur;

/**
 * Console skeleton that exposes analysis-model use cases as menu-driven
 * actions.
 */
public class Skeleton {
    private static GameEngine gameEngine = new GameEngine();

    /** Prevents instantiation of this static utility entry point. */
    private Skeleton() {
        throw new AssertionError("No instantiation for static factory class");
    }

    /** Main menu listing the sequence-diagram scenarios from the skeleton plan. */
    private final static String MAIN_MENU = """
            === MAIN MENU ===
            1.\tStart Game
            2.\tPlayer walks on a pump
            3.\tPlayer walks on a pipe
            4.\tPlayer changes pump direction
            5.\tPlumber fixes a broken pump\s
            6.\tPlumber picks up a pump
            7.\tPlumber installs a new pump
            8.\tPlumber fixes a broken pipe
            9.\tPlumber picks a pipe
            10.\tPlumber installs a new pipe
            11.\tPlumber redirects an end of a pipe
            12.\tSaboteur punctures a pipe
            0.\tExit
            """;

    public static void main(String[] args) throws InterruptedException {
        runSkeletonConsole();
    }

    private static void runSkeletonConsole() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println(MAIN_MENU);
            System.out.print("Please Choose an Option: ");
            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                choice = -1; // Defaults to Invalid input
            }
            switch (choice) {
                case 0 -> running = false;
                case 1 -> StartGame();
                case 2 -> PlayerWalksOnPump();
                case 3 -> PlayerWalksOnPipe();
                case 4 -> PlayerChangesPumpDirection();
                case 5 -> PlumberFixesBrokenPump();
                case 6 -> PlumberPicksUpPump();
                case 7 -> PlumberInstallsPump();
                case 8 -> PlumberFixesBrokenPipe();
                case 9 -> PlumberPicksPipe();
                case 10 -> PlumberInstallsPipe();
                case 11 -> PlumberRedirectsPipeEnd();
                case 12 -> SaboteurPuncturesPipe();
                default -> System.out.println("Invalid choice, please try again.");
            }
            Thread.sleep(500);
            if (running) {
                System.out.println("Press anything to return to main menu... ");
                sc.nextLine();
            }
        }
    }

    /**
     * Use case: Start / Configure Game.
     * Based on section 5.2.2.1 of the Planning the Skeleton.
     * Called from the main menu.
     */
    private static void StartGame() {
        gameEngine.startGame();
        gameEngine.initGameField();
        System.out.println("Game started successfully.");
    }

    /**
     * Use case: Player walks on a pump.
     * Based on section 5.2.2.2 of the Planning the Skeleton.
     * Called from the main menu.
     */
    private static void PlayerWalksOnPump() {
        Plumber pl = new Plumber();
        Pump pump = new Pump();
        pl.occupy(pump);
    }

    /**
     * Use case: Player walks on a pipe.
     * Based on section 5.2.2.3 of the Planning the Skeleton.
     * Called from the main menu.
     */
    private static void PlayerWalksOnPipe() {
        Saboteur sab = new Saboteur();
        Pipe pipe = new Pipe();
        sab.occupy(pipe);
    }

    /**
     * Use case: Player changes pump direction.
     * Based on section 5.2.2.4 of the Planning the Skeleton.
     * Called from the main menu.
     */
    private static void PlayerChangesPumpDirection() {
        Scanner sc = new Scanner(System.in);
        Plumber tempPlayer = new Plumber();
        Pump tempPump = new Pump();
        Pipe inputPipe = new Pipe();
        Pipe outputPipe = new Pipe();
        inputPipe.end1 = new PipeEnd();
        inputPipe.end2 = new PipeEnd();
        outputPipe.end1 = new PipeEnd();
        outputPipe.end2 = new PipeEnd();
        inputPipe.end1.connectedElement = tempPump;
        outputPipe.end1.connectedElement = tempPump;
        System.out.print("Condition Check: player is on a pump → player.position == pump (y/n) ");
        String input = sc.nextLine().trim().toLowerCase();
        if (input.equals("y")) {
            tempPlayer.setIncomingPipe(tempPump, inputPipe);
            tempPlayer.setOutgoingPipe(tempPump, outputPipe);
            System.out.println("\nDirection updated.");
        } else if (input.equals("n")) {
            System.out.println("\nPump redirection failed: Player is not on pump");
        } else {
            System.out.println("Invalid Input");
        }
    }

    /**
     * Use case: Plumber fixes a broken pump.
     * Based on section 5.2.2.5 of the Planning the Skeleton.
     * Called from the main menu.
     */
    private static void PlumberFixesBrokenPump() {
        Scanner sc = new Scanner(System.in);
        Plumber tempPlumber = new Plumber();
        Pump tempPump = new Pump();
        tempPlumber.fixPump(tempPump);
        System.out.print("Condition Check: player.position == pump (y/n) ");
        String input = sc.nextLine().trim().toLowerCase();
        if (input.equals("y")) {
            System.out.print("Condition Check: !pump.isHealthy (y/n) ");
            String input2 = sc.nextLine().trim().toLowerCase();
            if (input2.equals("y")) {
                tempPump.fix();
                tempPlumber.consumeStamina(1);
                System.out.println("\nPump repaired successfully.");
            } else if (input2.equals("n")) {
                System.out.println("\nPump not repaired: Pump is already healthy");
            } else {
                System.out.println("Invalid Input");
            }
        } else if (input.equals("n")) {
            System.out.println("\nPump not repaired: Player is not on pump");
        } else {
            System.out.println("Invalid Input");
        }
    }

    /** Use case placeholder: Plumber picks up a pump. */
    private static void PlumberPicksUpPump() {
        Scanner sc = new Scanner(System.in);
        Plumber tempPlumber = new Plumber();
        Cistern tempCistern = new Cistern();
        tempPlumber.pickUpPump(tempCistern);
        System.out.print("Condition Check: plumber is on a cistern → player.position == cistern (y/n) ");
        String input = sc.nextLine().trim().toLowerCase();
        if (input.equals("y")) {
            System.out.print("Condition Check: plumber is not holding a pump → !Plumber.holdingPump (y/n) ");
            String input2 = sc.nextLine().trim().toLowerCase();
            if (input2.equals("y")) {
                tempCistern.generatePumps();
                System.out.println("\nPump picked up successfully.");
            } else if (input2.equals("n")) {
                System.out.println("\nPickup rejected: already holding a pump.");
            } else {
                System.out.println("Invalid Input");
            }
        } else if (input.equals("n")) {
            System.out.println("\nPickup rejected: player not on cistern.");
        } else {
            System.out.println("Invalid Input");
        }
    }

    /**
     * Use case: Plumber installs a new pump.
     * Based on section 5.2.2.7 of the Planning the Skeleton.
     * Called from the main menu.
     */
    private static void PlumberInstallsPump() {
        Scanner sc = new Scanner(System.in);
        Plumber plumber = new Plumber();
        Pipe pipe = new Pipe();
        Pump pump = new Pump();
        pipe.end1 = new PipeEnd();
        pipe.end2 = new PipeEnd();
        plumber.insertPump(pipe, pump);
        System.out.print("Condition Check: plumber is on a pipe →: ");
        System.out.println("player.position == pipe");
        System.out.print("(y/n): ");
        String onPipe = sc.nextLine().trim().toLowerCase();
        if (!onPipe.equals("y")) {
            System.out.println("Pickup rejected: player not on pipe");
            return;
        }

        System.out.print("Condition Check: plumber is holding a pump → : ");
        System.out.println("plumber.holdingPump");
        System.out.print("(y/n): ");
        String holdingPump = sc.nextLine().trim().toLowerCase();
        if (!holdingPump.equals("y")) {
            System.out.println("Pickup rejected: player not holding a pump");
            return;
        }

        System.out.println("Pump inserted.");
    }

    /**
     * Use case: Plumber fixes broken pump.
     * Based on section 5.2.2.8 of the Planning the Skeleton.
     * Called from the main menu.
     */
    private static void PlumberFixesBrokenPipe() {
        Scanner sc = new Scanner(System.in);
        Plumber tempPlumber = new Plumber();
        Pipe tempPipe = new Pipe();
        tempPlumber.fixPipe(tempPipe);
        System.out.print("Condition Check: pipe is leaking → pipe.leaking (y/n) ");
        String input = sc.nextLine().trim().toLowerCase();
        if (input.equals("y")) {
            System.out.print("Condition Check: plumber is on the pipe → player.position == pipe (y/n) ");
            String input2 = sc.nextLine().trim().toLowerCase();

            if (input2.equals("y")) {
                tempPipe.repair();
                System.out.println("\nPipe repaired successfully.");
            } else if (input2.equals("n")) {
                System.out.println("\nPlumber is not on pipe. No action taken.");
            } else {
                System.out.println("Invalid Input");
            }

        } else if (input.equals("n")) {
            System.out.println("\nPipe is not leaking. No action taken.");
        } else {
            System.out.println("Invalid Input");
        }
    }

    /** Use case placeholder: Plumber picks a pipe end. */
    private static void PlumberPicksPipe() {
        Scanner sc = new Scanner(System.in);
        Plumber tempPlumber = new Plumber();
        Pipe tempPipe = new Pipe();
        tempPipe.end1 = new PipeEnd();
        tempPipe.end2 = new PipeEnd();
        PipeEnd targetEnd = tempPipe.end1;
        tempPlumber.getEnd(targetEnd);
        System.out.print("Condition Check: pipe end is available → ");
        targetEnd.isFree();
        System.out.print("(y/n) ");
        String input = sc.nextLine().trim().toLowerCase();
        if (input.equals("y")) {
            tempPlumber.heldPipeEnd = targetEnd;
            System.out.println("\nPipe end picked up successfully.");
        } else if (input.equals("n")) {
            System.out.println("\nPipeEnd pickup failed: Pipe End is currently connected to an element");
        } else {
            System.out.println("Invalid Input");
        }
    }

    /**
     * Use case: Plumber installs a new pipe.
     * Based on section 5.2.2.10 of the Planning the Skeleton.
     * Called from the main menu.
     */
    private static void PlumberInstallsPipe() {
        Scanner sc = new Scanner(System.in);
        Plumber plumber = new Plumber();
        PipeEnd pipeEnd = new PipeEnd();
        IConnectable element = new Pump();
        plumber.connectPipeEnd(element);
        System.out.print("Condition Check: Plumber has a held pipe end → ");
        System.out.println("plumber.heldPipeEnd != null");
        System.out.print("(y/n): ");
        String hasPipeEnd = sc.nextLine().trim().toLowerCase();
        if (!hasPipeEnd.equals("y")) {
            System.out.println("Connection rejected: Plumber isn't holding a pipe end");
            return;
        }
        System.out.print("Condition Check: pipe end is free → ");
        pipeEnd.isFree();
        System.out.print("(y/n): ");
        String isFree = sc.nextLine().trim().toLowerCase();
        if (!isFree.equals("y")) {
            System.out.println("Connection rejected: Pipe End is not free");
            return;
        }
        System.out.print("Condition Check: pipe end can connect → ");
        pipeEnd.canConnect(element);
        System.out.print("(y/n): ");
        String canConnect = sc.nextLine().trim().toLowerCase();
        if (!canConnect.equals("y")) {
            System.out.println("Connection rejected: Pipe End cannot connect");
            return;
        }

        System.out.println("Connection successful.");
    }

    /**
     * Use case: Plumber redirects an end of a pipe
     * Based on section 5.2.2.11 of the Planning the Skeleton.
     * Called from the main menu.
     */
    private static void PlumberRedirectsPipeEnd() {
        Scanner sc = new Scanner(System.in);
        Plumber tempPlumber = new Plumber();
        Pipe tempPipe = new Pipe();
        tempPipe.end1 = new PipeEnd();
        tempPipe.end2 = new PipeEnd();
        IConnectable tempConnectable = new Pump();
        tempPlumber.disconnectPipeEnd(tempPipe, tempConnectable);
        System.out.print("Condition Check: pipe end is connected → ");
        tempPipe.end1.isConnected();
        System.out.print("(y/n) ");
        String input = sc.nextLine().trim().toLowerCase();
        if (input.equals("y")) {
            tempPipe.end1.disconnect();
            System.out.println("\nDisconnection successful.");
        } else if (input.equals("n")) {
            System.out.println("\nDisconnection rejected: pipe already disconnected");
        } else {
            System.out.println("Invalid Input");
        }
    }

    /**
     * Use case: Saboteur punctures a pipe
     * Based on section 5.2.2.12 of the Planning the Skeleton.
     * Called from the main menu.
     */
    private static void SaboteurPuncturesPipe() {
        Scanner sc = new Scanner(System.in);
        Saboteur tempSaboteur = new Saboteur();
        Pipe tempPipe = new Pipe();

        tempSaboteur.puncturePipe(tempPipe);
        System.out.print("Condition Check: pipe is not already leaking → !pipe.leaking (y/n): ");
        String input = sc.nextLine().trim().toLowerCase();
        if (input.equals("n")) {
            System.out.println("Pipe not punctured: pipe is already leaking");
            return;
        } else if (!input.equals("y")) {
            System.out.println("Invalid Input");
            return;
        }
        System.out.print("Condition Check: saboteur is on the pipe → player.position == pipe (y/n): ");
        input = sc.nextLine().trim().toLowerCase();
        if (input.equals("n")) {
            System.out.println("Pipe not punctured: player is not on pipe");
            return;
        } else if (!input.equals("y")) {
            System.out.println("Invalid Input");
            return;
        }
        tempPipe.puncture();
        System.out.println("Pipe punctured.");
    }

}
