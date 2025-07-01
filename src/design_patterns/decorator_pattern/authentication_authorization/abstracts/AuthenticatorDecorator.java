package design_patterns.decorator_pattern.authentication_authorization.abstracts;

import design_patterns.decorator_pattern.authentication_authorization.interfaces.Authenticator;

//This abstract class implements the Authenticator interface
//and provides a default delegation mechanism to the wrapped authenticator.
public abstract class AuthenticatorDecorator implements Authenticator {
 // This is the core: a reference to the Authenticator object being decorated.
 protected Authenticator authenticator;

 public AuthenticatorDecorator(Authenticator authenticator) {
     // Ensure that we always have a valid authenticator to wrap.
     if (authenticator == null) {
         throw new IllegalArgumentException("Authenticator cannot be null for decoration.");
     }
     this.authenticator = authenticator;
 }

 @Override
 public boolean authenticate(String username, String password) {
     // By default, decorators pass the authentication request to the wrapped authenticator.
     // Concrete decorators will add logic *before* or *after* this call.
     return this.authenticator.authenticate(username, password);
 }

 @Override
 public String getDescription() {
     // By default, decorators include the description of the wrapped authenticator.
     // Concrete decorators will append their own specific description.
     return this.authenticator.getDescription();
 }
}