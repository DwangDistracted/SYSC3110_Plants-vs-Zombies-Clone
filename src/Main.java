import java.util.Scanner;

import levels.LevelLoader;
import util.Logger;

/**
 * This class acts as the program entry point; loading the UI and Game Levels. AKA - The Main Menu
 */
public class Main {
	private static Logger LOG = new Logger("Main");
	
	public static void main (String[] args) {
		//Log init
		Logger.setDebug();
		
		//Title Card
		LOG.info("================================");
		LOG.info("        Zombies are Vegan       ");
		LOG.info("================================");
		LOG.info("      Derek Shao | David Wang   ");
		LOG.info(" Michael Pastula | Tanisha Garg ");
		LOG.info("================================");
		
		//Load Data
		LevelLoader.init();
		LOG.debug("Data Loading Complete");
		
		while(true) {
			try {
				Scanner sc = new Scanner(System.in);
				LOG.prompt("Press 1 to Load Sample Level. Press 2 to Exit.");
				String s = sc.nextLine();
				sc.close();
				
				if (s.equals("1")) {
					LOG.info("Loading Sample Level...");
				} else if (s.equals("2")){
					break;
				} else {
					LOG.error("Invalid Input");
				}
			} catch (Exception e) {
				LOG.error(e.getMessage());
				break;
			}
		}
		
		LOG.info("Program Exit");
	}
}
