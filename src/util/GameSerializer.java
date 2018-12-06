package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import engine.Game;

/**
 * Helper class for serializing Game. Used for saving game which
 * could be loaded. 
 * 
 * @author Derek Shao
 *
 */
public class GameSerializer {

	private static Logger LOG = new Logger("GameSerializer");

	private GameSerializer() {}
	
	// have a single instance of saved games to be loaded
	public static List<Game> savedGames = new ArrayList<Game>();
	
	/**
	 * Load all serialized games.
	 */
	public static void init() {
		deserialize();
	}
		
	/**
	 * Serialize a Game object.
	 * 
	 * @param game
	 */
	public static void serialize(Game game) {
		try {
			FileOutputStream fileOut = new FileOutputStream("saved/" + 
											game.getLevelInfo().getName() + "-" + 
											game.getTurns() + "-" + 
											System.currentTimeMillis() + 
											".ser");
			ObjectOutputStream outStream = new ObjectOutputStream(fileOut);
			outStream.writeObject(game);
			outStream.close();
			fileOut.close();
		} catch (IOException e) {
			LOG.error("Failed to Serialize Game - IO Exception");
			e.printStackTrace();
		}		
	}
	
	/**
	 * Deserialize all saved games. 
	 */
	public static void deserialize() {
		try {
			File saveFolder = new File("saved/");	
			saveFolder.mkdirs();
			File[] serializedGameFiles = saveFolder.listFiles();
			
			for (File gameFile : serializedGameFiles) {
				System.out.println(gameFile.getPath());
				FileInputStream fileIn = new FileInputStream(gameFile);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				Game game = (Game) in.readObject();
				game.reImplementTransientFields();
				savedGames.add(game);
				in.close();
				fileIn.close();
			}
		} catch(IOException e) {
			LOG.error("Failed to Deserialize Game - IO Exception");
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			LOG.error("Failed to Deserialize Game - Class Not Found Exception");
			e.printStackTrace();
		}
	}
}
