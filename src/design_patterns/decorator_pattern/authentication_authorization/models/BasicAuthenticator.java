// File: design_patterns/decorator_pattern/authentication_authorization/models/BasicAuthenticator.java
package design_patterns.decorator_pattern.authentication_authorization.models;

import design_patterns.decorator_pattern.authentication_authorization.interfaces.Authenticator;

public class BasicAuthenticator implements Authenticator {

    public BasicAuthenticator() {
        System.out.println("Initializing Basic Authenticator (Username/Password Check)");
    }

    @Override
    public boolean authenticate(String username, String password) {
        // --- MODIFICATION HERE ---
    	// This is where api request can checked against user inputs
        boolean isAuthenticated = ("admin".equals(username) && "password123".equals(password)) ||
                                  ("user1".equals(username) && "pass123".equals(password)); // <-- Added this line

        if (isAuthenticated) {
            System.out.println("Basic authentication successful for: " + username);
        } else {
            System.out.println("Basic authentication failed for: " + username);
        }
        return isAuthenticated;
    }

    @Override
    public String getDescription() {
        return "Basic Username/Password Authentication";
    }
}