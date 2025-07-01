// File: design_patterns/decorator_pattern/weapon_customization/models/ScopeAttachment.java
package design_patterns.decorator_pattern.weapon_customization.models;

import design_patterns.decorator_pattern.weapon_customization.abstracts.WeaponDecorator;
import design_patterns.decorator_pattern.weapon_customization.interfaces.Weapon;

public class ScopeAttachment extends WeaponDecorator {
    // A scope often provides a small accuracy/precision bonus, which can translate to damage.
    private static final double PRECISION_DAMAGE_BONUS = 2.5; 

    public ScopeAttachment(Weapon decoratedWeapon) {
        super(decoratedWeapon); // Pass the weapon to be decorated to the superclass constructor
        System.out.println("Attaching Scope: Improves precision, adds +" + PRECISION_DAMAGE_BONUS + " damage.");
    }

    @Override
    public double getAttackPower() {
        // Get the attack power from the wrapped weapon, then add our precision bonus.
        return super.getAttackPower() + PRECISION_DAMAGE_BONUS;
    }

    @Override
    public String getDescription() {
        // Append this attachment's description to the base description.
        return super.getDescription() + " with Scope (Precision)";
    }
}