package design_patterns.command_pattern.game_boy_layered.commands.character_actions.kirby;

import design_patterns.command_pattern.game_boy_layered.interfaces.Command;
import design_patterns.command_pattern.game_boy_layered.receivers.KirbyCharacterReceiver;

public class KirbyUpCommand implements Command {

    private KirbyCharacterReceiver kirbyCharacterReceiver;

    public KirbyUpCommand(KirbyCharacterReceiver kirbyCharacterReceiver) {
        this.kirbyCharacterReceiver = kirbyCharacterReceiver;
    }

    @Override
    public void execute() {
        kirbyCharacterReceiver.moveUp();
    }
}