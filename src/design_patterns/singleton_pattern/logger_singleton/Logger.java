package design_patterns.singleton_pattern.logger_singleton;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Logger class implements the Singleton pattern to provide a single,
 * globally accessible logging instance for the entire application.
 * It uses the Initialization-on-demand holder idiom for lazy, thread-safe initialization.
 */
public class Logger {

    // Private constructor to prevent direct instantiation from outside
    private Logger() {
        System.out.println("Logger: Initializing the logging system (opened log file/connection)...");
        // In a real application, this is where you'd set up resources:
        // - Open a log file (e.g., new FileWriter("application.log", true))
        // - Establish connection to a logging service (e.g., Logstash, Splunk)
        // - Load logging configurations
    }

    // Static nested class that holds the single instance of Logger.
    // This leverages the JVM's class loading mechanism to ensure:
    // 1. Lazy initialization: INSTANCE is only created when LoggerHolder is first accessed (i.e., when getInstance() is called).
    // 2. Thread-safety: JVM guarantees that static initializers are executed safely by only one thread.
    private static class LoggerHolder {
        private static final Logger INSTANCE = new Logger();
    }

    /**
     * Provides the global access point to the single instance of the Logger.
     *
     * @return The single Logger instance.
     */
    public static Logger getInstance() {
        return LoggerHolder.INSTANCE;
    }

    // --- Public Logging Methods ---

    /**
     * Logs an informational message.
     * @param message The message to log.
     */
    public void logInfo(String message) {
        log("INFO", message);
    }

    /**
     * Logs a warning message.
     * @param message The message to log.
     */
    public void logWarning(String message) {
        log("WARNING", message);
    }

    /**
     * Logs an error message.
     * @param message The message to log.
     */
    public void logError(String message) {
        log("ERROR", message);
    }

    /**
     * Private helper method to format and output log messages.
     * @param level The log level (e.g., INFO, WARNING, ERROR).
     * @param message The message content.
     */
    private void log(String level, String message) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        // This line is updated for Java 17 compatibility
        System.out.println("[" + timestamp + "] [" + level + "] " + message);
    }

    /**
     * (Optional) Method to release resources held by the logger.
     * This might be called during application shutdown.
     */
    public void close() {
        System.out.println("Logger: Shutting down the logging system (closed log file/connection)...");
        // In a real application, this would close file handles,
        // flush pending messages, release network connections, etc.
    }
}