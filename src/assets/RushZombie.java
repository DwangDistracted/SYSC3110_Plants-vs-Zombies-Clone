package assets;

import java.io.Serializable;

/**
 * Class for Rush Zombie type. Has extremely high
 * attack power.
 * 
 * @author David Wang
 *
 */
public class RushZombie extends Zombie implements Serializable {
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
