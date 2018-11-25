package commands;

import assets.Plant;
import assets.Zombie;

/**
 * The Structure for a Mow Command in the Command History Queue
 * @author David Wang
 */
public class MowCommand extends Command {
	private static final long serialVersionUID = 1L;
	private Plant[] plantsRemoved;
	private Zombie[] zombiesRemoved;
	private int row;
	
	public MowCommand(Plant[] plants, Zombie[] zombies, int row) {
		this.plantsRemoved = plants;
		this.zombiesRemoved = zombies;
		this.row = row;
	}
	
	public Plant[] getPlants() {
		return plantsRemoved;
	}

	public Zombie[] getZombies() {
		return zombiesRemoved;
	}
	
	public int getRow() {
		return row;
	}
	
	@Override
	public CommandType getCommand() {
		return CommandType.MOWER;
	}

}
