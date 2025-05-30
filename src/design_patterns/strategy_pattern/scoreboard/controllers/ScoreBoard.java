package design_patterns.strategy_pattern.scoreboard.controllers;

import design_patterns.strategy_pattern.scoreboard.ScoreAlgorithmBase;

public class ScoreBoard {
    // The reference to the chosen strategy
    public ScoreAlgorithmBase algorithmBase;
    private int totalScore; // New: To keep track of the running score

    public ScoreBoard() {
        this.totalScore = 0;
    }

    // Method to set the strategy at runtime
    public void setScoreAlgorithm(ScoreAlgorithmBase algorithm) {
        this.algorithmBase = algorithm;
        System.out.println("Scoring algorithm set to: " + algorithm.getScoreType());
    }

    // The method that delegates to the current strategy
    public void showScore(int taps, int multiplier) {
        if (algorithmBase == null) {
            System.out.println("Error: No scoring algorithm has been set!");
            return;
        }

        int scoreEarned = algorithmBase.calculateScore(taps, multiplier);
        totalScore += scoreEarned; // Add to total score

        System.out.println("  Object: " + algorithmBase.getScoreType() + " | Taps: " + taps + " | Multiplier: " + multiplier);
        System.out.println("  Score earned: " + scoreEarned);
        System.out.println("  Current total score: " + totalScore);
        System.out.println("------------------------------------");
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void resetScore() {
        this.totalScore = 0;
        System.out.println("Score has been reset to 0.");
    }
}