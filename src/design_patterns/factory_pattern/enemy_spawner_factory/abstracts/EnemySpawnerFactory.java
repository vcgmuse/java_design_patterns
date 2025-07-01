package design_patterns.factory_pattern.enemy_spawner_factory.abstracts;

// Creator Abstract Class
public abstract class EnemySpawnerFactory {

    // Template method (uses the factory method)
    public Enemy deployEnemy(String enemyType) {
        Enemy enemy = createEnemy(enemyType); // The Factory Method call

        if (enemy != null) {
            System.out.println("--- " + enemy.getName() + " has been spawned and prepared for battle! ---");
        } else {
            System.out.println("--- Failed to spawn enemy of type: " + enemyType + " in this area. ---");
        }
        return enemy;
    }

    // The abstract Factory Method - to be implemented by Concrete Creators
    public abstract Enemy createEnemy(String enemyType);
}