package design_patterns.command_pattern.game_boy.models;

import design_patterns.command_pattern.game_boy.interfaces.Command;

public class MarioDownCommand implements Command {
    private MarioCharacterReceiver marioCharacterReceiver;

    public MarioDownCommand(MarioCharacterReceiver marioCharacterReceiver) {
        this.marioCharacterReceiver = marioCharacterReceiver;
    }

    @Override
    public void execute() {
        marioCharacterReceiver.moveDown();

    }
}
