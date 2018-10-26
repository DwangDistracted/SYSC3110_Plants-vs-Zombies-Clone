package engine;
/**
 * This class manages the user's points within the game
 * @author
 */
public class Purse 
{
	private int points = 0;
	
	/**
	 * Sets the user to an initial default number of points. 
	 * @param lvlInit
	 */
	public Purse(int lvlInit) 
	{
		this.points = lvlInit;
	}
	
	/**
	 * gets the amount of points the user has
	 * @return an int of the number of current points the user has 
	 */
	public int getPoints()
	{
		return points;
	}
	
	/**
	 * adds points to the player's purse 
	 * @param points - the amount to be added
	 */
	public void addPoints(int points)
	{
		this.points += points;
	}
	
	/**
	 * This method spends a given amount of points
	 * @param points - the amount that a player wants to spend
	 * @return True if transaction was successful, otherwise false
	 */
	public boolean spendPoints(int points) 
	{
		if(canSpend(points))
		{
			this.points -= points;
			return true;
		}
		return false;
	}
	
	/**
	 * Determines if the user has enough points to spend a 
	 * given amount of money
	 * @param points
	 * @return a boolean - true if the user has enough points, false otherwise
	 */
	public boolean canSpend(int points)
	{
		return points <= this.points;
	}
}