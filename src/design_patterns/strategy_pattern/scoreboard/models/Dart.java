package design_patterns.strategy_pattern.scoreboard.models;

import design_patterns.strategy_pattern.scoreboard.ScoreAlgorithmBase;

public class Dart extends ScoreAlgorithmBase{

    @Override
    public int calculateScore(int taps, int multiplier) {
        // Dart: High base score, but limited by taps
        return 100 + (taps * multiplier / 2);
    }

    @Override
    public String getScoreType() {
        return "Dart";
    }

}
