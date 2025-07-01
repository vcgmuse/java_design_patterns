package design_patterns.factory_pattern.enemy_spawner_factory.models;

import design_patterns.factory_pattern.enemy_spawner_factory.abstracts.Enemy;
import design_patterns.factory_pattern.enemy_spawner_factory.abstracts.EnemySpawnerFactory;

// Concrete Creator for a Mountain Level
public class MountainEnemySpawnerFactory extends EnemySpawnerFactory {
    @Override
    public Enemy createEnemy(String enemyType) {
        switch (enemyType.toLowerCase()) {
            case "orc":
                return new Orc();
            case "dragon":
                return new Dragon();
            // Goblins are less common in mountains for this game
            default:
                System.out.println("Warning: Cannot spawn " + enemyType + " in the mountain area.");
                return null;
        }
    }
}