package design_patterns.command_pattern.game_boy.models;

import design_patterns.command_pattern.game_boy.interfaces.Command;

public class KirbyDownCommand implements Command {

    private KirbyCharacterReceiver kirbyCharacterReceiver;

    public KirbyDownCommand(KirbyCharacterReceiver kirbyCharacterReceiver) {
        this.kirbyCharacterReceiver = kirbyCharacterReceiver;
    }

    @Override
    public void execute() {
        kirbyCharacterReceiver.moveDown();

    }
}
