package input;

import engine.Board;
import engine.Purse;

/**
 * The Structure for a End Turn Command in the Command History Queue
 * @author David Wang
 */
public class EndTurnCommand extends Command {
	private static final long serialVersionUID = 1L;
	
	private Board oldBoard;
	private Purse userResources;
	
	public EndTurnCommand(Board board, Purse resources) {
		this.oldBoard = new Board(board);
		this.userResources = new Purse(resources);
	}
	
	public Board getBoard() {
		return oldBoard;
	}

	public Purse getResources() {
		return userResources;
	}
	
	@Override
	public CommandType getCommand() {
		return CommandType.ENDTURN;
	}
}
