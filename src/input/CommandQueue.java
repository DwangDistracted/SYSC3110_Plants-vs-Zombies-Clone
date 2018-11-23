package input;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

import assets.Plant;
import assets.PlantTypes;
import assets.Zombie;
import engine.Board;
import engine.Game;
import engine.Grid;
import engine.Purse;
import ui.GameUI;
import util.Logger;

/**
 * This is the Command History Queue for the Player throughout a Game
 * @author David Wang
 */
public class CommandQueue extends LinkedList<Command> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private static Logger LOG = new Logger("Command Queue");
	private Game game;
	private GameUI ui;
	private Board gameBoard;
	private Purse userResources;
	
	private LinkedList<Command> redoQueue;
	
	public CommandQueue(Game game, GameUI ui, Board gB, Purse userResources) {
		this.game = game;
		this.ui = ui;
		this.gameBoard = gB;
		this.userResources = userResources;
		
		redoQueue = new LinkedList<Command>();
	}
	
	/**
	 * Adds a Place Command to the Command History
	 * @param type the planttype that was placed
	 * @param x the location of the placement
	 * @param y the location of the placement
	 */
	public void registerPlace(PlantTypes type, int x, int y) {
		redoQueue.clear(); //a new command prevents redo-ing old commands
		this.addFirst(new PlaceCommand(type, x, y));
		LOG.debug("registered place command");
	}

	/**
	 * Adds a Digup Command to the Command History
	 * @param type the planttype that was dug up
	 * @param x the location of the placement
	 * @param y the location of the placement
	 */
	public void registerDig(PlantTypes type, int x, int y) {
		redoQueue.clear(); //a new command prevents redo-ing old commands
		this.addFirst(new DigCommand(type, x, y));
		LOG.debug("registered dig command");
	}

	/**
	 * Adds a Mower Command to the Command History
	 * @param grids the array of grids the mower affected
	 */
	public void registerMow(Grid[] grids, int row) {
		redoQueue.clear(); //a new command prevents redo-ing old commands
		ArrayList<Plant> plants = new ArrayList<>();
		ArrayList<Zombie> zombies = new ArrayList<>();
		for (Grid g : grids) {
			plants.add(g.getPlant()); //get all the plants the mower killed
			zombies.addAll(g.getZombies()); //get all the zombies the mower killed
		}
		this.addFirst(new MowCommand(plants.toArray(new Plant[plants.size()]), zombies.toArray(new Zombie[zombies.size()]), row));
		LOG.debug("registered mow command");
	}
	
	/**
	 * Adds an End Turn to the Command History
	 * @param board
	 */
	public void registerEndTurn(Board board) {
		redoQueue.clear(); //a new command prevents redo-ing old commands //a new command prevents redo-ing old commands
		this.addFirst(new EndTurnCommand(board, userResources));
		LOG.debug("registered end turn command");
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
				redoQueue.addFirst(c);
				gameBoard.placePlant(PlantTypes.toPlant(((DigCommand)c).getType()), ((DigCommand)c).getLocX(), ((DigCommand)c).getLocY()); //re-place the plant
				ui.getBoardTiles()[((DigCommand)c).getLocX()][((DigCommand)c).getLocY()].renderPlant();
				ui.getBoardTiles()[((DigCommand)c).getLocX()][((DigCommand)c).getLocY()].repaint();
				ui.getBoardTiles()[((DigCommand)c).getLocX()][((DigCommand)c).getLocY()].revalidate(); //need both repaint and revalidate for the image to show up properly
				LOG.debug("undo dig command");
				break;
			case MOWER:
				//Mower not implementated for Milestone 3
				LOG.debug("undo mow command");
				break;
			case PLACE:
				redoQueue.addFirst(c);
				gameBoard.removePlant(((PlaceCommand)c).getLocX(), ((PlaceCommand)c).getLocY()); //remove the plant
				ui.getBoardTiles()[((PlaceCommand)c).getLocX()][((PlaceCommand)c).getLocY()].renderPlant();
				
				userResources.addPoints(PlantTypes.toPlant(((PlaceCommand)c).getType()).getCost()); //refund the plant
				ui.setPointsLabel(userResources.getPoints());
				LOG.debug("undo place command");
				break;
			case ENDTURN:
				redoQueue.addFirst(new EndTurnCommand(gameBoard, userResources));
				gameBoard.setBoard(((EndTurnCommand)c).getBoard());
				userResources.setPoints(((EndTurnCommand)c).getResources());
				game.decrementTurns();
				
				ui.setTurnLabel(game.getTurns());
				ui.setPointsLabel(userResources.getPoints());
				ui.refreshAllGrids();
				LOG.debug("undo end turn command");
				break;
			default:
				break;
		}
		return true;
	}
	
	/**
	 * Redos the most recent undo. Allows redo until the no more undone commands OR user issues a new command
	 * @return
	 */
	public boolean redo() {
		if (redoQueue.isEmpty()) {
			LOG.debug("No Commands to undo");
			return false;
		}
		
		Command c = redoQueue.removeFirst();
		switch (c.getCommand()){
			case DIGUP: //redo a digup command
				this.addFirst(c); //allow us to undo redo
				gameBoard.removePlant(((DigCommand)c).getLocX(), ((DigCommand)c).getLocY()); //re-place the plant
				ui.getBoardTiles()[((DigCommand)c).getLocX()][((DigCommand)c).getLocY()].renderPlant();
				ui.getBoardTiles()[((DigCommand)c).getLocX()][((DigCommand)c).getLocY()].repaint();
				ui.getBoardTiles()[((DigCommand)c).getLocX()][((DigCommand)c).getLocY()].revalidate(); //need both repaint and revalidate for the image to show up properly
				LOG.debug("redo dig command");
				break;
			case ENDTURN: //redo an end turn command
				this.addFirst(new EndTurnCommand(gameBoard, userResources));
				gameBoard.setBoard(((EndTurnCommand)c).getBoard());
				userResources.setPoints(((EndTurnCommand)c).getResources());
				game.incrementTurns();
				
				ui.setTurnLabel(game.getTurns());
				ui.setPointsLabel(userResources.getPoints());
				ui.refreshAllGrids();
				LOG.debug("redo end turn command");
				break;
			case MOWER:
				//Mower not implementated for Milestone 3
				LOG.debug("redo mow command");
				break;
			case PLACE: //redo a place command
				this.addFirst(c);
				gameBoard.placePlant(PlantTypes.toPlant(((PlaceCommand)c).getType()), ((PlaceCommand)c).getLocX(), ((PlaceCommand)c).getLocY()); //place the plant
				ui.getBoardTiles()[((PlaceCommand)c).getLocX()][((PlaceCommand)c).getLocY()].renderPlant();
				
				userResources.spendPoints(PlantTypes.toPlant(((PlaceCommand)c).getType()).getCost()); //re-spend the plant cost
				ui.setPointsLabel(userResources.getPoints());
				LOG.debug("redo place command");
				break;
			default:
				break;
		}
		return true;
	}
}
