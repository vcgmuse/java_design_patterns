// File: design_patterns/decorator_pattern/file_access_control/models/AccessLoggingFileProcessor.java
package design_patterns.decorator_pattern.file_access_control.models;

import design_patterns.decorator_pattern.file_access_control.interfaces.FileProcessor;
import design_patterns.decorator_pattern.file_access_control.interfaces.FileProcessorDecorator;

import java.time.LocalDateTime; // For timestamping logs
import java.time.format.DateTimeFormatter; // For formatting timestamp

public class AccessLoggingFileProcessor extends FileProcessorDecorator {

    public AccessLoggingFileProcessor(FileProcessor decoratedProcessor) {
        super(decoratedProcessor); // Pass the processor to be decorated
        System.out.println("Adding Access Logging Layer.");
    }

    private String getCurrentTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public byte[] readData() {
        System.out.println("  [LOG - " + getCurrentTimestamp() + "] Attempting to read data.");
        byte[] data = super.readData(); // Delegate to the wrapped processor
        System.out.println("  [LOG - " + getCurrentTimestamp() + "] Data read completed.");
        return data;
    }

    @Override
    public boolean writeData(byte[] data) {
        System.out.println("  [LOG - " + getCurrentTimestamp() + "] Attempting to write data.");
        boolean success = super.writeData(data); // Delegate to the wrapped processor
        if (success) {
            System.out.println("  [LOG - " + getCurrentTimestamp() + "] Data write completed successfully.");
        } else {
            System.out.println("  [LOG - " + getCurrentTimestamp() + "] Data write failed.");
        }
        return success;
    }

    @Override
    public String getDescription() {
        // Append this decorator's description
        return super.getDescription() + " with Access Logging";
    }
}