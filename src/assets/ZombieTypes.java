package assets;

/**
 * This is the listing of all zombie types we have
 * 
 * Useful for command parsing and spawning
 * @author David Wang
 *
 */
public enum ZombieTypes {
	REG_ZOMBIE,
	RUSH_ZOMBIE,
	SPRINT_ZOMBIE,
	TANK_ZOMBIE,
	YETI_ZOMBIE;

	/**
	 * Translates a ZombieTypes Enumeration into a Zombie Object
	 * @param e the Zombie Type
	 * @return Zombie Object
	 */
	public static Zombie toZombie(ZombieTypes e) 
	{
		switch (e) {
			case REG_ZOMBIE:
				return new Regular_Zombie();
			case RUSH_ZOMBIE:
				return new RushZombie();
			case SPRINT_ZOMBIE:
				return new SprintZombie();
			case TANK_ZOMBIE:
				return new TankZombie();
			case YETI_ZOMBIE:
				return new YetiZombie();
			default: 
				return null;
		}
	}
}
