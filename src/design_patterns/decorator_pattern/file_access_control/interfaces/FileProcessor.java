// File: design_patterns/decorator_pattern/file_access_control/interfaces/FileProcessor.java
package design_patterns.decorator_pattern.file_access_control.interfaces;

public interface FileProcessor {
    /**
     * Reads data from the designated file source.
     * @return The raw byte array of data read.
     */
    byte[] readData();

    /**
     * Writes data to the designated file destination.
     * @param data The raw byte array of data to write.
     * @return true if the write operation was successful, false otherwise.
     */
    boolean writeData(byte[] data);

    /**
     * Returns a description of the file processing chain.
     * @return A string description.
     */
    String getDescription();
}