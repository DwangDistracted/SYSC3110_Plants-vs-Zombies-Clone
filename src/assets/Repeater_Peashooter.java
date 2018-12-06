package assets;

import java.io.Serializable;
import java.util.ArrayList;

import engine.Board;
import util.Logger;

public class Repeater_Peashooter extends Plant implements Serializable{
private static Logger LOG = new Logger("Repeated Peashooter");
	
	private static final int DEFAULT_HP = HEALTH_MEDIUM;
	private static final int DEFAULT_POWER = ATTACK_LOW;
	private static final int COST = 150;
	private static final PlantTypes PLANT_TYPE = PlantTypes.REPEATER_PEASHOOTER;
	
	public Repeater_Peashooter() {
		super(DEFAULT_HP, DEFAULT_POWER, COST);
	}
	
	/**
	 * returns name of the type of Peashooter
	 */
	public String toString() {
		return "Repeater Peashooter";
	}

	/**
	 * returns Plant Type
	 */
	public PlantTypes getPlantType() {
		return PLANT_TYPE;
	}
	
	@Override
	public void attack(Board board) {
		int row = getRow();
		int column = getCol();
		
		ArrayList<Zombie> zombieInRow = new ArrayList<>();
		zombieInRow = (ArrayList<Zombie>) board.getRowTargets(row, column);
		
		if(zombieInRow!=null) {
			for(Zombie z : zombieInRow) {
				LOG.debug(String.format("Repeater Peashooter at : (%d, %d) attacking Zombie at: (%d, %d)", 
						row, column, z.getRow(), z.getCol()));
				z.takeDamage(getPower()); // attacking all zombies in a row
				removeZombie(z, board);
			}
		}
		
	}		
}
