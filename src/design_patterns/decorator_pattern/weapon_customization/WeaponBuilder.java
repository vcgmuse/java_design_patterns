// File: design_patterns/decorator_pattern/weapon_customization/WeaponBuilder.java
package design_patterns.decorator_pattern.weapon_customization;

import design_patterns.decorator_pattern.weapon_customization.interfaces.Weapon;
import design_patterns.decorator_pattern.weapon_customization.models.BasicSword;
import design_patterns.decorator_pattern.weapon_customization.models.FireEnchantment;
import design_patterns.decorator_pattern.weapon_customization.models.ScopeAttachment;
import design_patterns.decorator_pattern.weapon_customization.models.DurabilityBuff;

public class WeaponBuilder {
    private Weapon currentWeapon; // Holds the current state of the decorated weapon

    // Start the chain with a basic sword by default
    public WeaponBuilder() {
        this.currentWeapon = new BasicSword();
    }

    // Or start with any existing weapon
    public WeaponBuilder(Weapon initialWeapon) {
        if (initialWeapon == null) {
            throw new IllegalArgumentException("Initial weapon cannot be null.");
        }
        this.currentWeapon = initialWeapon;
    }

    // Method to add Fire Enchantment
    public WeaponBuilder withFireEnchantment() {
        this.currentWeapon = new FireEnchantment(this.currentWeapon);
        return this; // Allows method chaining
    }

    // Method to add Scope Attachment
    public WeaponBuilder withScope() {
        this.currentWeapon = new ScopeAttachment(this.currentWeapon);
        return this; // Allows method chaining
    }

    // Method to add Durability Buff
    public WeaponBuilder withDurabilityBuff() {
        this.currentWeapon = new DurabilityBuff(this.currentWeapon);
        return this; // Allows method chaining
    }

    // You could add methods for other decorators here (e.g., withLifesteal(), withPoisonDamage())

    // Final build method to get the fully configured weapon
    public Weapon build() {
        return this.currentWeapon;
    }
}