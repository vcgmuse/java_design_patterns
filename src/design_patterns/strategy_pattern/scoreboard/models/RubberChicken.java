package design_patterns.strategy_pattern.scoreboard.models;

import design_patterns.strategy_pattern.scoreboard.ScoreAlgorithmBase;

public class RubberChicken extends ScoreAlgorithmBase {
    @Override
    public int calculateScore(int taps, int multiplier) {
        // Rubber Chicken: Randomness!
        return (taps * multiplier) + (int)(Math.random() * 50); // Add some randomness
    }

    @Override
    public String getScoreType() {
        return "Rubber Chicken";
    }
}