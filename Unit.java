
public interface Unit {
	
	
	public int getSpeed();
	
	public int getDamage();
	
	public int getHP();
	
	public void takeDamage(int dmg);
	
	public int[] getPlacement();

	public void setPlacement(int row, int column);
	
	public String toString();

}
