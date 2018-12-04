package assets;

import java.io.Serializable;
import java.util.ArrayList;

import engine.Board;
import engine.Grid;

/**
 * Class for Potato Mine type. Kills all zombies
 * on the same grid when triggered.
 * 
 * @author Michael Patsula
 *
 */
public class Potato_Mine extends Plant implements Serializable {
	private static final int DEFAULT_HP = HEALTH_LOW;
	private static final int DEFAULT_POWER = ATTACK_INSTANT;  //This is irrelevant, it kills the target instantly
	private static final int COST = 50;
	private static final PlantTypes PLANT_TYPE = PlantTypes.POTATOMINE;
	private boolean discharged = false;
	
	public Potato_Mine()	{
		super(DEFAULT_HP, DEFAULT_POWER, COST);
	}
	
	@Override
	public String toString() {
		return "Potato Mine";
	}

	public PlantTypes getPlantType() {
		return PLANT_TYPE;
	}

	@Override
	public void attack(Board board) {
		ArrayList<Zombie> removeBin = new ArrayList<Zombie>(); //To track zombies that die (needed due to ConcurrentModificationException)
		Grid grid = board.getGrid(this.getRow(), this.getCol());
		
		if(grid.getNumberOfZombies() > 0) //if a zombie is within melee range
		{
			for(Zombie zom : grid.getZombies())  
			{
				zom.takeDamage(zom.getHP());    //instantly kill all the zombies within the grid
				removeBin.add(zom);
			}
			for(Zombie zom : removeBin) 
			{
				board.removeZombie(zom.getRow(), zom.getCol());
			}
			discharged = true; // Game must check if this needs to be removed (due to ConcurrentModificationException)
		}
	}
	
	/**
	 * Returns if the mine has already discharged
	 * @return
	 */
	public boolean getDischarged() {
		return discharged;
	}
}
