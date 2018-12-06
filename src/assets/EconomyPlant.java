package assets;
/**
 * Abstract Class for plants that generate points.  
 * Every class that extends EconomyPlant should produce sunshine points after every turn.
 * @author Michael Patsula
 *
 */
public abstract class EconomyPlant extends Plant {

	private int POINTS = 10;
	
	public EconomyPlant(int hp, int pwr, int cost, int points) {
		super(hp, pwr, cost);
		this.POINTS = points;
	}
	
	/**
	 * Retrieves the amount of points this plant generates per turn
	 * @return the number of points the plant produces
	 */
	public int getPoints(){
		return POINTS;
	}

}
