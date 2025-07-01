package design_patterns.factory_pattern.auth_provider_factory.abstracts;

// Creator Abstract Class
public abstract class AuthenticatorFactory {

    // Template method (uses the factory method)
    public Authenticator getAuthenticator(String type) {
        Authenticator authenticator = createAuthenticator(type); // The Factory Method call

        if (authenticator != null) {
            System.out.println("--- Authenticator for " + authenticator.getProviderName() + " prepared. ---");
        } else {
            System.out.println("--- Failed to prepare authenticator of type: " + type + " ---");
        }
        return authenticator;
    }

    // The abstract Factory Method - to be implemented by Concrete Creators
    public abstract Authenticator createAuthenticator(String type);
}