package assets;

public class Exploding_Zombie extends Zombie {

	private static final int DEFAULT_SPEED = 1;
	private static final int DEFAULT_POWER = 10;
	private static final int DEFAULT_HP = 4;
	private static final ZombieTypes ZOMBIE_TYPE = ZombieTypes.EXP_ZOMBIE;
	
	public Exploding_Zombie()	{
		super(DEFAULT_SPEED, DEFAULT_POWER, DEFAULT_HP);
	}
	
	/**
	 * returns the name of regular type zombie
	 */
	public String toString() {
		return "XZ";	
	}

	public ZombieTypes getZombieType() {
		return ZOMBIE_TYPE;
	}

	@Override
	public int getDefaultSpeed() {
		return DEFAULT_SPEED;
	} 
	
	

}
