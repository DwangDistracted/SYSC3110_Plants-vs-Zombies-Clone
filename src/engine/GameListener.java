package engine;

/**
 * The Views that listen to the game need to 
 * @author David Wang
 *
 */
public interface GameListener {
	/**
	 * Tells the game listener that a specific grid has changed
	 * @param x
	 * @param y
	 */
	public void updateGrid(int x, int y);
	/**
	 * Tells the game listener that all the grids have changed
	 */
	public void updateAllGrids();
	/**
	 * Tells the game listener that the purse has changed
	 */
	public void updatePurse();
	/**
	 * Tells the game listener that the turn number has changed
	 */
	public void updateTurnNumber();
	/**
	 * Tells the game listener that there has been an end turn sequence
	 */
	public void updateEndTurn();
	/**
	 * Tells the game listener to process a message from the game
	 * @param title - the title of the message
	 * @param message - the contents of the message
	 */
	public void updateMessage(String title, String message);
}
