package engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import assets.Zombie;
import assets.ZombieTypes;
import main.Main;
import levels.LevelInfo;
import util.Logger;
import view.Board;

/**
 * The Primary Game Loop. Instance per level
 * @author david
 *
 */
public class Game {
	private static Logger LOG = new Logger("Game");
	
	private Combat combat; //combat engine
	private LevelInfo levelInfo;
	private Board board;
	
	private int userResources;

	private boolean finished = false;
	
	private HashMap<ZombieTypes, Integer> zombieQueue;
	private int numZombies;
	
	public Game (LevelInfo lvl) {
		//set up config from level config
		board = new Board(lvl.getRows(), lvl.getColumns());
		levelInfo = lvl;
		combat = new Combat(board);
		
		zombieQueue = (HashMap<ZombieTypes, Integer>) lvl.getZombies();
		numZombies = zombieQueue.values().stream().mapToInt(Integer::intValue).sum();
	}
	
	//start the game loop
	public void start() {
		userResources = levelInfo.getInitResources();
		
		while (true) {		
			if (!playerTurn()) { break; }
			board.displayBoard();
			zombieTurn();		
			board.displayBoard();
			doEndOfTurn();
			
			if (finished) { break; }
		}
	}
	
	private boolean playerTurn() {
		LOG.info("It is your turn. You have " + userResources + " sunshine.");
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
		
		//move existing zombies
		for(Object key : board.getExperimental().keySet())
		{
			if(key instanceof Zombie)
			{
				int[] coordinates = board.getExperimental().get(key);
				int[] newCoordinates = board.moveUnit(((Zombie) key).getSpeed(), coordinates[0], coordinates[1]);
				if(newCoordinates == null) //if the zombies get to the end
				{
					endGame(false); //zombies win
					return;
				}
				board.getExperimental().replace(key, coordinates, newCoordinates);
			}
		}
		
		
		//spawn new zombies
		if (!zombieQueue.isEmpty()) { //there must be zombies to spawn
			Random rand = new Random();
			int zombiesToSpawn = rand.nextInt(numZombies/4);
			
			if (zombiesToSpawn > zombieQueue.values().stream().mapToInt(Integer::intValue).sum()) { 
				//if the random number is larger than the reamining zombies then spawn all remaining zombies
				zombiesToSpawn = zombieQueue.values().stream().mapToInt(Integer::intValue).sum();
			}
			
			LOG.debug("Spawning " + zombiesToSpawn + " zombies");
			
			for(int i = 0; i < zombiesToSpawn; i++)  //spawn zombies
			{
				//determine type of zombie spawn
				List<ZombieTypes> keys = new ArrayList<ZombieTypes>(zombieQueue.keySet());
				ZombieTypes type = keys.get(rand.nextInt(keys.size()));
				
				LOG.debug("Spawning a " + type.toString());
				
				int rowNumber = rand.nextInt(levelInfo.getRows()); //determines which row the zombie will go down
				Zombie zombie = ZombieTypes.toZombie(type);
				board.addUnit(zombie, rowNumber, levelInfo.getColumns() - 1); //spawn the zombie
				
				//removes the spawned zombie from the Queue
				int x = zombieQueue.get(type) - 1;
				if (x == 0) {
					zombieQueue.remove(type);
				} else {
					zombieQueue.put(type, x);
				}
			}
		} else {
			LOG.debug("No More Zombies to Spawn");
		}
	}
	
	private void doEndOfTurn() {
		//combat calculations
		if(!board.getExperimental().isEmpty())
		{
			combat.computePlantAttacks();
			combat.computeZombieAttacks();
		}
		
		//economy calculations
		userResources += levelInfo.getResPerTurn(); //do default sunshine gain
		userResources += board.getNumberOfSF() * 10; //change 10 to Sunflower attribute //do sunflower/economy plants sunshine gain
		
		//did player win?
		if (zombieQueue.isEmpty() && board.getNumberOfZombies() == 0) {
			endGame(true);
		}
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
