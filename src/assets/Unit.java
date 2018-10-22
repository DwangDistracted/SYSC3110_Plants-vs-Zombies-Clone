package assets;

public interface Unit 
{
	public int getSpeed();
	
	public int getDamage();
	
	public int getHP();
	
	public void takeDamage(int dmg);
	
	public String toString();

}
