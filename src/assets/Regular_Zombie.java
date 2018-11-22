package assets;

/**
 * The Regular_Zombie class is used to create a basic type of zombie
 * 
 *@author Tanisha 
 */

public class Regular_Zombie extends Zombie{
	private static final int DEFAULT_SPEED = SPEED_LOW;
	private static final int DEFAULT_POWER = ATTACK_LOW;
	private static final int DEFAULT_HP = HEALTH_LOW;
	private static final ZombieTypes ZOMBIE_TYPE = ZombieTypes.REG_ZOMBIE;
	
	public Regular_Zombie()	{
		super(DEFAULT_SPEED, DEFAULT_POWER, DEFAULT_HP);
	}
	
	/**
	 * returns the name of regular type zombie
	 */
	public String toString() {
		return "Meatbag Zombie";
	}

	public ZombieTypes getZombieType() {
		return ZOMBIE_TYPE;
	}

	@Override
	public int getDefaultSpeed() {

		return DEFAULT_SPEED;
	}
}
