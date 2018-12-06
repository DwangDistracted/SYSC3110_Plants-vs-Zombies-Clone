package assets;

import java.io.Serializable;

import engine.Board;

/**
 * Enarged zombie spawns a regular type zombie once killed
 * @author Tanisha Garg
 *
 */
public class Enraged_Zombie extends Zombie implements Serializable{
	private static final int DEFAULT_SPEED = SPEED_LOW;
	private static final int DEFAULT_POWER = ATTACK_LOW;
	private static final int DEFAULT_HP = HEALTH_MEDIUM;
	private static final ZombieTypes ZOMBIE_TYPE = ZombieTypes.ENRAGED_ZOMBIE;
	
	public Enraged_Zombie()	{
		super(DEFAULT_SPEED, DEFAULT_POWER, DEFAULT_HP);
	}

	/**
	 * returns the name of zombie
	 */
	@Override 
	public String toString() {
		
		return ZOMBIE_TYPE.toString();
	}

	public ZombieTypes getZombieType() {
		return ZOMBIE_TYPE;
	}
	
	@Override
	public int getDefaultSpeed() {
		return DEFAULT_SPEED;
	}
		
	/**
	 * method to spawn zombie once enraged zombie is killed
	 * @param board
	 */
	public void spawnZombie(Board board) {
		int row = this.getRow();
		int column = this.getCol();
		if(this.getHP()==0) {
			Zombie zombie = ZombieTypes.toZombie(ZombieTypes.REG_ZOMBIE);
			zombie.setListener(board);
			zombie.setRow(row);
			zombie.setColumn(column);
			board.placeZombie(zombie, row, column);
		}
	}

}
