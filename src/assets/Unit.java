package assets;

public interface Unit{
	
	public int getSpeed();
	
	public void setSpeed(int speed);
	
	public int getPower();
	
	public void setPower(int pwr);
	
	public int getHP();
	
	public void setHp(int hp);
	
	public void takeDamage(int dmg);
	
	public String toString();

}
