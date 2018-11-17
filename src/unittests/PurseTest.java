package unittests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import engine.*;

/**
 * Test Purse Class
 * @author Tanisha Garg
 *
 */
public class PurseTest {

	/**
	 * tests Purse constructor
	 */
	@Test
	public void testPurseConstructor() {
		Purse p = new Purse(25);
		assertEquals("Points = 25", 25, p.getPoints());
	}
	
	/**
	 * creates a Purse object and tests addPoints() and getPoints() methods
	 */
	@Test
	public void testAddPoints() {
		Purse p = new Purse(25);
		p.addPoints(25);
		assertEquals("Points = 50", 50, p.getPoints());
	}
	
	/**
	 * creates Purse object and tests canSpend and spendPonts() methods
	 */
	@Test
	public void testSpendPointsMethods() {
		Purse p = new Purse(50);
		assertTrue("True", p.canSpend(25));
		assertTrue("True", p.spendPoints(25));
	}

}
