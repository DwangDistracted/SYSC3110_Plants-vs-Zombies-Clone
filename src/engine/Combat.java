package engine;

import assets.Unit;

/**
 * Handles combat calculations. Acts as a mediator between Plant and Zombie interaction.
 * 
 * @author Derek Shao
 *
 */
public class Combat {
	
	/* Singleton */
	private Combat() {}
	
	/**
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public static boolean attack(Unit source, Unit target) {
		
		/**
		 * 
		 * 1. Get source's power level
		 * 2. Deduct the target's hit points based on source's power level
		 * 3. Return true if target is dead, false otherwise
		 * 
		 */
		return false;
	}
}
