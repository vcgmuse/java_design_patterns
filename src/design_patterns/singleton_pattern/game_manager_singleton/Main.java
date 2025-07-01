package design_patterns.singleton_pattern.game_manager_singleton;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Demonstrating Singleton Game Manager ---");

        // Get the initial instance of the GameManager.
        // This will trigger its one-time initialization.
        GameManager gm1 = GameManager.getInstance();
        System.out.println("GameManager Instance 1 (Hashcode): " + gm1.hashCode());
        System.out.println("Current Game State: " + gm1.getCurrentGameState());

        // Another part of the game (or another developer) tries to get the instance.
        // This will return the EXACT SAME instance created above.
        GameManager gm2 = GameManager.getInstance();
        System.out.println("GameManager Instance 2 (Hashcode): " + gm2.hashCode());

        // Verify that both references point to the same object.
        if (gm1 == gm2) {
            System.out.println("\nBoth GameManager instances are indeed the same object in memory!");
        }

        System.out.println("\n--- Simulating Game Flow ---");

        // 1. Start the game from the main menu
        System.out.println("\n--- Main Menu Interaction ---");
        gm1.startGame(); // Game transitions from MENU to PLAYING, Level 1

        // 2. Simulate gameplay actions
        System.out.println("\n--- During Gameplay ---");
        gm1.addScore(100); // Player gains score
        gm2.addScore(50);  // Another part of the game adds score (still on the same instance)
        System.out.println("Total Score (from gm1): " + gm1.getPlayerScore());

        // 3. Simulate completing a level
        System.out.println("\n--- Level Progression ---");
        gm1.levelCompleted(); // Level 1 completed, now on Level 2

        // 4. Simulate pausing and resuming the game
        System.out.println("\n--- Pausing and Resuming ---");
        gm2.pauseGame(); // Game transitions to PAUSED
        System.out.println("Current Game State (from gm1): " + gm1.getCurrentGameState());
        gm1.resumeGame(); // Game transitions back to PLAYING
        System.out.println("Current Game State (from gm2): " + gm2.getCurrentGameState());

        // 5. Simulate more gameplay
        gm1.addScore(200);

        // 6. Simulate the game ending
        System.out.println("\n--- Game Over Scenario ---");
        gm2.gameOver(); // Game transitions to GAME_OVER
        System.out.println("Current Game State (from gm1): " + gm1.getCurrentGameState());
        System.out.println("Final Player Score: " + gm1.getPlayerScore());

        // 7. Try to perform actions from an invalid state
        System.out.println("\n--- Invalid State Actions ---");
        gm1.addScore(50); // Should fail as game is over
        gm2.resumeGame(); // Should fail as game is over

        // 8. Start a new game after game over (possible as per game logic)
        System.out.println("\n--- Starting New Game After Game Over ---");
        gm1.startGame();
        System.out.println("New Game State: " + gm1.getCurrentGameState() + ", Level: " + gm1.getCurrentLevel() + ", Score: " + gm1.getPlayerScore());
    }
}