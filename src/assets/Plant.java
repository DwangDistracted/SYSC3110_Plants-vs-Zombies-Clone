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
	private static Logger LOG = new Logger("Plant");
	private int hitPoints;
	private int power;
	private int row;
	private int column;
	private int cost;
	
	public Plant(int hp, int pwr, int cost){
		this.hitPoints = hp;
		this.power = pwr;
		this.cost = cost;
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

	public int getCost() {
		return this.cost;
	}
	
	@Override
	public void takeDamage(int dmg)	{
		this.hitPoints -= dmg;
	}
	
	@Override
	public boolean isAlive() {
		if(getHP() <= 0) {
			LOG.info("Flower is Dead");
			return false;
		}
		return true;
	}

	public void setCoordinates(int row, int column) {
	
		this.row = row;
		this.column = column;
	}

	@Override
	public int getRow() {
		return this.row;
	}

	@Override
	public int getCol() {
		return this.column;
	}
}
