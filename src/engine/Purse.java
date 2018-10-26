package engine;

/**
 * Keeps track of player resources and handles expenditure. Created per Game Instance.
 * @author david
 *
 */
public class Purse 
{
	//The amount of resoures
	private int points = 0;
	
	/**
	 * @param lvlInit The Initial Resources a player has in a level
	 */
	public Purse(int lvlInit) {
		this.points = lvlInit;
	}
	
	/**
	 * @return The Amount of Resources a Player has
	 */
	public int getPoints() {
		return points;
	}
	
	/**
	 * Add points to the player's purse 
	 * @param points - the amount to be added
	 */
	public void addPoints(int points) {
		this.points += points;
	}
	
	/**
	 * Spend Points from the player's purse
	 * @param points - the amount that a player wants to spend
	 * @return True if transaction was successful, otherwise false
	 */
	public boolean spendPoints(int points) {
		if(canSpend(points))
		{
			this.points -= points;
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if the player can spend a given amount of resources
	 * @param points the amount of resources
	 * @return true is yes, false otherwise
	 */
	public boolean canSpend(int points) {
		return points <= this.points;
	}
}