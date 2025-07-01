package design_patterns.factory_pattern.auth_provider_factory.models;

import design_patterns.factory_pattern.auth_provider_factory.abstracts.Authenticator;

public class LocalDBAuthenticator implements Authenticator {
    private String providerName = "Local Database";
    private boolean authenticated = false;
    private String currentUser = null;

    @Override
    public boolean authenticate(String username, String password) {
        System.out.println("Checking '" + username + "' against " + providerName + "...");
        // Simulate local database lookup (e.g., hash password and compare)
        if (username.equals("admin") && password.equals("secure_pass")) {
            this.authenticated = true;
            this.currentUser = username;
            System.out.println(providerName + " authentication successful for " + username + ".");
            return true;
        }
        System.out.println(providerName + " authentication failed for " + username + ".");
        this.authenticated = false;
        this.currentUser = null;
        return false;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public String getUserDetails() {
        return authenticated ? "User: " + currentUser + ", Permissions: [ALL]" : "No user authenticated.";
    }

    @Override
    public String getProviderName() {
        return providerName;
    }
}