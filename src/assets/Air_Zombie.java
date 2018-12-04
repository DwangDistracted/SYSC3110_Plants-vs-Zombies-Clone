package assets;

import java.io.Serializable;

/**
 * Class for Air Zombie type that can only be attacked
 * by Air Monkeys.
 * 
 * @author Michael Patsula
 *
 */
public class Air_Zombie extends Zombie implements Serializable {
	private static final int DEFAULT_SPEED = SPEED_LOW;
	private static final int DEFAULT_POWER = ATTACK_LOW;
	private static final int DEFAULT_HP = HEALTH_LOW;
	private static final ZombieTypes ZOMBIE_TYPE = ZombieTypes.AIR_ZOMBIE;
	
	public Air_Zombie()	{
		super(DEFAULT_SPEED, DEFAULT_POWER, DEFAULT_HP);
	}
	
	
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
