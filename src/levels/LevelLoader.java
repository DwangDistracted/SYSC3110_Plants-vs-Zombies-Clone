package levels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import assets.PlantTypes;
import assets.ZombieTypes;
import util.Logger;

/**
 * Milestone 1: This class creates a sample level
 * This class loads levels from serialized LevelInfo Files (JSON/XML) (Future Milestone) and sends Level Info to the Main Program.
 * @author david
 *
 */
public class LevelLoader {
	private static Logger LOG = new Logger("LevelLoader");
	
	//The list of levels loaded into the game
	private static List<LevelInfo> levels;
	
	private static int currentLevel;
	
	private LevelLoader() {} //Singleton
	
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
					2,									//column
					2,									//row
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
	
	public static void init() {
		levels = new ArrayList<>();
		currentLevel = -1;

		//Loads sample LevelInfo
		if(Logger.isDebug()) sampleLevels();
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
	
	/**
	 * Resets progress through the levels.
	 */
	public static void reset() {
		currentLevel = -1;
	}
}
