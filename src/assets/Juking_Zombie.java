package assets;

import levels.LevelInfo;

public class Juking_Zombie extends Zombie {
	
	private static final int DEFAULT_SPEED = SPEED_LOW;
	private static final int DEFAULT_POWER = ATTACK_LOW;
	private static final int DEFAULT_HP = HEALTH_HIGH;
	private static final ZombieTypes ZOMBIE_TYPE = ZombieTypes.JUK_ZOMBIE;
	private boolean toggleDirection; //true indicates incrementing row, false indicates decreaseing row
	
	
	public Juking_Zombie()	{
		super(DEFAULT_SPEED, DEFAULT_POWER, DEFAULT_HP);
		toggleDirection = true;
	}
	
	/**
	 * returns the name of regular type zombie
	 */
	public String toString() {
		return "Juking Zombie";	
	}
	
	/**
	 * Determines the new row of the zombie on for its next move
	 * @param maxRow - The maximum row number within the board
	 * @return -> the new row number 
	 */
	public int getPath(int maxRow)
	{
		if(this.getRow() == maxRow - 1)
		{
			toggleDirection = false;
			return maxRow - 2;
		}
		else if(this.getRow() == 0)
		{
			toggleDirection = true;
			return 1;
		}
		else if(toggleDirection == true)
		{
			return this.getRow() + 1;
		}
		
		//indicates toggleDirection == false
		return this.getRow() - 1;
	}
	public ZombieTypes getZombieType() {
		return ZOMBIE_TYPE;
	}

	@Override
	public int getDefaultSpeed() {
		return DEFAULT_SPEED;
	} 

}
