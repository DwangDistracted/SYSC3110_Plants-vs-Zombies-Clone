package engine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import assets.Flower;
import assets.Plant;
import assets.Zombie;
import assets.Juking_Zombie;
import util.Logger;
import engine.Grid;
 
/**
 * Contains the Game state (i.e., location of all plants and zombies) and detects board conflicts.
 * 
 * @author Derek Shao
 * 
 */
public class Board implements ZombieMoveListener, Serializable {
	private static final long serialVersionUID = 1L;

	private static Logger LOG = new Logger("Board");
	
	/* Holds location of each Plant and Zombie  
	 * Used for displaying to user.
	 * */
	private Grid gameBoard[][];

	/* Number of rows in game board */
	private int row;

	/* Number of columns in game board */
	private int col;
	
	/* Checks if a Zombie has reached the end of the board
	 * If a zombie has, then the game is over and the player loses
	 * */
	private boolean zombieReachedEnd;

	/**
	 * Track all plants currently in the game
	 */
	private List<Plant> plantsInGame;
	
	/**
	 * Track all zombies currently in the game
	 */
	private List<Zombie> zombiesInGame;
	
	/**
	 * Number of Sunflowers in game
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
		
		//initialize board
		gameBoard = new Grid[row][col];
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				gameBoard[r][c] = new Grid(r, c);
			}
		}
	}
	
	/**
	 * Creates a new instance of Board as a deep copy of another instance
	 * @param other
	 * @author David Wang
	 */
	public Board(Board other) {
		this.row = other.row;
		this.col = other.col;
		this.zombieReachedEnd = other.zombieReachedEnd;
		this.sfCounter = other.sfCounter;
		
		this.zombiesInGame = new LinkedList<Zombie>();
		LOG.debug("Made a Clone of Board");
		this.plantsInGame = new LinkedList<Plant>();
		this.zombiesInGame.addAll(other.zombiesInGame);
		this.plantsInGame.addAll(other.plantsInGame);
		
		//initialize board
		gameBoard = new Grid[row][col];
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				gameBoard[r][c] = new Grid(other.gameBoard[r][c]);
			}
		}
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
	 * Get the grid at the specified location
	 * 
	 * @param row The row of the grid
	 * @param col The column of the grid
	 * @return the grid at the location
	 */
	public Grid getGrid(int row, int col) {
		return gameBoard[row][col];
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
			plant.setCoordinates(x, y);
			LOG.debug(String.format("Placed plant at location: (%d, %d)", x, y));
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
		this.plantsInGame.remove(gameBoard[x][y].getPlant());
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
			zombie.setRow(x);
			zombie.setColumn(y);
			this.zombiesInGame.add(zombie);
			LOG.debug(String.format("Placed zombie at location: (%d, %d)", x, y));
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
	 * Get the First Zombie at the specified location (x,y)
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
	 * Gets ALL Zombie at the specified location (x,y)
	 * This is likely used when Plant needs to decide which zombie to attack.
	 * 
	 * @param x coordinate
	 * @param y coordinate 
	 * @return the zombie at location (x, y) - null if no zombie present
	 * 
	 */
	public Queue<Zombie> getAllZombies(int x, int y) {
		return gameBoard[x][y].getZombies();
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
	 * Method for Debug Purposes only.
	 * Prints the current game state to console.
	 * 
	 */
	public String displayBoard() {
		StringBuilder str = new StringBuilder("\n");
		int rowCount = 0;
		for(int row = 0; row < gameBoard.length; row++) {
			//label the rows
			str.append(rowCount + " ");
			rowCount++;
			
			for(int col = 0; col < gameBoard[row].length; col++) {
				str.append(" | ");
				if (gameBoard[row][col].getPlant() != null) {
					str.append(gameBoard[row][col].getPlant().toString() + " ");
				} else {
					str.append("N ");
				}
				str.append(gameBoard[row][col].getNumberOfZombies() + "Z");
				str.append(" | ");
			}
			
			str.append("\n");
		}
		
		// label the columns
		for (int i = 0; i < gameBoard[0].length; i++) {
			str.append("      " + i + "   ");
		}

		str.append("\n");
		return str.toString();
	}

	/**
	 * Sets the state of this board to another's state
	 * @param other
	 */
	public void setBoard(Board other) {
		this.zombiesInGame = other.zombiesInGame;
		this.plantsInGame = other.plantsInGame;

    	for (int i = 0; i < gameBoard.length; i++) {
    		for (int j = 0; j < gameBoard[i].length; j++) {
    			this.removePlant(i, j);
    			while(!this.getAllZombies(i, j).isEmpty()) {
        			this.removeZombie(i,j);	
    			}
    			LOG.debug("Reset Grid ("+i+","+j+")");
    			
    			if (other.getPlant(i,j) != null) {
    				this.placePlant(other.getPlant(i, j), i, j);
        			LOG.debug("Planting from other board");
    			} else {
        			LOG.debug("No Plants on Grid");
    			}
    			
    			for(Zombie z : other.getAllZombies(i, j)) {
        			this.placeZombie(z, i, j);
        			LOG.debug("Placing Zombie from other board");
    			}
    		}
    	}
	}
	
	/**
	 * Returns the first zombie that the plant can attack. Null if no zombies can be attacked
	 * @param x
	 * @param y
	 * @return
	 */
	public Zombie getSingleZombieTarget(int x, int y) {
		for (int col = y; col < gameBoard[row].length; col++) {
			if (gameBoard[x][col].getFirstZombie() != null) {
				return gameBoard[x][col].getFirstZombie();
			}
		}
		return null;
	}
	
	/**
	 * Returns a list of all zombies that the plant can attack IF the plant can attack all zombies in a grid. Null if no zombies can be attacked
	 * @param x
	 * @param y
	 * @return
	 */
	public List<Zombie> getGridTargets(int x, int y) {
		for (int col = y; col < gameBoard[row].length; col++) {
			if (!gameBoard[x][col].getZombies().isEmpty()) {
				return new ArrayList<Zombie>(gameBoard[x][col].getZombies());
			}
		}
		return null;
	}
	
	/**
	 * Returns a list of all zombies that the plant can attack IF the plant can attack all zombies in a row. Null if no zombies can be attacked
	 * @param x
	 * @param y
	 * @return
	 */
	public List<Zombie> getRowTargets(int x, int y) {
		ArrayList<Zombie> targets = new ArrayList<>();
		for (int col = y; col < gameBoard[row].length; col++) {
			if (!gameBoard[x][col].getZombies().isEmpty()) {
				targets.addAll(gameBoard[x][col].getZombies());
			}
		}
		return targets.isEmpty()? null : targets;
	}
	
	@Override
	public boolean onZombieMove(Zombie zombie, int maxRow) {
		
		int currentZombieRow = zombie.getRow();
		int currentZombieCol = zombie.getCol();
		
		// if the zombie is in a grid that is currently occupied by a plant, the zombie should attack plant
		// therefore moving zombie was unsuccessful 
		if (gameBoard[currentZombieRow][currentZombieCol].isOccupied()) {
			return false;
		} 
		
		Queue<Zombie> zombiesOnGrid = gameBoard[currentZombieRow][currentZombieCol].getZombies();
		
		// remove the zombie from the grid
		for (Zombie z : zombiesOnGrid) {
			if (zombie == z) {
				zombiesOnGrid.remove(z);
				gameBoard[currentZombieRow][currentZombieCol].updateZombieTypeCount();
				break;
			}
		}
		
		int speed = zombie.getSpeed();
		
		// keep track of the number movements the zombie is able to make 
		int modifier = 0;
		
		// move the zombie based on speed
		for (int i = 1; i <= speed; i++) {
			modifier = i;
			
			// can move zombie until it reaches end of grid or reaches a plant
			if (!(currentZombieCol - i < 0)) {
				if(getNewZomPosition(currentZombieRow, currentZombieCol, modifier, zombie, maxRow).isOccupied()){
					break;
				}
			}
		}
		// determines if this zombie has reached the end of the board
		if(currentZombieCol - modifier < 0)
		{
			this.zombieReachedEnd = true;
			zombie.setColumn(0);
		}
		else
		{
			int[] coord = getNewZomPosition(currentZombieRow, currentZombieCol, modifier, zombie, maxRow).getCoordinates();
			zombie.setRow(coord[0]);
			zombie.setColumn(coord[1]);
		}
		
		// update the board with new position
		gameBoard[zombie.getRow()][zombie.getCol()].addZombie(zombie);
		
		return true;
	}
	
	/**
	 * Gets the potential new position of the zombie when it is moving
	 * @param currentZombieRow - the current zombie row
	 * @param currentZombieCol - the current zombie column
	 * @param modifer - The amount of columns down the board the zombie will move
	 * @param zombie - the zombie that is being movied
	 * @param maxRow - the maximum row allowed within the gameboard
	 * @return a potential new position on the board for the zombie
	 */
	public Grid getNewZomPosition(int currentZombieRow, int currentZombieCol, int modifer, Zombie zombie, int maxRow)
	{
		if(zombie instanceof Juking_Zombie){
			Juking_Zombie jukZombie = (Juking_Zombie) zombie;
			return gameBoard[jukZombie.getPath(maxRow)][currentZombieCol - modifer];
		}
		return gameBoard[currentZombieRow][currentZombieCol - modifer];
	}
} 