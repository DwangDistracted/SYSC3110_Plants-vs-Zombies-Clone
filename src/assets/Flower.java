package assets;

/**
 * The Flower class is used for initializing a sunflower that provides points to the user
 * to purchase more plants
 * 
 *@author Tanisha 
 */

public class Flower extends Plant{
	private int points; //points that a sunflower provides a player to buy other plants
	
	public Flower()	{
		super(2,0, 25);
	}
	
	/**
	 * returns points of a sunflower
	 * @return
	 */
	public int getPoints() {
		return this.points;
	}
	
	/**
	 * returns name of the sunflower
	 */
	public String toString() {
		return "F";
	}
}
