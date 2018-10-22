package assets;

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
