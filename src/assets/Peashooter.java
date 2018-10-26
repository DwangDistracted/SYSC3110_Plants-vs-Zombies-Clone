package assets;

/**
 * The Peashooter Class is used to initialize a plant type having the power to shoot peas 
 * at the zombie to reduce its health.
 * 
 *@author Tanisha 
 */

public class Peashooter extends Plant{
	public Peashooter()	{
		super(2,2);
	}
	
	public String toString() {
		return "P";
	}

}
