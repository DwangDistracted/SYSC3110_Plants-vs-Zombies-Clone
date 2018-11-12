package engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import assets.Plant;
import assets.Flower;
import assets.PlantTypes;
import assets.Zombie;
import assets.ZombieTypes;
import input.Command;
import input.CommandWords;
import input.Parser;
import levels.LevelInfo;
import util.Logger;

/**
 * The Primary Game Loop. Instance per level
 * @author David Wang
 */
public class Game {
	private static Logger LOG = new Logger("Game");
	//combat engine
	private Combat combat; 
	//The Level this game is playing
	private LevelInfo levelInfo;
	//The Game Board
	private Board board;
	//The Player's Purse
	private Purse userResources;
	//Whether or not the Game has concluded
	private boolean finished = false;
	//The Zombies that have not yet spawned into the game
	private HashMap<ZombieTypes, Integer> zombieQueue;
	//The number of zombies (total) in the level
	private int numZombies;

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
	}
	
	/**
	 * Processes a Player's Turn
	 */
	private void playerTurn() {
		LOG.info("It is your turn. You have " + userResources.getPoints() + " sunshine.");
		LOG.prompt(CommandWords.getPrimaryGameCommands());
		
		while(!processCommand(Parser.getCommand())) {
			LOG.prompt(CommandWords.getPrimaryGameCommands());
		}
		
		//plants action
		List<Plant> plantsInGame = board.getPlantsInGame();
		
		for (Plant plant : plantsInGame) {
			if (!(plant instanceof Flower)) {
				int [] zombieTargetCoordinates = combat.plantAttack(plant);
				if (zombieTargetCoordinates != null) {
					board.removeZombie(zombieTargetCoordinates[0], zombieTargetCoordinates[1]);
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
	private void doEndOfTurn() {
		
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
		board.displayBoard();
		this.finished = true;
		if(playerWin) {
			LOG.info("Player has Won");
		} else {
			LOG.info("Player was eaten by Zombies");
		}
	}
	
	
	//INPUT - MILESTONE 1 ONLY

	 /**
	  * Displays a help message informing user on the type of zombies they will face within the level,
	  * the plants that are at that disposal and how to use the command line.
	  * @author Michael Patsula
	  */
	 public void printHelp()
	 {
		 Map<ZombieTypes,Integer> allowedZombies = levelInfo.getZombies();
		 Set<PlantTypes> allowedPlants = levelInfo.getAllowedPlants();
		 StringBuilder s = new StringBuilder();
		 
		 s.append("Help message: to place a unit Type 'Place <unitName> <row #> <column #>. \nNote: row & column numbers start at coordinate 0");
		 
		 s.append("\nThe plants commands at your disposal are: ");
		 for(PlantTypes p : allowedPlants)
		 {
			 s.append(p.toString() + " | ");
		 }
		 s.append("\nThe zombies that you will face against are: ");
		 for(ZombieTypes z : allowedZombies.keySet())
		 {
			 s.append(z.toString() + " | ");
		 }
		 LOG.info(s.toString());
	 }
	 
	/**
	 * Determines if the user inputed commands is within the grid boundaries.
	 * @param row - coordinate
	 * @param column - coordinate
	 * @return true if the parameters are within the grid boundaries, and false otherwise.
	 * @author Michael Patsula
	 */
	 public boolean inRange(int row, int column)  
	 {
		if(row <= levelInfo.getRows() && row >= 0 && column < levelInfo.getColumns() && column >= 0)  // board.getRow and column was subtracted by one due to arrays
			return true;
			
		return false;
	 }

	/**
	 * Processes the user input
	 * @param command - the user input
	 * @return true if the input was valid, false otherwise
	 * @author Michael Patsula and refactored by David Wang
	 */
	 public boolean processCommand(Command command)
	 {
		 if (command == null) { return false; } 
		 
		 String commandWord = command.getWord(1);

		 if (commandWord.equalsIgnoreCase(CommandWords.HELP.toString())) {
			 printHelp();
			 return false;

		 } else if (commandWord.equalsIgnoreCase(CommandWords.QUIT.toString())) {
			 finished = true;
			 LOG.info("User quit the game");
			 return true;

		 } else if (commandWord.equalsIgnoreCase(CommandWords.PASS.toString())) {
			 return true;

		 } else if (commandWord.equalsIgnoreCase(CommandWords.PLACE.toString())) {
			 if (command.isUnknownWord(3) || command.isUnknownWord(4)) // restriction - check if word 3 and 4 is null
			 {
				 LOG.warn("Please input your command with coordinates");
				 return false;
			 } else { // restriction 2 see if the coordinates are in range of the board
				 boolean inRange;
				 try {
					 inRange = inRange(Integer.valueOf(command.getWord(3)), Integer.valueOf(command.getWord(4))); // check
					 // valid
					 // coordinates
				 } catch (Exception e) {
					 LOG.error("Exception: " + e.getMessage());
					 e.printStackTrace();
					 return false;
				 }

				 if (!inRange) {
					 LOG.warn("The inputted coordinates are out of range");
					 return false;
				 }
			 }

			 if (CommandWords.isValidUnit(command.getWord(2))) {
				 if (levelInfo.getAllowedPlants().contains(PlantTypes.valueOf(command.getWord(2).toUpperCase()))) {
					 Plant p = PlantTypes.toPlantFromString(command.getWord(2));
					 if (userResources.canSpend(p.getCost())) {
						 int plantRow = Integer.valueOf(command.getWord(3));
						 int plantCol = Integer.valueOf(command.getWord(4));
						 userResources.spendPoints(p.getCost());
						 if (!board.placePlant(p, plantRow, plantCol)) {
							 LOG.info(String.format("Cannot place plant: %s at tile: (%s, %s) because the tile is already occupied",
									 command.getWord(2), command.getWord(3), command.getWord(4)));
							 return false;
						 }
						 p.setCoordinates(plantRow, plantCol);
						 LOG.info(command.getWord(2) + " was placed on tile " + command.getWord(3) + ", "
								 + command.getWord(4));
						 return true;
					 } else {
						 LOG.warn("Cannot Afford Unit");
						 return false;
					 }
				 } else {
					 LOG.warn("Unit not Available");
					 return false;
				 }
			 } else {
				 LOG.warn("Not a Valid Unit");
				 return false;
			 }
		 }
		 LOG.warn("Invalid Command");
		 return false;
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
}
