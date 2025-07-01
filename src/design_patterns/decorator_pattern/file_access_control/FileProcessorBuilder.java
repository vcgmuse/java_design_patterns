// File: design_patterns/decorator_pattern/file_access_control/FileProcessorBuilder.java
package design_patterns.decorator_pattern.file_access_control;

import design_patterns.decorator_pattern.file_access_control.interfaces.FileProcessor;
import design_patterns.decorator_pattern.file_access_control.models.CompressionFileProcessor;
import design_patterns.decorator_pattern.file_access_control.models.EncryptionFileProcessor;
import design_patterns.decorator_pattern.file_access_control.models.AccessLoggingFileProcessor;
import design_patterns.decorator_pattern.file_access_control.models.BasicFileProcessor;

public class FileProcessorBuilder {
    private FileProcessor currentProcessor; // Holds the current state of the decorated processor

    // Start the chain with a basic file processor for a given path
    public FileProcessorBuilder(String filePath) {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path cannot be null or empty for BasicFileProcessor.");
        }
        this.currentProcessor = new BasicFileProcessor(filePath);
    }

    // Or start with any existing file processor (for further decoration)
    public FileProcessorBuilder(FileProcessor initialProcessor) {
        if (initialProcessor == null) {
            throw new IllegalArgumentException("Initial file processor cannot be null.");
        }
        this.currentProcessor = initialProcessor;
    }

    // Method to add Encryption decorator
    public FileProcessorBuilder withEncryption() {
        this.currentProcessor = new EncryptionFileProcessor(this.currentProcessor);
        return this; // Allows method chaining
    }

    // Method to add Compression decorator
    public FileProcessorBuilder withCompression() {
        this.currentProcessor = new CompressionFileProcessor(this.currentProcessor);
        return this; // Allows method chaining
    }

    // Method to add Access Logging decorator
    public FileProcessorBuilder withAccessLogging() {
        this.currentProcessor = new AccessLoggingFileProcessor(this.currentProcessor);
        return this; // Allows method chaining
    }

    // Final build method to get the fully configured file processor
    public FileProcessor build() {
        return this.currentProcessor;
    }
}