package design_patterns.decorator_pattern.character_abilities_spells.abstracts;

import design_patterns.decorator_pattern.character_abilities_spells.interfaces.CharacterAbility;

public abstract class AbilityDecorator implements CharacterAbility {
 protected CharacterAbility decoratedAbility;

 public AbilityDecorator(CharacterAbility decoratedAbility) {
     if (decoratedAbility == null) {
         throw new IllegalArgumentException("Ability to decorate cannot be null.");
     }
     this.decoratedAbility = decoratedAbility;
 }

 @Override
 public String cast() {
     // Default behavior: delegate to the wrapped ability
     return this.decoratedAbility.cast();
 }

 @Override
 public int getManaCost() {
     // Default behavior: delegate to the wrapped ability's mana cost
     return this.decoratedAbility.getManaCost();
 }

 @Override
 public String getDescription() {
     // Default behavior: delegate to the wrapped ability's description
     return this.decoratedAbility.getDescription();
 }
}