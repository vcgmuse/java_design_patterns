package design_patterns.singleton_pattern.game_manager_singleton;

public class GameManager {
    // Holder class for lazy, thread-safe initialization of the Singleton instance
    private static class GameManagerHolder {
        private static final GameManager INSTANCE = new GameManager();
    }

    private GameManager() {
        System.out.println("GameManager: Initializing game systems...");
        this.currentGameState = GameState.MENU; // Game starts at the main menu
        this.currentLevel = 1; // Default starting level
        this.playerScore = 0; // Initial score
        System.out.println("GameManager: Current state - " + currentGameState);
    }

    /**
     * Provides the global access point to the single GameManager instance.
     *
     * @return The single GameManager instance.
     */
    public static GameManager getInstance() {
        return GameManagerHolder.INSTANCE;
    }

    // Enum to define the various states of the game
    public enum GameState {
        MENU,
        PLAYING,
        PAUSED,
        GAME_OVER
    }

    private GameState currentGameState;
    private int currentLevel;
    private int playerScore;

    /**
     * Gets the current state of the game.
     * @return The current GameState.
     */
    public GameState getCurrentGameState() {
        return currentGameState;
    }

    /**
     * Gets the current level the player is on.
     * @return The current level number.
     */
    public int getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Gets the player's current score.
     * @return The player's score.
     */
    public int getPlayerScore() {
        return playerScore;
    }

    /**
     * Starts a new game, resetting the state, level, and score.
     */
    public void startGame() {
        if (currentGameState == GameState.MENU || currentGameState == GameState.GAME_OVER) {
            currentGameState = GameState.PLAYING;
            currentLevel = 1;
            playerScore = 0;
            System.out.println("GameManager: Starting new game. State - " + currentGameState + ", Level - " + currentLevel + ", Score - " + playerScore);
        } else {
            System.out.println("GameManager: Cannot start game from current state: " + currentGameState);
        }
    }

    /**
     * Pauses the game if it is currently playing.
     */
    public void pauseGame() {
        if (currentGameState == GameState.PLAYING) {
            currentGameState = GameState.PAUSED;
            System.out.println("GameManager: Pausing game. State - " + currentGameState);
        } else {
            System.out.println("GameManager: Cannot pause game from current state: " + currentGameState);
        }
    }

    /**
     * Resumes the game if it is currently paused.
     */
    public void resumeGame() {
        if (currentGameState == GameState.PAUSED) {
            currentGameState = GameState.PLAYING;
            System.out.println("GameManager: Resuming game. State - " + currentGameState);
        } else {
            System.out.println("GameManager: Cannot resume game from current state: " + currentGameState);
        }
    }

    /**
     * Marks the current level as completed and prepares for the next level.
     */
    public void levelCompleted() {
        if (currentGameState == GameState.PLAYING) {
            System.out.println("GameManager: Level " + currentLevel + " completed.");
            currentLevel++;
            System.out.println("GameManager: Loading next level: " + currentLevel);
            // In a real game, this would involve loading new assets, re-initializing game elements for the next level.
        } else {
            System.out.println("GameManager: Cannot complete level from current state: " + currentGameState);
        }
    }

    /**
     * Transitions the game to the 'Game Over' state.
     */
    public void gameOver() {
        if (currentGameState != GameState.GAME_OVER) {
            currentGameState = GameState.GAME_OVER;
            System.out.println("GameManager: Game Over. Final Score: " + playerScore);
            // Display game over screen, persist high scores, etc.
        } else {
            System.out.println("GameManager: Already in Game Over state.");
        }
    }

    /**
     * Adds points to the player's score if the game is currently playing.
     * @param points The number of points to add.
     */
    public void addScore(int points) {
        if (currentGameState == GameState.PLAYING) {
            playerScore += points;
            System.out.println("GameManager: Score increased by " + points + ". Current Score: " + playerScore);
        } else {
            System.out.println("GameManager: Cannot add score when game is not playing.");
        }
    }
}