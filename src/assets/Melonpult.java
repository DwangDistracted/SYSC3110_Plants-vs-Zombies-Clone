package assets;

import java.util.List;
import engine.Board;
import util.Logger;

/**
 * Class for plant type "Melonpult" which does high damage to
 * all zombies in a grid. 
 * 
 * @author Derek Shao
 *
 */
public class Melonpult extends Plant {
	private static Logger LOG = new Logger("Melonpult");

	
	private static final int DEFAULT_HP = HEALTH_MEDIUM;
	private static final int DEFAULT_POWER = ATTACK_HIGH;
	private static final int COST = 150;
	private static final PlantTypes PLANT_TYPE = PlantTypes.MELONPULT;

	public Melonpult() {
		super(DEFAULT_HP, DEFAULT_POWER, COST);
	}
	
	@Override
	public String toString() {
		
		return "Melon-Pult";
	}

	@Override
	public PlantTypes getPlantType() {

		return PLANT_TYPE;
	}

	@Override
	public void attack(Board board) {
		
		int row = getRow();
		int column = getCol();
		
		List<Zombie> zombieTargets = board.getGridTargets(row, column);
		
		if (zombieTargets != null) {
			
			LOG.debug(String.format("Melonpult at : (%d, %d) attacking Zombies at: (%d, %d)", 
					row, column, row, zombieTargets.get(0).getCol()));
			
			for (Zombie zombie : zombieTargets) {
				zombie.takeDamage(getPower());
				
				if (!zombie.isAlive()) {
					board.removeZombie(zombie.getRow(), zombie.getCol());
					LOG.debug(String.format("Melonpult at : (%d, %d) defeated Zombie at: (%d, %d)", 
							row, column, zombie.getRow(), zombie.getCol()));
				}	
			}
		}
	}
}
