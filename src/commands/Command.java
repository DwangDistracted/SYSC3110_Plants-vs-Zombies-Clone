package commands;

import java.io.Serializable;

/**
 * Prototype for a Command in the Command History Queue. These are used to Undo Moves.
 * @author David Wang
 */
public abstract class Command implements Serializable {
	private static final long serialVersionUID = 1L;

	public enum CommandType {
		PLACE,
		DIGUP,
		MOWER,
		ENDTURN
	}
	
	/**
	 * Returns the type of command
	 * @return
	 */
	public abstract CommandType getCommand();
}
