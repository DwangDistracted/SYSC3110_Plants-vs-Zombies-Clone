
public class Peashooter implements Unit{
	private int hp;
	private int dmg;
	private int speed;
	private int[] placement;

	
	@Override
	public int getSpeed() {
		return this.speed;
	}

	@Override
	public int getDamage() {
		return this.dmg;
	}

	@Override
	public int getHP() {
		return this.hp;
	}

	@Override
	public void takeDamage(int dmg) {
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
		return "P";
	}

	

}
