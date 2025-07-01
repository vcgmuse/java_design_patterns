package design_patterns.command_pattern.game_boy_layered.receivers;

import design_patterns.command_pattern.game_boy_layered.interfaces.Command;
import design_patterns.command_pattern.game_boy_layered.interfaces.IGameCharacter;
import design_patterns.command_pattern.game_boy_layered.commands.character_actions.*; // Import all character action commands
import design_patterns.command_pattern.game_boy_layered.commands.character_actions.kirby.KirbyDownCommand;
import design_patterns.command_pattern.game_boy_layered.commands.character_actions.kirby.KirbyLeftCommand;
import design_patterns.command_pattern.game_boy_layered.commands.character_actions.kirby.KirbyRightCommand;
import design_patterns.command_pattern.game_boy_layered.commands.character_actions.kirby.KirbyUpCommand;

public class KirbyCharacterReceiver implements IGameCharacter {

    private String name;

    // Commands specific to Kirby, instantiated here
    private Command upCommand;
    private Command downCommand;
    private Command leftCommand;
    private Command rightCommand;

    public KirbyCharacterReceiver(String name) {
        this.name = name;
        // Instantiate Kirby's specific commands, passing 'this' (Kirby himself) as the receiver
        this.upCommand = new KirbyUpCommand(this);
        this.downCommand = new KirbyDownCommand(this);
        this.leftCommand = new KirbyLeftCommand(this);
        this.rightCommand = new KirbyRightCommand(this);
    }

    @Override
    public String getName() {
        return name;
    }

    // Concrete methods for Kirby's actions (these are called by KirbyUpCommand, etc.)
    public void moveUp() {
        System.out.println(getName() + " jumping up!");
    }

    public void moveLeft() {
        System.out.println(getName() + " moving Left!");
    }

    public void moveRight() {
        System.out.println(getName() + " moving Right!");
    }

    public void moveDown() {
        System.out.println(getName() + " moving Down!");
    }

    // Implement IGameCharacter methods to provide the commands
    @Override
    public Command getUpCommand() {
        return upCommand;
    }

    @Override
    public Command getDownCommand() {
        return downCommand;
    }

    @Override
    public Command getLeftCommand() {
        return leftCommand;
    }

    @Override
    public Command getRightCommand() {
        return rightCommand;
    }
}