package design_patterns.decorator_pattern.authentication_authorization.models;
//File: security_patterns/decorator/authentication/models/RoleBasedAuthAuthenticator.java

import java.util.Set;

import design_patterns.decorator_pattern.authentication_authorization.abstracts.AuthenticatorDecorator;
import design_patterns.decorator_pattern.authentication_authorization.interfaces.Authenticator;

public class RoleBasedAuthAuthenticator extends AuthenticatorDecorator {
 private String requiredRole;

 public RoleBasedAuthAuthenticator(Authenticator authenticator, String requiredRole) {
     super(authenticator);
     this.requiredRole = requiredRole;
     System.out.println("Adding Role-Based Authentication (Role: " + requiredRole + ")");
 }

 @Override
 public boolean authenticate(String username, String password) {
     // First, delegate to the wrapped authenticator (e.g., basic + MFA).
     if (!super.authenticate(username, password)) {
         System.out.println("Role-Based Auth bypassed: Previous authentication failed for " + username);
         return false;
     }

     // Simulate a role check (in a real system, this would come from a database, etc.)
     boolean hasRole = false;
     if ("admin".equals(username) && requiredRole.equals("admin")) {
         hasRole = true;
     } else if ("user1".equals(username) && requiredRole.equals("user")) {
         hasRole = true;
     }

     if (hasRole) {
         System.out.println(username + " has the required role: " + requiredRole);
         return true;
     } else {
         System.out.println(username + " does not have the required role: " + requiredRole);
         return false;
     }
 }

 @Override
 public String getDescription() {
     return super.getDescription() + " with Role-Based Authentication (" + requiredRole + ")";
 }
}