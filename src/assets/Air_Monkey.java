package assets;

import engine.Board;
import util.Logger;

/**
 * Class for Air Monkey unit that are the only units that can 
 * attack Air Zombies.
 * 
 * @author Michael Patusla
 *
 */
public class Air_Monkey extends Plant {
	private static Logger LOG = new Logger("Air_Monkey");
	private static final int DEFAULT_HP = HEALTH_LOW;
	private static final int DEFAULT_POWER = ATTACK_LOW;
	private static final int COST = 50;
	private static final PlantTypes PLANT_TYPE = PlantTypes.AIRMONKEY;
	
	public Air_Monkey()	{
		super(DEFAULT_HP, DEFAULT_POWER, COST);
	}
	
	/**
	 * returns name of Peashooter
	 */
	public String toString() {
		return "Air Monkey";
	}

	public PlantTypes getPlantType() {
		return PLANT_TYPE;
	}

	@Override
	public void attack(Board board) {
		int row = getRow();
		int column = getCol();
		
		Zombie zombieTarget = board.getSingleAirTarget(row, column);
		
		if(zombieTarget != null)
		{
			LOG.debug(String.format("Air monkey at : (%d, %d) attacking Zombie at: (%d, %d)", 
					row, column, zombieTarget.getRow(), zombieTarget.getCol()));
			
			zombieTarget.takeDamage(getPower());
			
			removeZombie(zombieTarget, board);
		}
	}			
}
