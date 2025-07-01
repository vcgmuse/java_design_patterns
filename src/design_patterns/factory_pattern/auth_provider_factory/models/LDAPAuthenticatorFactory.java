package design_patterns.factory_pattern.auth_provider_factory.models;

import design_patterns.factory_pattern.auth_provider_factory.abstracts.Authenticator;
import design_patterns.factory_pattern.auth_provider_factory.abstracts.AuthenticatorFactory;

// Concrete Creator for LDAP
public class LDAPAuthenticatorFactory extends AuthenticatorFactory {
    @Override
    public Authenticator createAuthenticator(String type) {
        // In a real app, 'type' might ensure specific LDAP configs (e.g., AD vs OpenLDAP)
        if (type.equalsIgnoreCase("ldap")) {
            return new LDAPAuthenticator();
        }
        return null; // Or throw an IllegalArgumentException
    }
}