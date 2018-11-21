package assets;

public class Air_Monkey extends Plant {
	private static final int DEFAULT_HP = 2;
	private static final int DEFAULT_POWER = 2;
	private static final int COST = 50;
	private static final PlantTypes PLANT_TYPE = PlantTypes.AIRMONKEY;
	
	public Air_Monkey()	{
		super(DEFAULT_HP, DEFAULT_POWER, COST);
	}
	
	/**
	 * returns name of Peashooter
	 */
	public String toString() {
		return "Air Monkey";
	}

	public PlantTypes getPlantType() {
		return PLANT_TYPE;
	}

}
