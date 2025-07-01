package design_patterns.strategy_pattern.anomaly_detection_system;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import design_patterns.strategy_pattern.anomaly_detection_system.controllers.MovingAverageStrategy;
import design_patterns.strategy_pattern.anomaly_detection_system.controllers.RateOfChangeStrategy;
import design_patterns.strategy_pattern.anomaly_detection_system.controllers.SimpleThresholdStrategy;

public class Main {
    public static void main(String[] args) {
        Monitor monitor = new Monitor();

        // --- Historical Data Scenarios ---
        List<Double> stableHistory = Arrays.asList(10.0, 10.5, 9.8, 10.2, 10.1); // Relatively stable data
        List<Double> increasingHistory = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0); // Steadily increasing
        List<Double> decreasingHistory = Arrays.asList(100.0, 95.0, 90.0, 85.0, 80.0); // Steadily decreasing
        List<Double> volatileHistory = Arrays.asList(50.0, 70.0, 45.0, 65.0, 55.0); // Fluctuating
        List<Double> zeroHistory = Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0); // All zeros
        List<Double> singleDataPointHistory = Arrays.asList(5.0); // Too short for some strategies
        List<Double> emptyHistory = new ArrayList<>(); // Empty history

        System.out.println("--- Starting Anomaly Detection Demonstrations ---");

        // --- TEST SCENARIO 1: SimpleThresholdStrategy ---
        // Anomaly if value is outside [20.0, 60.0]
        System.out.println("\n===== Testing SimpleThresholdStrategy (Bounds: [20.0, 60.0]) =====");
        monitor.setAnomalyDetectionStrategy(new SimpleThresholdStrategy(20.0, 60.0));

        monitor.analyze(50.0, stableHistory); // Inside bounds: No Anomaly
        monitor.analyze(15.0, stableHistory); // Below lower bound: ANOMALY
        monitor.analyze(65.0, stableHistory); // Above upper bound: ANOMALY
        monitor.analyze(20.0, stableHistory); // Exactly on lower bound: No Anomaly (based on < or > strictness)
        monitor.analyze(60.0, stableHistory); // Exactly on upper bound: No Anomaly

        // --- TEST SCENARIO 2: MovingAverageStrategy ---
        // Anomaly if value is outside (Mean +/- 2.0 * StdDev)
        System.out.println("\n===== Testing MovingAverageStrategy (Deviation Factor: 2.0) =====");
        monitor.setAnomalyDetectionStrategy(new MovingAverageStrategy(2.0));

        monitor.analyze(10.3, stableHistory); // Within range: No Anomaly
        monitor.analyze(25.0, stableHistory); // Outside range (stable history mean=10.12, stddev=0.25): ANOMALY
        monitor.analyze(5.0, stableHistory);  // Outside range (stable history): ANOMALY
        monitor.analyze(100.0, increasingHistory); // Large jump from increasing trend: ANOMALY
        
        // Edge cases for MovingAverageStrategy
        monitor.analyze(10.0, singleDataPointHistory); // Insufficient history: No Anomaly (handled by strategy)
        monitor.analyze(10.0, emptyHistory);          // Empty history: No Anomaly (handled by strategy)


        // --- TEST SCENARIO 3: RateOfChangeStrategy ---
        // Anomaly if % change > 25.0 or (if previous was 0, absolute change > 5.0)
        System.out.println("\n===== Testing RateOfChangeStrategy (Change %: 25.0, Abs for Zero: 5.0) =====");
        monitor.setAnomalyDetectionStrategy(new RateOfChangeStrategy(25.0, 5.0));

        monitor.analyze(12.0, Arrays.asList(10.0)); // 20% change: No Anomaly (20% < 25%)
        monitor.analyze(15.0, Arrays.asList(10.0)); // 50% change: ANOMALY (50% > 25%)
        monitor.analyze(70.0, decreasingHistory);   // Large decrease from 80: ANOMALY (abs((70-80)/80*100) = 12.5%, wait this shouldn't be anomaly)
                                                    
        // Let's adjust this test case for clarity or the threshold.                                            
        // current=70, prev=80. Change = -10. abs % change = 12.5%.                                            
        // If threshold is 25%, then 12.5% is NOT an anomaly.
        // Let's use a different scenario for anomaly.
        
        monitor.analyze(100.0, Arrays.asList(50.0)); // From 50 to 100 is 100% change: ANOMALY
        monitor.analyze(40.0, Arrays.asList(80.0));  // From 80 to 40 is 50% change: ANOMALY

        // Test with previous value being zero
        monitor.analyze(4.0, zeroHistory);   // Current 4.0, prev 0.0, absThreshold 5.0: No Anomaly (4.0 < 5.0)
        monitor.analyze(6.0, zeroHistory);   // Current 6.0, prev 0.0, absThreshold 5.0: ANOMALY (6.0 > 5.0)
        monitor.analyze(0.0, zeroHistory);   // Current 0.0, prev 0.0: No Anomaly (0.0 not > 5.0)

        // Edge case for RateOfChangeStrategy
        monitor.analyze(10.0, emptyHistory); // Empty history: No Anomaly (handled by strategy)

        System.out.println("\n--- All Anomaly Detection Tests Completed ---");
    }
}