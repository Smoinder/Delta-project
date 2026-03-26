package PipesInTheDesert;

import java.util.Scanner;

public class Skeleton {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    Which use case you would like to execute?
                    1.\tStart Game 
                    2.\tPlayer walks on a pump 
                    3.\tPlayer walks on a pipe 
                    4.\tPlayer changes pump direction 
                    5.\tPlumber fixes a broken pump 
                    6.\tPlumber picks up a pump 
                    7.\tPlumber installs a new pump 
                    8.\tPlumber fixes a broken pipe 
                    9.\tPlumber picks a pipe 
                    10.\tPlumber installs a new pipe 
                    11.\tPlumber redirects an end of a pipe
                    12.\tSaboteur punctures a pipe
                    13.\tClose menu""");
            int choice = sc.nextInt();
            if (choice == 1) {
                // Start Game
            } else if (choice == 2) {
                // Player walks on a pump
            } else if (choice == 3) {
                // Player walks on a pipe
            } else if (choice == 4) {
                // Player changes pump direction
            } else if (choice == 5) {
                // Plumber fixes a broken pump
            } else if (choice == 6) {
                // Plumber picks up a pump
            } else if (choice == 7) {
                // Plumber installs a new pump
            } else if (choice == 8) {
                // Plumber fixes a broken pipe
            } else if (choice == 9) {
                // Plumber picks a pipe
            } else if (choice == 10) {
                // Plumber installs a new pipe
            } else if (choice == 11) {
                // Plumber redirects an end of a pipe
            } else if (choice == 12) {
                // Saboteur punctures a pipe
            } else if (choice == 13) {
                break;
            } else {
                System.out.println("Invalid choice, please try again.");
            }
        }
        sc.close();
    }
}
