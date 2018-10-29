package assets;

/**
 * The Flower class is used for initializing a sunflower that provides points to the user
 * to purchase more plants
 * 
 *@author Tanisha 
 */

public class Flower extends Plant{
	private static final int COST = 25;
	//The Amount of Points that are added per turn per sunflower
	private static final int POINTS = 10; //points that a sunflower provides a player to buy other plants
	
	public Flower()	{
		super(2,0, COST);
	}
	
	/**
	 * returns points of a sunflower
	 * @return the points of a sunflower  
	 */
	public static int getPoints() {
		return POINTS;
	}
	
	/**
	 * returns name of the sunflower
	 * @return name of the sunflower
	 */
	public String toString() {
		return "F";
	}
}
