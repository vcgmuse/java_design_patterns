package design_patterns.command_pattern.game_boy_layered.receivers;

import design_patterns.command_pattern.game_boy_layered.commands.character_actions.luigi.LuigiDownCommand;
import design_patterns.command_pattern.game_boy_layered.commands.character_actions.luigi.LuigiLeftCommand;
import design_patterns.command_pattern.game_boy_layered.commands.character_actions.luigi.LuigiRightCommand;
import design_patterns.command_pattern.game_boy_layered.commands.character_actions.luigi.LuigiUpCommand;
import design_patterns.command_pattern.game_boy_layered.interfaces.Command;
import design_patterns.command_pattern.game_boy_layered.interfaces.IGameCharacter;


public class LuigiCharacterReceiver implements IGameCharacter {

    private String name;

    // Commands specific to Luigi, instantiated here
    private Command upCommand;
    private Command downCommand;
    private Command leftCommand;
    private Command rightCommand;

    public LuigiCharacterReceiver(String name) {
        this.name = name;
        // Instantiate Luigi's specific commands, passing 'this' (Luigi himself) as the receiver
        this.upCommand = new LuigiUpCommand(this);
        this.downCommand = new LuigiDownCommand(this);
        this.leftCommand = new LuigiLeftCommand(this);
        this.rightCommand = new LuigiRightCommand(this);
    }

    @Override
    public String getName() {
        return name;
    }

    // Concrete methods for Luigi's actions (these are called by LuigiUpCommand, etc.)
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