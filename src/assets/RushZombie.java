package assets;

public class RushZombie extends Zombie {
	private static final int DEFAULT_SPEED = 2;
	private static final int DEFAULT_POWER = 3;
	private static final int DEFAULT_HP = 4;
	private static final ZombieTypes ZOMBIE_TYPE = ZombieTypes.RUSH_ZOMBIE;
	
	public RushZombie()	{
		super(DEFAULT_SPEED, DEFAULT_POWER, DEFAULT_HP);
	}
	
	/**
	 * returns the name of regular type zombie
	 */
	public String toString() {
		return "Rush Zombie";	
	}

	public ZombieTypes getZombieType() {
		return ZOMBIE_TYPE;
	} 
}
