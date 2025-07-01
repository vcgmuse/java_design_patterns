package design_patterns.factory_pattern.enemy_spawner_factory.models;

import design_patterns.factory_pattern.enemy_spawner_factory.abstracts.Enemy;

public class Goblin implements Enemy {
    private String name = "Goblin";
    private int health = 30;

    @Override
    public void attack() {
        System.out.println(name + " lunges forward with a rusty dagger!");
    }

    @Override
    public void takeDamage(int damage) {
        this.health -= damage;
        System.out.println(name + " takes " + damage + " damage. Health: " + health);
        if (health <= 0) {
            System.out.println(name + " has been defeated!");
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHealth() {
        return health;
    }
}