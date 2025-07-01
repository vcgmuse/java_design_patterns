package design_patterns.decorator_pattern.authentication_authorization.models;

import design_patterns.decorator_pattern.authentication_authorization.abstracts.AuthenticatorDecorator;
import design_patterns.decorator_pattern.authentication_authorization.interfaces.Authenticator;

public class MFAAuthenticator extends AuthenticatorDecorator {

 public MFAAuthenticator(Authenticator authenticator) {
     super(authenticator);
     System.out.println("Adding Multi-Factor Authentication (MFA) layer.");
 }

 @Override
 public boolean authenticate(String username, String password) {
     // First, delegate to the wrapped authenticator (e.g., basic username/password check)
     if (!super.authenticate(username, password)) {
         System.out.println("MFA bypassed: Basic authentication failed for " + username);
         return false; // If basic authentication fails, MFA isn't even attempted.
     }

     // If basic authentication passes, then perform the MFA check.
     // In a real app, this would involve sending a code, verifying a token, etc.
     // For demonstration, let's assume MFA is always successful for "admin" but fails for others.
     boolean mfaPassed = "admin".equals(username); // Simulate MFA check
     
     if (mfaPassed) {
         System.out.println("MFA check successful for: " + username);
     } else {
         System.out.println("MFA check failed for: " + username);
     }
     return mfaPassed;
 }

 @Override
 public String getDescription() {
     // Append this decorator's description to the base description.
     return super.getDescription() + " with MFA";
 }
}