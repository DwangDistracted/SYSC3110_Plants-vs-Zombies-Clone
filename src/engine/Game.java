package engine;

import java.util.Random;
import assets.Regular_Zombie;
import assets.Zombie;
import main.Main;
import levels.LevelInfo;
import util.Logger;
import view.Board;

/**
 * The Primary Game Loop. Instance per level. Coordinates with Turn and Combat Engines
 * @author david
 *
 */
public class Game {
	private static Logger LOG = new Logger("Game");
	
	private Combat combat; //combat engine
	private LevelInfo levelInfo;
	private Board board;
	
	private Purse userResources;

	private boolean finished = false;
	
	public Game (LevelInfo lvl) {
		
		//set up config from level config
		board = new Board(lvl.getGridY(), lvl.getGridX());
		levelInfo = lvl;
		combat = new Combat(board);
		userResources = new Purse(levelInfo);
	}
	
	//start the game loop
	public void start() {
		while (true) {
			if (!playerTurn()) { break; }
			doEndOfTurn();
			zombieTurn();
			if (!doEndOfTurn()) { break; }
		}
	}
	
	private boolean playerTurn() {
		userResources.addPoints(levelInfo.getResPerTurn());
		
		board.displayBoard();
		LOG.info("It is your turn. You have " + userResources.getPoints() + " sunshine.");
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
		board.displayBoard();
				
		//move zombies
		for(Object key : board.getExperimental().keySet())
		{
			if(key instanceof Zombie)
			{
				int[] coordinates = board.getExperimental().get(key);
				int[] newCoordinates = board.moveUnit(((Zombie) key).getSpeed(), coordinates[0], coordinates[1]);
				if(newCoordinates == null) //if the zombies get to the end
				{
					endGame(false);
					return;
				}
				board.getExperimental().replace(key, coordinates, newCoordinates);
			}
		}
		
		Random rand = new Random();
		int rowNumber = rand.nextInt(levelInfo.getGridY()); //determines which row the zombie will go down
		
		for(int i = 0; i < 1; i++)  //spawn zombies   1 can replaced with a variable later
		{
			Regular_Zombie zombie = new Regular_Zombie();
			board.addUnit(zombie, rowNumber, levelInfo.getGridX() - 1);
		}

		board.displayBoard();
	}
	
	private boolean doEndOfTurn() {
		if(!board.getExperimental().isEmpty())
		{
			combat.computePlantAttacks();
			combat.computeZombieAttacks();
		}
		
		//move zombies
		if (finished) { return false; }
		return true;
	}
	
	private void endGame(boolean playerWin) {
		this.finished = true;
		if(playerWin) {
			LOG.info("Player has Won");
		} else {
			LOG.info("Player was eaten by Zombies");
		}
	}
}
