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
	private int speed;
	private int power;
	private int hitPoints;
	private int row;
	private int column;
	private Board listener;
	
	public Zombie(int speed, int pwr, int hp) {
		this.speed = speed;
		this.power = pwr;
		this.hitPoints = hp;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
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
	
	public int getCol() {
		return this.column;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}

	@Override
	public void takeDamage(int dmg) {
		this.hitPoints -= dmg;
		isAlive();
	}

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
		if (listener.onZombieMove(this)) {
			return true;
		} else {
			return false;
		}
	}
}
