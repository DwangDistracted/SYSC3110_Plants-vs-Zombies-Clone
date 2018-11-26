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
	EXP_ZOMBIE,
	ENRAGED_ZOMBIE,
	JUK_ZOMBIE,
	AIR_ZOMBIE,
	RUSH_ZOMBIE,
	SPRINT_ZOMBIE,
	TANK_ZOMBIE,
	YETI_ZOMBIE,
	VAULTING_ZOMBIE;

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
			case EXP_ZOMBIE: 
				return new Exploding_Zombie();
			case JUK_ZOMBIE: 
				return new Juking_Zombie();
			case AIR_ZOMBIE:
				return new Air_Zombie();
			case RUSH_ZOMBIE:
				return new RushZombie();
			case SPRINT_ZOMBIE:
				return new SprintZombie();
			case TANK_ZOMBIE:
				return new TankZombie();
			case YETI_ZOMBIE:
				return new YetiZombie();
			case ENRAGED_ZOMBIE:
				return new Enraged_Zombie();
			case VAULTING_ZOMBIE:
				return new Vaulting_Zombie();
			default: 
				return null;
		}
	}
	
	public String toString() {
		switch (this) {
			case REG_ZOMBIE: 
				return "Meatbag Zombie";
			case EXP_ZOMBIE: 
				return "Exploding Zombie";
			case JUK_ZOMBIE: 
				return "Juking Zombie";
			case AIR_ZOMBIE:
				return "Air Zombie";
			case RUSH_ZOMBIE:
				return "Rush Zombie";
			case SPRINT_ZOMBIE:
				return "Sprint Zombie";
			case TANK_ZOMBIE:
				return "Tank Zombie";
			case YETI_ZOMBIE:
				return "Yeti Zombie";
			case ENRAGED_ZOMBIE:
				return "Enraged Zombie";
			case VAULTING_ZOMBIE:
				return "Vaulting Zombie";
			default: 
				return null;
		}
	}
}
