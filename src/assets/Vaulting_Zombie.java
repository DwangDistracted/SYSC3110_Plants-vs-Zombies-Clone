package assets;

import engine.Board;

/**
 * valuting zombie valuts to the first plant in the row and attacks it
 * @author Tanisha Garg
 *
 */
public class Vaulting_Zombie extends Zombie{
	private static int default_speed = SPEED_HIGH;
	private static final int DEFAULT_POWER = ATTACK_LOW;
	private static final int DEFAULT_HP = HEALTH_MEDIUM;
	private static final ZombieTypes ZOMBIE_TYPE = ZombieTypes.VAULTING_ZOBIE;
	
	public Vaulting_Zombie()	{
		super(default_speed, DEFAULT_POWER, DEFAULT_HP);
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
		return default_speed;
	}
	
	public void setDefaultSpeed() {
		this.default_speed = SPEED_LOW;
	}
	
	/**
	 * Zombie attack method.
	 */
	@Override
	public void attack(Board board) {
		Plant plantTarget = board.getPlant(getRow(), getCol());
		if(this.getCol()==plantTarget.getCol()+2) {
			this.setDefaultSpeed();
			this.setRow(plantTarget.getRow());
			this.setColumn(plantTarget.getCol());
			plantTarget.takeDamage(getPower());
			
			if (!plantTarget.isAlive()) {
				board.removePlant(getRow(), getCol());
			}
		}
		
	}
	
}
