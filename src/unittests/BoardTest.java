package unittests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import engine.Board;

public class BoardTest {

	@Test
	public void testBoardConstructor() {
		//Board b = new Board();
	}
	
	@Test
	public void testGetBoard() {
		Board b = new Board(4, 1);
		assertEquals("Row should be 4 ", 4, b.getRow());
		assertEquals("Column should be 1", 1, b.getColumn());
	}
	
	@Test
	public void BoardConstructorTest() {
		//Board b = new Board();
	}
	
	@Test
	public void BoardConstructorTest() {
		//Board b = new Board();
	}
	
	@Test
	public void BoardConstructorTest() {
		//Board b = new Board();
	}
	

}
