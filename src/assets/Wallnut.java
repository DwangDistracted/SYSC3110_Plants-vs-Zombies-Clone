package assets;

public class Wallnut extends Plant {
	private static final int DEFAULT_HP = 10;
	private static final int DEFAULT_POWER = 0;
	private static final int COST = 100;
	
	public Wallnut() {
		super(DEFAULT_HP, DEFAULT_POWER, COST);
	}

	@Override
	public PlantTypes getPlantType() {
		return PlantTypes.WALLNUT;
	}
	
	@Override
	public String toString() {
		return "Wallnut";
	}
}
