package design_patterns.strategy_pattern.scoreboard;

public abstract class ScoreAlgorithmBase {
	
	public abstract int calculateScore(int taps, int multiplier);
	public abstract String getScoreType(); // Added for better output
	

}
