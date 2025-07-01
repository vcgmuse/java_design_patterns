package design_patterns.command_pattern.game_boy_layered.commands.console_input;

import design_patterns.command_pattern.game_boy_layered.interfaces.Command;
import design_patterns.command_pattern.game_boy_layered.processors.GameInputProcessor;

public class ConsoleDownCommand implements Command {

    private GameInputProcessor gameInputProcessor;

    public ConsoleDownCommand(GameInputProcessor gameInputProcessor) {
        this.gameInputProcessor = gameInputProcessor;
    }

    @Override
    public void execute() {
        System.out.println("Console Command: 'Up' button pressed, delegating to processor...");
        gameInputProcessor.processMoveUp();
    }
}