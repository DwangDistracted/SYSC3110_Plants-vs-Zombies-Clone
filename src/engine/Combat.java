package engine;

import assets.*;
import engine.Grid;
import util.Logger;

/**
 * Handles combat calculations. Acts as a mediator between Plant and Zombie interaction.
 * 
 * @author Derek Shao
 *
 */
public class Combat {
	
	private static Logger LOG = new Logger("Combat");
 	
	private Grid[][] gameBoard;

	public Combat(Grid[][] board) {
		
		this.gameBoard = board; 
	}
	
	/**
	 * Combat calculations for a plant attacking a zombie. If a zombie was killed by this attack,
	 * the coordinates of the zombie are retrieved to allow game to remove zombie from the game.
	 * 
	 * @param source Plant that is attacking
	 * @return coordinates of the zombie that was killed, null otherwise
	 */
	public int[] plantAttack(Plant source) {
		
		int plantRow = source.getRow();
		int damage = source.getPower();
		
		// iterate through the plant's row to find zombie to attack
		for (int col = source.getCol(); col < gameBoard[plantRow].length; col++) {
			if (gameBoard[plantRow][col].getFirstZombie() != null) {
				
				Zombie zombieTarget = gameBoard[plantRow][col].getFirstZombie();
				
				LOG.info(String.format("Plant at : (%d, %d) attacking Zombie at: (%d, %d)", 
						source.getRow(), source.getCol(), zombieTarget.getRow(), zombieTarget.getCol()));
				
				zombieTarget.takeDamage(damage);
				
				if (unitIsDead(zombieTarget)) {
					int [] zombieToRemoveCoords = {zombieTarget.getRow(), zombieTarget.getCol()};
					return zombieToRemoveCoords;
				} else {
					return null;
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Combat calculations for zombie attacking a plant
	 * 
	 * @param source Zombie that is attacking
	 * @param target Plant being attacked
	 * @return true if attacking resulted in killed unit, false otherwise
	 */
	public boolean zombieAttack(Zombie source, Plant target) {
		
		LOG.info(String.format("Zombie at : (%d, %d) attacking Plant at: (%d, %d)", 
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
			LOG.info(String.format("Unit at : (%d, %d) is dead", 
					unit.getRow(), unit.getCol()));
			return true;
		}
		
		return false;
	}
}