package design_patterns.factory_pattern.auth_provider_factory.models;

import design_patterns.factory_pattern.auth_provider_factory.abstracts.Authenticator;

public class SAMLAuthenticator implements Authenticator {
    private String providerName = "SAML";
    private boolean authenticated = false;
    private String currentUser = null;

    @Override
    public boolean authenticate(String samlResponse, String unusedCredentials) { // SAML uses an XML response
        System.out.println("Processing SAML Response...");
        // Simulate SAML Response validation (e.g., XML parsing, signature validation)
        if (samlResponse.startsWith("<saml:Response>") && samlResponse.contains("NameID Value=\"samluser\"")) {
            this.authenticated = true;
            this.currentUser = "samluser";
            System.out.println(providerName + " response validated successfully for samluser.");
            return true;
        }
        System.out.println(providerName + " response validation failed.");
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
        return authenticated ? "User: " + currentUser + ", Attributes: {dept=IT, role=Viewer}" : "No user authenticated.";
    }

    @Override
    public String getProviderName() {
        return providerName;
    }
}