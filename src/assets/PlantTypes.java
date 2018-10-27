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
	
	/**
	 * Translates a PlantType Enumeration into a Plant Object
	 * @param e the PlantType
	 * @return Plant Object
	 */
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
	
	/**
	 * Translates a String into a Plant Object
	 * @param str
	 * @return
	 */
	public static Plant toPlantFromString(String str) {
		try {
			return toPlant(PlantTypes.valueOf(str.toUpperCase()));
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
}
