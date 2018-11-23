package assets;

public class YetiZombie extends Zombie {

	private static final int DEFAULT_SPEED = SPEED_LOW;
	private static final int DEFAULT_POWER = HEALTH_HIGH;
	private static final int DEFAULT_HP = HEALTH_HIGH;
	private static final ZombieTypes ZOMBIE_TYPE = ZombieTypes.YETI_ZOMBIE;

	public YetiZombie() {
		super(DEFAULT_SPEED, DEFAULT_POWER, DEFAULT_HP);
	}
	
	@Override
	public ZombieTypes getZombieType() {

		return ZOMBIE_TYPE;
	}
	
	@Override 
	public String toString() {
		
		return "Yeti Zombie";
	}

	@Override
	public int getDefaultSpeed() {

		return DEFAULT_SPEED;
	}
}
