package design_patterns.strategy_pattern.anomaly_detection_system.controllers;

import java.util.List;

import design_patterns.strategy_pattern.anomaly_detection_system.AnomalyDetectionStrategy;

public class SimpleThresholdStrategy implements AnomalyDetectionStrategy{
	private double upperBound;
	private double lowerBound;

	public SimpleThresholdStrategy(double lowerBound, double upperBound) { 
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}

	@Override
	public boolean isAnomaly(Double currentValue, List<Double> historicalData) {

		return currentValue < lowerBound || currentValue > upperBound;
	}
}
