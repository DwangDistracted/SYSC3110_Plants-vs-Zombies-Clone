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
	private int hitPoints;	//life left of a plant
	private int power;		//power of a plant to damage a zombie	
	private int row;
	private int column;
	private int cost;
	
	public Plant(int hp, int pwr, int cost){
		this.hitPoints = hp;
		this.power = pwr;
		this.cost = cost;
	}

	/**
	 * returns power of a plant
	 * @return
	 */
	@Override
	public int getPower() {
		return this.power;
	}
	
	/**
	 * sets the power of a plant to a desired value
	 * @param pwr
	 */
	@Override
	public void setPower(int pwr) {
		this.power = pwr;
	}

	/**
	 * returns hit-point(life left) of a plant
	 * @return
	 */
	@Override
	public int getHP() {
		return this.hitPoints;
	}
	
	/**
	 * sets hit-point(life left) of a plant
	 * @param hp
	 */
	@Override
	public void setHp(int hp) {
		this.hitPoints = hp;
	}

	/**
	 * returns cost of a plant
	 * @return
	 */
	public int getCost() {
		return this.cost;
	}
	
	/**
	 * calculates the hit-points(life left) of a plant after taking damage from zombie
	 * @param dmg
	 */
	@Override
	public void takeDamage(int dmg)	{
		this.hitPoints -= dmg;
	}
	
	/**
	 * returns true if a plant is alive 
	 * otherwise returns false
	 * @return
	 */
	@Override
	public boolean isAlive() {
		if(getHP() <= 0) {
			LOG.info("Flower is Dead");
			return false;
		}
		return true;
	}
  
	/**
	 * updates the current coordinates of a plant
	 * @param row
	 * @param column
	 */
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
