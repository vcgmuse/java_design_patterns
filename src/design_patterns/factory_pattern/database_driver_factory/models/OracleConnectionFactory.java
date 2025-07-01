package design_patterns.factory_pattern.database_driver_factory.models;

import design_patterns.factory_pattern.database_driver_factory.abstracts.DatabaseConnection;
import design_patterns.factory_pattern.database_driver_factory.abstracts.DatabaseConnectionFactory;

// Concrete Creator for Oracle
public class OracleConnectionFactory extends DatabaseConnectionFactory {
    @Override
    public DatabaseConnection createConnection(String type) {
        if (type.equalsIgnoreCase("oracle")) {
            return new OracleConnection();
        }
        return null;
    }
}