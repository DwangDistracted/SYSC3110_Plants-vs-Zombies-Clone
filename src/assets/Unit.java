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
	 * @return
	 */
	public int getPower();
	
	/**
	 * sets the power of an unit to a desired value
	 * @param pwr
	 */
	public void setPower(int pwr);
	
	/**
	 * returns hit-point(life left) of an unit
	 * @return
	 */
	public int getHP();
	
	/**
	 * sets hit-point(life left) of an unit
	 * @param hp
	 */
	public void setHp(int hp);
	
	/**
	 * calculates the hit-points(life left) of an unit after taking damage from another unit
	 * @param dmg
	 */
	public void takeDamage(int dmg);
	
	/**
	 * returns true if an unit is alive 
	 * otherwise returns false
	 * @return
	 */
	public boolean isAlive();
	
	/**
	 * returns the name of an unit
	 * @return
	 */
	public String toString();
	
	public int getRow();
	
	public int getCol();
}
