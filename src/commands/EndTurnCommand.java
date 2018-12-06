package commands;

import java.io.Serializable;
import java.util.ArrayList;

import engine.Board;
import engine.Purse;

/**
 * The Structure for a End Turn Command in the Command History Queue
 * @author David Wang
 */
public class EndTurnCommand extends Command implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Board oldBoard;
	private Purse userResources;
	private ArrayList<Integer> mowers;
	
	public EndTurnCommand(Board board, Purse resources) {
		this.oldBoard = new Board(board);
		this.userResources = new Purse(resources);
		this.mowers = new ArrayList<Integer>();
	}
	
	public Board getBoard() {
		return oldBoard;
	}

	public Purse getResources() {
		return userResources;
	}
	
	public void addMowerRow(int row){
		mowers.add(row);
	}
	public void setMowerList(ArrayList<Integer> mower){
		this.mowers = mower;
	}
	public ArrayList<Integer> getMowerList(){
		return mowers;
	}
	@Override
	public CommandType getCommand() {
		return CommandType.ENDTURN;
	}
}
