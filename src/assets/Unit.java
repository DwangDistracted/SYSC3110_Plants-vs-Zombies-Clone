package assets;

public interface Unit 
{
	public int getSpeed();
	
	public int getDamage();
	
	public int getHP();
	
	public void takeDamage(int dmg);
	
	public String toString();
	
	public int getRow();
	
	public int getCol();
	
	public void setRow(int row);
	
	public void setCol(int col);

}
