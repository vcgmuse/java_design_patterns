package design_patterns.factory_pattern.database_driver_factory.models;

import design_patterns.factory_pattern.database_driver_factory.abstracts.DatabaseConnection;
import design_patterns.factory_pattern.database_driver_factory.abstracts.DatabaseConnectionFactory;

// Concrete Creator for MongoDB
public class MongoDBConnectionFactory extends DatabaseConnectionFactory {
    @Override
    public DatabaseConnection createConnection(String type) {
        if (type.equalsIgnoreCase("mongodb")) {
            return new MongoDBConnection();
        }
        return null;
    }
}