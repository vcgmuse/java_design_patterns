// File: design_patterns/decorator_pattern/weapon_customization/models/FireEnchantment.java
package design_patterns.decorator_pattern.weapon_customization.models;

import design_patterns.decorator_pattern.weapon_customization.abstracts.WeaponDecorator;
import design_patterns.decorator_pattern.weapon_customization.interfaces.Weapon;

public class FireEnchantment extends WeaponDecorator {
    private static final double FIRE_DAMAGE_BONUS = 5.0; // The extra damage this enchantment adds

    public FireEnchantment(Weapon decoratedWeapon) {
        super(decoratedWeapon); // Call the superclass constructor to wrap the weapon
        System.out.println("Applying Fire Enchantment: Adds +" + FIRE_DAMAGE_BONUS + " fire damage.");
    }

    @Override
    public double getAttackPower() {
        // Get the base attack power from the wrapped weapon, then add our fire damage bonus.
        return super.getAttackPower() + FIRE_DAMAGE_BONUS;
    }

    @Override
    public String getDescription() {
        // Append this enchantment's description to the base description.
        return super.getDescription() + " with Fire Enchantment";
    }
}