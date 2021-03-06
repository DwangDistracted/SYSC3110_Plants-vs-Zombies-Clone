package assets;

import java.io.Serializable;

/**
 * The TankZombie class creates instances of Tank Zombies
 * that contains high hitpoints. 
 * 
 * @author Derek Shao
 *
 */
public class TankZombie extends Zombie implements Serializable {
	
	private static final int DEFAULT_SPEED = SPEED_LOW;
	private static final int DEFAULT_POWER = ATTACK_LOW;
	private static final int DEFAULT_HP = HEALTH_VERY_HIGH;
	private static final ZombieTypes ZOMBIE_TYPE = ZombieTypes.TANK_ZOMBIE;

	public TankZombie() {
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
