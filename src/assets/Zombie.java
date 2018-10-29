package assets;

import engine.Board;

/**
 * The Zombie class initializes a set of variables and implements associated setters and getters
 * 
 * Used for creating a Zombie and determining if a Zombie is alive
 * 
 *@author Tanisha 
 */

import util.Logger;

public abstract class Zombie implements Unit{
	private static Logger LOG = new Logger("Zombie");
	private int speed; 		 //speed of a zombie
	private int power; 		// power of a zombie 
	private int hitPoints;  // life of a zombie
	private int row;
	private int column;
	private Board listener;
	
	public Zombie(int speed, int pwr, int hp) {
		this.speed = speed;
		this.power = pwr;
		this.hitPoints = hp;
	}
	
	/**
	 * returns speed of a zombie
	 * @return
	 */
	public int getSpeed() {
		return this.speed;
	}
	
	/**
	 * sets the speed of a zombie
	 * @param speed
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * returns power of an zombie
	 * @return
	 */
	@Override
	public int getPower() {
		return this.power;
	}
	
	/**
	 * sets the power of an zombie to a desired value
	 * @param pwr
	 */
	@Override
	public void setPower(int pwr) {
		this.power = pwr;
	}

	/**
	 * returns hit-point(life left) of an unit
	 * @return
	 */
	@Override
	public int getHP() {
		return this.hitPoints;
	}
	
	/**
	 * sets hit-point(life left) of an unit
	 * @param hp
	 */
	@Override
	public void setHp(int hp) {
		this.hitPoints = hp;
	}
	
	/**
	 * returns the row of a zombie
	 * @return
	 */
	public int getRow() {
		return this.row;
	}
  
	/**
	 * returns the column of a zombie
	 * @return
	 */
	public int getCol() {
		return this.column;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}

	/**
	 * calculates the hit-points(life left) of a zombie after taking damage from a plant
	 * @param dmg
	 */
	@Override
	public void takeDamage(int dmg) {
		this.hitPoints -= dmg;
	}

	/**
	 * returns true if a zombie is alive 
	 * otherwise returns false
	 * @return
	 */
	@Override
	public boolean isAlive() {
		if(getHP() <= 0) {
			LOG.info("Zombie is Dead");
			return false;
		}
		return true;
	}
	
	public void setListener(Board board) {
		this.listener = board;
	}
	
	public boolean move() {

		return listener.onZombieMove(this);
	}
}
