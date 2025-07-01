package design_patterns.strategy_pattern.anomaly_detection_system;

import java.util.List;

public interface AnomalyDetectionStrategy {
	public boolean isAnomaly(Double currentValue, List<Double> historicalData);
	

}
