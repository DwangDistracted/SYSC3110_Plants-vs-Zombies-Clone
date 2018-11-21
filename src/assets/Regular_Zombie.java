package assets;

import engine.Board;

/**
 * The Regular_Zombie class is used to create a basic type of zombie
 * 
 *@author Tanisha 
 */

public class Regular_Zombie extends Zombie{
	private static final int DEFAULT_SPEED = 1;
	private static final int DEFAULT_POWER = 1;
	private static final int DEFAULT_HP = 4;
	private static final ZombieTypes ZOMBIE_TYPE = ZombieTypes.REG_ZOMBIE;
	
	public Regular_Zombie()	{
		super(DEFAULT_SPEED, DEFAULT_POWER, DEFAULT_HP);
	}
	
	/**
	 * returns the name of regular type zombie
	 */
	public String toString() {
		return "RZ";	
	}

	public ZombieTypes getZombieType() {
		return ZOMBIE_TYPE;
	}

	@Override
	public void attack(Board board) {
		
		Plant plantTarget = board.getPlant(getRow(), getCol());
		
		plantTarget.takeDamage(getPower());
		
		if (!plantTarget.isAlive()) {
			board.removePlant(getRow(), getCol());
		}
	} 
}
