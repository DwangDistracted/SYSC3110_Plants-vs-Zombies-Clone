package assets;

import engine.Board;

public class Twin_Flower extends Plant{
	private static final int DEFAULT_HP = HEALTH_LOW;
	private static final int DEFAULT_POWER = ATTACK_NONE;
	private static final int COST = 50;
	
	//The Amount of Points that are added per turn per sunflower
	private static final int POINTS = 10; //points that a sunflower provides a player to buy other plants

	private static final PlantTypes PLANT_TYPE = PlantTypes.TWIN_SUNFLOWER;
	
	public Twin_Flower()	{
		super(DEFAULT_HP, DEFAULT_POWER, COST);
	}
	
	/**
	 * Returns the number of Points a sunflower gives the player per turn
	 * @return the points of a sunflower  
	 */
	public static int getPoints() {
		return POINTS;
	}
	
	/**
	 * returns name of the sunflower
	 * @return name of the sunflower
	 */
	public String toString() {
		return "Twin Sunflower";
	}

	public PlantTypes getPlantType() {
		return PLANT_TYPE;
	}
	
	@Override
	public void attack(Board board) {
		return; //cannot attack
	}
}
