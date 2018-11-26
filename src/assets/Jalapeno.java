package assets;

import java.util.ArrayList;

import engine.Board;
import util.Logger;

/**
 * Jalapeno Kills all Zombie in its row
 * @author Tanisha Garg
 *
 */

public class Jalapeno extends Plant{
	private static final Logger LOG = new Logger("Jalapeno");

	private static final int DEFAULT_HP = HEALTH_LOW;
	private static final int DEFAULT_POWER = ATTACK_INSTANT;
	private static final int COST = 125;
	private static final PlantTypes PLANT_TYPE = PlantTypes.JALAPENO;
	private boolean discharged = false;
	
	public Jalapeno() {
		super(DEFAULT_HP, DEFAULT_POWER, COST);
	}

	/**
	 * returns name of the plant
	 */
	@Override
	public String toString() {
		
		return "Jalapeno";
	}
	
	/**
	 * returns Plant Type
	 */
	@Override
	public PlantTypes getPlantType() {
		
		return PLANT_TYPE;
	}

	/**
	 * attack method kills all zombies of a row
	 */
	@Override
	public void attack(Board board) {
		int row = getRow();
		int column = getCol();
		
		ArrayList<Zombie> zombieInRow = new ArrayList<>();
		zombieInRow = (ArrayList<Zombie>) board.getRowTargets(row, column);
		
		if(zombieInRow!=null) {
			for(Zombie z : zombieInRow) {
				z.takeDamage(this.getPower());
				board.removeZombie(z.getRow(), z.getCol());
			}
		}
		
		//LOG.debug(String.format("Jalapeno at : (%d, %d) defeats all Zombies in row: (%d)", row));
		discharged = true;
	}
	
	public boolean getDischarged() {
		return discharged;
	}
}
