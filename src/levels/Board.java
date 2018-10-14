package levels;

import assets.Plant;
import assets.Zombie;

/**
 * Contains the Game state (i.e., location of all plants and zombies)
 * 
 * @author Derek Shao
 */
public class Board {
	
	/* Holds location of each Plant and Zombie  
	 * Used for displaying to user.
	 */
	private Grid gameBoard[][]; // Temporarily of Object type to contain both Plant and Zombie objects
	
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
	 * @Override
	 * public void update(Observable arg0, Object arg) {
	 * 
	 * 
	 * }
	 * 
	 */

	/**
	 * Method for Milestone 1 only.
	 * Prints the current game state to console.
	 */
	public void printBoard() {
		//@Michael Patsula, we can use the print method you made here
	}
}
