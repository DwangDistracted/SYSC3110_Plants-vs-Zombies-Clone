package assets;

import engine.Board;

public class Melonpult extends Plant {
	
	private static final int DEFAULT_HP = HEALTH_MEDIUM;
	private static final int DEFAULT_POWER = ATTACK_HIGH;
	private static final int COST = 150;
	private static final PlantTypes PLANT_TYPE = PlantTypes.MELONPULT;

	public Melonpult() {
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
