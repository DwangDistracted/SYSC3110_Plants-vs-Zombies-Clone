package levels;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	 * Deserializes all LevelInfo from SER files in the levels directory into the game
	 */
	private static void deserializeLevels () {
		try {
			File folder = new File("levels/");
			File[] listOfFiles = folder.listFiles();

			if (listOfFiles == null) {
				throw new IOException("Missing Level Directory");
			} else if (listOfFiles.length == 0) {
				throw new IOException("Missing Level Files");
			} else {
				for (int i = 0; i < listOfFiles.length; i++) {
					if (listOfFiles[i].isFile()) {
						LOG.debug("Attempting to Deserialize LevelInfo from " + listOfFiles[i].getName());

						FileInputStream fileIn = new FileInputStream(listOfFiles[i]);
						ObjectInputStream in = new ObjectInputStream(fileIn);
						LevelInfo lvl = (LevelInfo) in.readObject();
						levels.add(lvl);
						in.close();
						fileIn.close();
					}
				}
				LOG.debug("Finished Deserialization");
			}
		} catch (IOException io) {
			LOG.error("Failed to deserialize levels - IO Error");
			io.printStackTrace();
		} catch (ClassNotFoundException e) {
			LOG.error("Failed to deserialize levels - Class not found");
			e.printStackTrace();
		}
	}
	
	/**
	 * Must be called before this class is used
	 */
	public static void init() {
		levels = new ArrayList<>();
		currentLevel = 0;

		//DeSerialize all Level Info into Level Info
		deserializeLevels();
		//Generate a Sample level if no serialized level was found
		if(levels.size()==0) {
			sampleLevels();

			//Test Serialization
			try {
				File fOut = new File("levels/" + levels.get(0).getName() + System.currentTimeMillis() + ".ser");
				fOut.getParentFile().mkdirs();
				
				FileOutputStream fileOut = new FileOutputStream(fOut);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(levels.get(0));
				out.close();
				fileOut.close();
			} catch (IOException e) {
				LOG.error("Failed to Serialize Level");
				e.printStackTrace();
			}
			LOG.debug("Level has been serialized");
		}
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
	 * Fetches the LevelInfo for a particular level
	 * @param level
	 * @return
	 */
	public static LevelInfo getLevel(int level) {
		if(level <= getNumLevels()) {
			currentLevel = level - 1;
			return levels.get(currentLevel);
		} else {
			return null;
		}
	}
	
	/**
	 * Returns the number of levels
	 * @return
	 */
	public static int getNumLevels() {
		return levels.size();
	}
	
	/**
	 * Returns the number of the current Level
	 * @return
	 */
	public static int getCurrentLevel() {
		return currentLevel + 1;
	}
	
	/**
	 * Resets progress through the levels.
	 */
	public static void reset() {
		currentLevel = 0;
	}
	
	/**
	 * Factory Class for building LevelInfos. Contains some default values
	 * @author david
	 *
	 */
	public class LevelFactory {
		//The level's name
		private String name = "default";
		//The Rating of the Level's Difficulty
		private int levelRating = 1;
		
		//The Size of the Game Grid for this level
		private int column = 5;
		private int row = 5;
		
		//The initial amount of resource points the user has
		private int initResources = 100;
		//The amount of resource points the user gains per turn
		private int resPerTurn = 50;
		//The zombies that will attack the player
		private Map<ZombieTypes, Integer> zombies;
		//The Plants that the player is allowed to use
		private Set<PlantTypes> allowedPlants;
		
		public LevelFactory() {
			zombies = new HashMap<ZombieTypes, Integer>();
			allowedPlants = new HashSet<PlantTypes>();
		}
		
		/**
		 * Sets the name of the level
		 * @param str set the name
		 * @return
		 */
		public LevelFactory setName(String str) {
			this.name = str;
			return this;
		}
		
		/**
		 * Set the difficulty rating of the level
		 * @param rating
		 * @return
		 */
		public LevelFactory setRating(int rating) {
			this.levelRating = rating;
			return this;
		}
		
		/**
		 * Set the grid size of the level
		 */
		public LevelFactory setGridSize(int x, int y) {
			this.column = x;
			this.row = y;
			return this;
		}
		
		/**
		 * Set the resource gain per turn for the player
		 * @param rpt
		 * @return
		 */
		public LevelFactory setResPerTurn(int rpt) {
			this.resPerTurn = rpt;
			return this;
		}

		/**
		 * Set the initial resources for the player
		 * @param rpt
		 * @return
		 */
		public LevelFactory setInitResources(int initResources) {
			this.initResources = initResources;
			return this;
		}
		
		/**
		 * adds an plant to the allowed list for the player
		 * @param type
		 * @return
		 */
		public LevelFactory addAllowedPlant(PlantTypes type) {
			allowedPlants.add(type);
			return this;
		}
		
		/** 
		 * Adds zombies to the level
		 * @param type
		 * @param number
		 * @return
		 */
		public LevelFactory addZombies(ZombieTypes type, int number) {
			zombies.put(type, number);
			return this;
		}
		
		/**
		 * builds a levelinfo object
		 * @return
		 */
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
