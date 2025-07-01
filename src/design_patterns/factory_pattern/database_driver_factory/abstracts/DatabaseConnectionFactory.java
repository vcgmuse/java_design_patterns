package design_patterns.factory_pattern.database_driver_factory.abstracts;

// Creator Abstract Class
public abstract class DatabaseConnectionFactory {

    // Template method (uses the factory method)
    public DatabaseConnection orderConnection(String type) {
        DatabaseConnection connection = createConnection(type); // The Factory Method call

        if (connection != null) {
            connection.connect();
            System.out.println("--- Connection established to " + connection.getConnectionInfo() + " ---");
        } else {
            System.out.println("--- Failed to create connection of type: " + type + " ---");
        }
        return connection;
    }

    // The abstract Factory Method - to be implemented by Concrete Creators
    public abstract DatabaseConnection createConnection(String type);
}