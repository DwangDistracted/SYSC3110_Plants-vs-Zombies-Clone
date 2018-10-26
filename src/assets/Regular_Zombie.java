package assets;

import View.Board;

public class Regular_Zombie extends Zombie{
	private Board b;
	private int[] coordinates;
	
	public Regular_Zombie()	{
		super(1,1,4);
		for(Object key : b.getExperimental().keySet()) {
			if((key instanceof Zombie) && (key.getClass() == this.getClass())) {
				this.coordinates = b.getExperimental().get(key);
			}
		}
	}
	
	public int getHP() {
		return super.getHP();
	}
	
	public void setHP(int hp) {
		super.setHp(hp);
	}
	
	public int getPower() {
		return super.getPower();
	}
	
	public void setPower(int pwr) {
		super.setPower(pwr);
	}
	
	public int getSpeed() {
		return super.getSpeed();
	}
	
	public void setSpeed(int speed) {
		super.setSpeed(speed);
	}
	
	public void takeDamage(int dmg)	{
		super.takeDamage(dmg);
		isAlive();
	}
	
	public boolean isAlive() {
		if(getHP() == 0 || getHP() < 0) {
			System.out.println("Zombie is Dead");
			return false;
		}
		return true;
	}
	
	public int[] getCoordinates() {
		return this.coordinates;
	}
	
	public String toString() {
		return "Z";	
	}

}
