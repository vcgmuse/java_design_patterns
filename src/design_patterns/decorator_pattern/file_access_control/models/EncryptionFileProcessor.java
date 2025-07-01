// File: design_patterns/decorator_pattern/file_access_control/models/EncryptionFileProcessor.java
package design_patterns.decorator_pattern.file_access_control.models;


import design_patterns.decorator_pattern.file_access_control.interfaces.FileProcessor;
import design_patterns.decorator_pattern.file_access_control.interfaces.FileProcessorDecorator;

public class EncryptionFileProcessor extends FileProcessorDecorator {

    public EncryptionFileProcessor(FileProcessor decoratedProcessor) {
        super(decoratedProcessor); // Pass the processor to be decorated
        System.out.println("Adding Encryption Layer.");
    }

    @Override
    public byte[] readData() {
        // Read encrypted data from the wrapped processor
        byte[] encryptedData = super.readData();
        // Simulate decryption
        byte[] decryptedData = decrypt(encryptedData);
        System.out.println("  - Decrypting data during read.");
        return decryptedData;
    }

    @Override
    public boolean writeData(byte[] data) {
        // Simulate encryption before writing
        byte[] encryptedData = encrypt(data);
        System.out.println("  - Encrypting data during write.");
        // Write encrypted data using the wrapped processor
        return super.writeData(encryptedData);
    }

    @Override
    public String getDescription() {
        // Append this decorator's description
        return super.getDescription() + " with Encryption";
    }

    // --- Simulated Encryption/Decryption Methods ---
    private byte[] encrypt(byte[] data) {
        // Simple XOR encryption for demonstration
        byte[] encrypted = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            encrypted[i] = (byte) (data[i] ^ 0x55); // XOR with a key byte
        }
        return encrypted;
    }

    private byte[] decrypt(byte[] data) {
        // Simple XOR decryption (same operation reverses it)
        byte[] decrypted = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            decrypted[i] = (byte) (data[i] ^ 0x55); // XOR with the same key byte
        }
        return decrypted;
    }
}