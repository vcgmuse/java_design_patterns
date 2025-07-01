package design_patterns.factory_pattern.enemy_spawner_factory;

import design_patterns.factory_pattern.enemy_spawner_factory.abstracts.Enemy;
import design_patterns.factory_pattern.enemy_spawner_factory.abstracts.EnemySpawnerFactory;
import design_patterns.factory_pattern.enemy_spawner_factory.models.ForestEnemySpawnerFactory;
import design_patterns.factory_pattern.enemy_spawner_factory.models.MountainEnemySpawnerFactory;

public class Main {
    public static void main(String[] args) {

        System.out.println("--- Demonstrating Enemy Spawner Factory Pattern ---");

        // Client code uses the abstract factory to deploy enemies.
        // It doesn't need to know the concrete enemy classes (Goblin, Orc, Dragon).

        System.out.println("\n===== Entering the Forest Level =====");
        EnemySpawnerFactory forestSpawner = new ForestEnemySpawnerFactory();

        // Spawn some forest enemies
        Enemy forestGoblin = forestSpawner.deployEnemy("goblin");
        if (forestGoblin != null) {
            forestGoblin.attack();
            forestGoblin.takeDamage(15);
        }

        Enemy forestOrc = forestSpawner.deployEnemy("orc");
        if (forestOrc != null) {
            forestOrc.attack();
            forestOrc.takeDamage(25);
        }

        // Try to spawn a Dragon in the forest (not supported by ForestSpawner)
        Enemy forestDragonAttempt = forestSpawner.deployEnemy("dragon");
        if (forestDragonAttempt == null) {
            System.out.println("Player: Looks like no dragons here. Good.");
        }


        System.out.println("\n===== Ascending the Mountain Peak =====");
        EnemySpawnerFactory mountainSpawner = new MountainEnemySpawnerFactory();

        // Spawn some mountain enemies
        Enemy mountainOrc = mountainSpawner.deployEnemy("orc");
        if (mountainOrc != null) {
            mountainOrc.attack();
            mountainOrc.takeDamage(30);
        }

        Enemy mountainDragon = mountainSpawner.deployEnemy("dragon");
        if (mountainDragon != null) {
            mountainDragon.attack();
            mountainDragon.takeDamage(100);
            mountainDragon.takeDamage(200); // More damage!
        }

        // Try to spawn a Goblin in the mountains (not supported by MountainSpawner)
        Enemy mountainGoblinAttempt = mountainSpawner.deployEnemy("goblin");
        if (mountainGoblinAttempt == null) {
            System.out.println("Player: No small fries in these heights!");
        }
    }
}