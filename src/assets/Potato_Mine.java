package assets;

import engine.Board;
import engine.Grid;
import util.Logger;

public class Potato_Mine extends Plant {
	private static Logger LOG = new Logger("Potato_Mine");
	private static final int DEFAULT_HP = HEALTH_LOW;
	private static final int DEFAULT_POWER = ATTACK_HIGH;  //This is irrelevant, it kills the target instaintly
	private static final int COST = 50;
	private static final PlantTypes PLANT_TYPE = PlantTypes.POTATOMINE;
	
	public Potato_Mine()	{
		super(DEFAULT_HP, DEFAULT_POWER, COST);
	}
	
	/**
	 * returns name of Peashooter
	 */
	public String toString() {
		return "Potato_Mine";
	}

	public PlantTypes getPlantType() {
		return PLANT_TYPE;
	}

	@Override
	public void attack(Board board) {
		return;
	}
}
