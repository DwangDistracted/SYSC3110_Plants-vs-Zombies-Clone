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
	private Object gameBoard[][]; // Temporarily of Object type to contain both Plant and Zombie objects
	
	public Board(int row, int col) {
		
		gameBoard = new Object[row][col];
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
		
		if (gameBoard[x][y] == null) {
			gameBoard[x][y] = plant;
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
	 */
	public void removePlant(int x, int y) {
		
		//Make sure we are removing a Plant
		//Our game logic should prevent that from happening - this is just pre-cautionary
		gameBoard[x][y] = gameBoard[x][y] instanceof Plant ? null : gameBoard[x][y]; 
	}
	
	public boolean placeZombie(Zombie zombie, int x, int y) {
		
		// make sure zombie is not placed on top of a plant
		if (!(gameBoard[x][y] instanceof Plant)) {
			gameBoard[x][y] = zombie;
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
		
		//Make sure we are removing a Zombie
		//Our game logic should prevent that from happening - this is just pre-cautionary
		gameBoard[x][y] = gameBoard[x][y] instanceof Zombie ? null : gameBoard[x][y];
	}
	
	/**
	 * Method for Milestone 1 only.
	 * Prints the current game state to console.
	 */
	public void printBoard() {
		//@Michael Patsula, we can use the print method you made here
	}
}
