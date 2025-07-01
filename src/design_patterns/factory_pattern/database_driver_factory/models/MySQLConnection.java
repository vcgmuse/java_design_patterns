package design_patterns.factory_pattern.database_driver_factory.models;

import design_patterns.factory_pattern.database_driver_factory.abstracts.DatabaseConnection;

public class MySQLConnection implements DatabaseConnection {
    private String info = "MySQL Database";

    @Override
    public void connect() {
        System.out.println("Connecting to " + info + " using JDBC driver...");
    }

    @Override
    public void executeQuery(String query) {
        System.out.println("Executing MySQL query: '" + query + "'");
        // Simulate query execution
    }

    @Override
    public void disconnect() {
        System.out.println("Disconnecting from " + info + ".");
    }

    @Override
    public String getConnectionInfo() {
        return info;
    }
}