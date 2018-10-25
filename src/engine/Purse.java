package engine;

public class Purse 
{
	private int points = 0;
	
	public Purse(int lvlInit) {
		this.points = lvlInit;
	}
	
	public int getPoints() {
		return points;
	}
	
	/**
	 * add points to the player's purse 
	 * @param points - the amount to be added
	 */
	public void addPoints(int points) {
		this.points += points;
	}
	
	/**
	 * 
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
	
	public boolean canSpend(int points) {
		return points <= this.points;
	}
}