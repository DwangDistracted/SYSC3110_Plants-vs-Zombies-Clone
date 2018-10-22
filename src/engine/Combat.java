package engine;

import assets.*;
import levels.Grid;

/**
 * Handles combat calculations. Acts as a mediator between Plant and Zombie interaction.
 * 
 * @author Derek Shao
 *
 */
public class Combat {
 	
	private Grid[][] gameBoard;

	public Combat(Grid[][] board) {
		
		this.gameBoard = board; 
	}
	
	/**
	 * Combat calcultions for a plant attacking a zombie.
	 * 
	 * @param source Plant that is attacking
	 * @return true if target dies due to attack, false otherwise
	 */
	public boolean plantAttack(Plant source) {
		
		int plantRow = source.getRow();
		int damage = source.getDamage();
		
		// iterate through the plant's row to find zombie to attack
		for (int col = 0; col < gameBoard[plantRow].length; col++) {
			if (gameBoard[plantRow][col].getFirstZombie() != null) {
				
				Zombie zombieTarget = gameBoard[plantRow][col].getFirstZombie();
				zombieTarget.takeDamage(damage);
				
				return unitIsDead(zombieTarget);
			}
		}
		
		return false;
	}
	
	/**
	 * Combat calculations for zombie attacking a plant
	 * 
	 * @param source Zombie that is attacking
	 * @param target Plant being attacked
	 * @return true if attacking resulted in killed unit, false otherwise
	 */
	public boolean zombieAttack(Zombie source, Plant target) {
		
		target.takeDamage(source.getDamage());
		
		return unitIsDead(target);

	}
	
	/**
	 * Check if a unit has died due to attack.
	 * 
	 * @param unit Unit that we are checking
	 * @return true if the unit is dead, false otherwise
	 */
	private boolean unitIsDead(Unit unit) {
		
		return unit.getHP() <= 0;
	}
}