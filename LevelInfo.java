import java.util.*;

public class LevelInfo {
	
	//private HashMap<Integer, String> level; 
	private int currentLevel;
	private int zombieCap;
	private Game game;    //******************
	private CommandWords cmdWord;
	private int row;
	

	public LevelInfo()
	{
		game = new Game();
		currentLevel = 1;
		//level = new HashMap<Integer, String>();
		//level.put(1, "false");
		//level.put(1, "false");
		
	}
	public int[] beginLevel()
	{

		if(currentLevel == 1)
		{
			//lvl1Info();
			System.out.println("Welcome to level 1! \nThe units at your disposal are the Peashooter and Sunflower."
			 		+ "\nEnemey units include: Regular Zombie of 10 units...\nType 'Help' for possible commands");
			
			zombieCap = 5;
			row = 1;
			int[] boardSize = {1, 8};
			return boardSize;
			//game.initBoard(1, 8);
			
			//computeWave();
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
	
	/*
	public boolean isLvlCompleted(Integer levelNum)
	{
		return level.get(levelNum) == "true";
	}
	
	public void setLvlCompleted(Integer levelNum)
	{
		level.replace(levelNum, "true");
	}
	*/
	/*
	public void lvl1Info()
	{
		System.out.println("Welcome to level 1! \n The units at your disposal are the Peashooter, Sunflower"
			 		+ " and Regular Zombie...\n Type 'Help' for possible commands");
			 
	    Board b = new Board(1,8);
	    b.displayBoard();
	    computeWave(1);
	}
	
	
	public void lvl2Info()
	{
		System.out.println("Under construction");
	}
	*/
	
	public int computeWave() //could also make this method pick how much zombies to spawn
	{
		Random rand = new Random();
		int rowNumber;
		
		rowNumber = rand.nextInt(row); //determines which row the zombie will go down
		
		zombieCap--;
		
		return rowNumber;
	}
	

}
