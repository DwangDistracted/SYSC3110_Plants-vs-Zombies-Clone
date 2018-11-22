package assets;

import engine.Board;

public class Snowshooter extends Plant {

	private static final int DEFAULT_HP = HEALTH_LOW;
	private static final int DEFAULT_POWER = ATTACK_MEDIUM;
	private static final int COST = 100;
	private static final PlantTypes PLANT_TYPE = PlantTypes.SNOWSHOOTER;
	
	public Snowshooter() {
		super(DEFAULT_HP, DEFAULT_POWER, COST);
	}

	@Override
	public PlantTypes getPlantType() {

		return PLANT_TYPE;
	}

	@Override
	public void attack(Board board) {
		// TODO Auto-generated method stub
		
	}

}
