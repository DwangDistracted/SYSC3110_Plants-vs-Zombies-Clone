package engine;

import java.util.ArrayList;
import java.util.List;

import Main.Main;
import levels.Board;
import levels.LevelInfo;
import util.Logger;

/**
 * The Primary Game Loop. Instance per level. Coordinates with Turn and Combat Engines
 * @author david
 *
 */
public class Game {
	private static Logger LOG = new Logger("Game");
	
	private LevelInfo levelInfo;
	private Board board;

	private int userResources;
	
	public Game (LevelInfo lvl) {
		//set up config from level config
		board = new Board(lvl.getGridX(), lvl.getGridY());
		levelInfo = lvl;
	}
	
	public boolean canSpend(int cost) {
		return cost <= userResources;
	}
	
	public boolean spendUserResources(int cost) {
		if (canSpend(cost)) {
			userResources -= cost;
			return true;
		} else {
			return false;
		}
	}
	public int getUserResources() {
		return userResources;
	}
	
	//start the game loop
	public void start() {
		userResources = levelInfo.getInitResources();
		
		while (true) {
			if (!playerTurn()) { break; }
			doEndOfTurn();
			zombieTurn();
			if (!doEndOfTurn()) { break; }
		}
		endGame();
	}

	private boolean playerTurn() {
		userResources += levelInfo.getResPerTurn();
		
		LOG.info("It is your turn. You have " + getUserResources() + " sunshine.");
		LOG.prompt("Press 1 to skip this turn. Press 2 to quit this game.");
		String s = null;
		
		while (true) { //User Input Block
			s = Main.sc.nextLine();
			//Parse User Commands
			if (s.equals("2")) {
				return false; 
			} else if (s.equals("1")) {
				LOG.debug("Skipping turn...");
				break;
			}
		}
		
		return true;
	}
	
	private void zombieTurn() {
		LOG.info("It is the zombie's turn.");
		//spawn zombies
	}

	private boolean doEndOfTurn() {
		//are there any collisions? -> do melee calculations
		//is any range combat needed? -> do ranged calculations
		//move zombies
		//if zombies win { return false; }
		return true;
	}
	
	private void endGame() {
		/**
		 * End Game Stats?
		 */
		LOG.info("Game has Ended");
	}
}
