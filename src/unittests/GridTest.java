package unittests;

import assets.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import engine.Grid;

/**
 * Test Grid Class
 * @author Tanisha Garg
 *
 */
public class GridTest {

	@Test
	public void testPlantMethods() {
		Grid g = new Grid(10, 10);
		Peashooter p = new Peashooter();
		assertTrue("True", g.setPlant(p));
		assertEquals("Peashooter", p, g.getPlant());
		g.removePlant();
		assertFalse("False", g.getPlant()==p);
	}
	
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
