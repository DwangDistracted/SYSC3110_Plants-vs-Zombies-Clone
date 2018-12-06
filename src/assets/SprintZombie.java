package assets;

import java.io.Serializable;

/**
 * Class for Sprint Zombie type. Has extremely high 
 * speed and low amounts of hitpoints/attack.
 * 
 * @author David Wang
 *
 */
public class SprintZombie extends Zombie implements Serializable {
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
	@Override 
	public String toString() {
		
		return ZOMBIE_TYPE.toString();
	}

	public ZombieTypes getZombieType() {
		return ZOMBIE_TYPE;
	}

	@Override
	public int getDefaultSpeed() {
		
		return DEFAULT_SPEED;
	}
}
