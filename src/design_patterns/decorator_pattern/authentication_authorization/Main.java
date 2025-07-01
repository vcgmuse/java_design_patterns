// File: design_patterns/decorator_pattern/authentication_authorization/Main.java
package design_patterns.decorator_pattern.authentication_authorization;

import design_patterns.decorator_pattern.authentication_authorization.interfaces.Authenticator;
import design_patterns.decorator_pattern.authentication_authorization.models.BasicAuthenticator;


public class Main {
    public static void main(String[] args) {
        System.out.println("--- Decorator Pattern: Authentication/Authorization Use Cases ---");

        // --- Scenario 1: Basic Authentication Only ---
        System.out.println("\n===== Scenario 1: Basic Authentication Only =====");
        Authenticator basicAuth = new AuthChainBuilder(new BasicAuthenticator()).build(); // Or just new BasicAuthenticator()
        System.out.println("Configured Authenticator: " + basicAuth.getDescription());

        // Use Case 1.1: Successful Basic Authentication
        System.out.println("Attempt 'admin' / 'password123': " + (basicAuth.authenticate("admin", "password123") ? "SUCCESS" : "FAIL"));
        // Use Case 1.2: Failed Basic Authentication
        System.out.println("Attempt 'wronguser' / 'wrongpass': " + (basicAuth.authenticate("wronguser", "wrongpass") ? "SUCCESS" : "FAIL"));

        // --- Scenario 2: Basic + MFA Authentication ---
        System.out.println("\n===== Scenario 2: Basic + MFA Authentication =====");
        Authenticator mfaAuthChain = new AuthChainBuilder() // Starts with Basic
                                          .withMFA()        // Adds MFA
                                          .build();
        System.out.println("Configured Authenticator: " + mfaAuthChain.getDescription());

        // Use Case 2.1: Successful Basic + MFA Authentication
        System.out.println("Attempt 'admin' / 'password123': " + (mfaAuthChain.authenticate("admin", "password123") ? "SUCCESS" : "FAIL"));
        // Use Case 2.2: Basic Authentication Fails (MFA not reached)
        System.out.println("Attempt 'wronguser' / 'wrongpass': " + (mfaAuthChain.authenticate("wronguser", "wrongpass") ? "SUCCESS" : "FAIL"));
        // Use Case 2.3: Basic Authentication Passes, MFA Fails (if MFA logic were more complex for 'user1')
        // (In our simplified MFA, 'user1' will pass MFA if basic passes. For a true MFA fail,
        // you'd need more complex MFA logic or a separate test user).
        // For current setup, 'user1' / 'pass123' will succeed if basic and MFA logic is updated:
        System.out.println("Attempt 'user1' / 'pass123': " + (mfaAuthChain.authenticate("user1", "pass123") ? "SUCCESS" : "FAIL"));


        // --- Scenario 3: Basic + MFA + Role-Based (Admin Role) Authentication ---
        System.out.println("\n===== Scenario 3: Basic + MFA + Role-Based (Admin) =====");
        Authenticator adminRoleAuthChain = new AuthChainBuilder()
                                                .withMFA()
                                                .withRole("admin") // Requires 'admin' role
                                                .build();
        System.out.println("Configured Authenticator: " + adminRoleAuthChain.getDescription());

        // Use Case 3.1: Full Success (Basic + MFA + Admin Role)
        System.out.println("Attempt 'admin' / 'password123': " + (adminRoleAuthChain.authenticate("admin", "password123") ? "SUCCESS" : "FAIL"));
        // Use Case 3.2: Role Check Fails (Basic + MFA pass, but role is wrong)
        System.out.println("Attempt 'user1' / 'pass123' (requires 'admin' role): " + (adminRoleAuthChain.authenticate("user1", "pass123") ? "SUCCESS" : "FAIL"));
        // Use Case 3.3: Failure at an earlier stage (Basic or MFA)
        System.out.println("Attempt 'wronguser' / 'wrongpass': " + (adminRoleAuthChain.authenticate("wronguser", "wrongpass") ? "SUCCESS" : "FAIL"));


        // --- Scenario 4: Basic + MFA + Role-Based (User Role) Authentication ---
        System.out.println("\n===== Scenario 4: Basic + MFA + Role-Based (User) =====");
        Authenticator userRoleAuthChain = new AuthChainBuilder()
                                               .withMFA()
                                               .withRole("user") // Requires 'user' role
                                               .build();
        System.out.println("Configured Authenticator: " + userRoleAuthChain.getDescription());

        // Use Case 4.1: Full Success (Basic + MFA + User Role)
        System.out.println("Attempt 'user1' / 'pass123': " + (userRoleAuthChain.authenticate("user1", "pass123") ? "SUCCESS" : "FAIL"));
        // Use Case 4.2: Role Check Fails (Basic + MFA pass, but role is wrong)
        System.out.println("Attempt 'admin' / 'password123' (requires 'user' role): " + (userRoleAuthChain.authenticate("admin", "password123") ? "SUCCESS" : "FAIL"));
    }
}