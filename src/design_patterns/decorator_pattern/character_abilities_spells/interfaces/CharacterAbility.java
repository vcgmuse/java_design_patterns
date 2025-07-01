package design_patterns.decorator_pattern.character_abilities_spells.interfaces;
public interface CharacterAbility {
    /**
     * Executes the ability, simulating its effect (e.g., dealing damage, applying a buff).
     * @return A string describing the immediate outcome of casting the ability.
     */
    String cast();

    /**
     * Returns the mana cost (or other resource cost) associated with using this ability.
     * @return The mana cost as an integer.
     */
    int getManaCost();

    /**
     * Returns a full description of the ability, including all its effects.
     * @return A string description.
     */
    String getDescription();
}