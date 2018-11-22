package assets;

import engine.Board;

/**
 * The Zombie class initializes a set of variables and implements associated setters and getters
 * 
 * Used for creating a Zombie and determining if a Zombie is alive
 * 
 *@author Tanisha 
 */

import util.Logger;

public abstract class Zombie implements Unit{
	public static final int ATTACK_LOW = 1;
	public static final int ATTACK_MEDIUM = 2;
	public static final int ATTACK_HIGH = 3;
	public static final int ATTACK_VERY_HIGH = 4;
	public static final int ATTACK_INSTANT = 1000;
	
	public static final int HEALTH_LOW = 2;
	public static final int HEALTH_MEDIUM = 4;
	public static final int HEALTH_HIGH = 6;
	public static final int HEALTH_VERY_HIGH = 8;
	
	public static final int SPEED_LOW = 1;
	public static final int SPEED_MEDIUM = 2;
	public static final int SPEED_HIGH = 3;
	
	private static Logger LOG = new Logger("Zombie");
	private int speed; 		 //speed of a zombie
	private int power; 		// power of a zombie 
	private int hitPoints;  // life of a zombie
	private int row;
	private int column;
	private Board listener;
	
	private int slowedTurnsLeft;
	private boolean immobilized;
	
	public Zombie(int speed, int pwr, int hp) {
		this.speed = speed;
		this.power = pwr;
		this.hitPoints = hp;
	}
	
	/**
	 * returns speed of a zombie
	 * @return the speed value
	 */
	public int getSpeed() {
		return this.speed;
	}
	
	/**
	 * sets the speed of a zombie
	 * @param speed the speed value
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * returns power of an zombie
	 * @return the power value
	 */
	@Override
	public int getPower() {
		return this.power;
	}
	
	/**
	 * sets the power of an zombie to a desired value
	 * @param pwr the power value
	 */
	@Override
	public void setPower(int pwr) {
		this.power = pwr;
	}

	/**
	 * returns hit-point(life left) of an unit
	 * @return the hitpoints remaining
	 */
	@Override
	public int getHP() {
		return this.hitPoints;
	}
	
	/**
	 * sets hit-point(life left) of an unit
	 * @param hp the hit points value
	 */
	@Override
	public void setHp(int hp) {
		this.hitPoints = hp;
	}
	
	/**
	 * returns the row of a zombie
	 * @return row of a zombie
	 */
	public int getRow() {
		return this.row;
	}
  
	/**
	 * returns the column of a zombie
	 * @return column of a zombie
	 */
	public int getCol() {
		return this.column;
	}
	
	/**
	 * Set the row of where the zombie is to be located
	 * @param row
	 */
	public void setRow(int row) {
		this.row = row;
	}
	
	/**
	 * Set the column of where the zombie is to be located
	 * @param column
	 */
	public void setColumn(int column) {
		this.column = column;
	}

	/**
	 * calculates the hit-points(life left) of a zombie after taking damage from a plant
	 * @param dmg the hit points to be reduced
	 */
	@Override
	public void takeDamage(int dmg) {
		this.hitPoints -= dmg;
	}

	/**
	 * returns true if a zombie is alive 
	 * otherwise returns false
	 * @return true if a zombie alive, false otherwise
	 */
	@Override
	public boolean isAlive() {
		if(getHP() <= 0) {
			LOG.debug("Zombie is Dead");
			return false;
		}
		return true;
	}
	
	/**
	 * Set the listener for this zombie when it moves
	 * @param board The listener that is listening
	 */
	public void setListener(Board board) {
		this.listener = board;
	}
	
	/**
	 * Notify listener that this zombie is moving and check for movement
	 * debuffs on zombie. 
	 * 
	 * @return true if move was not stopped by plant, false otherwise
	 */
	public boolean move() {

		if (immobilized) {
			immobilized = false;
			return true;
		} else if (slowedTurnsLeft > 0) {
			if (slowedTurnsLeft-- == 0) {
				restoreSpeed();
			}
			return true; 
		}
		
		return listener.onZombieMove(this);
	}

	/**
	 * Returns the zombie type of this zombie
	 * 
	 * @return the zombie type
	 */
	public abstract ZombieTypes getZombieType();
	
	/**
	 * Zombie attack method.
	 */
	public void attack(Board board) {
		Plant plantTarget = board.getPlant(getRow(), getCol());
		
		plantTarget.takeDamage(getPower());
		
		if (!plantTarget.isAlive()) {
			board.removePlant(getRow(), getCol());
		}
	}
	
	/**
	 * Restore the zombie's original speed. 
	 */
	public void restoreSpeed() {
		
		this.speed = getDefaultSpeed();
	}
	
	/**
	 * Get the default speed of this zombie
	 * 
	 * @return 
	 */
	public abstract int getDefaultSpeed();
	
	
	/**
	 * Reduce the speed of a zombie for a specified amount and duration.
	 * 
	 * @param speedReduction the amount of speed to reduce
	 * @param duration the amount of turns to reduce speed
	 */
	public void speedDebuff(int speedReduction, int duration) {
		
		// only slow down the zombie if the zombie is currently not slowed
		if (slowedTurnsLeft <= 0) {
			slowedTurnsLeft = duration;
			
			// only reduce the speed of zombie if the
			// speed reduction does not immobilize the zombie
			if (getSpeed() - speedReduction >= 1) {
				setSpeed(getSpeed() - speedReduction);
			}
		}
	}
	
	/**
	 * Set flag indicating that this zombie cannot move. 
	 */
	public void immobilize() {
		
		immobilized = true;
	}
}
