package assets;

public class SprintZombie extends Zombie {
	private static final int DEFAULT_SPEED = SPEED_HIGH;
	private static final int DEFAULT_POWER = ATTACK_LOW;
	private static final int DEFAULT_HP = HEALTH_LOW;
	private static final ZombieTypes ZOMBIE_TYPE = ZombieTypes.SPRINT_ZOMBIE;
	
	public SprintZombie()	{
		super(DEFAULT_SPEED, DEFAULT_POWER, DEFAULT_HP);
	}
	
	/**
	 * returns the name of regular type zombie
	 */
	public String toString() {
		return "Sprint Zombie";
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
