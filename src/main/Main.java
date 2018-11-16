package main;

import assets.PlantTypes;
import assets.ZombieTypes;
import levels.LevelLoader;
import ui.Images;
import ui.MainMenu;
import util.Logger;

/**
 * This class acts as the program entry point; loading the UI and Game Levels. AKA - The Main Menu
 */
public class Main {
	private static Logger LOG = new Logger("Main");
	
	/**
	 * Program Entry Point
	 * @param args
	 * @author David Wang
	 */
	public static void main (String[] args) {
		//Log init
		Logger.clearDebug(); //change to clearDebug to get rid of debug messages
		LevelLoader.init();
		if(!Images.preloadImages()) {
			LOG.error("Failed to Load Graphics Images");
		}
		LOG.debug("Data Loading Complete");
		
		new MainMenu();
	}
}