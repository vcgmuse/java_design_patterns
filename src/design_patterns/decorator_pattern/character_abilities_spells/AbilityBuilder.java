package design_patterns.decorator_pattern.character_abilities_spells;

import design_patterns.decorator_pattern.character_abilities_spells.interfaces.CharacterAbility;
import design_patterns.decorator_pattern.character_abilities_spells.models.AreaEffectAbility;
import design_patterns.decorator_pattern.character_abilities_spells.models.BasicAttack;
import design_patterns.decorator_pattern.character_abilities_spells.models.FireDamageAbility;
import design_patterns.decorator_pattern.character_abilities_spells.models.StunAbility;

public class AbilityBuilder {
 private CharacterAbility currentAbility;

 public AbilityBuilder() {
     this.currentAbility = new BasicAttack();
 }

 public AbilityBuilder(CharacterAbility initialAbility) {
     if (initialAbility == null) {
         throw new IllegalArgumentException("Initial ability cannot be null.");
     }
     this.currentAbility = initialAbility;
 }

 public AbilityBuilder withFireDamage() {
     this.currentAbility = new FireDamageAbility(this.currentAbility);
     return this;
 }

 public AbilityBuilder withStun() {
     this.currentAbility = new StunAbility(this.currentAbility);
     return this;
 }

 public AbilityBuilder withAreaEffect() {
     this.currentAbility = new AreaEffectAbility(this.currentAbility);
     return this;
 }

 public CharacterAbility build() {
     return this.currentAbility;
 }
}