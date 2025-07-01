package design_patterns.decorator_pattern.character_abilities_spells.models;

import design_patterns.decorator_pattern.character_abilities_spells.abstracts.AbilityDecorator;
import design_patterns.decorator_pattern.character_abilities_spells.interfaces.CharacterAbility;

public class StunAbility extends AbilityDecorator {
 private static final int STUN_MANA_COST = 10;
 private static final String STUN_EFFECT = ", potentially stunning the target";

 public StunAbility(CharacterAbility decoratedAbility) {
     super(decoratedAbility);
     System.out.println("Adding Stun effect to ability.");
 }

 @Override
 public String cast() {
     return super.cast() + STUN_EFFECT;
 }

 @Override
 public int getManaCost() {
     return super.getManaCost() + STUN_MANA_COST;
 }

 @Override
 public String getDescription() {
     return super.getDescription() + " (Stun)";
 }
}