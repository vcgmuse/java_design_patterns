// File: design_patterns/decorator_pattern/file_access_control/Main.java
package design_patterns.decorator_pattern.file_access_control;

import design_patterns.decorator_pattern.file_access_control.interfaces.FileProcessor;


public class Main {
    public static void main(String[] args) {
        System.out.println("--- Decorator Pattern: File Access Control Example ---");

        String testFilePath1 = "data/document.txt";
        String testFilePath2 = "data/sensitive_data.bin";
        byte[] originalData1 = "Hello, Decorator Pattern!".getBytes();
        byte[] originalData2 = "Confidential Information".getBytes();

        // --- Scenario 1: Basic File Processing ---
        System.out.println("\n===== Scenario 1: Basic File Processing =====");
        FileProcessor basicProcessor = new FileProcessorBuilder(testFilePath1).build(); // <-- Corrected        basicProcessor.writeData(originalData1);
        byte[] readData1 = basicProcessor.readData();
        System.out.println("Read Data: " + new String(readData1));
        System.out.println("-------------------------------------");


        // --- Scenario 2: Encrypted File Processing ---
        System.out.println("\n===== Scenario 2: Encrypted File Processing =====");
        FileProcessor encryptedProcessor = new FileProcessorBuilder(testFilePath2)
                                                .withEncryption()
                                                .build();
        System.out.println("Configured Processor: " + encryptedProcessor.getDescription());
        encryptedProcessor.writeData(originalData2);
        byte[] readData2 = encryptedProcessor.readData();
        System.out.println("Read Data: " + new String(readData2)); // Should be decrypted back to original
        System.out.println("-------------------------------------");


        // --- Scenario 3: Encrypted + Compressed File Processing ---
        System.out.println("\n===== Scenario 3: Encrypted + Compressed File Processing =====");
        FileProcessor encryptedCompressedProcessor = new FileProcessorBuilder("data/compressed_doc.zip")
                                                          .withEncryption()
                                                          .withCompression() // Order matters: Encryption then Compression
                                                          .build();
        System.out.println("Configured Processor: " + encryptedCompressedProcessor.getDescription());
        byte[] complexData = "This is some data to be encrypted and compressed.".getBytes();
        encryptedCompressedProcessor.writeData(complexData);
        byte[] readComplexData = encryptedCompressedProcessor.readData();
        System.out.println("Read Data: " + new String(readComplexData));
        System.out.println("-------------------------------------");

        // --- Scenario 4: Logged + Encrypted + Compressed File Processing ---
        System.out.println("\n===== Scenario 4: Logged + Encrypted + Compressed File Processing =====");
        FileProcessor fullStackProcessor = new FileProcessorBuilder("data/log_secure_file.dat")
                                                .withAccessLogging() // Logging is outermost
                                                .withEncryption()
                                                .withCompression()
                                                .build();
        System.out.println("Configured Processor: " + fullStackProcessor.getDescription());
        byte[] sensitiveLogData = "Very sensitive log entry.".getBytes();
        fullStackProcessor.writeData(sensitiveLogData);
        byte[] readSensitiveLogData = fullStackProcessor.readData();
        System.out.println("Read Data: " + new String(readSensitiveLogData));
        System.out.println("-------------------------------------");

        // --- Scenario 5: Different Order (Compression then Encryption) ---
        System.out.println("\n===== Scenario 5: Different Order (Compression then Encryption) =====");
        FileProcessor compressedEncryptedProcessor = new FileProcessorBuilder("data/comp_enc_file.dat")
                                                            .withCompression()
                                                            .withEncryption() // Order matters: Compression then Encryption
                                                            .build();
        System.out.println("Configured Processor: " + compressedEncryptedProcessor.getDescription());
        byte[] orderData = "Order of decorators matters!".getBytes();
        compressedEncryptedProcessor.writeData(orderData);
        byte[] readOrderData = compressedEncryptedProcessor.readData();
        System.out.println("Read Data: " + new String(readOrderData));
        System.out.println("-------------------------------------");
    }
}