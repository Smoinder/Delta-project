package PipesInTheDesert;


import PipesInTheDesert.Connectors.Pipe;
import PipesInTheDesert.Connectors.PipeEnd;
import PipesInTheDesert.Elements.Cistern;
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

    private static void PlumberInstallsPump() {
        // TODO: Implement Plumber installs a new pump use case
    }

    private static void PlumberFixesBrokenPipe() {
        // TODO: Implement Plumber fixes a broken pipe use case
    }

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

    private static void PlumberInstallsPipe() {
        // TODO: Implement Plumber installs a new pipe use case
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
