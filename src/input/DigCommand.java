package input;

import assets.PlantTypes;

public class DigCommand extends Command {
	public PlantTypes getType() {
		return type;
	}

	public int getLocX() {
		return locX;
	}

	public int getLocY() {
		return locY;
	}

	private PlantTypes type;
	private int locX;
	private int locY;
	
	public DigCommand(PlantTypes plant, int x, int y) {
		this.type = plant;
		this.locX = x;
		this.locY = y;
	}
	
	@Override
	public CommandType getCommand() {
		return CommandType.DIGUP;
	}

}
