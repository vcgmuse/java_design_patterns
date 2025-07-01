package design_patterns.factory_pattern.database_driver_factory.models;

import design_patterns.factory_pattern.database_driver_factory.abstracts.DatabaseConnection;
import design_patterns.factory_pattern.database_driver_factory.abstracts.DatabaseConnectionFactory;

// Concrete Creator for PostgreSQL
public class PostgreSQLConnectionFactory extends DatabaseConnectionFactory {
    @Override
    public DatabaseConnection createConnection(String type) {
        if (type.equalsIgnoreCase("postgresql") || type.equalsIgnoreCase("postgres")) {
            return new PostgreSQLConnection();
        }
        return null;
    }
}