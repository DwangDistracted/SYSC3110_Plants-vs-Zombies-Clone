package assets;

/**
 * The Peashooter Class is used to initialize a plant type having the power to shoot peas 
 * at the zombie to reduce its health.
 * 
 *@author Tanisha 
 */

public class Peashooter extends Plant{
	private static final int COST = 50;
	
	public Peashooter()	{
		super(2,2,COST);
	}
	
	public String toString() {
		return "P";
	}
}
