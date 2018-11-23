package assets;

import engine.Board;
import engine.Grid;
import util.Logger;

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
		Grid[][] gameBoard = board.getBoard();
		int row = getRow();
		int column = getCol();
		
		Zombie zombieTarget = gameBoard[row][column].getFirstZombie();
		
		LOG.debug(String.format("Peashooter at : (%d, %d) attacking Zombie at: (%d, %d)", 
				row, column, zombieTarget.getRow(), zombieTarget.getCol()));
		
		if(zombieTarget instanceof Air_Zombie)
		{
			zombieTarget.takeDamage(getPower());
		}
		
		if (!zombieTarget.isAlive()) {
			board.removeZombie(zombieTarget.getRow(), zombieTarget.getCol());
			LOG.debug(String.format("Peashooter at : (%d, %d) defeated Zombie at: (%d, %d)", 
					row, column, zombieTarget.getRow(), zombieTarget.getCol()));
		}
	}
		
		
				
}
