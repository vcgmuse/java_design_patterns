package design_patterns.strategy_pattern.anomaly_detection_system;

import java.util.List;

public class Monitor {

	private AnomalyDetectionStrategy anomalyDetectionStrategy; 

	public void setAnomalyDetectionStrategy(AnomalyDetectionStrategy anomalyDetectionStrategy) {
		this.anomalyDetectionStrategy = anomalyDetectionStrategy;
	}


	public void analyze(Double value, List<Double> history) {
		if (anomalyDetectionStrategy == null) {
			System.out.println("Error: No AnomalyDetectionStrategy has been set for the Monitor.");
			return;
		}

		boolean isAnomalyDetected = anomalyDetectionStrategy.isAnomaly(value, history);

		String strategyName = anomalyDetectionStrategy.getClass().getSimpleName(); 

		System.out.println("--- Analysis Report ---");
		System.out.println("  Value to analyze: " + value);
		System.out.println("  Using Strategy:   " + strategyName);
		System.out.print("  Result:           ");

		if (isAnomalyDetected) {
			System.out.println("ANOMALY DETECTED!");
		} else {
			System.out.println("No Anomaly.");
		}
		System.out.println("-----------------------\n");
	}
}
