package input;

import assets.Plant;
import assets.Zombie;

public class MowCommand extends Command {
	private Plant[] plantsRemoved;
	private Zombie[] zombiesRemoved;
	
	public MowCommand(Plant[] plants, Zombie[] zombies) {
		this.plantsRemoved = plants;
		this.zombiesRemoved = zombies;
	}
	
	public Plant[] getPlants() {
		return plantsRemoved;
	}

	public Zombie[] getZombies() {
		return zombiesRemoved;
	}
	
	@Override
	public CommandType getCommand() {
		return CommandType.MOWER;
	}

}
