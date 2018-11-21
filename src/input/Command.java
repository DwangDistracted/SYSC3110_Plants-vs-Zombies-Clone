package input;

public abstract class Command {
	enum CommandType {
		PLACE,
		DIGUP,
		MOWER
	}
	
	public abstract CommandType getCommand();
}
