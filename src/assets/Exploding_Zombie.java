package assets;

public class Exploding_Zombie extends Zombie {

	private static final int DEFAULT_SPEED = SPEED_LOW;
	private static final int DEFAULT_POWER = ATTACK_LOW; //this value is irrelevant. The zombie will instantly kill plant
	private static final int DEFAULT_HP = HEALTH_LOW;
	private static final ZombieTypes ZOMBIE_TYPE = ZombieTypes.EXP_ZOMBIE;
	
	public Exploding_Zombie()	{
		super(DEFAULT_SPEED, DEFAULT_POWER, DEFAULT_HP);
	}
	
	@Override
	public String toString() {
		return "Exploding Zombie";	
	}

	public ZombieTypes getZombieType() {
		return ZOMBIE_TYPE;
	}

	@Override
	public int getDefaultSpeed() {
		return DEFAULT_SPEED;
	} 

}
