package design_patterns.command_pattern.game_boy_layered.invokers;

import design_patterns.command_pattern.game_boy_layered.interfaces.Command;

public class GameBoy {

    private Command upCommand;
    private Command downCommand;
    private Command leftCommand;
    private Command rightCommand;
    private Command swapCommand; // New command slot

    public GameBoy(Command upCommand, Command downCommand, Command leftCommand, Command rightCommand, Command swapCommand) {
        this.upCommand = upCommand;
        this.downCommand = downCommand;
        this.leftCommand = leftCommand;
        this.rightCommand = rightCommand;
        this.swapCommand = swapCommand; // Initialize new command
    }

    public void pressUpButton() {
        System.out.println("GameBoy: 'Up' button pressed.");
        upCommand.execute();
    }

    public void pressDownButton() {
        System.out.println("GameBoy: 'Down' button pressed.");
        downCommand.execute();
    }

    public void pressLeftButton() {
        System.out.println("GameBoy: 'Left' button pressed.");
        leftCommand.execute();
    }

    public void pressRightButton() {
        System.out.println("GameBoy: 'Right' button pressed.");
        rightCommand.execute();
    }

    // *** NEW METHOD FOR SWAP BUTTON ***
    public void pressSwapButton() {
        System.out.println("GameBoy: 'Swap' button pressed.");
        swapCommand.execute();
    }
}