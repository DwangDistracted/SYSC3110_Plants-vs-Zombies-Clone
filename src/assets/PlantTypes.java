package assets;

/**
 * This is the listing of all plant types we have
 * 
 * Useful for command parsing and spawning
 * @author david
 *
 */
public enum PlantTypes {
	SUNFLOWER,
	PEASHOOTER;
	
	public static Plant toPlant(PlantTypes e) 
	{
		switch (e) {
			case SUNFLOWER:
				return new Flower();
			case PEASHOOTER:
				return new Peashooter();
			default: 
				return null;
		}
	}
}
