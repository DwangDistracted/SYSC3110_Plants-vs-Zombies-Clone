package assets;

public abstract class EconomyPlant extends Plant {

	public static final int points = 10;
	
	public EconomyPlant(int hp, int pwr, int cost) {
		super(hp, pwr, cost);
	}
	
	public int getPoints()
	{
		return points;
	}

}
