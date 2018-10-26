package input;
import java.awt.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import assets.Flower;
import assets.Peashooter;
import assets.Plant;
import assets.PlantTypes;
import assets.Zombie;
import assets.ZombieTypes;
import engine.Game;
import engine.Purse;
import levels.LevelInfo;
import view.Board;


/**
 * This class processes the input that the user types in during Player's turn.
 * Different aspects of the program will be called according to the user input.
 * 
 * @author Michael Patsula
 *
 */
public class ProcessInput 
{
	private LevelInfo lvl;
	private Game game;
	private Board board;
	private Purse purse;
	 private CommandWords validCommands;  // all valid cmd words
	
	public ProcessInput(LevelInfo lvl, Game game, Board board, Purse purse)
	{
		this.lvl = lvl;
		this.game = game;
		this.board = board;
		this.purse = purse;
		validCommands = new CommandWords();
	}
	
	
	/**
	 * Processes the user input
	 * @param command - the user input
	 * @return true if the input was valid, false otherwise
	 */
	public boolean processCommand(Command command) //return false if cmd is processCommand is unsuccessful
	 {
	        if(validCommands.isPrimaryCommand(command.getWord(1)) && validCommands.isValidUnit(command.getWord(2)))
            {
	            System.out.println("Invalid commands");
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
	        	if(command.getWord(2) != null)
	        	{
	        		game.load(Integer.valueOf(command.getWord(2)));  //where commandSecondWord is the level selector
	        	}
	        	
	        	game.load(null);
	            
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
	        	if(command.isUnknownWord(3) || command.isUnknownWord(4))      //restriction - check if word 3 and 4 is null
	            {
		            System.out.println("Please input your command with coordinates");
		            return false;
	            }
		        else  //restriction 2  see if the coordinates are in range of the board
		        {
		        	boolean inRange;
		        	try
		        	{
		        		inRange = inRange(Integer.valueOf(command.getWord(3)), Integer.valueOf(command.getWord(4)));  //check valid 3rd 4th
		        	}
		        	catch (Exception e)
		        	{
		        		System.out.println("exception");
		        		return false;
		        	}
		        	
		        	if(!inRange)
		        	{
		        		System.out.println("The inputted coordinates are out of range");
				         return false;
		        	}
		        }
	        	if (command.getWord(2).equalsIgnoreCase("sf") && purse.spendPoints(10) && validCommands.isValidUnit("sf"))
	 	        {
	        			 
	 	        	System.out.println("Sunflower was placed on tile " + command.getWord(3) + ", " + command.getWord(4));
	 		        	
	 		        Flower sf = new Flower();
	 		        board.addUnit(sf, Integer.valueOf(command.getWord(3)), Integer.valueOf(command.getWord(4)));
	 		        return true;
	 	        }
	 	        else if (command.getWord(2).equalsIgnoreCase("ps") && purse.spendPoints(15) && validCommands.isValidUnit("ps"))
	 	        {
	 	        	System.out.println("Peashooter was placed on tile " + command.getWord(3) + ", " + command.getWord(4));
	 	        	
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
		if(row <= lvl.getRows() - 1 && row >= 0 && column < lvl.getColumns() - 1 && column >= 0)  // board.getRow and column was subtracted by one due to arrays
			return true;
			
		return false;
	 }
	 
	 /**
	  * Displays a help message informing user on the type of zombies they will face within the level,
	  * the plants that are at that disposal and how to use the command line.
	  */
	 public void printHelp()
	 {
		 Map<ZombieTypes,Integer> allowedZombies = lvl.getZombies();
		 Set<PlantTypes> allowedPlants = lvl.getAllowedPlants();
		 
		 System.out.println("Help message: to place a unit Type 'Place <unitName> <row #> <column #>'\n"
		 	+ "\n note: row & column numbers start at coordinate 0");
		 
		 System.out.println("The plants commands at your disposal are: ");
		 for(PlantTypes p : allowedPlants)
		 {
			 System.out.println(p.toString() + " | ");
		 }
		 System.out.println();
		 
		 System.out.println("The zombies that you will face against are: ");
		 for(ZombieTypes z : allowedZombies.keySet())
		 {
			 System.out.println(z.toString() + " | ");
		 }
	 }

}
