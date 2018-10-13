package levels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import assets.Plant;
import assets.Zombie;

public class LevelInfo {
	//The level's name
	private String name;
	//The Rating of the Level's Difficulty
	private int levelRating;
	//The Type of this should be adjusted
	private String nextLevel;
	
	//The Size of the Game Grid for this level
	private int gridX;
	private int gridY;
	
	private int initResources;
	private int resPerTurn;
	
	private HashMap<Zombie, Integer> zombies;
	private List<Plant> prohibitedPlants;
	public LevelInfo (String name, int levelRating, String nextLevel, int gridX, int gridY, int initResources, int resPerTurn) {
		zombies = new HashMap<Zombie, Integer>();
		prohibitedPlants = new ArrayList<Plant>();
		
		this.name = name;
		this.levelRating = levelRating;
		this.nextLevel = nextLevel;
		
		this.gridX = gridX;
		this.gridY = gridY;
		this.initResources = initResources;
		this.resPerTurn = resPerTurn;
	}
	
	public LevelInfo (String name, int levelRating, String nextLevel, int gridX, int gridY, int resPerTurn, int initResources,
													HashMap<Zombie, Integer> zombies, ArrayList<Plant> plants) {
		this.zombies = zombies;
		this.prohibitedPlants = plants;
		
		this.name = name;
		this.levelRating = levelRating;
		this.nextLevel = nextLevel;
		
		this.gridX = gridX;
		this.gridY = gridY;
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
	public int getGridX() {
		return gridX;
	}
	public int getGridY() {
		return gridY;
	}
	public int getResPerTurn() {
		return resPerTurn;
	}
	public int getInitResources() {
		return initResources;
	}

	
	public HashMap<Zombie, Integer> getZombies() {
		return zombies;
	}
	public List<Plant> getProhibitedPlants() {
		return prohibitedPlants;
	}
}
