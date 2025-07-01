package design_patterns.singleton_pattern.logger_singleton;

public class Main {
    public static void main(String[] args) {

        System.out.println("--- Demonstrating Singleton Logger ---");

        // --- Step 1: Get the first instance of the Logger ---
        // This will trigger the instantiation of the Logger class
        // because getInstance() is called for the first time.
        Logger logger1 = Logger.getInstance();
        logger1.logInfo("Application started successfully.");

        // --- Step 2: Get another instance (which will be the same object) ---
        // No new Logger object will be created here.
        Logger logger2 = Logger.getInstance();
        logger2.logWarning("Configuration check: Missing optional database connection details.");

        // --- Step 3: Verify that both references point to the same object ---
        System.out.println("\n--- Instance Verification ---");
        System.out.println("Is logger1 the same instance as logger2? " + (logger1 == logger2));
        System.out.println("logger1 hashcode: " + logger1.hashCode());
        System.out.println("logger2 hashcode: " + logger2.hashCode());
        // Hash codes being identical confirms they are the same object in memory.

        // --- Step 4: Simulate logging from different parts/modules of the application ---
        // Any module can get the logger instance and use it consistently.
        System.out.println("\n--- Simulating Logging from Different Modules ---");
        simulateUserModuleActivity();
        simulateOrderProcessingActivity();

        // Log a critical error directly from main
        logger1.logError("Critical System Error: Unable to write to primary data store!");

        // --- Step 5: (Optional) Close the logger resources when application shuts down ---
        System.out.println("\n--- Application Shutdown ---");
        logger1.close(); // Only one instance needs to be closed.
    }

    // A method simulating activity in a "User Module"
    private static void simulateUserModuleActivity() {
        // Just call getInstance() - no need to pass the logger around
        Logger.getInstance().logInfo("User 'john_doe' logged in.");
        Logger.getInstance().logWarning("User 'jane_doe' attempted login with incorrect password.");
    }

    // A method simulating activity in an "Order Processing Module"
    private static void simulateOrderProcessingActivity() {
        Logger.getInstance().logInfo("Order #12345 received for processing.");
        Logger.getInstance().logError("Order #54321 processing failed: Invalid item ID.");
    }
}