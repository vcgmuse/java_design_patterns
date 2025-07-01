package design_patterns.command_pattern.game_boy_layered.commands.character_actions.mario;

import design_patterns.command_pattern.game_boy_layered.interfaces.Command;
import design_patterns.command_pattern.game_boy_layered.receivers.MarioCharacterReceiver;

public class MarioRightCommand implements Command {

    private MarioCharacterReceiver marioCharacterReceiver;

    public MarioRightCommand(MarioCharacterReceiver marioCharacterReceiver) {
        this.marioCharacterReceiver = marioCharacterReceiver;
    }

    @Override
    public void execute() {
        marioCharacterReceiver.moveUp();
    }
}