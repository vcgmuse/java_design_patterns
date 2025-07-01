// File: design_patterns/decorator_pattern/weapon_customization/interfaces/Weapon.java
package design_patterns.decorator_pattern.weapon_customization.interfaces;

public interface Weapon {
    double getAttackPower();
    String getDescription();
    
    /**
     * Returns the current durability (or lifespan) of the weapon.
     * @return The durability as a double.
     */
    double getDurability(); // <-- NEW METHOD ADDED
}