package design_patterns.factory_pattern.database_driver_factory.models;

import design_patterns.factory_pattern.database_driver_factory.abstracts.DatabaseConnection;

public class MongoDBConnection implements DatabaseConnection {
    private String info = "MongoDB NoSQL Database";

    @Override
    public void connect() {
        System.out.println("Connecting to " + info + " via MongoDB Driver...");
    }

    @Override
    public void executeQuery(String query) {
        System.out.println("Executing MongoDB command: '" + query + "' (e.g., find query)");
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