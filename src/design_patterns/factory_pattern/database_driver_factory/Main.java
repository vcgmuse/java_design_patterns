package design_patterns.factory_pattern.database_driver_factory;

import design_patterns.factory_pattern.database_driver_factory.abstracts.DatabaseConnection;
import design_patterns.factory_pattern.database_driver_factory.abstracts.DatabaseConnectionFactory;
import design_patterns.factory_pattern.database_driver_factory.models.MongoDBConnectionFactory;
import design_patterns.factory_pattern.database_driver_factory.models.MySQLConnectionFactory;
import design_patterns.factory_pattern.database_driver_factory.models.OracleConnectionFactory;
import design_patterns.factory_pattern.database_driver_factory.models.PostgreSQLConnectionFactory;

public class Main {
    public static void main(String[] args) {

        System.out.println("--- Demonstrating Database Driver Factory Pattern ---");

        // Client code uses the abstract factory to order connections
        // It doesn't need to know the concrete connection classes (MySQLConnection, etc.)

        // 1. Order a MySQL connection
        System.out.println("\nOrdering a MySQL connection:");
        DatabaseConnectionFactory mySQLFactory = new MySQLConnectionFactory();
        DatabaseConnection mySQLConnection = mySQLFactory.orderConnection("mysql"); // Type parameter is used by the concrete factory
        if (mySQLConnection != null) {
            mySQLConnection.executeQuery("SELECT * FROM users;");
            mySQLConnection.disconnect();
        }

        // 2. Order a PostgreSQL connection
        System.out.println("\nOrdering a PostgreSQL connection:");
        DatabaseConnectionFactory pgSQLFactory = new PostgreSQLConnectionFactory();
        DatabaseConnection pgSQLConnection = pgSQLFactory.orderConnection("postgresql");
        if (pgSQLConnection != null) {
            pgSQLConnection.executeQuery("INSERT INTO logs (message) VALUES ('Application started');");
            pgSQLConnection.disconnect();
        }

        // 3. Order an Oracle connection
        System.out.println("\nOrdering an Oracle connection:");
        DatabaseConnectionFactory oracleFactory = new OracleConnectionFactory();
        DatabaseConnection oracleConnection = oracleFactory.orderConnection("oracle");
        if (oracleConnection != null) {
            oracleConnection.executeQuery("UPDATE products SET price = 100 WHERE id = 1;");
            oracleConnection.disconnect();
        }

        // 4. Order a MongoDB connection
        System.out.println("\nOrdering a MongoDB connection:");
        DatabaseConnectionFactory mongoFactory = new MongoDBConnectionFactory();
        DatabaseConnection mongoDBConnection = mongoFactory.orderConnection("mongodb");
        if (mongoDBConnection != null) {
            mongoDBConnection.executeQuery("db.collection.find({ status: 'active' });");
            mongoDBConnection.disconnect();
        }

        // 5. Attempt to order an unsupported connection type from a specific factory
        System.out.println("\nAttempting to order an unsupported connection type from MySQL factory:");
        DatabaseConnection unsupportedConnection = mySQLFactory.orderConnection("oracle");
        if (unsupportedConnection == null) {
            System.out.println("Successfully handled unsupported connection type (returned null).");
        }
    }
}