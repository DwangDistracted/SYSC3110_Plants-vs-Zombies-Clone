package engine;

import assets.Zombie;

public interface ZombieMoveListener {
	
	/**
	 * Invoked whenever a Zombie moves. Returns true if the move was successful, false otherwise.
	 * 
	 * @param zombie
	 * @return true if move was successful, false otherwise
	 */
	public boolean onZombieMove(Zombie zombie);
}
