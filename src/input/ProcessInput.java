package input;
import java.awt.List;
import java.util.HashMap;

import assets.Flower;
import assets.Peashooter;
import assets.Zombie;
import engine.Game;
import engine.Purse;
import levels.LevelInfo;
import view.Board;

public class ProcessInput 
{
	private LevelInfo lvl;
	private Game game;
	private Board board;
	private Purse purse;
	
	public ProcessInput(LevelInfo lvl, Game game, Board board, Purse purse)
	{
		this.lvl = lvl;
		this.game = game;
		this.board = board;
		this.purse = purse;
	}
	
	
	/**
	 * Processes the user input
	 * @param command - the user input
	 * @return true if the input was valid, false otherwise
	 */
	public boolean processCommand(Command command) //return false if cmd is processCommand is unsuccessful
	 {
	        if(command.isUnknownWord(1))
           {
	            System.out.println("I don't know what you mean...");
	            return false;
	        }
	        
	        String commandWord = command.getWord(1);
	        
	        if (commandWord.equalsIgnoreCase("help")) 
	        {
	            printHelp();
	            return true;
	        }
	        if (commandWord.equalsIgnoreCase("load") && command.getWord(2) != null)
	        {
	            System.out.println("This feature is not avaliable yet");
	            return true;
	        }
	        else if (commandWord.equalsIgnoreCase("play")) 
	        {
	        	if(command.getWord(2) != null)
	        	{
	        		game.play(Integer.valueOf(command.getWord(2)));  //where commandSecondWord is the level selector
	        	}
	        	
	        	game.play(null);
	            
	            return true;
	        }
	        else if (commandWord.equalsIgnoreCase("quit")) 
	        {
	           game.setFinished();
	           System.out.println("User quit the game");
	           return true;
	        }
	        else if (commandWord.equalsIgnoreCase("pass"))
	        {
	        	return true;
	        }
	        
	        
	        else if(commandWord.equalsIgnoreCase("place"))
	        {
	        	if(command.getWord(3) == null || command.getWord(4) == null)             //restriction 1
	            {
		            System.out.println("Please input your command with coordinates");
		            return false;
		        }
		        else if(!inRange(Integer.valueOf(command.getWord(3)), Integer.valueOf(command.getWord(4))))  //restriction 2  see if the coordinates are in range
		        {
		        	 System.out.println("The inputted coordinates are out of range");
			         return false;
		        }
	        	
	        	
	        	if (command.getWord(2).equalsIgnoreCase("sf") && purse.redeemPoints(10) && lvl.isValidUnit("sf"))
	 	        {
	        			 
	 	        	System.out.println("Sunflower was placed on tile " + command.getWord(3) + ", " + command.getWord(4));
	 		        	
	 		        Flower sf = new Flower();
	 		       
	 		        board.addUnit(sf, Integer.valueOf(command.getWord(3)), Integer.valueOf(command.getWord(4)));
	 		        return true;
	 	        }
	 	        else if (command.getWord(2).equalsIgnoreCase("ps") && purse.redeemPoints(15) && lvl.isValidUnit("ps"))
	 	        {
	 	        	Peashooter ps = new Peashooter();
	 	        	
	 	        	board.addUnit(ps, Integer.valueOf(command.getWord(3)), Integer.valueOf(command.getWord(4)));
	 	        	return true;
	 	        }
	        }
	       
	       return false;
	 }
	 
	/**
	 * Determines if the user inputed commands is within the grid boundaries.
	 * @param row - coordinate
	 * @param column - coordinate
	 * @return true if the parameters are within the grid boundaries, and false otherwise.
	 */
	 public boolean inRange(int row, int column)  
	 {
		if(row <= lvl.getGridY() - 1 && row >= 0 && column < lvl.getGridX() - 1 && column >= 0)  // board.getRow and column was subtracted by one due to arrays
			return true;
			
		return false;
	 }
	 
	 /**
	  * Displays a help message informing user on the type of zombies they will face within the level,
	  * the plants that are at that disposal and how to use the command line.
	  */
	 public void printHelp()
	 {
		 HashMap<Zombie,Integer> allowedZombies = lvl.getZombies();
		 List<Plant> allowedPlants = lvl.getAllowedPlants();
		 
		 System.out.println("Help message: to place a unit Type 'Place <unitName> <row #> <column #>'\n"
		 	+ "\n note: row & column numbers start at coordinate 0");
		 
		 System.out.println("The plants commands at your disposal are: ");
		 for(Plant p : allowedPlants)
		 {
			 System.out.println(z.toString() + " | ");
		 }
		 System.out.println();
		 
		 System.out.println("The zombies that you will face against are: ");
		 for(Zombie z : allowedZombies.keySet())
		 {
			 System.out.println(z.toString() + " | ");
		 }
	 }

}
