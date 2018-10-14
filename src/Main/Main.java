package Main;

import java.util.Scanner;

import engine.Game;
import levels.LevelInfo;
import levels.LevelLoader;
import util.Logger;

/**
 * This class acts as the program entry point; loading the UI and Game Levels. AKA - The Main Menu
 */
public class Main {
	private static Logger LOG = new Logger("Main");
	
	public static Scanner sc = new Scanner(System.in);
	
	public static void main (String[] args) {
		//Log init
		Logger.setDebug();
		
		//Title Card
		LOG.info("==================================");
		LOG.info("        Zombies are Vegan         ");
		LOG.info("==================================");
		LOG.info("      Derek Shao | David Wang     ");
		LOG.info(" Michael Pastula | Tanisha Garg   ");
		LOG.info("==================================");
		
		//Load Data
		LevelLoader.init();
		LOG.debug("Data Loading Complete");
		
		while(true) {
			LOG.prompt("Press 1 to Load Sample Level. Press 2 to Exit.");
			String s = sc.nextLine();
			
			if (s.equals("1")) {
				LOG.info("Loading Sample Level...");
				
				LevelInfo lvl = LevelLoader.getNextLevel();
				if (lvl != null) {
					new Game(lvl).start();
				} else {
					LOG.info("No More Levels");
					LOG.prompt("Do You want to restart from the first level? (Y/N)");
					
					String yn = sc.nextLine();
					
					if (yn.equalsIgnoreCase("Y")) {
						LevelLoader.reset();
					} else {
						break;
					}
				}
				
			} else if (s.equals("2")){
				break;
			} else {
				LOG.error("Invalid Input");
			}
		}
		
		LOG.debug("Program Exit");
		sc.close();
	}
}
