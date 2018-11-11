package assets;

/**
 * The Peashooter Class is used to initialize a plant type having the power to shoot peas 
 * at the zombie to reduce its health.
 * 
 *@author Tanisha 
 */

public class Peashooter extends Plant{
	private static final int DEFAULT_HP = 2;
	private static final int DEFAULT_POWER = 2;
	private static final int COST = 50;
	private static final PlantTypes PLANT_TYPE = PlantTypes.PEASHOOTER;
	
	public Peashooter()	{
		super(DEFAULT_HP, DEFAULT_POWER, COST);
	}
	
	/**
	 * returns name of Peashooter
	 */
	public String toString() {
		return "Peashooter";
	}

	public PlantTypes getPlantType() {
		return PLANT_TYPE;
	}
}
