package design_patterns.command_pattern.game_boy_layered.commands.character_actions.luigi;

import design_patterns.command_pattern.game_boy_layered.interfaces.Command;
import design_patterns.command_pattern.game_boy_layered.receivers.LuigiCharacterReceiver;

public class LuigiDownCommand implements Command {

    private LuigiCharacterReceiver luigiCharacterReceiver;

    public LuigiDownCommand(LuigiCharacterReceiver luigiCharacterReceiver) {
        this.luigiCharacterReceiver = luigiCharacterReceiver;
    }

    @Override
    public void execute() {
        luigiCharacterReceiver.moveUp();
    }
}