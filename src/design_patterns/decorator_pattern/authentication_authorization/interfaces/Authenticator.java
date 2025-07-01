package design_patterns.decorator_pattern.authentication_authorization.interfaces;

public interface Authenticator {
 /**
  * Attempts to authenticate a user with the given credentials.
  * @param username The user's username.
  * @param password The user's password.
  * @return true if authentication is successful, false otherwise.
  */
 boolean authenticate(String username, String password);

 /**
  * Returns a description of the authentication method(s) used.
  * @return A string description.
  */
 String getDescription();
}