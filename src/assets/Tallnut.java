package assets;

import engine.Board;

/**
 * Class for Tallnut type. Cannot attack and has
 * extremely high amounts of hitpoints.
 * 
 * @author David Wang
 *
 */
public class Tallnut extends Plant {
	private static final int DEFAULT_HP = HEALTH_SUPER_TANK;
	private static final int DEFAULT_POWER = ATTACK_NONE;
	private static final int COST = 150;
	
	public Tallnut() {
		super(DEFAULT_HP, DEFAULT_POWER, COST);
	}

	@Override
	public PlantTypes getPlantType() {
		return PlantTypes.TALLNUT;
	}
	
	@Override
	public String toString() {
		return "Tallnut";
	}

	@Override
	public void attack(Board board) {
		return; //cannot attack
	}
}
