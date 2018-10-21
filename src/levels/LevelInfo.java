package Level;

import java.util.Random;

import Input.CommandWords;

public class LevelInfo {
	
	//private HashMap<Integer, String> level; 
	private int currentLevel;
	private int zombieCap;
	private CommandWords cmdWord;
	private int row;
	

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
	

}
