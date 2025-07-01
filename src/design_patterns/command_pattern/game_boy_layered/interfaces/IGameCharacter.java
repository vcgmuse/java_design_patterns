package design_patterns.command_pattern.game_boy_layered.interfaces;

// This interface defines the common contract for all playable characters.
// It also provides access to the character's specific commands.
public interface IGameCharacter {
    String getName();

    // Methods to get the character's specific action commands
    Command getUpCommand();
    Command getDownCommand();
    Command getLeftCommand();
    Command getRightCommand();

    // Add any other common character behaviors here (e.g., moveUp(), if they were abstract)
    // For this example, moveUp() etc. remain in the concrete receivers,
    // as the commands will call them directly.
}