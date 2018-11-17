package unittests;

import assets.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import engine.Grid;

/**
 * Tests Grid Class
 * @author Tanisha Garg
 *
 */
public class GridTest {

	/**
	 * creates a new Grid object and tests setPlant(), getPlant() and removePlant() methods
	 */
	@Test
	public void testPlantMethods() {
		Grid g = new Grid(10, 10);
		Peashooter p = new Peashooter();
		assertTrue("True", g.setPlant(p));
		assertEquals("Peashooter", p, g.getPlant());
		g.removePlant();
		assertFalse("False", g.getPlant()==p);
	}
	
	/**
	 * creates a Grid object and tests getFirrstZombie(), getNumberOfZombies() and removeZombie() methods
	 */
	@Test
	public void testZombieMethods() {
		Grid g = new Grid(10, 10);
		Regular_Zombie z = new Regular_Zombie();
		g.addZombie(z);
		assertEquals("Regular Zombie", z, g.getFirstZombie());
		assertEquals("Number of zombies = 1", 1, g.getNumberOfZombies());
		assertEquals("Regular Zombie", z, g.removeZombie());
	}

}
