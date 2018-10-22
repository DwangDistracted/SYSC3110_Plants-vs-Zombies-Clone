package levels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import assets.Zombie;
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
		levels.add(new LevelInfo(
					"Sample",							//level name
					1,									//level rating
					null,								//the next level
					8,									//Grid X
					1,									//Grid Y
					25,									//Resources per Turn
					1975,								//Initial Resources
					new HashMap<Zombie, Integer>(),		//The Enemy Zombies
					new ArrayList<>()					//The Allowed Plants
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
