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
	
	public Kernelpult() {
		super(DEFAULT_HP, DEFAULT_POWER, COST);
	}
	
	@Override
	public PlantTypes getPlantType() {
		
		return PLANT_TYPE;
	}

	@Override
	public void attack(Board board) {
		
		// use a random number generator to check if the next attack
		// can immobilize the zombie target
		Random random = new Random();
		
		int immobilizeThreshold = 4;
		
		int rollForImmobilizeAttack = random.nextInt(4) + 1;
		
		Grid[][] gameBoard = board.getBoard();
		int row = getRow();
		int column = getCol();
		
		for (int col = column; col < gameBoard[row].length; col++) {
			if (gameBoard[row][col].getFirstZombie() != null) {
				
				Zombie zombieTarget = gameBoard[row][col].getFirstZombie();
			
				zombieTarget.takeDamage(getPower());
				
				if (!zombieTarget.isAlive()) {
					board.removeZombie(zombieTarget.getRow(), zombieTarget.getCol());
				}
			}
		}
	}	
}
