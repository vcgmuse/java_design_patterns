package design_patterns.factory_pattern.enemy_spawner_factory.models;

import design_patterns.factory_pattern.enemy_spawner_factory.abstracts.Enemy;
import design_patterns.factory_pattern.enemy_spawner_factory.abstracts.EnemySpawnerFactory;

// Concrete Creator for a Forest Level
public class ForestEnemySpawnerFactory extends EnemySpawnerFactory {
    @Override
    public Enemy createEnemy(String enemyType) {
        switch (enemyType.toLowerCase()) {
            case "goblin":
                return new Goblin();
            case "orc":
                return new Orc();
            // Dragons are not typically found in forests for this game
            default:
                System.out.println("Warning: Cannot spawn " + enemyType + " in the forest area.");
                return null;
        }
    }
}