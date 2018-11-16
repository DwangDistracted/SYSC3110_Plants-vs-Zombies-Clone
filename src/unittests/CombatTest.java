package unittests;

import static org.junit.Assert.assertFalse;
import engine.*;
import assets.*;

import org.junit.jupiter.api.Test;

/**
 * Tests Combat Class
 * @author Tanisha Garg
 *
 */
public class CombatTest {
	
	@Test
	public void testPlantAttack() {
		Board b = new Board(1, 8);
		Combat c = new Combat(b.getBoard());
		b.displayBoard();
		Peashooter p = new Peashooter();
		Regular_Zombie z = new Regular_Zombie();
		b.placePlant(p, 0, 0);
		b.placeZombie(z, 0, 7);
		int turnsRequiredToKillZombies = z.getHP()/p.getPower();
		if (z.getHP()%p.getHP() != 0) turnsRequiredToKillZombies++;
		for(int i = 0; i<turnsRequiredToKillZombies; i++) {
			c.plantAttack(p);
		}
		assertFalse("Zombie is dead", z.isAlive());
	}
	
	@Test
	public void testZombieAttack() {
		Combat c = new Combat(new Grid[1][1]);
		Board b = new Board(1,8);
		Peashooter p = new Peashooter();
		Regular_Zombie z = new Regular_Zombie();
		b.placePlant(p, 0, 0);
		b.placeZombie(z, 0, 7);
		int turnsRequiredToKillZombies = p.getHP()/z.getPower();
		if (p.getHP()%z.getHP() != 0) turnsRequiredToKillZombies++;
		
		if(b.onZombieMove(z)==false){
			for(int i = 0; i<turnsRequiredToKillZombies; i++) {
				c.zombieAttack(z, p);
			}
			
		}
		assertFalse("Plant is dead", p.isAlive());
	}
	
}
