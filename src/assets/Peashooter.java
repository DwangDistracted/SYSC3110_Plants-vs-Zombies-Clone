package assets;

import java.io.Serializable;

import engine.Board;
import util.Logger;

/**
 * The Peashooter Class is used to initialize a plant type having the power to shoot peas 
 * at the zombie to reduce its health.
 * 
 *@author Tanisha 
 */

public class Peashooter extends Plant implements Serializable {
	private static Logger LOG = new Logger("Peashooter");
	
	private static final int DEFAULT_HP = HEALTH_MEDIUM;
	private static final int DEFAULT_POWER = ATTACK_MEDIUM;
	private static final int COST = 50;
	private static final PlantTypes PLANT_TYPE = PlantTypes.PEASHOOTER;
	
	public Peashooter()	{
		super(DEFAULT_HP, DEFAULT_POWER, COST);
	}
	
	/**
	 * returns name of Peashooter
	 */
	public String toString() {
		return "Peashooter";
	}

	public PlantTypes getPlantType() {
		return PLANT_TYPE;
	}
	
	@Override
	public void attack(Board board) {
		int row = getRow();
		int column = getCol();
		
		Zombie zombieTarget = board.getSingleZombieTarget(row, column);
		
		if (zombieTarget != null) {
			LOG.debug(String.format("Peashooter at : (%d, %d) attacking Zombie at: (%d, %d)", 
					row, column, zombieTarget.getRow(), zombieTarget.getCol()));
			
			zombieTarget.takeDamage(getPower());
			removeZombie(zombieTarget, board);
		}
	}		
}
