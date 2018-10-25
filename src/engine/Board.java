package engine;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import assets.Flower;
import assets.Plant;
import assets.Zombie;
import levels.Grid;
 
/**
 * Contains the Game state (i.e., location of all plants and zombies) and detects board conflicts.
 * 
 * @author Derek Shao
 * 
 */
public class Board implements ZombieMoveListener {
	
	/* Holds location of each Plant and Zombie  
	 * Used for displaying to user.
	 */
	private Grid gameBoard[][];
	private int row;
	private int col;
	
	/* Checks if a Zombie has reached the end of the board.
	 * If a zombie has, then the game is over and the player loses.
	 * */
	private boolean zombieReachedEnd;

	/**
	 * Track all plants currently in the game.
	 */
	private List<Plant> plantsInGame;
	
	/**
	 * Track all zombies currently in the game.	
	 */
	private List<Zombie> zombiesInGame;
	
	/**
	 * Number of Sunflowers in game.
	 */
	private int sfCounter;
	
	/**
	 * Creates a new instance of Board.
	 * 
	 * @param row number of rows in board
	 * @param col number of columns in board
	 * 
	 */
	public Board(int row, int col) {
		
		this.row = row;
		this.col = col;
		
		this.zombieReachedEnd = false;
		
		this.sfCounter = 0;
		
		this.zombiesInGame = new LinkedList<Zombie>();
		
		this.plantsInGame = new LinkedList<Plant>();
		
		gameBoard = new Grid[row][col];
	}
	
	/**
	 * Get the game board.
	 * 
	 * @return gameBoard
	 */
	public Grid[][] getBoard() {
		
		return gameBoard;
	}
	
	/**
	 * Return the row size of this board
	 * 
	 * @return the row size of this board
	 * 
	 */
	public int getRow() {
		return this.row;
	}
	
	/**
	 * Return the column size of this board
	 * 
	 * @return the column size of this board
	 * 
	 */
	public int getColumn() {
		
		return this.col;
	}
	
	/**
	 * Return whether a zombie has the end of a board.
	 * 
	 * @return true if a zombie has reached the end of a board, false otherwise
	 */
	public boolean hasReachedEnd() {
		
		return this.zombieReachedEnd;
	}
	
	/**
	 * Get the number of zombies currently in game.
	 * 
	 * @return number of zombies in game
	 */
	public int getNumberOfZombies() {
		
		return this.zombiesInGame.size();
	}
	
	/**
	 * Return a list of zombies in game
	 * 
	 * @return zombies in game
	 */
	public List<Zombie> getZombiesInGame() {
		
		return this.zombiesInGame;
	}
	
	/**
	 * Return a list of plants in game
	 * 
	 * @return plants in game
	 */
	public List<Plant> getPlantsInGame() {
		
		return this.plantsInGame;
	}
	
	/**
	 * Places a plant in location x and y.
	 * 
	 * @param plant The Plant to be placed
	 * @param x The X coordinate of where to place Plant
	 * @param y The Y coordinate of where to place Plant
	 * 
	 * @return true if plant placement was successful (i.e., no existing plant is in location),
	 * false otherwise
	 *  
	 * */
	public boolean placePlant(Plant plant, int x, int y) {
		
		if (plant instanceof Flower) {
			sfCounter++;
		}
		
		if (gameBoard[x][y].setPlant(plant)) {
			this.plantsInGame.add(plant);
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * Remove a plant from the grid.
	 * Used for player removal or zombie fully killing a Plant.
	 * 
	 * @param x coordinate of Plant
	 * @param y coordinate of Plant
	 * 
	 */
	public void removePlant(int x, int y) {
		
		gameBoard[x][y].removePlant();
	}
	
	/**
	 * Place a zombie in a specified location on the board. 
	 * 
	 * @param zombie to be placed
	 * @param x coordinate of where to place zombie
	 * @param y coordinate of where to place zombie
	 * @return true if zombie was successfully added, false otherwise
	 * 
	 */
	public boolean placeZombie(Zombie zombie, int x, int y) {
		
		if (gameBoard[x][y].addZombie(zombie)) {
			this.zombiesInGame.add(zombie);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Remove a Zombie from the grid.
	 * Used when Player's plant kills a Zombie.
	 * 
	 * @param x coordinate of Zombie
	 * @param y coordinate of Zombie
	 */
	public void removeZombie(int x, int y) {
		
		Zombie zombieRemoved = gameBoard[x][y].removeZombie();
		
		this.zombiesInGame.remove(zombieRemoved);
	}
	
	/**
	 * Get the Plant at the specified location (x,y)
	 * 
	 * @param x coordinate 
	 * @param y coordinate
	 * @return the plant at location (x, y) - null if no plant present
	 * 
	 */
	public Plant getPlant(int x, int y) {
		
		return gameBoard[x][y].getPlant();
	}
	
	/**
	 * Get the Zombie at the specified location (x,y)
	 * This is likely used when Plant needs to decide which zombie to attack.
	 * 
	 * @param x coordinate
	 * @param y coordinate 
	 * @return the zombie at location (x, y) - null if no zombie present
	 * 
	 */
	public Zombie getZombie(int x, int y) {
		
		return gameBoard[x][y].getFirstZombie();
	}
	
	/**
	 * Get the number of Sunflowers in game.
	 * 
	 * @return number of sunflowers
	 */
	public int getNumberOfSF() {
		
		return this.sfCounter;
	}
 	/**
	 * Method for Milestone 1 only.
	 * Prints the current game state to console.
	 * 
	 */
	public void displayBoard() {
		
		int rowCount = 0;
		for(int row = 0; row < gameBoard.length; row++) {
			//label the rows
			System.out.print(rowCount + " ");
			rowCount++;
			
			for(int col = 0; col < gameBoard[row].length; col++) {
				System.out.print(" | ");
				System.out.print(gameBoard[row][col].getPlant().toString() + " ");
				System.out.print(gameBoard[row][col].getNumberOfZombies() + "Z");
				System.out.print(" | ");
			}
		}
		
		// label the columns
		for (int i = 0; i < gameBoard[0].length; i++) {
			System.out.print("   " + i + "   ");
		}
	}

	@Override
	public boolean onZombieMove(Zombie zombie) {
		
		int currentZombieRow = zombie.getRow();
		int currentZombieCol = zombie.getCol();
		
		// if the zombie is in a grid that is currently occupied by a plant, the zombie should attack plant
		// therefore moving zombie was unsuccessful 
		if (gameBoard[currentZombieRow][currentZombieCol].isOccupied()) {
			
			return false;
		} 
		
		Queue<Zombie> zombies = gameBoard[currentZombieRow][currentZombieCol].getZombies();
		
		// remove the zombie from the grid
		for (Zombie z : zombies) {
			if (zombie == z) {
				zombies.remove(z);
				break;
			}
		}
		
		int speed = zombie.getSpeed();
		
		// keep track of the number movements the zombie is able to make 
		int modifier = 0;
		
		// move the zombie based on speed
		for (int i = 1; i <= speed; i++) {
			
			// can moving zombie until it reaches end of grid or reaches a plant
			if (!(currentZombieCol - i >= 0)) {
				modifier++;
				if (gameBoard[currentZombieRow][currentZombieCol - i].isOccupied()) {
					break;
				}
			}
		}
		
		// update zombie coordinates
		zombie.setRow(currentZombieRow);
		zombie.setCol(currentZombieCol - modifier);
		
		// update the board with new position
		gameBoard[currentZombieRow][currentZombieCol - modifier].addZombie(zombie);
		
		return true;
	}
} 