package assets;

public class RushZombie extends Zombie {
	private static final int DEFAULT_SPEED = SPEED_MEDIUM;
	private static final int DEFAULT_POWER = ATTACK_VERY_HIGH;
	private static final int DEFAULT_HP = HEALTH_LOW;
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

	@Override
	public int getDefaultSpeed() {

		return DEFAULT_SPEED;
	}
}
