// File: design_patterns/decorator_pattern/weapon_customization/models/BasicSword.java
package design_patterns.decorator_pattern.weapon_customization.models;

import design_patterns.decorator_pattern.weapon_customization.interfaces.Weapon;

public class BasicSword implements Weapon {
    private static final double BASE_DAMAGE = 10.0;
    private static final double BASE_DURABILITY = 100.0; // Define base durability
    private String description = "A simple iron sword";

    public BasicSword() {
        System.out.println("Forging a Basic Iron Sword.");
    }

    @Override
    public double getAttackPower() {
        return BASE_DAMAGE;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double getDurability() { // <-- IMPLEMENTATION FOR NEW METHOD
        return BASE_DURABILITY;
    }
}