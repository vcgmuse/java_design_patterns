// File: design_patterns/decorator_pattern/authentication_authorization/AuthChainBuilder.java
package design_patterns.decorator_pattern.authentication_authorization;

import design_patterns.decorator_pattern.authentication_authorization.interfaces.Authenticator;
import design_patterns.decorator_pattern.authentication_authorization.models.BasicAuthenticator;
import design_patterns.decorator_pattern.authentication_authorization.models.MFAAuthenticator;
import design_patterns.decorator_pattern.authentication_authorization.models.RoleBasedAuthAuthenticator;

public class AuthChainBuilder {
    private Authenticator currentAuthenticator; // Holds the current state of the decorated authenticator

    // Start the chain with a basic authenticator
    public AuthChainBuilder() {
        this.currentAuthenticator = new BasicAuthenticator();
    }

    // Or start with any existing authenticator (useful if you want to compose pre-built chains)
    public AuthChainBuilder(Authenticator initialAuthenticator) {
        if (initialAuthenticator == null) {
            throw new IllegalArgumentException("Initial authenticator cannot be null.");
        }
        this.currentAuthenticator = initialAuthenticator;
    }

    // Method to add MFA decorator
    public AuthChainBuilder withMFA() {
        this.currentAuthenticator = new MFAAuthenticator(this.currentAuthenticator);
        return this; // Return 'this' to allow method chaining
    }

    // Method to add Role-Based decorator
    public AuthChainBuilder withRole(String role) {
        this.currentAuthenticator = new RoleBasedAuthAuthenticator(this.currentAuthenticator, role);
        return this; // Return 'this' to allow method chaining
    }

    // You could add methods for other decorators here (e.g., withRateLimiting(), withIPWhitelist())

    // Final build method to get the fully configured authenticator
    public Authenticator build() {
        return this.currentAuthenticator;
    }
}