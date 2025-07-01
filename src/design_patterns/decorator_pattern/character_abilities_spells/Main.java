package design_patterns.decorator_pattern.character_abilities_spells;

import design_patterns.decorator_pattern.character_abilities_spells.interfaces.CharacterAbility;
import design_patterns.decorator_pattern.character_abilities_spells.models.AreaEffectAbility;
import design_patterns.decorator_pattern.character_abilities_spells.models.BasicAttack;
import design_patterns.decorator_pattern.character_abilities_spells.models.FireDamageAbility;
import design_patterns.decorator_pattern.character_abilities_spells.models.StunAbility;

public class Main {
 public static void main(String[] args) {
     System.out.println("--- Decorator Pattern: Character Abilities/Spells Example ---");

     // --- Scenario 1: Basic Attack ---
     System.out.println("\n===== Scenario 1: Basic Attack =====");
     CharacterAbility basicAttack = new BasicAttack();
     System.out.println("Ability: " + basicAttack.getDescription());
     System.out.println("  - Mana Cost: " + basicAttack.getManaCost());
     System.out.println("  - Cast Result: " + basicAttack.cast());
     System.out.println("-------------------------------------");

     // --- Scenario 2: Fireball (Basic Attack + Fire Damage) ---
     System.out.println("\n===== Scenario 2: Fireball =====");
     CharacterAbility fireball = new FireDamageAbility(new BasicAttack());
     System.out.println("Ability: " + fireball.getDescription());
     System.out.println("  - Mana Cost: " + fireball.getManaCost());
     System.out.println("  - Cast Result: " + fireball.cast());
     System.out.println("-------------------------------------");

     // --- Scenario 3: Stun Blast (Basic Attack + Stun Effect) ---
     System.out.println("\n===== Scenario 3: Stun Blast =====");
     CharacterAbility stunBlast = new StunAbility(new BasicAttack());
     System.out.println("Ability: " + stunBlast.getDescription());
     System.out.println("  - Mana Cost: " + stunBlast.getManaCost());
     System.out.println("  - Cast Result: " + stunBlast.cast());
     System.out.println("-------------------------------------");

     // --- Scenario 4: AoE Fire Nova (Basic Attack + Fire + AoE) ---
     System.out.println("\n===== Scenario 4: AoE Fire Nova =====");
     CharacterAbility fireNova = new AreaEffectAbility(new FireDamageAbility(new BasicAttack()));
     System.out.println("Ability: " + fireNova.getDescription());
     System.out.println("  - Mana Cost: " + fireNova.getManaCost());
     System.out.println("  - Cast Result: " + fireNova.cast());
     System.out.println("-------------------------------------");

     // --- Scenario 5: Ultimate Spell (using Builder) ---
     System.out.println("\n===== Scenario 5: Ultimate Spell (using Builder) =====");
     CharacterAbility ultimateSpell = new AbilityBuilder() // Starts with BasicAttack
                                         .withFireDamage()    // Add fire
                                         .withStun()          // Add stun
                                         .withAreaEffect()    // Make it AoE
                                         .build();            // Get the final spell

     System.out.println("Ability: " + ultimateSpell.getDescription());
     System.out.println("  - Mana Cost: " + ultimateSpell.getManaCost());
     System.out.println("  - Cast Result: " + ultimateSpell.cast());
     System.out.println("-------------------------------------");

     // --- Scenario 6: Custom Spell Combination (using Builder) ---
     System.out.println("\n===== Scenario 6: Custom Spell Combination =====");
     CharacterAbility customSpell = new AbilityBuilder()
                                        .withAreaEffect()     // AoE first
                                        .withStun()           // then stun
                                        .build();             // Build it

     System.out.println("Ability: " + customSpell.getDescription());
     System.out.println("  - Mana Cost: " + customSpell.getManaCost());
     System.out.println("  - Cast Result: " + customSpell.cast());
     System.out.println("-------------------------------------");
 }
}