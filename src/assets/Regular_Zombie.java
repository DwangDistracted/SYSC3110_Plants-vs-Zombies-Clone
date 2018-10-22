package assets;

public class Regular_Zombie extends Zombie{
	
	private int speed;
	private int dmg;
	private int hp;
	
	public Regular_Zombie()
	{
		this.speed = 1;
		this.dmg = 1;
		this.hp = 4;
	}
	
	public int getHP() 
	{
		return this.hp;
	}
	
	public int getDamage()
	{
		return dmg;
	}
	
	public void takeDamage(int dmg)
	{
		this.hp = hp - dmg; 
		System.out.println("TEST: took damage: "+ dmg + " have hp: " + this.hp);
	}
	
	public int getSpeed() 
	{
		return this.speed;
	}
	
	public String toString()
	{
		return "Z";	
	}

}
