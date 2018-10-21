package Assets;

public class Peashooter extends pDmgDealer{
	
	private int hp;
	private int dmg;
	private int speed;
	
	public Peashooter()
	{
		this.hp = 2;
		this.dmg = 2;
		this.speed = 0;
	}
	
	public int getDamage()
	{
		return this.dmg;
	}
	public void takeDamage(int dmg) 
	{
		this.hp = hp - dmg; 
	}
	
	public int getHP()
	{
		return this.hp;
	}
	
	public String toString()
	{
		return "P";
	}

}
