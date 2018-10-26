package assets;

import java.util.Observable;

import View.Board;

public class Zombie implements Unit{
	private int speed;
	private int power;
	private int hitPoints;
	private Board b;
	//private int row;
	//private int column;
	private int[] coordinates;
	
	public Zombie(int speed, int pwr, int hp) {
		this.speed = speed;
		this.power = pwr;
		this.hitPoints = hp;
		
		for(Object key : b.getExperimental().keySet()) {
				if(key instanceof Zombie) {
					this.coordinates = b.getExperimental().get(key);
				}
		}
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
	
	public int[] getCoordinates() {
		return this.coordinates;
	}
	
	public void newCoordinates(int[] coordinates) {
		this.coordinates = coordinates;
	}

	@Override
	public void takeDamage(int dmg) {
		this.hitPoints = hitPoints - dmg;
	}
}
