
public class Regular_Zombie implements Unit {
	
	private int speed = 1;
	private int dmg = 1;
	private int hp = 10;
	private int row;
	private int[] placement = new int[2];
	
	public int getSpeed()
	{
		return this.speed;
	}
	public int getDamage()
	{
		return this.dmg;
	}
	public int getHP()
	{
		return this.hp;
	}
	public void takeDamage(int dmg)
	{
		this.hp = hp - dmg; 
	}
	
	@Override
	public int[] getPlacement() {
		return this.placement;
	}

	@Override
	public void setPlacement(int row, int column) {
		placement[0] = row;
		placement[1] = column;
		
	}
	
	public String toString()
	{
		return "Z";	
	}

}
