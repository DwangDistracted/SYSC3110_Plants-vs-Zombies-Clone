package assets;

/**
 * The Flower class is used for initializing a sunflower that provides points to the user
 * to purchase more plants
 * 
 *@author Tanisha 
 */

public class Flower extends Plant{
	private int points;
	private static final int cost = 25;
	
	public Flower()	{
		super(2,0);
		this.points = 25;
	}
	
	public int getPoints() {
		return this.points;
	}
	
	public int getCost() {
		return this.cost;
	}
	
	public String toString() {
		return "F";
	}
}
