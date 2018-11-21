package assets;

public class Potato_Mine extends Plant {
	private static final int DEFAULT_HP = 2;
	private static final int DEFAULT_POWER = 2;
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

}
