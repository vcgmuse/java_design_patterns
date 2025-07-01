package design_patterns.command_pattern.game_boy_layered.commands.console_input;

import design_patterns.command_pattern.game_boy_layered.interfaces.Command;
import design_patterns.command_pattern.game_boy_layered.processors.GameInputProcessor;

public class ConsoleSwapCommand implements Command {

    private GameInputProcessor inputProcessor;

    public ConsoleSwapCommand(GameInputProcessor inputProcessor) {
        this.inputProcessor = inputProcessor;
    }

    @Override
    public void execute() {
        System.out.println("ConsoleInput: 'Swap' button pressed.");
        inputProcessor.processSwapCharacter(); // Delegate the swap logic to the processor
    }
}