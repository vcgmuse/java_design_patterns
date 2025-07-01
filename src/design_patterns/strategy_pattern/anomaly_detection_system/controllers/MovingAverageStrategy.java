package design_patterns.strategy_pattern.anomaly_detection_system.controllers;

import java.util.List;

import design_patterns.strategy_pattern.anomaly_detection_system.AnomalyDetectionStrategy;


public class MovingAverageStrategy implements AnomalyDetectionStrategy {

    private double deviationFactor;

    public MovingAverageStrategy(double deviationFactor) {
        this.deviationFactor = deviationFactor;
    }

    @Override
    public boolean isAnomaly(Double currentValue, List<Double> historicalData) {
        if (historicalData == null || historicalData.size() < 2) { 
            return false;
        }

        double mean = calculateMean(historicalData);
        double stdDeviation = calculateStandardDeviation(historicalData);

        double lowerLimit = mean - deviationFactor * stdDeviation;
        double upperLimit = mean + deviationFactor * stdDeviation;

        return currentValue < lowerLimit || currentValue > upperLimit;
    }

    private static double calculateMean(List<Double> data) {
        double sum = 0.0;
        for (Double num : data) {
            sum += num;
        }
        return sum / data.size();
    }

    private static double calculateStandardDeviation(List<Double> data) {
        double mean = calculateMean(data);
        double sumSquaredDiffs = 0.0;
        for (Double num : data) {
            sumSquaredDiffs += Math.pow(num - mean, 2);
        }

        if (data.size() == 0) {
            return 0.0; 
        }
        return Math.sqrt(sumSquaredDiffs / data.size());
    }

    public double getDeviationFactor() {
        return deviationFactor;
    }
}