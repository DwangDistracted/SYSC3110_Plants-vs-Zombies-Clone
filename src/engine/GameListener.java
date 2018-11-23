package engine;

/**
 * The Views that listen to the game need to 
 * @author david
 *
 */
public interface GameListener {
	public void updateGrid(int x, int y);
	public void updateAllGrids();
	public void updatePurse();
	public void updateTurnNumber();
	public void updateEndTurn();
	public void updateMessage(String title, String message);
}
