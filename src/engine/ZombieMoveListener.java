package engine;

import assets.Zombie;

/**
 * The listener interface for when a Zombie object moves. Board will update the location of 
 * the Zombie object that moved. 
 * 
 * @author Derek Shao
 *
 */
public interface ZombieMoveListener {
	
	/**
	 * Invoked whenever a Zombie moves. Returns true if the move was successful, false otherwise.
	 * 
	 * @param zombie
	 * @return true if move was successful, false otherwise
	 */
	public boolean onZombieMove(Zombie zombie, Game game);
}
