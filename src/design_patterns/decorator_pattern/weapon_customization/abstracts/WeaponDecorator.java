// File: design_patterns/decorator_pattern/weapon_customization/abstracts/WeaponDecorator.java
package design_patterns.decorator_pattern.weapon_customization.abstracts;

import design_patterns.decorator_pattern.weapon_customization.interfaces.Weapon;

public abstract class WeaponDecorator implements Weapon {
    protected Weapon decoratedWeapon;

    public WeaponDecorator(Weapon decoratedWeapon) {
        if (decoratedWeapon == null) {
            throw new IllegalArgumentException("Weapon to decorate cannot be null.");
        }
        this.decoratedWeapon = decoratedWeapon;
    }

    @Override
    public double getAttackPower() {
        return this.decoratedWeapon.getAttackPower();
    }

    @Override
    public String getDescription() {
        return this.decoratedWeapon.getDescription();
    }

    @Override
    public double getDurability() { // <-- ADD THIS METHOD
        // Delegate the call to the wrapped weapon
        return this.decoratedWeapon.getDurability();
    }
}