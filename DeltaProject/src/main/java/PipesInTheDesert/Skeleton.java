package PipesInTheDesert;


import PipesInTheDesert.Connectors.Pipe;
import PipesInTheDesert.Connectors.PipeEnd;
import PipesInTheDesert.Elements.Pump;
import PipesInTheDesert.Interfaces.IConnectable;
import PipesInTheDesert.Players.Plumber;

import java.util.Scanner;

public class Skeleton {
    private Skeleton(){
        throw new AssertionError("No instantiation for static factory class");
    }
    private final static String MAIN_MENU =
            """
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
            }catch(NumberFormatException e){
                choice =-1; //Defaults to Invalid input
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
            if(running)
            {
                System.out.println("Press anything to return to main menu... ");
                sc.nextLine();
            }
        }
    }

    private static void StartGame(){
        // TODO: Implement Start / Configure Game use case
    }

    private static void PlayerWalksOnPump() {
        // TODO: Implement Player walks on a pump use case
    }

    private static void PlayerWalksOnPipe() {
        // TODO: Implement Player walks on a pipe use case
    }

    private static void PlayerChangesPumpDirection() {
        // TODO: Implement Player changes pump direction use case
    }

    private static void PlumberFixesBrokenPump() {
        // TODO: Implement Plumber fixes a broken pump use case
    }

    private static void PlumberPicksUpPump() {
        // TODO: Implement Plumber picks up a pump use case
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

    private static void PlumberFixesBrokenPipe() {
        // TODO: Implement Plumber fixes a broken pipe use case
    }

    private static void PlumberPicksPipe() {
        // TODO: Implement Plumber picks a pipe (end) use case
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

    private static void PlumberRedirectsPipeEnd() {
        Scanner sc = new Scanner(System.in);
        Plumber tempPlumber =  new Plumber();
        Pipe tempPipe = new Pipe();
        tempPipe.end1 = new PipeEnd();
        tempPipe.end2 = new PipeEnd();
        IConnectable tempConnectable = new Pump();
        tempPlumber.disconnectPipeEnd(tempPipe, tempConnectable);
        System.out.print("Condition Check: pipe end is connected → ");
        tempPipe.end1.isConnected();
        System.out.print("(y/n) ");
        String input = sc.nextLine().trim().toLowerCase();
        if(input.equals("y")) {
            tempPipe.end1.disconnect();
            System.out.println("\nDisconnection successful.");
        } else if (input.equals("n")) {
            System.out.println("\nDisconnection rejected: pipe already disconnected");
        }
        else{
            System.out.println("Invalid Input");
        }
    }

    private static void SaboteurPuncturesPipe() {
        // TODO: Implement Saboteur punctures a pipe use case
    }

}
