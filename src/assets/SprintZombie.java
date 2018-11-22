package assets;

public class SprintZombie extends Zombie {
	private static final int DEFAULT_SPEED = 3;
	private static final int DEFAULT_POWER = 1;
	private static final int DEFAULT_HP = 4;
	private static final ZombieTypes ZOMBIE_TYPE = ZombieTypes.SPRINT_ZOMBIE;
	
	public SprintZombie()	{
		super(DEFAULT_SPEED, DEFAULT_POWER, DEFAULT_HP);
	}
	
	/**
	 * returns the name of regular type zombie
	 */
	public String toString() {
		return "Cheetah Zombie";	
	}

	public ZombieTypes getZombieType() {
		return ZOMBIE_TYPE;
	} 
}
