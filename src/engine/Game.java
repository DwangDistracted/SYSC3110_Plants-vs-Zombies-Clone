package engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import assets.Flower;
import assets.Plant;
import assets.Zombie;
import assets.ZombieTypes;
import levels.LevelInfo;
import util.Logger;

/**
 * The Primary Game Loop. Instance per level
 * @author David Wang
 */
public class Game {
	public enum GameState {
		PLAYING,
		WON,
		LOST
	}
	
	private static Logger LOG = new Logger("Game");
	//combat engine
	private Combat combat; 
	//The Level this game is playing
	private LevelInfo levelInfo;
	//The Game Board
	private Board board;
	//The Player's Purse
	private Purse userResources;
	//The Zombies that have not yet spawned into the game
	private HashMap<ZombieTypes, Integer> zombieQueue;
	//The number of zombies (total) in the level
	private int numZombies;
	//the number of turns elapsed
	private int numTurns;

	private GameState gamestate;
	
	private List<Grid> gridsChanged; 
	
	/**
	 * Initializes a Game for a given Level
	 * @param lvl the LevelInfo for the given Level
	 */
	public Game(LevelInfo lvl) {
		//set up config from level config
		board = new Board(lvl.getRows(), lvl.getColumns());
		levelInfo = lvl;
		combat = new Combat(board.getBoard());
		
		zombieQueue = (HashMap<ZombieTypes, Integer>) lvl.getZombies();
		numZombies = zombieQueue.values().stream().mapToInt(Integer::intValue).sum();
		userResources = new Purse(levelInfo.getInitResources());
		gamestate = GameState.PLAYING;
		numTurns = 0;
		gridsChanged = new ArrayList<Grid>();
	}
	
	/**
	 * Processes a Player's Turn
	 */
	public void playerTurn() {
		//plants action
		List<Plant> plantsInGame = board.getPlantsInGame();
		LOG.debug("Doing Plant Attack Calculations");
		for (Plant plant : plantsInGame) {
			LOG.debug("Plant at (" + plant.getCol() + "," + plant.getRow() + ")");
			if (!(plant instanceof Flower)) {
				int [] zombieTargetCoordinates = combat.plantAttack(plant);
				if (zombieTargetCoordinates != null) {
					board.removeZombie(zombieTargetCoordinates[0], zombieTargetCoordinates[1]);
					LOG.debug("Plant at (" + plant.getCol() + "," + plant.getRow() + ") has defeated zombie at (" + zombieTargetCoordinates[0] + "," + zombieTargetCoordinates[1] + ")");
				}
			}
		}
	}
	
	/**
	 * Processes the Zombie's Turn.
	 * @author David Wang; Modified by Derek Shao
	 */
	private void zombieTurn() {
		LOG.info("It is the zombie's turn.");
		
		//create a new collection to prevent concurrent modification of Board zombies attribute
		List<Zombie> zombiesInGame = new LinkedList<Zombie>(board.getZombiesInGame());
		Iterator<Zombie> iterator = zombiesInGame.iterator();
		
		while (iterator.hasNext()) {
			Zombie nextZombie = iterator.next();
			//if a zombie has failed to move, it means it is being blocked by a Plant
			if (!nextZombie.move()) {
				boolean targetIsDead = combat.zombieAttack(nextZombie, board.getPlant(nextZombie.getRow(), nextZombie.getCol()));
				if (targetIsDead) {
					board.removePlant(nextZombie.getRow(), nextZombie.getCol());
				}
			} else {
				gridsChanged.add(board.getGrid(nextZombie.getRow(), nextZombie.getCol()));
			}

			if (board.hasReachedEnd()) {
				// a zombie has reached the end of the board and player loses
				endGame(false);
				break;
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
				zombie.setListener(board);
				board.placeZombie(zombie, rowNumber, levelInfo.getColumns() - 1); //spawn the zombie
				zombie.setRow(rowNumber);
				zombie.setColumn(levelInfo.getColumns() - 1);
				
				gridsChanged.add(board.getGrid(rowNumber, levelInfo.getColumns() - 1));
				
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
	
	/**
	 * Tells Combat Engine to handle attack and damage calculations. Adds Resources to Player Purse. Checks if the pLayer has won
	 */
	public void doEndOfTurn() {
		playerTurn(); //player plants attack
		numTurns++;
		//do the zombie Turn
		zombieTurn();
		
		//economy calculations
		userResources.addPoints(levelInfo.getResPerTurn()); //do default sunshine gain
		userResources.addPoints(board.getNumberOfSF() * Flower.getPoints()); //do sunflower/economy plants sunshine gain
		
		//did player win?
		if (zombieQueue.values().stream().mapToInt(Integer::intValue).sum() == 0 && board.getNumberOfZombies() == 0) {
			endGame(true);
		}
	}
	
	/**
	 * Ends the Game
	 * @param playerWin True if the player won, false otherwise
	 */
	private void endGame(boolean playerWin) {
		if(playerWin) {
			LOG.info("Player has Won");
			gamestate = GameState.WON;
		} else {
			LOG.info("Player was eaten by Zombies");
			gamestate = GameState.LOST;
		}
	}
	 
	 /**
	  * Get the LevelInfo 
	  * 
	  * @return the level info
	  */
	 public LevelInfo getLevelInfo() {
		 
		 return this.levelInfo;
	 }
	 
	 /**
	  * Get the Board 
	  * 
	  * @return the board
	  */
	 public Board getBoard() {
		 
		 return this.board;
	 }
	 
	 /**
	  * Get the Purse
	  * 
	  * @return the purse
	  */
	 public Purse getPurse() {
		 
		 return this.userResources;
	 }
	 
	 public GameState getState() {
		 return gamestate;
	 }

	public int getTurns() {
		return numTurns;
	}
	
	public List<Grid> getGridsChanged() {
		
		return gridsChanged;
	}
	
	public void resetGridsChanged()  {
		
		gridsChanged = new ArrayList<Grid>();
	}
}
