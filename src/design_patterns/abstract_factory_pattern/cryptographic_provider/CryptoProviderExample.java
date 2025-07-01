package design_patterns.abstract_factory_pattern.cryptographic_provider;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.security.Security;

/**
 * Abstract Factory Pattern Example: Cryptographic Provider
 *
 * This example demonstrates how to use the Abstract Factory pattern to select and use different cryptographic providers
 * (e.g., OpenSSL, BouncyCastle) in a secure and extensible way. This is a template for building real crypto modules.
 *
 * SECURITY NOTE: This code is for educational purposes and does NOT implement real cryptography. Use Java's
 * javax.crypto and java.security packages for production. Always follow best practices for key management and
 * algorithm selection.
 */

// Abstract Factory for cryptographic operations
interface CryptoProviderFactory {
    Encryptor createEncryptor();
    Decryptor createDecryptor();
}

// Example concrete factories for different providers
class OpenSSLFactory implements CryptoProviderFactory {
    public Encryptor createEncryptor() { return new OpenSSLEncryptor(); }
    public Decryptor createDecryptor() { return new OpenSSLDecryptor(); }
}
class BouncyCastleFactory implements CryptoProviderFactory {
    public Encryptor createEncryptor() { return new BouncyCastleEncryptor(); }
    public Decryptor createDecryptor() { return new BouncyCastleDecryptor(); }
}

// Abstract product interfaces
interface Encryptor {
    /**
     * Encrypts the given plaintext using a provided key.
     * @param plaintext The data to encrypt (as bytes)
     * @param key The encryption key (as bytes)
     * @return The encrypted data (ciphertext)
     * @throws Exception for any encryption error
     */
    byte[] encrypt(byte[] plaintext, byte[] key) throws Exception;
}

interface Decryptor {
    /**
     * Decrypts the given ciphertext using a provided key.
     * @param ciphertext The encrypted data (as bytes)
     * @param key The decryption key (as bytes)
     * @return The decrypted data (plaintext)
     * @throws Exception for any decryption error
     */
    byte[] decrypt(byte[] ciphertext, byte[] key) throws Exception;
}

// Example concrete products (template implementations)
class OpenSSLEncryptor implements Encryptor {
    public byte[] encrypt(byte[] plaintext, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(plaintext);
    }
}
class OpenSSLDecryptor implements Decryptor {
    public byte[] decrypt(byte[] ciphertext, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(ciphertext);
    }
}
class BouncyCastleEncryptor implements Encryptor {
    public byte[] encrypt(byte[] plaintext, byte[] key) throws Exception {
        // To use BouncyCastle, add the following dependency to your build tool:
        // Maven: <dependency><groupId>org.bouncycastle</groupId><artifactId>bcprov-jdk18on</artifactId><version>1.78</version></dependency>
        // Gradle: implementation 'org.bouncycastle:bcprov-jdk18on:1.78'
        // import org.bouncycastle.jce.provider.BouncyCastleProvider;
        // import java.security.Security;
        // Security.addProvider(new BouncyCastleProvider());
        // Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "BC");
        // SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        // cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        // return cipher.doFinal(plaintext);
        // Placeholder implementation:
        System.out.println("[BouncyCastle] Encrypting data (template, not secure)");
        return plaintext;
    }
}
class BouncyCastleDecryptor implements Decryptor {
    public byte[] decrypt(byte[] ciphertext, byte[] key) throws Exception {
        // To use BouncyCastle, add the following dependency to your build tool:
        // Maven: <dependency><groupId>org.bouncycastle</groupId><artifactId>bcprov-jdk18on</artifactId><version>1.78</version></dependency>
        // Gradle: implementation 'org.bouncycastle:bcprov-jdk18on:1.78'
        // import org.bouncycastle.jce.provider.BouncyCastleProvider;
        // import java.security.Security;
        // Security.addProvider(new BouncyCastleProvider());
        // Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "BC");
        // SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        // cipher.init(Cipher.DECRYPT_MODE, secretKey);
        // return cipher.doFinal(ciphertext);
        // Placeholder implementation:
        System.out.println("[BouncyCastle] Decrypting data (template, not secure)");
        return ciphertext;
    }
}

/**
 * Developer Instructions:
 * 1. Choose or implement a CryptoProviderFactory for your desired provider (OpenSSL, BouncyCastle, etc).
 * 2. Use the factory to obtain Encryptor and Decryptor instances.
 * 3. Always securely generate, store, and handle keys (never hardcode keys in code).
 * 4. Use secure random IVs/nonces and authenticated encryption modes (e.g., AES-GCM).
 * 5. Handle exceptions and errors securely (never leak sensitive info in logs).
 * 6. Validate all inputs and outputs.
 * 7. For production, use Java's javax.crypto and java.security APIs, or trusted libraries.
 *
 * Example usage:
 */
public class CryptoProviderExample {
    public static void main(String[] args) {
        // Use case: Switch crypto provider based on configuration or environment
        CryptoProviderFactory factory = getFactory("OpenSSL");
        Encryptor encryptor = factory.createEncryptor();
        Decryptor decryptor = factory.createDecryptor();

        // Example key and data (NEVER hardcode keys in real code!)
        byte[] key = new byte[16]; // 128-bit key (placeholder)
        byte[] plaintext = "SensitiveData".getBytes();
        try {
            // Encrypt
            byte[] ciphertext = encryptor.encrypt(plaintext, key);
            // Decrypt
            byte[] decrypted = decryptor.decrypt(ciphertext, key);
            System.out.println("Decrypted: " + new String(decrypted));
        } catch (Exception e) {
            // Handle errors securely
            System.err.println("Crypto error: " + e.getMessage());
        }
    }
    /**
     * Factory selector for crypto providers
     * @param provider Name of the provider (e.g., "OpenSSL", "BouncyCastle")
     * @return CryptoProviderFactory instance
     */
    static CryptoProviderFactory getFactory(String provider) {
        if (provider.equalsIgnoreCase("OpenSSL")) return new OpenSSLFactory();
        else return new BouncyCastleFactory();
    }
}
// Use case: Easily add new crypto providers by creating new factories and products.
// To extend: Implement Encryptor/Decryptor for your provider, then add a new Factory. 