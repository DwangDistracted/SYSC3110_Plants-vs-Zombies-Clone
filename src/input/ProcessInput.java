package input;
import assets.Flower;
import assets.Peashooter;
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
	
	public boolean processCommand(Command command) //return false if cmd is processCommand is unsuccessful
	 {
	        if(command.isUnknown())
           {
	            System.out.println("I don't know what you mean...");
	            return false;
	        }
	        
	        String commandWord = command.getCommandWord();
	        if (commandWord.equals("help")) 
	        {
	            printHelp();
	            return true;
	        }
	        if (commandWord.equals("load") && command.getSecondWord() != null)
	        {
	            System.out.println("This feature is not avaliable yet");
	            return true;
	        }
	        else if (commandWord.equals("play")) 
	        {
	        	if(command.getSecondWord() != null)
	        	{
	        		game.play(Integer.valueOf(command.getSecondWord()));  //where commandSecondWord is the level selector
	        	}
	        	
	        	game.play(null);
	            
	            return true;
	        }
	        else if (commandWord.equals("quit")) 
	        {
	           game.setFinished();
	           System.out.println("User quit the game");
	           return true;
	        }
	        else if (commandWord.equals("pass"))
	        {
	        	return true;
	        }
	        
	        
	        
	        //======================================================== placing units commands
	        
	        else if(command.getCommandWord().equals("place"))
	        {
	        	if(command.getThirdWord() == null || command.getFourthWord() == null)             //restriction 1
	            {
		            System.out.println("I don't know what you mean...you forgot to place coordinates");
		            return false;
		        }
		        else if(!inRange(Integer.valueOf(command.getThirdWord()), Integer.valueOf(command.getFourthWord())))  //restriction 2  see if the coordinates are in range
		        {
		        	 System.out.println("The inputted coordinates are out of range");
			         return false;
		        }
	        	
	        	
	        	if (command.getSecondWord().equals("sf") && purse.redeemPoints(10) && lvl.isValidUnit("sf"))
	 	        {
	        			 
	 	        	System.out.println("Sunflower was placed on tile " + command.getSecondWord() + ", " + command.getThirdWord());
	 		        	
	 		        Flower sf = new Flower();
	 		        //sf.setPlacement(Integer.valueOf(command.getSecondWord()), Integer.valueOf(command.getThirdWord())); //set unit coordinates
	 		       
	 		        board.addUnit(sf, Integer.valueOf(command.getSecondWord()), Integer.valueOf(command.getThirdWord()));
	 		        return true;
	 	        }
	 	        else if (command.getSecondWord().equals("ps") && purse.redeemPoints(15) && lvl.isValidUnit("ps"))
	 	        {
	 	        	Peashooter ps = new Peashooter();
	 	        	//ps.setPlacement(Integer.valueOf(command.getSecondWord()), Integer.valueOf(command.getThirdWord())); //set unit coordinates
	 	        	
	 	        	board.addUnit(ps, Integer.valueOf(command.getSecondWord()), Integer.valueOf(command.getThirdWord()));
	 	        	return true;
	 	        }
	        }
	       
	       return false;
	 }
	 
	
	 public boolean inRange(int row, int column)  //determine if play input is within the gameboard bounds
	 {
		if(row <= board.getRow() - 1 && row >= 0 && column < board.getColumn() - 1 && column >= 0)  // board.getRow and column was subtracted by one due to arrays
			return true;
			
		return false;
	 }
	 
	 
	 public void printHelp()
	 {
		 System.out.println("Help message: to place a unit Type 'unitName (row #) (column #)'\n"
		 	+ "The names for the units at your disposal: sunflower - sf , peashooter - ps , regular zombie - rz"
		 	+ "\n note: row & column numbers start at coordinate 0");
	 }

}
