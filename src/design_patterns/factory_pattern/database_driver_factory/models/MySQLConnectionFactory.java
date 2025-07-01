package design_patterns.factory_pattern.database_driver_factory.models;

import design_patterns.factory_pattern.database_driver_factory.abstracts.DatabaseConnection;
import design_patterns.factory_pattern.database_driver_factory.abstracts.DatabaseConnectionFactory;

// Concrete Creator for MySQL
public class MySQLConnectionFactory extends DatabaseConnectionFactory {
    @Override
    public DatabaseConnection createConnection(String type) {
        // In a real app, 'type' might be used for specific MySQL variants
        // For this example, we just return a new MySQLConnection
        if (type.equalsIgnoreCase("mysql")) {
            return new MySQLConnection();
        }
        return null; // Or throw an IllegalArgumentException
    }
}