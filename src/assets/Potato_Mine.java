package assets;

import java.util.ArrayList;

import engine.Board;
import engine.Grid;
import util.Logger;

public class Potato_Mine extends Plant {
	private static Logger LOG = new Logger("Potato_Mine");
	private static final int DEFAULT_HP = HEALTH_LOW;
	private static final int DEFAULT_POWER = ATTACK_HIGH;  //This is irrelevant, it kills the target instaintly
	private static final int COST = 50;
	private static final PlantTypes PLANT_TYPE = PlantTypes.POTATOMINE;
	
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
			this.takeDamage(getHP()); //The plant also instantly dies
			board.removePlant(getRow(), getCol());
		}
	}
}
