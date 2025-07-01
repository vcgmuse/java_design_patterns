// File: design_patterns/decorator_pattern/weapon_customization/models/DurabilityBuff.java
package design_patterns.decorator_pattern.weapon_customization.models;

import design_patterns.decorator_pattern.weapon_customization.abstracts.WeaponDecorator;
import design_patterns.decorator_pattern.weapon_customization.interfaces.Weapon;

public class DurabilityBuff extends WeaponDecorator {
    private static final double DURABILITY_BONUS = 50.0; // The extra durability this buff adds

    public DurabilityBuff(Weapon decoratedWeapon) {
        super(decoratedWeapon); // Pass the weapon to be decorated
        System.out.println("Applying Durability Buff: Adds +" + DURABILITY_BONUS + " durability.");
    }

    @Override
    public double getAttackPower() {
        // Durability buff doesn't change attack power, so just delegate.
        return super.getAttackPower();
    }

    @Override
    public String getDescription() {
        // Append this buff's description.
        return super.getDescription() + " with Durability Buff";
    }

    @Override
    public double getDurability() {
        // Get the base durability from the wrapped weapon, then add our bonus.
        return super.getDurability() + DURABILITY_BONUS;
    }
}