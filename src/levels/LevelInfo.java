package levels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import assets.PlantTypes;
import assets.ZombieTypes;

public class LevelInfo {
	//The level's name
	private String name;
	//The Rating of the Level's Difficulty
	private int levelRating;
	//The Type of this should be adjusted
	private String nextLevel;
	
	//The Size of the Game Grid for this level
	private int column;
	private int row;
	
	private int initResources;
	private int resPerTurn;
	
	//The zombies that will attack the player
	private HashMap<ZombieTypes, Integer> zombies;
	//The Plants that the player is allowed to use
	private List<PlantTypes> allowedPlants;
	
	public LevelInfo (String name, int levelRating, String nextLevel, int gridX, int gridY, int resPerTurn, int initResources,
		HashMap<ZombieTypes, Integer> zombies, ArrayList<PlantTypes> plants) {
		this.zombies = zombies;
		this.allowedPlants = plants;
		
		this.name = name;
		this.levelRating = levelRating;
		this.nextLevel = nextLevel;
		
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
	public String getNextLevel() {
		return nextLevel;
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
	public HashMap<ZombieTypes, Integer> getZombies() {
		return zombies;
	}
	public List<PlantTypes> getAllowedPlants() {
		return allowedPlants;
	}
}
