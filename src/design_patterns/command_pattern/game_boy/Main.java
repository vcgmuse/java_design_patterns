package design_patterns.command_pattern.game_boy;

import design_patterns.command_pattern.game_boy.models.GameBoy;
import design_patterns.command_pattern.game_boy.models.KirbyCharacterReceiver;
import design_patterns.command_pattern.game_boy.models.KirbyDownCommand;
import design_patterns.command_pattern.game_boy.models.KirbyLeftCommand;
import design_patterns.command_pattern.game_boy.models.KirbyRightCommand;
import design_patterns.command_pattern.game_boy.models.KirbyUpCommand;
import design_patterns.command_pattern.game_boy.models.MarioCharacterReceiver;
import design_patterns.command_pattern.game_boy.models.MarioDownCommand;
import design_patterns.command_pattern.game_boy.models.MarioLeftCommand;
import design_patterns.command_pattern.game_boy.models.MarioRightCommand;
import design_patterns.command_pattern.game_boy.models.MarioUpCommand;

public class Main {

    public static void main(String[] args) {

        // Create our receivers
        MarioCharacterReceiver mario = new MarioCharacterReceiver();
        mario.setName("Mario");

        KirbyCharacterReceiver kirby = new KirbyCharacterReceiver();
        kirby.setName("Kirby");


        //Create commands
        MarioUpCommand marioUpCommand = new MarioUpCommand(mario);
        MarioDownCommand marioDownCommand = new MarioDownCommand(mario);
        MarioLeftCommand marioLeftCommand = new MarioLeftCommand(mario);
        MarioRightCommand marioRightCommand = new MarioRightCommand(mario);

        KirbyUpCommand kirbyUpCommand = new KirbyUpCommand(kirby);
        KirbyDownCommand kirbyDownCommand = new KirbyDownCommand(kirby);
        KirbyLeftCommand kirbyLeftCommand = new KirbyLeftCommand(kirby);
        KirbyRightCommand kirbyRightCommand = new KirbyRightCommand(kirby);




        //Invoker
        GameBoy gameBoy = new GameBoy(marioUpCommand, marioDownCommand, marioLeftCommand, marioRightCommand);
        gameBoy.arrowDown();


        GameBoy gameBoy2 = new GameBoy(kirbyUpCommand, kirbyDownCommand, kirbyLeftCommand, kirbyRightCommand);
        gameBoy2.arrowDown();
        gameBoy2.arrowUp();
        gameBoy2.arrowLeft();
        gameBoy2.arrowRight();



    }
}
