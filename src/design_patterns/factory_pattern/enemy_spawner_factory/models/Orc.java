package design_patterns.factory_pattern.enemy_spawner_factory.models;

import design_patterns.factory_pattern.enemy_spawner_factory.abstracts.Enemy;

public class Orc implements Enemy {
    private String name = "Orc";
    private int health = 70;

    @Override
    public void attack() {
        System.out.println(name + " swings its crude axe with great force!");
    }

    @Override
    public void takeDamage(int damage) {
        this.health -= damage;
        System.out.println(name + " takes " + damage + " damage. Health: " + health);
        if (health <= 0) {
            System.out.println(name + " bellows and falls!");
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