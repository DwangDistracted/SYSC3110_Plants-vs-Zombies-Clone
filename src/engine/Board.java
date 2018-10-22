package engine;

import assets.Plant;
import assets.Zombie;
import levels.Grid;
  /**
 * Contains the Game state (i.e., location of all plants and zombies)
 * 
 * @author Derek Shao
 * 
 */
public class Board {
	
	/* Holds location of each Plant and Zombie  
	 * Used for displaying to user.
	 */
	private Grid gameBoard[][];
	
	/**
	 * Creates a new instance of Board.
	 * 
	 * @param row number of rows in board
	 * @param col number of columns in board
	 */
	public Board(int row, int col) {
		
		gameBoard = new Grid[row][col];
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
	 */
	public boolean placePlant(Plant plant, int x, int y) {
		
		return gameBoard[x][y].setPlant(plant);
	}
	
	
	/**
	 * Remove a plant from the grid.
	 * Used for player removal or zombie fully killing a Plant.
	 * 
	 * @param x coordinate of Plant
	 * @param y coordinate of Plant
	 */
	public void removePlant(int x, int y) {
		
		gameBoard[x][y].removePlant();
	}
	
	public boolean placeZombie(Zombie zombie, int x, int y) {
		
		return gameBoard[x][y].addZombie(zombie);
	}
	
	/**
	 * Remove a Zombie from the grid.
	 * Used when Player's plant kills a Zombie.
	 * 
	 * @param x coordinate of Zombie
	 * @param y coordinate of Zombie
	 */
	public void removeZombie(int x, int y) {
		
		gameBoard[x][y].removeZombie();
	}
	
	/**
	 * Get the Plant at the specified location (x,y)
	 * 
	 * @param x coordinate 
	 * @param y coordinate
	 * @return the plant at location (x, y) - null if no plant present
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
	 */
	public Zombie getZombie(int x, int y) {
		
		return gameBoard[x][y].getFirstZombie();
	}
	
 	/**
	 * Method for Milestone 1 only.
	 * Prints the current game state to console.
	 * @Michael Patsula
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
} 