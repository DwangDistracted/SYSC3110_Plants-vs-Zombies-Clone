package input;

import engine.Board;

/**
 * The Structure for a End Turn Command in the Command History Queue
 * @author David Wang
 */
public class EndTurnCommand extends Command {
	private static final long serialVersionUID = 1L;
	
	private Board oldBoard;
	
	public EndTurnCommand(Board board) {
		this.oldBoard = new Board(board);
	}
	
	public Board getBoard() {
		return oldBoard;
	}
	
	@Override
	public CommandType getCommand() {
		return CommandType.ENDTURN;
	}
}
