package design_patterns.strategy_pattern.anomaly_detection_system.controllers;

import java.util.List;

import design_patterns.strategy_pattern.anomaly_detection_system.AnomalyDetectionStrategy;

public class RateOfChangeStrategy implements AnomalyDetectionStrategy {

    private double percentageThreshold; // Max allowed percentage change
    private double absoluteThresholdForZeroBase; // Max allowed absolute change if previous is zero

    /**
     * Constructs a RateOfChangeStrategy.
     * An anomaly is detected if the absolute percentage change from the last historical point
     * exceeds 'percentageThreshold'.
     * If the previous value was zero, an anomaly is detected if the current value's absolute
     * change from zero exceeds 'absoluteThresholdForZeroBase'.
     *
     * @param percentageThreshold The maximum allowed absolute percentage change (e.g., 20.0 for 20%).
     * @param absoluteThresholdForZeroBase The maximum allowed absolute value when the previous value was 0.
     * If previous was 0 and current's absolute value exceeds this, it's an anomaly.
     */
    public RateOfChangeStrategy(double percentageThreshold, double absoluteThresholdForZeroBase) {
        this.percentageThreshold = percentageThreshold;
        this.absoluteThresholdForZeroBase = absoluteThresholdForZeroBase;
    }

    @Override
    public boolean isAnomaly(Double currentValue, List<Double> historicalData) {
        // Rule 1: Cannot calculate rate of change without at least one previous data point.
        // In this case, we consider it not an anomaly by this specific strategy's definition.
        if (historicalData == null || historicalData.isEmpty()) {
            return false;
        }

        double previousValue = historicalData.get(historicalData.size() - 1);

        // Rule 2: Special handling for when the previous value is zero.
        // A percentage change from zero is mathematically undefined (division by zero).
        // Here, we define an anomaly as any significant *absolute* change from zero.
        if (previousValue == 0.0) {
            // If the current value is non-zero AND its absolute value exceeds a specified threshold,
            // we consider it an anomaly when the base was zero.
            return Math.abs(currentValue) > absoluteThresholdForZeroBase;
        }

        // Rule 3: Standard rate of percentage change calculation.
        // Calculate the absolute percentage change.
        // Example: if previous=100, current=110 -> (110-100)/100 * 100 = 10%
        // if previous=100, current=90 -> (90-100)/100 * 100 = -10%, abs = 10%
        double changePercent = Math.abs((currentValue - previousValue) / previousValue * 100.0);

        // Anomaly if the calculated percentage change exceeds our configured threshold.
        return changePercent > percentageThreshold;
    }
}