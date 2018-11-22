package assets;

import java.util.Queue;

import engine.Board;
import engine.Grid;
import util.Logger;

public class Melonpult extends Plant {
	private static Logger LOG = new Logger("Melonpult");

	
	private static final int DEFAULT_HP = HEALTH_MEDIUM;
	private static final int DEFAULT_POWER = ATTACK_HIGH;
	private static final int COST = 150;
	private static final PlantTypes PLANT_TYPE = PlantTypes.MELONPULT;

	public Melonpult() {
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
				
				LOG.debug(String.format("Melonpult at : (%d, %d) attacking Zombies at: (%d, %d)", 
						row, column, row, col));
				
				Queue<Zombie> zombiesOnGrid = gameBoard[row][col].getZombies();
				
				for (Zombie zombie : zombiesOnGrid) {
					zombie.takeDamage(getPower());
					
					if (!zombie.isAlive()) {
						board.removeZombie(zombie.getRow(), zombie.getCol());
						LOG.debug(String.format("Melonpult at : (%d, %d) defeated Zombie at: (%d, %d)", 
								row, column, zombie.getRow(), zombie.getCol()));
					}
				}
				
				break;
			}
		}
	}
}
