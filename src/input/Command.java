package input;

public abstract class Command {
	enum CommandType {
		PLACE,
		DIGUP,
		MOWER,
		ENDTURN
	}
	
	public abstract CommandType getCommand();
}
