package assets;

import util.Logger;
import view.Board;

public class Plant implements Unit{
	private static Logger LOG = new Logger("Zombie");
	private int speed;
	private int hitPoints;
	private int power;
	
	public Plant(int speed, int hp, int pwr){
		this.speed = speed;
		this.hitPoints = hp;
		this.power = pwr;
	}
	
	@Override
	public int getSpeed() {
		return this.speed;
	}
	
	@Override
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	@Override
	public int getPower() {
		return this.power;
	}
	
	public void setPower(int pwr) {
		this.power = pwr;
	}

	@Override
	public int getHP() {
		return this.hitPoints;
	}
	
	@Override
	public void setHp(int hp) {
		this.hitPoints = hp;
	}

	@Override
	public void takeDamage(int dmg)	{
		this.hitPoints -= dmg;
		isAlive();
	}
	
	public boolean isAlive() {
		if(getHP() == 0 || getHP() < 0) {
			LOG.info("Flower is Dead");
			return false;
		}
		return true;
	}
}
