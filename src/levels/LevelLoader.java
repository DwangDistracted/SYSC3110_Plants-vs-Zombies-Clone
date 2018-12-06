package levels;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

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
		sampleZombies.put(ZombieTypes.JUK_ZOMBIE, 5);
		sampleZombies.put(ZombieTypes.AIR_ZOMBIE, 5);
		sampleZombies.put(ZombieTypes.REG_ZOMBIE, 30);		
		sampleZombies.put(ZombieTypes.RUSH_ZOMBIE, 5);		
		sampleZombies.put(ZombieTypes.SPRINT_ZOMBIE, 5);
		sampleZombies.put(ZombieTypes.EXP_ZOMBIE, 20);
		sampleZombies.put(ZombieTypes.YETI_ZOMBIE, 5);
		sampleZombies.put(ZombieTypes.TANK_ZOMBIE, 5);
		sampleZombies.put(ZombieTypes.ENRAGED_ZOMBIE, 5);
    
		ArrayList<PlantTypes> samplePlants = new ArrayList<>();
		samplePlants.add(PlantTypes.PEASHOOTER);
		samplePlants.add(PlantTypes.REPEATER_PEASHOOTER);
		samplePlants.add(PlantTypes.SUNFLOWER);	
		samplePlants.add(PlantTypes.TWIN_FLOWER);
		samplePlants.add(PlantTypes.TALLNUT);	
		samplePlants.add(PlantTypes.WALLNUT);
		samplePlants.add(PlantTypes.POTATOMINE);
		samplePlants.add(PlantTypes.AIRMONKEY);
		samplePlants.add(PlantTypes.KERNELPULT);
		samplePlants.add(PlantTypes.MELONPULT);
		samplePlants.add(PlantTypes.SNOWSHOOTER);
		samplePlants.add(PlantTypes.JALAPENO);
    
		LevelFactory f = getLevelFactory()
										.setName("Sample")
										.setGridSize(8,8)
										.setInitResources(200)
										.setResPerTurn(25)
										.addAllAllowedPlants(samplePlants)
										.addAllZombies(sampleZombies);
		levels.add(f.toLevelInfo());
		f.toXML();
		LOG.debug("Added Sample Level");
	}
	
	/**
	 * Deserializes all LevelInfo from SER files in the levels directory into the game.
	 */
	private static void deserializeLevels () {
		try {
			JAXBContext jc = JAXBContext.newInstance(LevelInfo.class); //uses JaxB
			Unmarshaller unM = jc.createUnmarshaller();
			
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

						LevelInfo lvl = (LevelInfo) unM.unmarshal(listOfFiles[i]);
						levels.add(lvl);
					}
				}
				LOG.debug("Finished Deserialization");
			}
		} catch (IOException io) {
			LOG.warn("Failed to deserialize levels - Missing Levels Directory. The Game will generate the Files Needed.");
		} catch (JAXBException e) {
			LOG.error("Failed to deserialize levels - JaxB Error");
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
		}
	}
	
	public static void refreshLevelLists() {
		levels.clear();
		init();
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
	 * Factory Class for building LevelInfos. Contains some default values.
	 * @author david
	 *
	 */
	public class LevelFactory {
		//The level's name
		private String name = "default";
		
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
		 * adds a list of plants to the allowed list for the player
		 * @param type
		 * @return
		 */
		public LevelFactory addAllAllowedPlants(List<PlantTypes> types) {
			allowedPlants.addAll(types);
			return this;
		}
		
		/** 
		 * Adds a type of zombies to the level along with the number of that type that will appear
		 * @param type
		 * @param number
		 * @return
		 */
		public LevelFactory addZombies(ZombieTypes type, int number) {
			zombies.put(type, number);
			return this;
		}

		/** 
		 * Adds a map of zombies and their number to the level
		 * @param type
		 * @param number
		 * @return
		 */
		public LevelFactory addAllZombies(Map<ZombieTypes,Integer> types) {
			zombies.putAll(types);
			return this;
		}
		/**
		 * builds a levelinfo object
		 * @return
		 */
		public LevelInfo toLevelInfo() {
			if (allowedPlants.isEmpty()) allowedPlants.add(PlantTypes.PEASHOOTER); //if the user has not specified add a peashooter to prevent NPE when loading level
			if (zombies.isEmpty()) zombies.put(ZombieTypes.REG_ZOMBIE, 1); //if the use has not added an engine. do the same as above. 
			return new LevelInfo(name, column, row, resPerTurn, initResources, zombies, allowedPlants);
		}
		
		/**
		 * Saves the constructed levelInfo object as an xml file
		 */
		public void toXML() {
			File fOut = new File("levels/" + this.name +  "-" + System.currentTimeMillis() + ".xml");
			fOut.getParentFile().mkdirs();
			try (FileOutputStream fileOut = new FileOutputStream(fOut)) {

		        JAXBContext jc = JAXBContext.newInstance(LevelInfo.class);
		        Marshaller m = jc.createMarshaller();
		        m.marshal(toLevelInfo(), fOut);
		        
				fileOut.close();
				
				LOG.debug("Level has been serialized");
			} catch (IOException e) {
				LOG.error("Failed to Serialize Level - IO Exception");
				e.printStackTrace();
			} catch (JAXBException e) {
				LOG.error("Failed to Serialize Level - JaxB Exception");
				e.printStackTrace();
			}
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
