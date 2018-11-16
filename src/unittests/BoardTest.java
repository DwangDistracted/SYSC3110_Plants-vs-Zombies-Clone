package unittests;

import assets.*;
import engine.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import engine.Board;

public class BoardTest {

	@Test
	public void testBoardConstructor() {
		Board b = new Board(4, 1);
		assertEquals("Row should be 4 ", 4, b.getRow());
		assertEquals("Column should be 1", 1, b.getColumn());	
		assertFalse("Zombies have not reached end", b.hasReachedEnd());
		assertEquals("Number of sunflower", 0, b.getNumberOfSF());
	}
	
	@Test
	public void testGetBoard() {
		Board b = new Board(4, 1);
		Grid[][] expected = new Grid[4][1];
		Grid[][] current = b.getBoard();
		assertTrue("Grid with rows = 4 and columns = 1", expected.equals(current));
	}
	
	@Test
	public void testPlacePlant() {
		Board b = new Board(4,1);
		assertTrue("Plant Placed.", b.placePlant(new Flower(), 0, 0));
	}
	
	@Test
	public void testPlaceZombie() {
		Board b = new Board(0,3);
		assertTrue("Zombie Placed.", b.placeZombie(new Regular_Zombie(), 3, 1));
	}
	
	@Test
	public void testGetPlantMethods() {
		Board b = new Board(4,1);
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
	
	@Test
	public void testGetZombieMethods() {
		Board b = new Board(4,1);
		Zombie z = new Regular_Zombie();
		b.placeZombie(z, 0, 3);
		assertEquals("Number of Zombies = 1", 1, b.getNumberOfZombies());
		assertTrue("True", b.getZombiesInGame().contains(z));
		assertEquals("Regular Zombie", z, b.getZombie(0, 3));
	}
	
	@Test
	public void testRemovePlant() {
		Board b = new Board(4,1);
		Peashooter p = new Peashooter();
		Flower f = new Flower();
		b.placePlant(f, 0, 0);
		b.placePlant(p, 0, 1);
		b.removePlant(0, 1);
		assertFalse("False", b.getPlantsInGame().contains(p));
	}
	
	@Test
	public void testRemoveZombie() {
		Board b = new Board(4,1);
		Zombie z = new Regular_Zombie();
		b.placeZombie(z, 3, 0);
		b.removeZombie(3, 0);
		assertFalse("False", b.getZombiesInGame().contains(z));
	}
	
	@Test
	public void testOnZombieMove() {
		Board b = new Board(2,2);
		Regular_Zombie z = new Regular_Zombie();
		
		//placing zombie and plant on grid[2][0]
		b.placeZombie(z, 0, 1);
		Peashooter p = new Peashooter();
		b.placePlant(p, 0, 0);
		assertFalse("False",b.onZombieMove(z));
		
		//placing zombie without the plant on grid[2][1]
		b.placeZombie(z, 1, 1);
		assertTrue("True", b.onZombieMove(z));
	}

}
