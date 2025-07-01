package design_patterns.factory_pattern.auth_provider_factory.models;

import design_patterns.factory_pattern.auth_provider_factory.abstracts.Authenticator;

public class LDAPAuthenticator implements Authenticator {
    private String providerName = "LDAP";
    private boolean authenticated = false;
    private String currentUser = null;

    @Override
    public boolean authenticate(String username, String password) {
        System.out.println("Attempting to authenticate '" + username + "' against " + providerName + "...");
        // Simulate LDAP authentication logic (e.g., connect to LDAP server)
        if (username.equals("ldapuser") && password.equals("ldap_pass")) {
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
        return authenticated ? "User: " + currentUser + ", Roles: [LDAP_USER, BASIC]" : "No user authenticated.";
    }

    @Override
    public String getProviderName() {
        return providerName;
    }
}