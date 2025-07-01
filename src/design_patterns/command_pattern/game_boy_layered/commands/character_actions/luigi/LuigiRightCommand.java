package design_patterns.command_pattern.game_boy_layered.commands.character_actions.luigi;

import design_patterns.command_pattern.game_boy_layered.interfaces.Command;
import design_patterns.command_pattern.game_boy_layered.receivers.LuigiCharacterReceiver;

public class LuigiRightCommand implements Command {

    private LuigiCharacterReceiver marioCharacterReceiver;

    public LuigiRightCommand(LuigiCharacterReceiver marioCharacterReceiver) {
        this.marioCharacterReceiver = marioCharacterReceiver;
    }

    @Override
    public void execute() {
        marioCharacterReceiver.moveUp();
    }
}