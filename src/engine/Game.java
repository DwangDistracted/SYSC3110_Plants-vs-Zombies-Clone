package engine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import assets.Exploding_Zombie;
import assets.Flower;
import assets.Plant;
import assets.PlantTypes;
import assets.Zombie;
import assets.ZombieTypes;
import levels.LevelInfo;
import util.Logger;

/**
 * The Primary Game Loop. Instance per level
 * @author David Wang
 */
public class Game implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public enum GameState {
		PLAYING,
		WON,
		LOST
	}
	
	private static Logger LOG = new Logger("Game");
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

	private CommandQueue cQ;
	private List<GameListener> listeners;
	
	/**
	 * Initializes a Game for a given Level
	 * @param lvl the LevelInfo for the given Level
	 */
	public Game(LevelInfo lvl) {
		//set up config from level config
		board = new Board(lvl.getRows(), lvl.getColumns());
		levelInfo = lvl;
		
		zombieQueue = (HashMap<ZombieTypes, Integer>) lvl.getZombies();
		numZombies = zombieQueue.values().stream().mapToInt(Integer::intValue).sum();
		LOG.debug("Level has " + numZombies + " zombies");
		userResources = new Purse(levelInfo.getInitResources());
		gamestate = GameState.PLAYING;
		numTurns = 0;
		listeners = new ArrayList<>();
		cQ = new CommandQueue(this, listeners);
	}
	
	public void addListener(GameListener gl) {
		listeners.add(gl);
	}
	
	/**
	 * Processes a Player's Turn
	 */
	public void playerTurn() {
		//plants action
		List<Plant> plantsInGame = board.getPlantsInGame();
		LOG.debug("Doing Plant Attack Calculations");
		for (Plant plant : plantsInGame) {
			LOG.debug("Plant at (" + plant.getRow() + "," + plant.getCol() + ")");
			plant.attack(board);
		}
	}
	
	/**
	 * Processes the Zombie's Turn.
	 * @author David Wang; Modified by Derek Shao
	 */
	private void zombieTurn() {
		LOG.debug("It is the zombie's turn.");
		
		//create a new collection to prevent concurrent modification of Board zombies attribute
		List<Zombie> zombiesInGame = new LinkedList<Zombie>(board.getZombiesInGame());
		Iterator<Zombie> iterator = zombiesInGame.iterator();
		
		while (iterator.hasNext()) {
			Zombie nextZombie = iterator.next();
			//if a zombie has failed to move, it means it is being blocked by a Plant
			if (!nextZombie.move(levelInfo.getRows())) {
				nextZombie.attack(board);
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
			int zombiesToSpawn = rand.nextInt(numZombies/4 == 0? 2: numZombies/4); //if there aren't enough zombies then spawn up to 1
			
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
		cQ.registerEndTurn(board);
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
		
		for (GameListener gl : listeners) {
			gl.updateAllGrids();
			gl.updateEndTurn();
		}
	}
	
	/**
	 * Ends the Game
	 * @param playerWin True if the player won, false otherwise
	 */
	private void endGame(boolean playerWin) {
		if(playerWin) {
			LOG.debug("Player has Won");
			gamestate = GameState.WON;
		} else {
			LOG.debug("Player was eaten by Zombies");
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
	 
	 /**
	  * Returns the current state of the game; Wom, lost, playing
	  * @return
	  */
	 public GameState getState() {
		 return gamestate;
	 }

	 /**
	  * Returns the number of turns elapsed since the start of the turns
	  * @return
	  */
	public int getTurns() {
		return numTurns;
	}
	
	/**
	 * Decrements the number of turns by 1. Used by the undo end turn function
	 */
	public void decrementTurns() {
		this.numTurns--;
	}

	/**
	 * Increments the number of turns by 1. Used by the redo end turn function
	 */
	public void incrementTurns() {
		this.numTurns++;
	}
	
	/**
	 * Plants a plant in a given position.
	 * @param type
	 * @param x
	 * @param y
	 */
	public void placePlant(PlantTypes type, int x, int y) {
		Plant selectedPlant = PlantTypes.toPlant(type);
		if (userResources.canSpend(selectedPlant.getCost())) {
			if (board.placePlant(selectedPlant, x, y)) {
				cQ.registerPlace(type,x,y);
				userResources.spendPoints(selectedPlant.getCost());
				
				for (GameListener gl : listeners) {
					gl.updateGrid(x, y);
					gl.updatePurse();
				}
			}
		} else {
			for (GameListener gl : listeners) {
				gl.updateMessage("Not Enough Points", "You do not have enough funds for: " + selectedPlant.toString());
			}
		}
	}
	
	/**
	 * Removes a Plant from a given position
	 * @param x
	 * @param y
	 */
	public void removePlant(int x, int y) {
		cQ.registerDig(board.getPlant(x, y).getPlantType(),x,y);
		board.removePlant(x, y);
		for (GameListener gl : listeners) {
			gl.updateGrid(x, y);
		}
	}

	/**
	 * Undos the last move
	 */
	public void undo() {
		if (!cQ.undo()) {
			for (GameListener gl : listeners) {
				gl.updateMessage("Cannot Undo", "No more moves to Undo");
			}
		}
	}
	
	/**
	 * Redos the previously undone move
	 */
	public void redo() {
		if (!cQ.redo()) {
			for (GameListener gl : listeners) {
				gl.updateMessage("Cannot Redo", "No more moves to Redo");
			}
		}
	}
}
