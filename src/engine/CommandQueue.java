package engine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import assets.Plant;
import assets.PlantTypes;
import assets.Zombie;
import commands.Command;
import commands.DigCommand;
import commands.EndTurnCommand;
import commands.MowCommand;
import commands.PlaceCommand;
import util.Logger;

/**
 * This is the Command History Queue for the Player throughout a Game
 * @author David Wang
 */
public class CommandQueue implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<GameListener> listeners; 
	private static Logger LOG = new Logger("Command Queue");
	private Game game;
	private LinkedList<Command> undoQueue;
	private LinkedList<Command> redoQueue;
	
	public CommandQueue(Game game, List<GameListener> listeners) {
		this.game = game;
		this.listeners = listeners;
		undoQueue = new LinkedList<Command>();
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
		undoQueue.addFirst(new PlaceCommand(type, x, y));
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
		undoQueue.addFirst(new DigCommand(type, x, y));
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
		undoQueue.addFirst(new MowCommand(plants.toArray(new Plant[plants.size()]), zombies.toArray(new Zombie[zombies.size()]), row));
		LOG.debug("registered mow command");
	}
	
	/**
	 * Adds an End Turn to the Command History
	 * @param board
	 */
	public void registerEndTurn(Board board) {
		redoQueue.clear(); //a new command prevents redo-ing old commands //a new command prevents redo-ing old commands
		undoQueue.addFirst(new EndTurnCommand(board, game.getPurse()));
		LOG.debug("registered end turn command");
	}
	
	/**
	 * Undos the most recent command
	 * @return
	 */
	public boolean undo() {
		if (undoQueue.isEmpty()) {
			LOG.debug("No Commands to undo");
			return false;
		}
		
		Command c = undoQueue.removeFirst();
		switch (c.getCommand()){
			case DIGUP:
				redoQueue.addFirst(c);
				game.getBoard().placePlant(PlantTypes.toPlant(((DigCommand)c).getType()), ((DigCommand)c).getLocX(), ((DigCommand)c).getLocY()); //re-place the plant
				for (GameListener gl : listeners) {
					gl.updateGrid(((DigCommand)c).getLocX(),((DigCommand)c).getLocY());
				}
				LOG.debug("undo dig command");
				break;
			case MOWER:
				//Mower not implementated for Milestone 3
				LOG.debug("undo mow command");
				break;
			case PLACE:
				redoQueue.addFirst(c);
				game.getBoard().removePlant(((PlaceCommand)c).getLocX(), ((PlaceCommand)c).getLocY()); //remove the plant
				game.getPurse().addPoints(PlantTypes.toPlant(((PlaceCommand)c).getType()).getCost()); //refund the plant
				for (GameListener gl : listeners) {
					gl.updateGrid(((PlaceCommand)c).getLocX(),((PlaceCommand)c).getLocY());
					gl.updatePurse();
				}
				LOG.debug("undo place command");
				break;
			case ENDTURN:
				redoQueue.addFirst(new EndTurnCommand(game.getBoard(), game.getPurse()));
				game.getBoard().setBoard(((EndTurnCommand)c).getBoard());
				game.getPurse().setPoints(((EndTurnCommand)c).getResources());
				game.decrementTurns();
				
				for (GameListener gl : listeners) {
					gl.updateAllGrids();
					gl.updatePurse();
					gl.updateTurnNumber();
				}
				
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
				undoQueue.addFirst(c); //allow us to undo redo
				game.getBoard().removePlant(((DigCommand)c).getLocX(), ((DigCommand)c).getLocY()); //re-place the plant
				for (GameListener gl : listeners) {
					gl.updateGrid(((DigCommand)c).getLocX(),((DigCommand)c).getLocY());
				}
				LOG.debug("redo dig command");
				break;
			case ENDTURN: //redo an end turn command
				undoQueue.addFirst(new EndTurnCommand(game.getBoard(), game.getPurse()));
				game.getBoard().setBoard(((EndTurnCommand)c).getBoard());
				game.getPurse().setPoints(((EndTurnCommand)c).getResources());
				game.incrementTurns();
				
				for (GameListener gl : listeners) {
					gl.updateAllGrids();
					gl.updatePurse();
					gl.updateTurnNumber();
				}
				LOG.debug("redo end turn command");
				break;
			case MOWER:
				//Mower not implementated for Milestone 3
				LOG.debug("redo mow command");
				break;
			case PLACE: //redo a place command
				undoQueue.addFirst(c);
				game.getBoard().placePlant(PlantTypes.toPlant(((PlaceCommand)c).getType()), ((PlaceCommand)c).getLocX(), ((PlaceCommand)c).getLocY()); //place the plant
				game.getPurse().spendPoints(PlantTypes.toPlant(((PlaceCommand)c).getType()).getCost()); //re-spend the plant cost
				
				for (GameListener gl : listeners) {
					gl.updateGrid(((PlaceCommand)c).getLocX(),((PlaceCommand)c).getLocY());
					gl.updatePurse();
				}
				LOG.debug("redo place command");
				break;
			default:
				break;
		}
		return true;
	}
}
