package input;

import java.util.ArrayList;
import java.util.LinkedList;

import assets.Plant;
import assets.PlantTypes;
import assets.Zombie;
import engine.Board;
import engine.Grid;
import engine.Purse;
import ui.GameUI;
import util.Logger;

/**
 * Contains the command history for the player in a turn
 * @author David Wang
 */
public class CommandQueue extends LinkedList<Command> {
	private static final long serialVersionUID = 1L;
	
	private static Logger LOG = new Logger("Command Queue");
	private GameUI ui;
	private Board gameBoard;
	private Purse userResources;
	
	public CommandQueue(GameUI ui, Board gB, Purse userResources) {
		this.ui = ui;
		this.gameBoard = gB;
		this.userResources = userResources;
	}
	
	/**
	 * Adds a place command to the command history
	 * @param type the planttype that was placed
	 * @param x the location of the placement
	 * @param y the location of the placement
	 */
	public void registerPlace(PlantTypes type, int x, int y) {
		this.addFirst(new PlaceCommand(type, x, y));
		LOG.debug("registered place command");
	}

	/**
	 * Adds a digup command to the command history
	 * @param type the planttype that was dug up
	 * @param x the location of the placement
	 * @param y the location of the placement
	 */
	public void registerDig(PlantTypes type, int x, int y) {
		this.addFirst(new DigCommand(type, x, y));
		LOG.debug("registered dig command");
	}

	/**
	 * Adds a mower command to the command history
	 * @param grids the array of grids the mower affected
	 */
	public void registerMow(Grid[] grids) {
		ArrayList<Plant> plants = new ArrayList<>();
		ArrayList<Zombie> zombies = new ArrayList<>();
		for (Grid g : grids) {
			plants.add(g.getPlant()); //get all the plants the mower killed
			zombies.addAll(g.getZombies()); //get all the zombies the mower killed
		}
		this.addFirst(new MowCommand(plants.toArray(new Plant[plants.size()]), zombies.toArray(new Zombie[zombies.size()])));
		LOG.debug("registered mow command");
	}
	
	/**
	 * Undos the most recent command
	 * @return
	 */
	public boolean undo() {
		if (this.isEmpty()) {
			LOG.debug("No Commands to undo");
			return false;
		}
		
		Command c = this.removeFirst();
		switch (c.getCommand()){
			case DIGUP:
				gameBoard.placePlant(PlantTypes.toPlant(((DigCommand)c).getType()), ((DigCommand)c).getLocX(), ((DigCommand)c).getLocY()); //re-place the plant
				ui.getBoardTiles()[((DigCommand)c).getLocX()][((DigCommand)c).getLocY()].renderPlant();
				ui.getBoardTiles()[((DigCommand)c).getLocX()][((DigCommand)c).getLocY()].repaint();
				ui.getBoardTiles()[((DigCommand)c).getLocX()][((DigCommand)c).getLocY()].revalidate(); //need both repaint and revalidate for the image to show up properly
				LOG.debug("undo dig command");
				break;
			case MOWER:
				LOG.debug("undo mow command");
				break;
			case PLACE:
				gameBoard.removePlant(((PlaceCommand)c).getLocX(), ((PlaceCommand)c).getLocY()); //remove the plant
				ui.getBoardTiles()[((PlaceCommand)c).getLocX()][((PlaceCommand)c).getLocY()].renderPlant();
				
				userResources.addPoints(PlantTypes.toPlant(((PlaceCommand)c).getType()).getCost()); //refund the plant
				ui.setPointsLabel(userResources.getPoints());
				LOG.debug("undo place command");
				break;
			default:
				break;
		}
		return true;
	}
}
