package design_patterns.command_pattern.game_boy_layered;

import design_patterns.command_pattern.game_boy_layered.invokers.GameBoy;
import design_patterns.command_pattern.game_boy_layered.processors.GameInputProcessor;
import design_patterns.command_pattern.game_boy_layered.receivers.KirbyCharacterReceiver;
import design_patterns.command_pattern.game_boy_layered.receivers.LuigiCharacterReceiver;
import design_patterns.command_pattern.game_boy_layered.receivers.MarioCharacterReceiver;
import design_patterns.command_pattern.game_boy_layered.commands.console_input.*; // Includes ConsoleSwapCommand now
import design_patterns.command_pattern.game_boy_layered.interfaces.IGameCharacter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainInputCommand {

    public static void main(String[] args) {
        System.out.println("--- Demonstrating Layered Command Pattern with Dynamic User Input ---");

        // 1. Create our Character Receivers
        MarioCharacterReceiver mario = new MarioCharacterReceiver("Mario");
        KirbyCharacterReceiver kirby = new KirbyCharacterReceiver("Kirby");
        LuigiCharacterReceiver luigi = new LuigiCharacterReceiver("Luigi");
        // Add more characters here to see the cycling in action!
        // LuigiCharacterReceiver luigi = new LuigiCharacterReceiver("Luigi");


        // 2. Prepare a list of all available characters
        List<IGameCharacter> allCharacters = new ArrayList<>();
        allCharacters.add(mario);
        allCharacters.add(kirby);
        allCharacters.add(luigi);
        // Add new characters to this list:
        // allCharacters.add(luigi);


        // 3. Create the Game Input Processor
        GameInputProcessor inputProcessor = new GameInputProcessor(allCharacters);

        // 4. Create Console Commands (including the new ConsoleSwapCommand)
        ConsoleUpCommand consoleUp = new ConsoleUpCommand(inputProcessor);
        ConsoleDownCommand consoleDown = new ConsoleDownCommand(inputProcessor);
        ConsoleLeftCommand consoleLeft = new ConsoleLeftCommand(inputProcessor);
        ConsoleRightCommand consoleRight = new ConsoleRightCommand(inputProcessor);
        ConsoleSwapCommand consoleSwap = new ConsoleSwapCommand(inputProcessor); // NEW: Swap Command

        // 5. Create the GameBoy Invoker (now accepts the swap command)
        GameBoy gameBoy = new GameBoy(consoleUp, consoleDown, consoleLeft, consoleRight, consoleSwap); // NEW: Pass swapCommand

        // 6. Setup for user input
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("\nGame Started! Enter commands:");
        System.out.println("  'up', 'down', 'left', 'right' to move current character");
        System.out.println("  'swap' to cycle through available characters");
        System.out.println("  'quit' to exit the game");

        while (true) {
            System.out.print("\nEnter command: ");
            input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "up":
                    gameBoy.pressUpButton();
                    break;
                case "down":
                    gameBoy.pressDownButton();
                    break;
                case "left":
                    gameBoy.pressLeftButton();
                    break;
                case "right":
                    gameBoy.pressRightButton();
                    break;
                case "swap":
                    gameBoy.pressSwapButton(); // NEW: Simply press the swap button
                    break;
                case "quit":
                    System.out.println("Exiting game. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Unknown command. Please try again.");
                    break;
            }
        }
    }
}