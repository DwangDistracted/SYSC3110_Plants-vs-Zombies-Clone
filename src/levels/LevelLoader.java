package levels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import assets.PlantTypes;
import assets.ZombieTypes;
import util.Logger;

/**
 * Milestone 1: This class creates a sample level
 * This class loads levels from serialized LevelInfo Files (JSON/XML) (Future Milestone) and sends Level Info to the Main Program.
 * @author David Wang
 *
 */
public class LevelLoader {
	private static Logger LOG = new Logger("LevelLoader");
	
	//The list of levels loaded into the game
	private static List<LevelInfo> levels;
	
	private static int currentLevel;
	
	private LevelLoader() {} //Singleton
	
	public static List<LevelInfo> getLevels(){
		return levels;
	}
	
	/**
	 * Generates sample levels and adds them to the list.
	 * @param levels
	 */
	private static void sampleLevels() {
		HashMap<ZombieTypes, Integer> sampleZombies = new HashMap<>();
		sampleZombies.put(ZombieTypes.REG_ZOMBIE, 20);		
		HashSet<PlantTypes> samplePlants = new HashSet<>();
		samplePlants.add(PlantTypes.PEASHOOTER);		
		samplePlants.add(PlantTypes.SUNFLOWER);
		levels.add(new LevelInfo(
					"Sample",							//level name
					4,									//level rating
					8,									//column
					8,									//row
					25,									//Resources per Turn
					200,								//Initial Resources
					sampleZombies,						//The Enemy Zombies
					samplePlants						//The Allowed Plants
		));
		
		LOG.debug("Added Sample Level");
	}
	
	/**
	 * Future Milestone: Deserializes LevelInfo from JSON/XML files
	 * Current: Does Nothing
	 */
	private static void deserializeLevels () {
		//nothing for milestone 1
	}
	
	/**
	 * Must be called before this class is used
	 */
	public static void init() {
		levels = new ArrayList<>();
		currentLevel = -1;

		//Loads sample LevelInfo
		for (int i = 0; i < 13; i ++) {
			sampleLevels();
		}
		//DeSerialize all Level Info into Level Info
		deserializeLevels();
	}
	
	/**
	 * Fetches the LevelInfo for the next Level
	 * @return LevelInfo. Null if all levels have been played
	 */
	public static LevelInfo getNextLevel() {
		currentLevel++;
		if (currentLevel < levels.size()) {
			return levels.get(currentLevel);
		}
		return null;
	}
	
	public static LevelInfo getLevel(int level) {
		if(level <= getNumLevels()) {
			currentLevel = level;
			return levels.get(level-1);
		} else {
			return null;
		}
	}
	
	public static int getNumLevels() {
		return levels.size();
	}
	
	/**
	 * Resets progress through the levels.
	 */
	public static void reset() {
		currentLevel = -1;
	}
	
	/**
	 * Factory Class for building LevelInfos.
	 * @author david
	 *
	 */
	public class LevelFactory {
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
		
		public LevelFactory() {
			zombies = new HashMap<ZombieTypes, Integer>();
			allowedPlants = new HashSet<PlantTypes>();
		}
		
		public LevelFactory setName(String str) {
			this.name = str;
			return this;
		}
		public LevelFactory setRating(int rating) {
			this.levelRating = rating;
			return this;
		}
		public LevelFactory setGridSize(int x, int y) {
			this.column = x;
			this.row = y;
			return this;
		}
		
		public LevelFactory setResPerTurn(int rpt) {
			this.resPerTurn = rpt;
			return this;
		}
		
		public LevelFactory setInitResources(int initResources) {
			this.initResources = initResources;
			return this;
		}
		
		public LevelFactory addAllowedPlant(PlantTypes type) {
			allowedPlants.add(type);
			return this;
		}
		
		public LevelFactory addZombies(ZombieTypes type, int number) {
			zombies.put(type, number);
			return this;
		}
		
		public LevelInfo toLevelInfo() {
			return new LevelInfo(name, levelRating, column, row, resPerTurn, initResources, zombies, allowedPlants);
		}
	}
	
	/**
	 * Get a LevelLoader instance
	 * @return
	 */
	public static LevelFactory getLevelFactory() {
		LevelLoader x = new LevelLoader();
		return x.new LevelFactory();
	}
}
