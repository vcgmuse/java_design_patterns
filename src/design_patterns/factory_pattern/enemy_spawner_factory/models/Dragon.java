package design_patterns.factory_pattern.enemy_spawner_factory.models;

import design_patterns.factory_pattern.enemy_spawner_factory.abstracts.Enemy;

public class Dragon implements Enemy {
    private String name = "Dragon";
    private int health = 500;

    @Override
    public void attack() {
        System.out.println(name + " unleashes a fiery breath!");
    }

    @Override
    public void takeDamage(int damage) {
        this.health -= damage;
        System.out.println(name + " takes " + damage + " damage. Health: " + health);
        if (health <= 0) {
            System.out.println(name + " crashes to the ground, defeated!");
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