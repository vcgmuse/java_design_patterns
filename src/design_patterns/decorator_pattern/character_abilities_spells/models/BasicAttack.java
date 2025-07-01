package design_patterns.decorator_pattern.character_abilities_spells.models;

import design_patterns.decorator_pattern.character_abilities_spells.interfaces.CharacterAbility;

public class BasicAttack implements CharacterAbility {
 private static final int BASE_MANA_COST = 0; // Basic attacks usually cost no mana
 private String description = "A simple physical attack.";

 public BasicAttack() {
     System.out.println("Defining Basic Attack.");
 }

 @Override
 public String cast() {
     return "Character performs a basic physical attack.";
 }

 @Override
 public int getManaCost() {
     return BASE_MANA_COST;
 }

 @Override
 public String getDescription() {
     return description;
 }
}