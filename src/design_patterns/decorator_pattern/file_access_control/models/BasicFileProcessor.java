// File: design_patterns/decorator_pattern/file_access_control/models/BasicFileProcessor.java
package design_patterns.decorator_pattern.file_access_control.models;

import design_patterns.decorator_pattern.file_access_control.interfaces.FileProcessor;

public class BasicFileProcessor implements FileProcessor {
    private String filePath; // Simulate a file path

    public BasicFileProcessor(String filePath) {
        this.filePath = filePath;
        System.out.println("Initializing Basic File Processor for: " + filePath);
    }

    @Override
    public byte[] readData() {
        System.out.println("Reading raw data from " + filePath + ".");
        // In a real scenario, this would read from the actual file.
        // For demo, return dummy data that includes the path.
        return ("Content from " + filePath).getBytes();
    }

    @Override
    public boolean writeData(byte[] data) {
        System.out.println("Writing raw data to " + filePath + ": " + new String(data));
        // In a real scenario, this would write to the actual file.
        return true; // Assume success for demo
    }

    @Override
    public String getDescription() {
        return "Basic File Operations on " + filePath;
    }
}