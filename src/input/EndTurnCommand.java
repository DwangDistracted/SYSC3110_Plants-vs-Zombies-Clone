package input;

import engine.Board;

public class EndTurnCommand extends Command {
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
