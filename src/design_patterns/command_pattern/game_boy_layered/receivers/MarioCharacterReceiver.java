package design_patterns.command_pattern.game_boy_layered.receivers;

import design_patterns.command_pattern.game_boy_layered.interfaces.Command;
import design_patterns.command_pattern.game_boy_layered.interfaces.IGameCharacter;
import design_patterns.command_pattern.game_boy_layered.commands.character_actions.*; // Import all character action commands
import design_patterns.command_pattern.game_boy_layered.commands.character_actions.mario.MarioDownCommand;
import design_patterns.command_pattern.game_boy_layered.commands.character_actions.mario.MarioLeftCommand;
import design_patterns.command_pattern.game_boy_layered.commands.character_actions.mario.MarioRightCommand;
import design_patterns.command_pattern.game_boy_layered.commands.character_actions.mario.MarioUpCommand;

public class MarioCharacterReceiver implements IGameCharacter {

    private String name;

    // Commands specific to Mario, instantiated here
    private Command upCommand;
    private Command downCommand;
    private Command leftCommand;
    private Command rightCommand;

    public MarioCharacterReceiver(String name) {
        this.name = name;
        // Instantiate Mario's specific commands, passing 'this' (Mario himself) as the receiver
        this.upCommand = new MarioUpCommand(this);
        this.downCommand = new MarioDownCommand(this);
        this.leftCommand = new MarioLeftCommand(this);
        this.rightCommand = new MarioRightCommand(this);
    }

    @Override
    public String getName() {
        return name;
    }

    // Concrete methods for Mario's actions (these are called by MarioUpCommand, etc.)
    public void moveUp() {
        System.out.println(getName() + " jumping high!");
    }

    public void moveLeft() {
        System.out.println(getName() + " running Left!");
    }

    public void moveRight() {
        System.out.println(getName() + " running Right!");
    }

    public void moveDown() {
        System.out.println(getName() + " crouching down!");
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