package main;

import java.util.Scanner;
import engine.Game;
import input.Command;
import input.CommandWords;
import input.Parser;
import levels.LevelInfo;
import levels.LevelLoader;
import util.Logger;
/**
 * This class acts as the program entry point; loading the UI and Game Levels. AKA - The Main Menu
 */
public class Main {
	private static Logger LOG = new Logger("Main");
	public static Scanner sc = new Scanner(System.in);
	private static boolean quit = false;
	
	/**
	 * Program Entry Point
	 * @param args
	 */
	public static void main (String[] args) {
		//Log init
		Logger.setDebug(); //change to clearDebug to get rid of debug messages
		
		//Title Card
		LOG.info("==================================");
		LOG.info("        Zombies are Vegan         ");
		LOG.info("==================================");
		LOG.info("      Derek Shao | David Wang     ");
		LOG.info(" Michael Pastula | Tanisha Garg   ");
		LOG.info("==================================");
		
		//Load Level Data
		LevelLoader.init();
		LOG.debug("Data Loading Complete");
		
		while(!quit) {
			LOG.prompt(CommandWords.getPrimaryMenuCommands());
			if (!menuProcessing(Parser.getCommand())) {
				LOG.warn("Invalid Command");
			};
		}
		
		LOG.debug("Program Exit");
		sc.close();
	}
	
	//INPUT - Milestone 1 Only
	/**
	 * Processes the menu commands (load and quit)
	 * @param command
	 * @return True if the command was valid, false otherwise
	 */
	public static boolean menuProcessing(Command command)
	{
		if (command == null) {
			LOG.debug("Command is null");
			return false;
		}
		if(CommandWords.isPrimaryCommand(command.getWord(1)))
        {
	        String commandWord = command.getWord(1);
	        LOG.debug("CommandWord is " + commandWord);
	        if (commandWord.equalsIgnoreCase(CommandWords.PLAY.toString())) {
				LOG.info("Loading Level 1...");
				
				LevelInfo lvl = LevelLoader.getLevel(1);
				if (lvl != null) {
					new Game(lvl).start();
					return true;
				} else {
					LOG.warn("Level 1 does not exist!");
					return false;
				}
	        }
	        else if (commandWord.equalsIgnoreCase(CommandWords.LOAD.toString()) && command.getWord(2) != null)
	        {
				LOG.info("Loading Level " + command.getWord(2) + "...");
				LevelInfo lvl = LevelLoader.getLevel(Integer.valueOf(command.getWord(2))); //where commandSecondWord is the level selector
				if (lvl != null) {
					new Game(lvl).start();
	    			return true;
				} else {
					LOG.warn("Level " + command.getWord(2) + " does not exist!");
					return false;
				}
	        }
	        else if (commandWord.equalsIgnoreCase(CommandWords.QUIT.toString())) 
	        {
	        	quit = true;
				LOG.debug("User is Quitting");
				return true;
	        }
        }
		return false;
	}
}