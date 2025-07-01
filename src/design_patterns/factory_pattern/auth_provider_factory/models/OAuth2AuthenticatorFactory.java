package design_patterns.factory_pattern.auth_provider_factory.models;

import design_patterns.factory_pattern.auth_provider_factory.abstracts.Authenticator;
import design_patterns.factory_pattern.auth_provider_factory.abstracts.AuthenticatorFactory;

// Concrete Creator for OAuth2
public class OAuth2AuthenticatorFactory extends AuthenticatorFactory {
    @Override
    public Authenticator createAuthenticator(String type) {
        if (type.equalsIgnoreCase("oauth2") || type.equalsIgnoreCase("openidconnect")) {
            return new OAuth2Authenticator();
        }
        return null;
    }
}