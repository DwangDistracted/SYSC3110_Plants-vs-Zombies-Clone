package levels;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import assets.PlantTypes;
import assets.ZombieTypes;

/**
 * A Class that holds all information about a particular level. Made to be serializable to SER
 * @author David Wang
 *
 */
public class LevelInfo implements Serializable {
	private static final long serialVersionUID = 1L; //change this if the class changes
	
	//The level's name
	private String name;
	//The Rating of the Level's Difficulty
	private int levelRating;
	
	//The Size of the Game Grid for this level
	private int column;
	private int row;
	
	//The initial amount of resource points the user has
	private int initResources;
	//The amount of resource points the user gains per turn
	private int resPerTurn;
	
	//The zombies that will attack the player
	private Map<ZombieTypes, Integer> zombies;
	//The Plants that the player is allowed to use
	private Set<PlantTypes> allowedPlants;
	
	/**
	 * Constructs a LevelInfo Object
	 * @param name Name of the Level
	 * @param levelRating the Raing of the level
	 * @param gridX the number of columns
	 * @param gridY the number of rows
	 * @param resPerTurn the resources gained per turn by the player
	 * @param initResources the resources the player starts with
	 * @param zombies the types and number of zombies that will attack the player
	 * @param plants the plants the player is allowed to use
	 */
	public LevelInfo (String name, int levelRating, int gridX, int gridY, int resPerTurn, int initResources,
		Map<ZombieTypes, Integer> zombies, Set<PlantTypes> plants) {
		this.zombies = zombies;
		this.allowedPlants = plants;
		
		this.name = name;
		this.levelRating = levelRating;
		
		this.column = gridX;
		this.row = gridY;
		this.initResources = initResources;
		this.resPerTurn = resPerTurn;
	}
	public String getName() {
		return name;
	}
	public int getLevelRating() {
		return levelRating;
	}
	public int getColumns() {
		return column;
	}
	public int getRows() {
		return row;
	}
	public int getResPerTurn() {
		return resPerTurn;
	}
	public int getInitResources() {
		return initResources;
	}
	public Map<ZombieTypes, Integer> getZombies() {
		return new HashMap<ZombieTypes, Integer>(zombies);
	}
	public Set<PlantTypes> getAllowedPlants() {
		return new HashSet<PlantTypes>(allowedPlants);
	}
}
