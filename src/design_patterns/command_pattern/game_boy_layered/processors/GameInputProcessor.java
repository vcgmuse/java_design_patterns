package design_patterns.command_pattern.game_boy_layered.processors;

import design_patterns.command_pattern.game_boy_layered.interfaces.Command;
import design_patterns.command_pattern.game_boy_layered.interfaces.IGameCharacter;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList; // Added for cycling through characters

public class GameInputProcessor {

    private final Map<String, IGameCharacter> availableCharacters;
    private IGameCharacter activeCharacter;

    public GameInputProcessor(List<IGameCharacter> characters) {
        this.availableCharacters = new HashMap<>();
        for (IGameCharacter character : characters) {
            this.availableCharacters.put(character.getName().toLowerCase(), character);
        }

        if (!characters.isEmpty()) {
            this.activeCharacter = characters.get(0);
        } else {
            this.activeCharacter = null;
        }

        String activeCharacterName = (activeCharacter != null) ? activeCharacter.getName() : "None (No characters initialized)";
        System.out.println("GameInputProcessor: Initialized. Active character: " + activeCharacterName);
    }

    public IGameCharacter getActiveCharacter() {
        return activeCharacter;
    }

    // Public method to explicitly set active character by name (used by Main for initial setup)
    public void setActiveCharacterByName(String characterName) {
        IGameCharacter newActive = availableCharacters.get(characterName.toLowerCase());
        if (newActive != null) {
            this.activeCharacter = newActive;
            System.out.println("GameInputProcessor: Active character changed to " + newActive.getName());
        } else {
            System.out.println("GameInputProcessor: Character '" + characterName + "' not found. Active character remains " + ((activeCharacter != null) ? activeCharacter.getName() : "None"));
        }
    }

    // *** NEW METHOD FOR SWAPPING CHARACTERS ***
    public void processSwapCharacter() {
        System.out.println("GameInputProcessor: Processing 'Swap Character' request...");

        if (availableCharacters.isEmpty()) {
            System.out.println("GameInputProcessor: No characters available to swap.");
            return;
        }

        // Convert map values to a list to get an ordered sequence for cycling
        // This is necessary because HashMap iteration order is not guaranteed.
        // If order matters, consider using LinkedHashMap for availableCharacters.
        List<IGameCharacter> characterList = new ArrayList<>(availableCharacters.values());

        if (characterList.isEmpty()) { // Should be caught by the above check but good to be defensive
            System.out.println("GameInputProcessor: No characters in the list to swap.");
            return;
        }

        int currentIndex = -1;
        if (activeCharacter != null) {
            // Find the index of the current active character in our ordered list
            for (int i = 0; i < characterList.size(); i++) {
                if (characterList.get(i).equals(activeCharacter)) { // Use .equals() for object comparison
                    currentIndex = i;
                    break;
                }
            }
        }

        // If no active character (or not found, should ideally not happen), start with the first one
        if (currentIndex == -1) {
            System.out.println("GameInputProcessor: No current active character found, defaulting to first.");
            setActiveCharacterByName(characterList.get(0).getName());
        } else {
            // Calculate the next index, cycling back to 0 if at the end
            int nextIndex = (currentIndex + 1) % characterList.size();
            IGameCharacter nextCharacter = characterList.get(nextIndex);
            setActiveCharacterByName(nextCharacter.getName());
        }
    }

    // Existing move methods (unchanged)
    public void processMoveUp() {
        System.out.println("GameInputProcessor: Processing general 'Move Up' request...");
        if (activeCharacter != null) {
            activeCharacter.getUpCommand().execute();
        } else {
            System.out.println("GameInputProcessor: No active character to move.");
        }
    }

    public void processMoveDown() {
        System.out.println("GameInputProcessor: Processing general 'Move Down' request...");
        if (activeCharacter != null) {
            activeCharacter.getDownCommand().execute();
        } else {
            System.out.println("GameInputProcessor: No active character to move.");
        }
    }

    public void processMoveLeft() {
        System.out.println("GameInputProcessor: Processing general 'Move Left' request...");
        if (activeCharacter != null) {
            activeCharacter.getLeftCommand().execute();
        } else {
            System.out.println("GameInputProcessor: No active character to move.");
        }
    }

    public void processMoveRight() {
        System.out.println("GameInputProcessor: Processing general 'Move Right' request...");
        if (activeCharacter != null) {
            activeCharacter.getRightCommand().execute();
        } else {
            System.out.println("GameInputProcessor: No active character to move.");
        }
    }
}