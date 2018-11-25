package assets;

import engine.Board;
import util.Logger;

public class Repeater_Peashooter extends Plant{
private static Logger LOG = new Logger("Repeated Peashooter");
	
	private static final int DEFAULT_HP = HEALTH_MEDIUM;
	private static final int DEFAULT_POWER = ATTACK_LOW + ATTACK_LOW;
	private static final int COST = 75;
	private static final PlantTypes PLANT_TYPE = PlantTypes.REPEATER_PEASHOOTER;
	
	public Repeater_Peashooter() {
		super(DEFAULT_HP, DEFAULT_POWER, COST);
	}
	
	/**
	 * returns name of Peashooter
	 */
	public String toString() {
		return "Repeater Peashooter";
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
			LOG.debug(String.format("Repeater Peashooter at : (%d, %d) attacking Zombie at: (%d, %d)", 
					row, column, zombieTarget.getRow(), zombieTarget.getCol()));
			
			zombieTarget.takeDamage(getPower());
			
			if (!zombieTarget.isAlive()) {
				board.removeZombie(zombieTarget.getRow(), zombieTarget.getCol());
				LOG.debug(String.format("Peashooter at : (%d, %d) defeated Zombie at: (%d, %d)", 
						row, column, zombieTarget.getRow(), zombieTarget.getCol()));
			}
		}
	}		
}
