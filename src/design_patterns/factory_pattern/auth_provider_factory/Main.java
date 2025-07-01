package design_patterns.factory_pattern.auth_provider_factory;

import design_patterns.factory_pattern.auth_provider_factory.abstracts.Authenticator;
import design_patterns.factory_pattern.auth_provider_factory.abstracts.AuthenticatorFactory;
import design_patterns.factory_pattern.auth_provider_factory.models.LDAPAuthenticatorFactory;
import design_patterns.factory_pattern.auth_provider_factory.models.LocalDBAuthenticatorFactory;
import design_patterns.factory_pattern.auth_provider_factory.models.OAuth2AuthenticatorFactory;
import design_patterns.factory_pattern.auth_provider_factory.models.SAMLAuthenticatorFactory;

public class Main {
    public static void main(String[] args) {

        System.out.println("--- Demonstrating Authentication Provider Factory Pattern ---");

        // Client code uses the abstract factory to get authenticators.
        // It doesn't need to know the concrete authenticator classes (LDAPAuthenticator, etc.).

        // Scenario 1: Using an LDAP Authenticator
        System.out.println("\n===== Attempting LDAP Authentication =====");
        AuthenticatorFactory ldapFactory = new LDAPAuthenticatorFactory();
        Authenticator ldapAuth = ldapFactory.getAuthenticator("ldap");
        if (ldapAuth != null) {
            boolean success = ldapAuth.authenticate("ldapuser", "ldap_pass");
            System.out.println("LDAP Auth Status: " + (success ? "SUCCESS" : "FAIL"));
            System.out.println("User Details: " + ldapAuth.getUserDetails());

            // Try with incorrect credentials
            success = ldapAuth.authenticate("ldapuser", "wrong_pass");
            System.out.println("LDAP Auth Status (wrong pass): " + (success ? "SUCCESS" : "FAIL"));
        }


        // Scenario 2: Using an OAuth2 Authenticator
        System.out.println("\n===== Attempting OAuth2 Authentication =====");
        AuthenticatorFactory oauth2Factory = new OAuth2AuthenticatorFactory();
        Authenticator oauth2Auth = oauth2Factory.getAuthenticator("oauth2");
        if (oauth2Auth != null) {
            boolean success = oauth2Auth.authenticate("oauthuser", "bearer_xyz123abc");
            System.out.println("OAuth2 Auth Status: " + (success ? "SUCCESS" : "FAIL"));
            System.out.println("User Details: " + oauth2Auth.getUserDetails());

            // Try with invalid token
            success = oauth2Auth.authenticate("oauthuser", "invalid_token");
            System.out.println("OAuth2 Auth Status (invalid token): " + (success ? "SUCCESS" : "FAIL"));
        }


        // Scenario 3: Using a Local Database Authenticator
        System.out.println("\n===== Attempting Local Database Authentication =====");
        AuthenticatorFactory localDBFactory = new LocalDBAuthenticatorFactory();
        Authenticator localDBAuth = localDBFactory.getAuthenticator("localdb");
        if (localDBAuth != null) {
            boolean success = localDBAuth.authenticate("admin", "secure_pass");
            System.out.println("Local DB Auth Status: " + (success ? "SUCCESS" : "FAIL"));
            System.out.println("User Details: " + localDBAuth.getUserDetails());

            // Try with incorrect username
            success = localDBAuth.authenticate("guest", "password");
            System.out.println("Local DB Auth Status (wrong user): " + (success ? "SUCCESS" : "FAIL"));
        }


        // Scenario 4: Using a SAML Authenticator
        System.out.println("\n===== Attempting SAML Authentication =====");
        AuthenticatorFactory samlFactory = new SAMLAuthenticatorFactory();
        Authenticator samlAuth = samlFactory.getAuthenticator("saml");
        if (samlAuth != null) {
            // Simulate a valid SAML response (simplified)
            String validSAMLResponse = "<saml:Response>...NameID Value=\"samluser\"...</saml:Response>";
            boolean success = samlAuth.authenticate(validSAMLResponse, null);
            System.out.println("SAML Auth Status: " + (success ? "SUCCESS" : "FAIL"));
            System.out.println("User Details: " + samlAuth.getUserDetails());
        }

        // Scenario 5: Attempting to get an unsupported authenticator type from a specific factory
        System.out.println("\n===== Attempting to get unsupported authenticator type from LDAP factory =====");
        Authenticator unsupportedAuth = ldapFactory.getAuthenticator("oauth2"); // LDAP factory won't create OAuth2
        if (unsupportedAuth == null) {
            System.out.println("Successfully handled unsupported authenticator type (returned null).");
        }
    }
}