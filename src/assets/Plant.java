package assets;

public class Plant implements Unit{
	public int speed = 0;
	public int hp = 2;
	public int dmg = 0;
	
	@Override
	public int getSpeed() {
		// TODO Auto-generated method stub
		return this.speed;
	}

	@Override
	public int getDamage() {
		// TODO Auto-generated method stub
		return this.dmg;
	}

	@Override
	public int getHP() {
		// TODO Auto-generated method stub
		return this.hp;
	}

	@Override
	public void takeDamage(int dmg) {
		this.hp = hp - dmg; 
	}
	

}
