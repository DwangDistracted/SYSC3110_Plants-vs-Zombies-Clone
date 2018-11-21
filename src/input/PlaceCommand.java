package input;

import assets.PlantTypes;

public class PlaceCommand extends Command {
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
	
	public PlaceCommand(PlantTypes plant, int x, int y) {
		this.type = plant;
		this.locX = x;
		this.locY = y;
	}
	
	@Override
	public CommandType getCommand() {
		return CommandType.PLACE;
	}

}
