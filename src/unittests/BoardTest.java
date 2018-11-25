package unittests;

import assets.*;
import engine.*;
import levels.LevelInfo;
import levels.LevelLoader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Tests Board Class
 * @author Tanisha Garg
 *
 */
public class BoardTest {

	/**
	 * tests Board constructor
	 */
	@Test
	public void testBoardConstructor() {
		Board b = new Board(4, 1);
		assertEquals("Row should be 4 ", 4, b.getRow());
		assertEquals("Column should be 1", 1, b.getColumn());	
		assertFalse("Zombies have not reached end", b.hasReachedEnd(0));
		assertEquals("Number of sunflower", 0, b.getNumberOfSF());
	}
	
	/**
	 * tests getBoard() method
	 */
	@Test
	public void testGetBoard() {
		Board b = new Board(4, 1);
		int expected = new Grid[4][1].length;
		int current = b.getBoard().length;
		assertTrue("Grid with rows = 4 and columns = 1", expected == current);
	}
	
	/**
	 * tests placePlant() method
	 */
	@Test
	public void testPlacePlant() {
		Board b = new Board(4,1);
		assertTrue("Plant Placed.", b.placePlant(new Flower(), 0, 0));
	}
	
	/**
	 * tests placeZombie() method
	 */
	@Test
	public void testPlaceZombie() {
		Board b = new Board(1,4);
		assertTrue("Zombie Placed.", b.placeZombie(new Regular_Zombie(), 0, 3));
	}
	
	/**
	 * tests all getPlant() Methods
	 */
	@Test
	public void testGetPlantMethods() {
		Board b = new Board(1,4);
		Flower f = new Flower();
		Peashooter p = new Peashooter();
		b.placePlant(f, 0, 0);
		b.placePlant(p, 0, 1);
		assertEquals("Number of sunflower = 1", 1, b.getNumberOfSF());
		assertTrue("True", b.getPlantsInGame().contains(f));
		assertTrue("True", b.getPlantsInGame().contains(p));
		assertEquals("Flower", f, b.getPlant(0, 0));
		assertEquals("Peashooter", p, b.getPlant(0, 1));
	}
	
	/**
	 * tests all getZombie() methods
	 */
	@Test
	public void testGetZombieMethods() {
		Board b = new Board(1,4);
		Zombie z = new Regular_Zombie();
		b.placeZombie(z, 0, 3);
		assertEquals("Number of Zombies = 1", 1, b.getNumberOfZombies());
		assertTrue("True", b.getZombiesInGame().contains(z));
		assertEquals("Regular Zombie", z, b.getZombie(0, 3));
	}
	
	/**
	 * tests removePlant() method
	 */
	@Test
	public void testRemovePlant() {
		Board b = new Board(1,4);
		Peashooter p = new Peashooter();
		Flower f = new Flower();
		b.placePlant(f, 0, 0);
		b.placePlant(p, 0, 1);
		b.displayBoard();
		b.removePlant(0, 1);
		assertEquals("This should be null" , null , b.getPlant(0, 1));
	}
	
	/**
	 * tests removeZombie() method
	 */
	@Test
	public void testRemoveZombie() {
		Board b = new Board(4,1);
		Zombie z = new Regular_Zombie();
		b.placeZombie(z, 3, 0);
		b.removeZombie(3, 0);
		assertFalse("False", b.getZombiesInGame().contains(z));
	}
	
	/**
	 * tests testOnZombieMove() method
	 */
	@Test
	public void testOnZombieMove() {
		LevelLoader.init();
		LevelInfo lvl = LevelLoader.getLevel(1);
		Game game = new Game(lvl);
		Board b = new Board(2,4);
		Regular_Zombie z1 = new Regular_Zombie();
		Regular_Zombie z2 = new Regular_Zombie();
		z1.setRow(0);
		z1.setColumn(2);
		z2.setRow(1);
		z2.setColumn(3);
		z1.setListener(b);
		z2.setListener(b);
		b.displayBoard();
		
		Peashooter p = new Peashooter();
		b.placePlant(p, 0, 2);
		b.placeZombie(z1, z1.getRow(), z1.getCol());
		assertFalse("False",z1.move());
		b.displayBoard();
		
		b.placeZombie(z2, z2.getRow(), z2.getCol());
		b.displayBoard();
		assertTrue("True", z2.move());//error
	}
	
	
	/**
	 * tests testOnZombieMove() method
	 */
	@Test
	public void testgetRowUnits() {
		Board b = new Board(8,8);
		Regular_Zombie z1 = new Regular_Zombie();
		Regular_Zombie z2 = new Regular_Zombie();
		Regular_Zombie z3 = new Regular_Zombie();
		Regular_Zombie z4 = new Regular_Zombie();
		z1.setRow(0);
		z1.setColumn(2);
		b.placeZombie(z1, z1.getRow(), z1.getCol());
		z2.setRow(0);
		z2.setColumn(3);
		b.placeZombie(z2, z2.getRow(), z2.getCol());
		z3.setRow(0);
		z3.setColumn(7);
		z1.setListener(b);
		z2.setListener(b);
		z4.setRow(0);
		z4.setColumn(6);
		
		Peashooter p = new Peashooter();
		Peashooter p2 = new Peashooter();
		Peashooter p3 = new Peashooter();
		Peashooter p4 = new Peashooter();
		b.placePlant(p, 0, 2);
		b.placePlant(p2, 0, 5);
		b.placePlant(p3, 0, 6);
		b.placePlant(p4, 0, 7);
		
		ArrayList<Unit> unitList = new ArrayList<Unit>();
		unitList.add(z1);
		unitList.add(p);
		unitList.add(z2);
		unitList.add(p2);
		unitList.add(p3);
		
		
		for(int i = 0; i < unitList.size();i++)
		{
			assertEquals(unitList.get(i),b.getRowUnits(0).get(i));
		}
		unitList.add(z3);
		b.placeZombie(z3, z3.getRow(), z3.getCol());
		unitList.add(p4);
		for(int i = 0; i < unitList.size();i++)
		{
			assertEquals(unitList.get(i),b.getRowUnits(0).get(i));
		}
	}
	
	/**
	 * Tests the UseLawnMower method. 
	 * Assumes the getRowUnits is working as intended
	 */
	@Test
	public void testUseLawnMower()
	{
		Board b = new Board(8,8);
		Regular_Zombie z1 = new Regular_Zombie();
		Regular_Zombie z2 = new Regular_Zombie();
		Regular_Zombie z3 = new Regular_Zombie();
		Regular_Zombie z4 = new Regular_Zombie();
		b.placeZombie(z1, 0, 2);
		b.placeZombie(z2, 0, 3);
		b.placeZombie(z3, 0, 6);
		b.placeZombie(z4, 0, 7);
		
		Peashooter p = new Peashooter();
		Peashooter p2 = new Peashooter();
		b.placePlant(p, 0, 2);
		b.placePlant(p2, 0, 5);
		
		ArrayList<Zombie> zomList = new ArrayList<Zombie>();
		zomList.add(z1);
		zomList.add(z2);
		zomList.add(z3);
		zomList.add(z4);
		
		assertEquals(zomList, b.useLawnMower(0));
	}
	
	/**
	 * Tests the getNewZomPosition method
	 */
	@Test
	public void testGetNewZomPostion()
	{
		Board b = new Board(5,20);
		Regular_Zombie z1 = new Regular_Zombie();
		b.placeZombie(z1, 0, 7);
		
		assertEquals(0, b.getNewZomPosition(0, 7, 2, z1, 5).getRow()); //testing for regular zombie
		assertEquals(5, b.getNewZomPosition(0, 7, 2, z1, 5).getCol()); 
		
		Juking_Zombie z2 = new Juking_Zombie();
		b.placeZombie(z2, 0, 7);
		assertEquals(1, b.getNewZomPosition(z2.getRow(),z2.getCol(),1,z2, 5).getRow()); //testing for when juking zombie starting at row 0 
		assertEquals(6, b.getNewZomPosition(z2.getRow(),z2.getCol(),1,z2, 5).getCol());
		
		b.placeZombie(z2, 1, 6);
		assertEquals(2, b.getNewZomPosition(z2.getRow(),z2.getCol(),1,z2, 5).getRow()); //testing for when juking zombie starts off not at the edges of the board
		assertEquals(5, b.getNewZomPosition(z2.getRow(),z2.getCol(),1,z2, 5).getCol());
		
		b.placeZombie(z2, 4, 19);
		//Should be 3, 18
		assertEquals(3, b.getNewZomPosition(z2.getRow(),z2.getCol(),1,z2, 5).getRow()); //testing for when juking zombie is at the bottom of the board
		assertEquals(18, b.getNewZomPosition(z2.getRow(),z2.getCol(),1,z2, 5).getCol());
		b.placeZombie(z2, b.getNewZomPosition(z2.getRow(),z2.getCol(),1,z2, 5).getRow(), b.getNewZomPosition(z2.getRow(),z2.getCol(),1,z2, 5).getCol()); 
		//Should be 2, 17
		assertEquals(2, b.getNewZomPosition(z2.getRow(),z2.getCol(),1,z2, 5).getRow()); //testing for when juking zombie is not at the edges of the board 
		assertEquals(17, b.getNewZomPosition(z2.getRow(),z2.getCol(),1,z2, 5).getCol());
		b.placeZombie(z2, b.getNewZomPosition(z2.getRow(),z2.getCol(),1,z2, 5).getRow(), b.getNewZomPosition(z2.getRow(),z2.getCol(),1,z2, 5).getCol()); 
		//Should be 1, 16
		b.placeZombie(z2, b.getNewZomPosition(z2.getRow(),z2.getCol(),1,z2, 5).getRow(), b.getNewZomPosition(z2.getRow(),z2.getCol(),1,z2, 5).getCol()); 
		//Should be 0, 15
		b.placeZombie(z2, b.getNewZomPosition(z2.getRow(),z2.getCol(),1,z2, 5).getRow(), b.getNewZomPosition(z2.getRow(),z2.getCol(),1,z2, 5).getCol()); 
		//Should be 1,14
		assertEquals(1, b.getNewZomPosition(z2.getRow(),z2.getCol(),1,z2, 5).getRow()); //testing for when juking zombie hits the other side of the board
		assertEquals(14, b.getNewZomPosition(z2.getRow(),z2.getCol(),1,z2, 5).getCol());
	}
}
