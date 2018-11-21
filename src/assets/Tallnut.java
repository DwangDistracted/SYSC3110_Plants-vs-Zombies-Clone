package assets;

public class Tallnut extends Plant {
	private static final int DEFAULT_HP = 20;
	private static final int DEFAULT_POWER = 0;
	private static final int COST = 150;
	
	public Tallnut() {
		super(DEFAULT_HP, DEFAULT_POWER, COST);
	}

	@Override
	public PlantTypes getPlantType() {
		return PlantTypes.TALLNUT;
	}
	
	@Override
	public String toString() {
		return "Tallnut";
	}
}
