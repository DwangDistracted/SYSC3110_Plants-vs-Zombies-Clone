
public class Flower {
	
	private int hp;
	private int points;
	private int[] placement = new int[2];
	

	public int getHP() {
		return this.hp;
	}

	public void takeDamage(int dmg) {
		this.hp = this.hp - dmg;
		
	}
	
	public int[] getPlacement() {
		return this.placement;
	}

	public void setPlacement(int row, int column) {
		placement[0] = row;
		placement[1] = column;
		
	}
	
	public String toString()
	{
		return "F";
	}


}
