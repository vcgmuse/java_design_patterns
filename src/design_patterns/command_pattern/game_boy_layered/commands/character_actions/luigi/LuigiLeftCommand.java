package design_patterns.command_pattern.game_boy_layered.commands.character_actions.luigi;

import design_patterns.command_pattern.game_boy_layered.interfaces.Command;
import design_patterns.command_pattern.game_boy_layered.receivers.LuigiCharacterReceiver;

public class LuigiLeftCommand implements Command {

    private LuigiCharacterReceiver marioCharacterReceiver;

    public LuigiLeftCommand(LuigiCharacterReceiver marioCharacterReceiver) {
        this.marioCharacterReceiver = marioCharacterReceiver;
    }

    @Override
    public void execute() {
        marioCharacterReceiver.moveUp();
    }
}