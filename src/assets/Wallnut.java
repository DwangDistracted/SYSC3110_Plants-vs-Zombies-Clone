package assets;

import engine.Board;

/**
 * Class for Wallnut plant type. Cannot attack but
 * has high amounts of hit points.
 * 
 * @author David Wang
 *
 */
public class Wallnut extends Plant {
	private static final int DEFAULT_HP = HEALTH_TANK;
	private static final int DEFAULT_POWER = ATTACK_NONE;
	private static final int COST = 100;
	
	public Wallnut() {
		super(DEFAULT_HP, DEFAULT_POWER, COST);
	}

	@Override
	public PlantTypes getPlantType() {
		return PlantTypes.WALLNUT;
	}
	
	@Override
	public String toString() {
		return "Wallnut";
	}

	@Override
	public void attack(Board board) {
		return; //cannot attack
	}
}
