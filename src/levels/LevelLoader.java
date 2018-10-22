package levels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import assets.PlantTypes;
import assets.ZombieTypes;
import util.Logger;

/**
 * This class loads levels from serialized LevelInfo
 * @author david
 *
 */
public class LevelLoader {
	private static Logger LOG = new Logger("LevelLoader");
	
	private static List<LevelInfo> levels;
	private static int currentLevel;
	
	private LevelLoader() {} //Singleton
	
	private static void sampleLevels(List<LevelInfo> levels) {
		HashMap<ZombieTypes, Integer> sampleZombies = new HashMap<ZombieTypes, Integer>();
		sampleZombies.put(ZombieTypes.REG_ZOMBIE, 20);		
		levels.add(new LevelInfo(
					"Sample",							//level name
					4,									//level rating
					null,								//the next level
					10,									//column
					10,									//row
					25,									//Resources per Turn
					2000,								//Initial Resources
					sampleZombies,		//The Enemy Zombies
					new ArrayList<PlantTypes>()					//The Allowed Plants
		));
		
		LOG.debug("Added Sample Level");
	}
	
	public static void init() {
		levels = new ArrayList<>();
		currentLevel = -1;
		
		//DeSerialize all Level Info into Level Info
		//Placeholder LevelInfo
		sampleLevels(levels);
	}
	
	public static LevelInfo getNextLevel() {
		currentLevel++;
		if (currentLevel < levels.size()) {
			return levels.get(currentLevel);
		}
		return null;
	}
	
	public static void reset() {
		currentLevel = -1;
	}
}
