package engine;

import view.Board;

public class Purse 
{
	private int points = 0;
	private Board board;
	
	
	public Purse(Board board)
	{
		this.board = board;
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

}
