package levels;

import java.util.Dictionary;
import java.util.List;

public class LevelInfo {
	//The level's name
	private String name;
	//The Rating of the Level's Difficulty
	private short levelRating;
	//The Type of this should be adjusted
	private String nextLevel;
	
	//The Size of the Game Grid for this level
	private short gridX;
	private short gridY;
	
	private Dictionary zombies;
	private List prohibitedPlants[];
}
