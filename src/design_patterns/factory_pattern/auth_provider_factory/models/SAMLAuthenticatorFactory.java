package design_patterns.factory_pattern.auth_provider_factory.models;

import design_patterns.factory_pattern.auth_provider_factory.abstracts.Authenticator;
import design_patterns.factory_pattern.auth_provider_factory.abstracts.AuthenticatorFactory;

// Concrete Creator for SAML
public class SAMLAuthenticatorFactory extends AuthenticatorFactory {
    @Override
    public Authenticator createAuthenticator(String type) {
        if (type.equalsIgnoreCase("saml")) {
            return new SAMLAuthenticator();
        }
        return null;
    }
}