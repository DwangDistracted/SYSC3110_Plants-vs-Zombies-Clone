package Assets;

public class Flower extends Plant{
	
	private int hp;
	private int dmg;
	private int speed;
	
	public Flower()
	{
		this.hp = 2;
		this.dmg = 0;
		this.speed = 0;
	}
	
	public int getHP() 
	{
		return this.hp;
	}
	
	public void takeDamage(int dmg)
	{
		this.hp = hp - dmg; 
	}
	
	public String toString()
	{
		return "F";
	}

}
