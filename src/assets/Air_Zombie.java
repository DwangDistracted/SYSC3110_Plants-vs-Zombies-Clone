package assets;

public class Air_Zombie extends Zombie {
	private static final int DEFAULT_SPEED = 1;
	private static final int DEFAULT_POWER = 1;
	private static final int DEFAULT_HP = 4;
	private static final ZombieTypes ZOMBIE_TYPE = ZombieTypes.AIR_ZOMBIE;
	
	public Air_Zombie()	{
		super(DEFAULT_SPEED, DEFAULT_POWER, DEFAULT_HP);
	}
	
	/**
	 * returns the name of regular type zombie
	 */
	public String toString() {
		return "AZ";	
	}

	public ZombieTypes getZombieType() {
		return ZOMBIE_TYPE;
	} 
}
