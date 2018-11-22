package assets;

public class RushZombie extends Zombie {
	private static final int DEFAULT_SPEED = SPEED_MEDIUM;
	private static final int DEFAULT_POWER = ATTACK_HIGH;
	private static final int DEFAULT_HP = HEALTH_MEDIUM;
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
	public void restoreSpeed() {
		
		setSpeed(DEFAULT_SPEED);
	}

	@Override
	public int getDefaultSpeed() {

		return DEFAULT_SPEED;
	}
}
