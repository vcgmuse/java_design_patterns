package design_patterns.strategy_pattern.scoreboard;

import java.util.InputMismatchException;
import java.util.Scanner;

import design_patterns.strategy_pattern.scoreboard.controllers.ScoreBoard;
import design_patterns.strategy_pattern.scoreboard.models.Balloon;
import design_patterns.strategy_pattern.scoreboard.models.BeachBall;
import design_patterns.strategy_pattern.scoreboard.models.Clown;
import design_patterns.strategy_pattern.scoreboard.models.Dart;
import design_patterns.strategy_pattern.scoreboard.models.RubberChicken;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ScoreBoard scoreBoard = new ScoreBoard();

        System.out.println("Welcome to the Tap Score Game!");
        System.out.println("Choose an object to tap to earn points.");
        System.out.println("Type 'exit' to quit.");
        System.out.println("------------------------------------");

        while (true) {
            displayMenu();
            System.out.print("Enter your choice: ");
            String choiceInput = scanner.nextLine().trim().toUpperCase();

            if (choiceInput.equals("EXIT")) {
                System.out.println("Thanks for playing! Final Score: " + scoreBoard.getTotalScore());
                break;
            }

            ScoreType chosenType = getScoreTypeFromInput(choiceInput);

            if (chosenType == ScoreType.UNKNOWN) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }

            // Set the strategy based on user's choice
            scoreBoard.setScoreAlgorithm(getStrategy(chosenType));

            int taps = 0;
            int multiplier = 0;

            try {
                System.out.print("Enter number of taps (e.g., 10): ");
                taps = scanner.nextInt();
                System.out.print("Enter score multiplier (e.g., 5): ");
                multiplier = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input for taps or multiplier. Please enter numbers.");
                scanner.nextLine(); // Consume the invalid input
                continue;
            } finally {
                scanner.nextLine(); // Consume the remaining newline character
            }

            if (taps < 0 || multiplier < 0) {
                System.out.println("Taps and multiplier cannot be negative. Try again.");
                continue;
            }
            
            // Execute the chosen strategy
            scoreBoard.showScore(taps, multiplier);
            System.out.println("------------------------------------");
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n--- Choose an Object ---");
        System.out.println("1. Balloon");
        System.out.println("2. Clown");
        System.out.println("3. BeachBall");
        System.out.println("4. Dart");
        System.out.println("5. RubberChicken");
        System.out.println("------------------------");
    }

    private static ScoreType getScoreTypeFromInput(String input) {
        switch (input) {
            case "1":
            case "BALLOON":
                return ScoreType.BALLOON;
            case "2":
            case "CLOWN":
                return ScoreType.CLOWN;
            case "3":
            case "BEACHBALL":
                return ScoreType.BEACHBALL;
            case "4":
            case "DART":
                return ScoreType.DART;
            case "5":
            case "RUBBERCHICKEN":
                return ScoreType.RUBBER_CHICKEN;
            default:
                return ScoreType.UNKNOWN;
        }
    }

    // Factory method to get the correct strategy instance
    private static ScoreAlgorithmBase getStrategy(ScoreType type) {
        switch (type) {
            case BALLOON:
                return new Balloon();
            case CLOWN:
                return new Clown();
            case BEACHBALL:
                return new BeachBall();
            case DART:
                return new Dart();
            case RUBBER_CHICKEN:
                return new RubberChicken();
            default:
                // This case should ideally not be reached with proper input validation
                throw new IllegalArgumentException("Unknown score type: " + type);
        }
    }
}
