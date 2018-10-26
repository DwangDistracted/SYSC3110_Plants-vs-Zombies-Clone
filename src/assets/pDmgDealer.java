package assets;
/*
 * used to group plants that deal have power to kill zombies
 */
public class pDmgDealer extends Plant {

	public pDmgDealer(int speed, int hp, int power ) {
		super(speed, hp, power );
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
	
	public void takeDamage(int dmg) {
		super.takeDamage(dmg);
	}
}
