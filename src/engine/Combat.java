package engine;

import assets.*;
import util.Logger;

/**
 * Handles combat calculations. Acts as a mediator between Plant and Zombie interaction.
 * 
 * @author Derek Shao
 *
 */
public class Combat {
	
	private static Logger LOG = new Logger("Combat");
 	
	private Board board;

	public Combat(Board board) {
		
		this.board = board; 
	}
	
	/**
	 * Performs a plant attack based on the type of plant.
	 * 
	 * @param source Plant that is attacking
	 */
	public void plantAttack(Plant source) {
		
		switch (source.getPlantType()) {
			case PEASHOOTER:
				peashooterAttack((Peashooter) source);
				break;
			default:
				break;
		}
	}
	
	/**
	 * Perform a Peashooter attack. Find and damage the first zombie
	 * target.
	 * 
	 * @param plant The peashooter Plant object
	 */
	public void peashooterAttack(Peashooter plant) {
		
		int plantRow = plant.getRow();
		
		Grid[][] gameBoard = board.getBoard();
		
		// iterate through the plant's row to find zombie to attack
		for (int col = plant.getCol(); col < gameBoard[plantRow].length; col++) {
			if (gameBoard[plantRow][col].getFirstZombie() != null) {
				
				Zombie zombieTarget = gameBoard[plantRow][col].getFirstZombie();
				
				LOG.debug(String.format("Peashooter at : (%d, %d) attacking Zombie at: (%d, %d)", 
						plant.getRow(), plant.getCol(), zombieTarget.getRow(), zombieTarget.getCol()));

				zombieTarget.takeDamage(plant.getPower());
				
				if (unitIsDead(zombieTarget)) {
					board.removeZombie(zombieTarget.getRow(), zombieTarget.getCol());
					LOG.debug("Plant at (" + plant.getRow() + "," + plant.getCol() + ") has defeated zombie at (" + zombieTarget.getRow() + "," + zombieTarget.getCol() + ")");

				}
			}
		}
	}
	
	/**
	 * Combat calculations for zombie attacking a plant
	 * 
	 * @param source Zombie that is attacking
	 * @param target Plant being attacked
	 * @return true if attacking resulted in killed unit, false otherwise
	 */
	public boolean zombieAttack(Zombie source, Plant target) {
		
		LOG.debug(String.format("Zombie at : (%d, %d) attacking Plant at: (%d, %d)", 
				source.getRow(), source.getCol(), target.getRow(), target.getCol()));
		
		target.takeDamage(source.getPower());

		return unitIsDead(target);

	}
	
	/**
	 * Check if a unit has died due to attack.
	 * 
	 * @param unit Unit that we are checking
	 * @return true if the unit is dead, false otherwise
	 */
	private boolean unitIsDead(Unit unit) {
		
		if (!unit.isAlive()) {
			LOG.debug(String.format("Unit at : (%d, %d) is dead", 
					unit.getRow(), unit.getCol()));
			return true;
		}
		
		return false;
	}
}