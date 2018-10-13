package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Main.Main;
import levels.Grid;
import levels.LevelInfo;
import util.Logger;

/**
 * The Primary Game Loop. Instance per level. Coordinates with Turn and Combat Engines
 * @author david
 *
 */
@SuppressWarnings("deprecation")
public class Game {
	private static Logger LOG = new Logger("Game");
	
	private LevelInfo levelInfo;
	private Grid[][] board;
	private List<Command> commands;

	private int userResources;
	
	public Game (LevelInfo lvl) {
		//set up config from level config
		board = new Grid[lvl.getGridX()][lvl.getGridY()];
		commands = new ArrayList<>();
		levelInfo = lvl;
	}
	
	public void spendUserResources(int cost) {
		userResources -= cost;
	}
	public int getUserResources() {
		return userResources;
	}
	
	//start the game loop
	public void start() {
		userResources = levelInfo.getInitResources();
		
		while (true) {
			if (!playerTurn()) { break; }
			if (!zombieTurn()) { break; }
		}
		endGame();
	}

	private boolean playerTurn() {
		userResources += levelInfo.getResPerTurn();
		
		LOG.info("It is your turn. You have " + getUserResources() + " sunshine.");
		LOG.prompt("Press 1 to skip this turn. Press 2 to quit this game. Press 3 to list commands");
		String s = Main.sc.nextLine();
		
		if (s.equals("2")) { return false; }
		
		return true;
	}
	
	private boolean zombieTurn() {
		LOG.info("It is the zombie's turn.");
		return true;
		//if (zombies win) { return false; } 
	}

	private void endGame() {
		/**
		 * End Game Stats?
		 */
		LOG.info("Game has Ended");
	}
}
