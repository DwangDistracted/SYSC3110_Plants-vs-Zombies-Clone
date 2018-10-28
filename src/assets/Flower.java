package assets;

/**
 * The Flower class is used for initializing a sunflower that provides points to the user
 * to purchase more plants
 * 
 *@author Tanisha 
 */

public class Flower extends Plant{
	private static final int cost = 25;
	//The Amount of Points that are added per turn per sunflower
	private static int points = 10;
	
	public Flower()	{
		super(2,0, cost);
	}
	
	public static int getPoints() {
		return points;
	}
	
	public String toString() {
		return "F";
	}
}
