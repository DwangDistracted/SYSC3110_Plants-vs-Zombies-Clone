package assets;

/**
 * The Regular_Zombie class is used to create a basic type of zombie
 * 
 *@author Tanisha 
 */

public class Regular_Zombie extends Zombie{
	private static final int DEFAULT_SPEED = 1;
	private static final int DEFAULT_POWER = 1;
	private static final int DEFAULT_HP = 4;
	
	public Regular_Zombie()	{
		super(DEFAULT_SPEED, DEFAULT_POWER, DEFAULT_HP);
	}
	
	/**
	 * returns the name of regular type zombie
	 */
	public String toString() {
		return "RZ";	
	}
}
