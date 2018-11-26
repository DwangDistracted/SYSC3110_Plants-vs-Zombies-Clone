package assets;

import engine.Board;

/**
 * Twin Flower produces a sun of worth 20 points
 * @author Tanisha Garg
 *
 */
public class Twin_Flower extends Plant{
	private static final int DEFAULT_HP = HEALTH_LOW;
	private static final int DEFAULT_POWER = ATTACK_NONE;
	private static final int COST = 50;
	
	//The Amount of Points that are added per turn per sunflower
	private static final int POINTS = 20; //points that a sunflower provides a player to buy other plants

	private static final PlantTypes PLANT_TYPE = PlantTypes.TWIN_FLOWER;
	
	public Twin_Flower()	{
		super(DEFAULT_HP, DEFAULT_POWER, COST);
	}
	
	/**
	 * Returns points of a twin sunflower
	 * @return 
	 */
	public static int getPoints() {
		return POINTS;
	}
	
	/**
	 * returns name of the Plant
	 * @return 
	 */
	public String toString() {
		return "Twin Sunflower";
	}

	/**
	 * returns Plant Type
	 */
	public PlantTypes getPlantType() {
		return PLANT_TYPE;
	}
	
	@Override
	public void attack(Board board) {
		return; //cannot attack
	}
}
