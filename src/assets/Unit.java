package assets;

/**
 * This is the interface specifying all the methods to be implemented
 * 
 * @author Tanisha 
 *
 */

public interface Unit{
	
	/**
	 * returns power of an unit
	 * @return the power value of this unit
	 */
	public int getPower();
	
	/**
	 * sets the power of an unit to a desired value
	 * @param pwr the power value
	 */
	public void setPower(int pwr);
	
	/**
	 * returns hit-point(life left) of an unit
	 * @return the hit points remaining
	 */
	public int getHP();
	
	/**
	 * sets hit-point(life left) of an unit
	 * @param hp the hit points value
	 */
	public void setHp(int hp);
	
	/**
	 * calculates the hit-points(life left) of an unit after taking damage from another unit
	 * @param dmg the hit points value to be reduced
	 */
	public void takeDamage(int dmg);
	
	/**
	 * returns true if an unit is alive 
	 * otherwise returns false
	 * @return true if unit is alive, false otherwise
	 */
	public boolean isAlive();
	
	/**
	 * returns the name of an unit
	 * @return the name of unit
	 */
	public String toString();
	
	/**
	 * the row this unit is located
	 * @return the row unit is located
	 */
	public int getRow();
	
	/**
	 * the column this unit is located
	 * @return the column unit is located
	 */
	public int getCol();
}
