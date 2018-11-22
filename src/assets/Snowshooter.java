package assets;

import java.util.Queue;

import engine.Board;
import engine.Grid;
import util.Logger;

public class Snowshooter extends Plant {
	
	private static final Logger LOG = new Logger("Snowshooter");

	private static final int DEFAULT_HP = HEALTH_LOW;
	private static final int DEFAULT_POWER = ATTACK_MEDIUM;
	private static final int COST = 100;
	private static final PlantTypes PLANT_TYPE = PlantTypes.SNOWSHOOTER;
	
	private static final int ATTACK_SPEED_REDUCTION = 1;
	private static final int SPEED_REDUCTION_DURATION = 3;
	
	public Snowshooter() {
		super(DEFAULT_HP, DEFAULT_POWER, COST);
	}

	@Override
	public PlantTypes getPlantType() {
		
		return PLANT_TYPE;
	}

	@Override
	public void attack(Board board) {
		Grid[][] gameBoard = board.getBoard();
		int row = getRow();
		int column = getCol();
		
		for (int col = column; col < gameBoard[row].length; col++) {
			if (gameBoard[row][col].getFirstZombie() != null) {
				
				LOG.debug(String.format("Snowshooter at : (%d, %d) attacking Zombies at: (%d, %d)", 
						row, column, row, col));	
				
				Zombie zombieTarget = gameBoard[row][col].getFirstZombie();
				
				zombieTarget.takeDamage(getPower());
				zombieTarget.speedDebuff(ATTACK_SPEED_REDUCTION, SPEED_REDUCTION_DURATION);
				
				if (!zombieTarget.isAlive()) {
					board.removeZombie(row, col);
					LOG.debug(String.format("Snowshooter at : (%d, %d) defeated Zombies at: (%d, %d)", 
							row, column, row, col));
				}
			}
		}
	}
}
