// File: design_patterns/decorator_pattern/file_access_control/models/CompressionFileProcessor.java
package design_patterns.decorator_pattern.file_access_control.models;

import design_patterns.decorator_pattern.file_access_control.interfaces.FileProcessor;
import design_patterns.decorator_pattern.file_access_control.interfaces.FileProcessorDecorator;

public class CompressionFileProcessor extends FileProcessorDecorator {

    public CompressionFileProcessor(FileProcessor decoratedProcessor) {
        super(decoratedProcessor); // Pass the processor to be decorated
        System.out.println("Adding Compression Layer.");
    }

    @Override
    public byte[] readData() {
        // Read compressed data from the wrapped processor
        byte[] compressedData = super.readData();
        // Simulate decompression
        byte[] decompressedData = decompress(compressedData);
        System.out.println("  - Decompressing data during read.");
        return decompressedData;
    }

    @Override
    public boolean writeData(byte[] data) {
        // Simulate compression before writing
        byte[] compressedData = compress(data);
        System.out.println("  - Compressing data during write.");
        // Write compressed data using the wrapped processor
        return super.writeData(compressedData);
    }

    @Override
    public String getDescription() {
        // Append this decorator's description
        return super.getDescription() + " with Compression";
    }

    // --- Simulated Compression/Decompression Methods ---
    private byte[] compress(byte[] data) {
        // For demonstration, let's just add a simple prefix/suffix or basic alteration
        // In a real scenario, this would use a compression library like GZIP, ZLIB.
        String original = new String(data);
        String compressedString = "COMPRESSED(" + original + ")";
        return compressedString.getBytes();
    }

    private byte[] decompress(byte[] data) {
        // Reverse the simulation from compress()
        String compressedString = new String(data);
        if (compressedString.startsWith("COMPRESSED(") && compressedString.endsWith(")")) {
            String original = compressedString.substring("COMPRESSED(".length(), compressedString.length() - 1);
            return original.getBytes();
        }
        return data; // If not in simulated compressed format, return as is
    }
}