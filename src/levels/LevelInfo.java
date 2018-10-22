package levels;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import input.CommandWords;

public class LevelInfo {
	
	//private HashMap<Integer, String> level; 
	private int currentLevel;
	private int zombieCap;
	private int row;
	
	//private static final HashMap<Integer,Object> lvlZom = new HashMap<Integer, Object>();
	private static final String[][] lvlCmds = {{"pass","sf","ps"}};
	

	public LevelInfo(int level)
	{
		currentLevel = level;
	}
	
	
	public int[] beginLevel()
	{

		if(currentLevel == 1)
		{
			System.out.println("Welcome to level 1! \nThe units at your disposal are the Peashooter(ps) and Sunflower(sf)."
			 		+ "\nEnemey units include: Regular Zombie of 10 units...\nType 'Help' for possible commands");
			
			zombieCap = 5;
			row = 1;
			
			int[] boardSize = {1, 8};
			
			
			return boardSize;
		}
		else if(currentLevel == 2)
		{
			//lvl2Info();
			//cmdWord.add()
		}
		else
		{
			System.out.println("Game Over -> You Win");
		}
		
		return null;
	}
	
	
	public boolean isLvlCompleted(Integer levelNum)
	{
		return levelNum < currentLevel;
	}
	
	public void incremenetLevel()
	{
		currentLevel++;
	}
	
	public int getCurrentLevel()
	{
		return currentLevel;
	}
	
	
	public int computeWave() //could also make this method pick how much zombies to spawn
	{
		Random rand = new Random();
		int rowNumber;
		
		rowNumber = rand.nextInt(row); //determines which row the zombie will go down
		return rowNumber;
	}
	
	public Object getZombieType()
	{
		return currentLevel;
	}
	
	/**
	 * note: not implemented yet. to use this call this method in Game and then call parser.getCommandWords.addValidCommands(theString[])
	 * @return a list of new commands for the given level
	 */
	public String[] getLvlCmds()
	{
		return lvlCmds[currentLevel-1];
	}
	
	/**
	 * Determine if the unit is valid for a given level
	 * @param a unit command (ex ps or sf)
	 * @return boolean - true if the unit is valid, false otherwise
	 */
	public boolean isValidUnit(String unit)
	{
		int counter = 0; 
		while(counter < lvlCmds[currentLevel-1].length)
		{
			if(lvlCmds[currentLevel-1][counter].equals(unit))
			{
				return true;
			}
		}
		return false;
	}
	

}
