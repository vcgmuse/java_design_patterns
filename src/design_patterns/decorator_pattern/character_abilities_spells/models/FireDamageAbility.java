package design_patterns.decorator_pattern.character_abilities_spells.models;

import design_patterns.decorator_pattern.character_abilities_spells.abstracts.AbilityDecorator;
import design_patterns.decorator_pattern.character_abilities_spells.interfaces.CharacterAbility;

public class FireDamageAbility extends AbilityDecorator {
 private static final int FIRE_MANA_COST = 5;
 private static final String FIRE_EFFECT = " and deals additional fire damage";

 public FireDamageAbility(CharacterAbility decoratedAbility) {
     super(decoratedAbility);
     System.out.println("Adding Fire Damage to ability.");
 }

 @Override
 public String cast() {
     return super.cast() + FIRE_EFFECT;
 }

 @Override
 public int getManaCost() {
     return super.getManaCost() + FIRE_MANA_COST;
 }

 @Override
 public String getDescription() {
     return super.getDescription() + " (Fire)";
 }
}