package assets;

/**
 * The Plant class initializes a set of variables and implements associated setters and getters
 * 
 * Used for creating a plant and determining if a plant is alive
 * 
 *@author Tanisha 
 */

import util.Logger;

public class Plant implements Unit{
	private static Logger LOG = new Logger("Zombie");
	private int hitPoints;
	private int power;
	
	public Plant(int hp, int pwr){
		this.hitPoints = hp;
		this.power = pwr;
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
	
	@Override
	public boolean isAlive() {
		if(getHP() == 0 || getHP() < 0) {
			LOG.info("Flower is Dead");
			return false;
		}
		return true;
	}
}
