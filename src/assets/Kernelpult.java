package assets;

import engine.Board;
import engine.Grid;
import util.Logger;

import java.util.Random;

/**
 * Class for the plant type "Kernelpult" which catapults corn.
 * The corn has a 25% chance of immobilizing zombies.
 * 
 * @author Derek Shao
 *
 */
public class Kernelpult extends Plant {

	private static Logger LOG = new Logger("Kernelpult");
	
	private static final int DEFAULT_HP = HEALTH_MEDIUM;
	private static final int DEFAULT_POWER = ATTACK_MEDIUM;
	private static final int COST = 100;
	private static final PlantTypes PLANT_TYPE = PlantTypes.KERNELPULT;
	
	private static final int IMMOBILIZE_ROLL_RANGE = 100;
	private static final int THRESHHOLD_FOR_IMMOBILIZATION = 75;
	
	public Kernelpult() {
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
				
				Zombie zombieTarget = gameBoard[row][col].getFirstZombie();
			
				LOG.debug(String.format("Kernelpult at : (%d, %d) attacking Zombie at: (%d, %d)", 
						row, column, zombieTarget.getRow(), zombieTarget.getCol()));
				
				zombieTarget.takeDamage(getPower());
					
				if (!zombieTarget.isAlive()) {
					board.removeZombie(zombieTarget.getRow(), zombieTarget.getCol());
					LOG.debug(String.format("Kernelpult at : (%d, %d) defeated Zombie at: (%d, %d)", 
							row, column, zombieTarget.getRow(), zombieTarget.getCol()));
				}
				else {
					// if the zombie didn't die, there is a chance for immobilization
					// use a random number generator to check if the next attack
					// can immobilize the zombie target
					Random random = new Random();
					
					if (random.nextInt(IMMOBILIZE_ROLL_RANGE) + 1 > THRESHHOLD_FOR_IMMOBILIZATION) {
						zombieTarget.immobilize();
					}
				}
			}
		}
	}	
}
