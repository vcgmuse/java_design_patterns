package design_patterns.strategy_pattern.scoreboard.models;

import design_patterns.strategy_pattern.scoreboard.ScoreAlgorithmBase;

public class Clown extends ScoreAlgorithmBase {

    @Override
    public int calculateScore(int taps, int multiplier) {
        // Clown: Score based on taps, but multiplier is less effective
        return (taps * 2) + (multiplier / 3);
    }

    @Override
    public String getScoreType() {
        return "Clown";
    }

}
