package design_patterns.strategy_pattern.scoreboard.models;

import design_patterns.strategy_pattern.scoreboard.ScoreAlgorithmBase;

public class BeachBall extends ScoreAlgorithmBase{

    @Override
    public int calculateScore(int taps, int multiplier) {
        // BeachBall: Less score per tap, but with a bonus if multiplier is high
        int baseScore = taps * (multiplier / 2);
        if (multiplier > 5) {
            baseScore += 20; // Bonus for high multiplier
        }
        return baseScore;
    }

	@Override
	public String getScoreType() {
		// TODO Auto-generated method stub
		return "Beach Ball";
	}

}
