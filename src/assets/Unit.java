package assets;

/**
 * This is the interface specifying all the methods to be implemented
 * 
 * @author Tanisha 
 *
 */

public interface Unit{
	
	public int getPower();
	
	public void setPower(int pwr);
	
	public int getHP();
	
	public void setHp(int hp);
	
	public void takeDamage(int dmg);
	
	public boolean isAlive();
	
	public String toString();

}
