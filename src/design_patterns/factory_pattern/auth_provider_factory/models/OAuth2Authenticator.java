package design_patterns.factory_pattern.auth_provider_factory.models;

import design_patterns.factory_pattern.auth_provider_factory.abstracts.Authenticator;

public class OAuth2Authenticator implements Authenticator {
    private String providerName = "OAuth2";
    private boolean authenticated = false;
    private String currentUser = null;

    @Override
    public boolean authenticate(String username, String token) { // For OAuth2, credentials might be an access token
        System.out.println("Validating OAuth2 token for '" + username + "'...");
        // Simulate OAuth2 token validation (e.g., call OAuth2 introspection endpoint)
        if (username.equals("oauthuser") && token.startsWith("bearer_")) {
            this.authenticated = true;
            this.currentUser = username;
            System.out.println(providerName + " token validation successful for " + username + ".");
            return true;
        }
        System.out.println(providerName + " token validation failed for " + username + ".");
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
        return authenticated ? "User: " + currentUser + ", Scopes: [profile, email]" : "No user authenticated.";
    }

    @Override
    public String getProviderName() {
        return providerName;
    }
}