package design_patterns.command_pattern.game_boy.models;

import design_patterns.command_pattern.game_boy.interfaces.Command;

public class MarioRightCommand implements Command {

    private MarioCharacterReceiver marioCharacterReceiver;

    public MarioRightCommand(MarioCharacterReceiver marioCharacterReceiver) {
        this.marioCharacterReceiver = marioCharacterReceiver;
    }

    @Override
    public void execute() {
        marioCharacterReceiver.moveRight();

    }
}
