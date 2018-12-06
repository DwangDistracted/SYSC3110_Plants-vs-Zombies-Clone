package assets;

import java.io.Serializable;

/**
 * Class for Yeti Zombie type. Has high hitpoints and attack power. 
 * 
 * @author Derek Shao
 *
 */
public class YetiZombie extends Zombie implements Serializable {

	private static final int DEFAULT_SPEED = SPEED_LOW;
	private static final int DEFAULT_POWER = ATTACK_HIGH;
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
		
		return ZOMBIE_TYPE.toString();
	}

	@Override
	public int getDefaultSpeed() {

		return DEFAULT_SPEED;
	}
}
