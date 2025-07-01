package design_patterns.factory_pattern.auth_provider_factory.models;

import design_patterns.factory_pattern.auth_provider_factory.abstracts.Authenticator;
import design_patterns.factory_pattern.auth_provider_factory.abstracts.AuthenticatorFactory;

// Concrete Creator for Local Database
public class LocalDBAuthenticatorFactory extends AuthenticatorFactory {
    @Override
    public Authenticator createAuthenticator(String type) {
        if (type.equalsIgnoreCase("localdb") || type.equalsIgnoreCase("internal")) {
            return new LocalDBAuthenticator();
        }
        return null;
    }
}