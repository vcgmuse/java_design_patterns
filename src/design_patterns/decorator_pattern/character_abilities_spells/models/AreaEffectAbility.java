package design_patterns.decorator_pattern.character_abilities_spells.models;

import design_patterns.decorator_pattern.character_abilities_spells.abstracts.AbilityDecorator;
import design_patterns.decorator_pattern.character_abilities_spells.interfaces.CharacterAbility;

public class AreaEffectAbility extends AbilityDecorator {
 private static final int AOE_MANA_COST = 15;
 private static final String AOE_EFFECT = " affecting an area";

 public AreaEffectAbility(CharacterAbility decoratedAbility) {
     super(decoratedAbility);
     System.out.println("Adding Area Effect to ability.");
 }

 @Override
 public String cast() {
     return super.cast() + AOE_EFFECT;
 }

 @Override
 public int getManaCost() {
     return super.getManaCost() + AOE_MANA_COST;
 }

 @Override
 public String getDescription() {
     return super.getDescription() + " (AoE)";
 }
}