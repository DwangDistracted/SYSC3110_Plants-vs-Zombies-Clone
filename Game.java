
public class Game {
	
	
	private Board board;
	private LevelInfo lvl;
	private Parser parser;
	
	private boolean finished = false;
	private int turnsLeft;
	private int points;
	
	public static void Salutation()
	{
		System.out.println("---- Plants VS Zombies ----\n Commands: Help, Play, Load \n\n");
	}
	
	
	 
	 public void printHelp()
	 {
		 System.out.println("Help message: to place a unit Type 'unitName (row #) (column #)'\n"
		 	+ "The names for the units at your disposal: sunflower - sf , peashooter - ps , regular zombie - rz");
	 }
	 
	 public boolean processCommand(Command command) //return false if cmd is processCommand is unsuccessful
	 {
	        if(command.isUnknown() || !inRange(Integer.valueOf(command.getSecondWord()), Integer.valueOf(command.getThirdWord())))
            {
	            System.out.println("I don't know what you mean...");
	            return false;
	        }

	        String commandWord = command.getCommandWord();
	        if (commandWord.equals("help")) {
	            printHelp();
	            return true;
	        }
	        else if (commandWord.equals("play")) {
	            play();
	            return true;
	        }
	        else if (commandWord.equals("quit")) {
	           finished = true;
	           System.out.println("User quit the game");
	           return true;
	        }
	        else if (commandWord.equals("sf"))
	        {
	        	System.out.println("Sunflower was placed on tile " + command.getSecondWord() + ", " + command.getThirdWord());
		        	
		        Flower sf = new Flower();
		        sf.setPlacement(Integer.valueOf(command.getSecondWord()), Integer.valueOf(command.getThirdWord())); //set unit coordinates
		       
		        board.addUnit(sf, Integer.valueOf(command.getSecondWord()), Integer.valueOf(command.getThirdWord()));
		       
		        return true;
	        }
	        else if (commandWord.equals("ps"))
	        {
	        	
	        	Peashooter ps = new Peashooter();
	        	ps.setPlacement(Integer.valueOf(command.getSecondWord()), Integer.valueOf(command.getThirdWord())); //set unit coordinates
	        	
	        	board.addUnit(ps, Integer.valueOf(command.getSecondWord()), Integer.valueOf(command.getThirdWord()));
	        	return true;
	        }
	     
	        return false;
	 }
	 

	 
	 private void play()
	 { 
		points = 10;
		Salutation();
		
		turnsLeft = 10;
		lvl = new LevelInfo();
		
		int[] boardSize = lvl.beginLevel();  //returns level board size and initializes level details
		board = new Board(boardSize[0], boardSize[1]); //create board with the appropriate size
		
		initTurn(board, boardSize);
	 }

	public void initTurn(Board board, int[] boardSize)
	{
		parser = new Parser(); 
		while(turnsLeft > 0 && finished == false)
		{
			/*
			if(turnsLeft <= 9)   //I planned to move the badGuy units from here. IDK how I am going to to do that
			{
				for(Regular_Zombie zombie : board.getBadGuysList())
				{
					board.moveUnit();
				}
			}
			*/
			
			int rowNumber = lvl.computeWave();
			for(int i = 0; i < 1; i++)  //spawn zombies   1 can replaced with a variable later
			{
				System.out.println("RowNumber: " + rowNumber + "  boardSize[1]" + boardSize[1]);
				Regular_Zombie zombie = new Regular_Zombie();
				board.addUnit(zombie, rowNumber, boardSize[1] - 1);
				zombie.setPlacement(rowNumber, boardSize[1] - 1);
			}
			
				
			
			board.displayBoard();
			System.out.println("\nPoints avaliable: " + points + "\n");
			
			boolean cmdSuccess = false;
			while(cmdSuccess == false)
			{
				Command command = parser.getCommand();
				cmdSuccess = processCommand(command);
			}
				 
			accumulatePoints();
			Computeattacks();
			//MoveAllUnits();
				 
			turnsLeft--;
		}
		
		System.out.println("Game Over");
	}
		 
	public void accumulatePoints()
	{
		points += 10; //default point accumulation
		points += 10 * board.getNumberOfSF();
	}
		 
	public int getPoints()
	{
		return this.points;
	}
		 	
	public static void Computeattacks()
	{
			
	}
	
	public boolean inRange(int row, int column)  //determine if play input is within the gameboard bounds
	{
		if(row <= board.getRow() - 1 && row >= 0 && column < board.getColumn() - 1 && column >= 0)  // board.getRow and column was subtracted by one due to arrays
			return true;
		
		return false;
	}
		
		
	public static void main(String[] args) {
		Game game = new Game();
		game.play();
	}

}
