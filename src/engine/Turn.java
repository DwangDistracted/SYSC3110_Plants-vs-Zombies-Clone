package engine;


import assets.*;
import levels.*;
import view.*;

public class Turn
{
	private Combat combat;
	private Game game;
	private LevelInfo lvl;
	
	public Turn(Game game, LevelInfo lvl, Board board)
	{
		combat = new Combat(board);
		this.game = game;
		this.lvl = lvl;
	}
	/**
	 * compute attacks, Move zombies, create zombies
	 * 
	 * note at level 1: boardSize[1] = 8 & boardSize[0] = 1    -- however rowNumber will always = 0
	 */
	public void zombieTurn(Board board, int[] boardSize)
	{
		if(!board.getExperimental().isEmpty())
		{
			combat.computePlantAttacks();
			combat.computeZombieAttacks();
		}
		
		
		//move zombies
		if(game.getTurnsLeft() <= 9)   
		{
			for(Object key : board.getExperimental().keySet())
			{
				if(key instanceof Zombie)
				{
					int[] coordinates = board.getExperimental().get(key);
					int[] newCoordinates = board.moveUnit(((Zombie) key).getSpeed(), coordinates[0], coordinates[1]);
					if(newCoordinates == null) //if the zombies get to the end
					{
						game.setFinished();
						return;
					}
					board.getExperimental().replace(key, coordinates, newCoordinates);
				}
			}
		}
		
		
		//create zombies
		int rowNumber = lvl.computeWave();
		for(int i = 0; i < 1; i++)  //spawn zombies   1 can replaced with a variable later
		{
			Regular_Zombie zombie = new Regular_Zombie();
			board.addUnit(zombie, rowNumber, boardSize[1] - 1);
			//zombie.setPlacement(rowNumber, boardSize[1] - 1);
		}
		
	}
	

	/**
	 * prompt the next input and display board
	 * @param board 
	 * @param boardSize
	 */
	public void playerTurn(Board board, int[] boardSize)
	{
		board.displayBoard();
		System.out.println("\nPoints avaliable: " + game.getPoints() + "\n");
		game.promptInput();
		game.accumulatePoints();
		
	}

}
