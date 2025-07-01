package design_patterns.factory_pattern.auth_provider_factory.abstracts;

// Product Interface
public interface Authenticator {
    boolean authenticate(String username, String credentials); // credentials could be password, token, etc.
    boolean isAuthenticated();
    String getUserDetails(); // Simplified for demonstration
    String getProviderName(); // Added to show specific provider type
}