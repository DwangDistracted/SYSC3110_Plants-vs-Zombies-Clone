package engine;
import java.util.ArrayList;
import java.util.Map.Entry;

import input.*;
import view.*;
import levels.*;
import assets.*;

public class Game {
	
	
	private Board board;
	private LevelInfo lvl;
	private Parser parser;
	
	private boolean finished = false;
	private int turnsLeft;
	private int points;
	private int cmdlevel;      //restrict some commands based on the current level
	
	
	public Game()  //singleton
	{
		cmdlevel = 0;
		Salutation();
		parser = new Parser(); 
		promptInput();
		
	}
	
	public void Salutation()
	{
		System.out.println("---- Plants VS Zombies ----\n Commands: Help, Play, Load and quit \n\n");
	}
	
	
	public void promptInput()
	{
		boolean cmdSuccess = false;
		while(cmdSuccess == false)
		{
			Command command = parser.getCommand(); //prompt input
			cmdSuccess = processCommand(command);
		}
	}
	
	
	 
	 public void printHelp()
	 {
		 System.out.println("Help message: to place a unit Type 'unitName (row #) (column #)'\n"
		 	+ "The names for the units at your disposal: sunflower - sf , peashooter - ps , regular zombie - rz"
		 	+ "\n note: row & column numbers start at coordinate 0");
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
	        		play(Integer.valueOf(command.getSecondWord()));  //where commandSecondWord is the level selector
	        	}
	        	
	        	play(null);
	            
	            return true;
	        }
	        else if (commandWord.equals("quit")) 
	        {
	           finished = true;
	           System.out.println("User quit the game");
	           return true;
	        }
	        else if (commandWord.equals("pass"))
	        {
	        	return true;
	        }
	        
	        
	        
	        //======================================================== placing units commands
	        
	        
	        if(cmdlevel >= 1) //restrict these commands for only when level >= 1
	        {
	        	if(command.getSecondWord() == null || command.getThirdWord() == null)             //restriction 1
	            {
		            System.out.println("I don't know what you mean...you forgot to place coordinates");
		            return false;
		        }
		        else if(!inRange(Integer.valueOf(command.getSecondWord()), Integer.valueOf(command.getThirdWord())))  //restriction 2
		        {
		        	 System.out.println("The inputted coordinates are out of range");
			         return false;
		        }
	        	
	        	
	        	if (commandWord.equals("sf") && redeemPoints(10) == true)
	 	        {
	        			 
	 	        	System.out.println("Sunflower was placed on tile " + command.getSecondWord() + ", " + command.getThirdWord());
	 		        	
	 		        Flower sf = new Flower();
	 		        //sf.setPlacement(Integer.valueOf(command.getSecondWord()), Integer.valueOf(command.getThirdWord())); //set unit coordinates
	 		       
	 		        board.addUnit(sf, Integer.valueOf(command.getSecondWord()), Integer.valueOf(command.getThirdWord()));
	 		        return true;
	 	        }
	 	        else if (commandWord.equals("ps") && redeemPoints(15) == true)
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
	 
	 /**
	  * 
	  * @param string - level selector
	  */
	 private void play(Integer level)
	 { 
		points = 10;       
		turnsLeft = 10;
		
		
		if(level == null)   //if no specific level was selected, start at level 1
		{
			level = 1;
		}
		this.cmdlevel = level;
		
		lvl = new LevelInfo(level); 
		int[] boardSize = lvl.beginLevel();  //returns level board size and initializes level details. returns null if there is no more levels
		
		if(boardSize == null)  //if true, it indicates there is no more levels to complete
		{
			finished = true;
			return;
		}
		
		board = new Board(boardSize[0], boardSize[1]); //create board with the appropriate size
		Turn turn = new Turn(this,lvl,board);
		
		while(turnsLeft > 0 && finished == false)
		{
			turn.zombieTurn(board,boardSize);
			if(finished == true)  // if zombies gets to the end
			{
				System.out.println("Game Over");
				return;
			}
			turn.playerTurn(board,boardSize);
			turnsLeft--;
		}
		
		System.out.println("Level completed");
		lvl.incremenetLevel();
		play(lvl.getCurrentLevel());  //go again!
	 }
	 
	 
		/**
		 * accumulate points for given turn	 
		 */
		public void accumulatePoints()
		{
			points += 10; //default point accumulation
			points += 10 * board.getNumberOfSF(); //extra 10 points per sunflower
		}
		
		/**
		 * 
		 * @param points - the amount a unit will cost
		 * @return a boolean value - True if transaction was successful, otherwise false
		 */
		public boolean redeemPoints(int points)
		{
			if(points <= this.points)
			{
				this.points -= points;
				return true;
			}
			
			System.out.println("That unit is too costly! You have " + this.points + " points but the "
						+ "item costs " + points);
			return false;
		}
		
		/**
		 * this method is pretty useless
		 * @return
		 */
		public int getPoints()
		{
			return this.points;
		}
		
		public int getTurnsLeft()
		{
			return turnsLeft;
		}
		
		public void setFinished()
		{
			this.finished = true;
		}
}
