package design_patterns.strategy_pattern.scoreboard.models;

import design_patterns.strategy_pattern.scoreboard.ScoreAlgorithmBase;

public class Balloon extends ScoreAlgorithmBase {
    @Override
    public int calculateScore(int taps, int multiplier) {
        // Balloon: Simple taps * multiplier
        return taps * multiplier;
    }

    @Override
    public String getScoreType() {
        return "Balloon";
    }
}
