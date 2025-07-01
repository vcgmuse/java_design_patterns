// File: design_patterns/decorator_pattern/weapon_customization/Main.java
package design_patterns.decorator_pattern.weapon_customization;

import design_patterns.decorator_pattern.weapon_customization.interfaces.Weapon;
import design_patterns.decorator_pattern.weapon_customization.models.BasicSword;
import design_patterns.decorator_pattern.weapon_customization.models.FireEnchantment;
import design_patterns.decorator_pattern.weapon_customization.models.ScopeAttachment;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Decorator Pattern: Weapon Customization Example ---");

        // --- Scenario 1: A Basic Sword ---
        System.out.println("\n===== Scenario 1: A Basic Sword =====");
        Weapon basicSword = new BasicSword();
        System.out.println("Configured Weapon: " + basicSword.getDescription());
        System.out.println("  - Attack Power: " + String.format("%.1f", basicSword.getAttackPower()));
        System.out.println("  - Durability: " + String.format("%.1f", basicSword.getDurability()));
        System.out.println("-------------------------------------");


        // --- Scenario 2: A Fire-Enchanted Sword ---
        System.out.println("\n===== Scenario 2: A Fire-Enchanted Sword =====");
        // Using direct constructor for clarity
        Weapon fireSword = new FireEnchantment(new BasicSword());
        System.out.println("Configured Weapon: " + fireSword.getDescription());
        System.out.println("  - Attack Power: " + String.format("%.1f", fireSword.getAttackPower()));
        System.out.println("  - Durability: " + String.format("%.1f", fireSword.getDurability()));
        System.out.println("-------------------------------------");


        // --- Scenario 3: A Fire Sword with a Scope ---
        System.out.println("\n===== Scenario 3: A Fire Sword with a Scope =====");
        // Chaining decorators manually
        Weapon scopedFireSword = new ScopeAttachment(new FireEnchantment(new BasicSword()));
        System.out.println("Configured Weapon: " + scopedFireSword.getDescription());
        System.out.println("  - Attack Power: " + String.format("%.1f", scopedFireSword.getAttackPower()));
        System.out.println("  - Durability: " + String.format("%.1f", scopedFireSword.getDurability()));
        System.out.println("-------------------------------------");


        // --- Scenario 4: A Fully Customized Legendary Sword (using Builder) ---
        System.out.println("\n===== Scenario 4: A Fully Customized Legendary Sword (using Builder) =====");
        Weapon legendarySword = new WeaponBuilder() // Starts with BasicSword
                                    .withFireEnchantment()    // Add fire magic
                                    .withScope()              // Add scope for precision
                                    .withDurabilityBuff()     // Add durability buff
                                    .build();                 // Get the final weapon

        System.out.println("Configured Weapon: " + legendarySword.getDescription());
        System.out.println("  - Attack Power: " + String.format("%.1f", legendarySword.getAttackPower()));
        System.out.println("  - Durability: " + String.format("%.1f", legendarySword.getDurability()));
        System.out.println("-------------------------------------");


        // --- Scenario 5: Re-buffing an Existing Weapon (using Builder with existing weapon) ---
        System.out.println("\n===== Scenario 5: Re-buffing an Existing Weapon =====");
        // Imagine you found an existing 'fireSword' and want to add durability to it
        Weapon alreadyFireSword = new FireEnchantment(new BasicSword()); // Start with an already built fire sword
        System.out.println("Initial Weapon: " + alreadyFireSword.getDescription());

        Weapon superFireSword = new WeaponBuilder(alreadyFireSword) // Start builder with existing weapon
                                      .withDurabilityBuff()      // Add durability
                                      .build();                  // Build the enhanced version

        System.out.println("Configured Weapon: " + superFireSword.getDescription());
        System.out.println("  - Attack Power: " + String.format("%.1f", superFireSword.getAttackPower()));
        System.out.println("  - Durability: " + String.format("%.1f", superFireSword.getDurability()));
        System.out.println("-------------------------------------");
    }
}