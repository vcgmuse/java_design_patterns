package design_patterns.factory_pattern.database_driver_factory.abstracts;

// Product Interface
public interface DatabaseConnection {
    void connect();
    void executeQuery(String query);
    void disconnect();
    String getConnectionInfo(); // Added to show specific connection type
}