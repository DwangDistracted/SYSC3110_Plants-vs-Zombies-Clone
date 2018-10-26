package assets;

public class Peashooter extends pDmgDealer{
//	
//	private int hp;
//	private int dmg;
//	private int speed;
	
	public Peashooter()	{
		super(0,2,2);
	}
	
	public int getHP() {
		return super.getHP();
	}
	
	public void setHp(int hp) {
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
			System.out.println("Pea Shooter is Dead");
			return false;
		}
		return true;
	}
	
	public String toString() {
		return "P";
	}

}
