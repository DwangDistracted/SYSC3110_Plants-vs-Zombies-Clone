package assets;

import util.Logger;

public abstract class Zombie implements Unit{
	private static Logger LOG = new Logger("Zombie");
	private int speed;
	private int power;
	private int hitPoints;
	private int row;
	private int column;
	
	public Zombie(int speed, int pwr, int hp) {
		this.speed = speed;
		this.power = pwr;
		this.hitPoints = hp;
		row = -1;
		column = -1;
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
	
	public int getRow() {
		return this.row;
	}
	
	public int getColumn() {
		return this.column;
	}
	
	public void newCoordinates( int row, int column) {
		this.row = row;
		this.column = column;
	}

	@Override
	public void takeDamage(int dmg) {
		this.hitPoints -= dmg;
		isAlive();
	}

	public boolean isAlive() {
		if(getHP() == 0 || getHP() < 0) {
			LOG.info("Zombie is Dead");
			return false;
		}
		return true;
	}
}
