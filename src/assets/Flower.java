package assets;

import engine.Board;

/**
 * The Flower class is used for initializing a sunflower that provides points to the user
 * to purchase more plants
 * 
 *@author Tanisha 
 */

public class Flower extends EconomyPlant{
	private static final int DEFAULT_HP = HEALTH_LOW;
	private static final int DEFAULT_POWER = ATTACK_NONE;
	private static final int COST = 25;
	//The Amount of Points that are added per turn per sunflower
	private static final int POINTS = 10; 
	private static final PlantTypes PLANT_TYPE = PlantTypes.SUNFLOWER;
	
	public Flower()	{
		super(DEFAULT_HP, DEFAULT_POWER, COST, POINTS);
	}
	
	/**
	 * returns name of the sunflower
	 * @return name of the sunflower
	 */
	public String toString() {
		return "Sunflower";
	}

	public PlantTypes getPlantType() {
		return PLANT_TYPE;
	}
	
	@Override
	public void attack(Board board) {
		return; //cannot attack
	}
}
