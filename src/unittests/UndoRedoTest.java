package unittests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import assets.PlantTypes;
import engine.Board;
import engine.Purse;
import engine.CommandQueue;
import engine.Game;
import levels.LevelInfo;
import levels.LevelLoader;

/**
 * Unit test for the undo and redo feature.
 * 
 * @author Derek Shao
 *
 */
public class UndoRedoTest {

	private static Game game;
	private static Board board;
	private static Purse purse;
	private static CommandQueue commandQueue;
	private static LevelInfo lvl;
	
	@BeforeAll
	public static void loadLevel() {
		LevelLoader.init();
		lvl = LevelLoader.getLevel(1);
	}
	
	@BeforeEach
	public void setUp() {
		game = new Game(lvl);
		board = game.getBoard();
		purse = game.getPurse();
		commandQueue = game.getCommandQueue();
	}
	
	@Test
	public void testUndoDigCommand() {
		game.placePlant(PlantTypes.SUNFLOWER, 0, 0);
		game.removePlant(0, 0);
		game.undo();
		assertTrue(board.getGrid(0, 0).isOccupied(), "Undo command placed the dug plant back on board");
	}
	
	@Test
	public void testUndoPlacecommand() {
		game.placePlant(PlantTypes.SUNFLOWER, 0, 0);
		game.undo();
		assertFalse(board.getGrid(0, 0).isOccupied(), "Undo command removed the placed plant from board");
	}
	
	@Test
	public void testUndoEndTurnCommand() {
		game.placePlant(PlantTypes.SUNFLOWER, 0, 0);
		Purse tempPurse = new Purse(game.getPurse());
		int previousTurn = game.getTurns();
		game.doEndOfTurn();
		game.undo();
		assertEquals(purse.getPoints(), tempPurse.getPoints(), "Purse contains points from previous turn");
		assertEquals(previousTurn, game.getTurns(), "Returned to previous turn");
	}
	
	@Test
	public void testRedoDigCommand() {
		game.placePlant(PlantTypes.SUNFLOWER, 0, 0);
		game.removePlant(0, 0);
		game.undo();
		game.redo();
		assertTrue(board.getPlant(0, 0) == null, "Re-did dig command");
	}
	
	@Test
	public void testRedoPlaceCommand() {
		game.placePlant(PlantTypes.SUNFLOWER, 0, 0);
		game.undo();
		game.redo();
		assertTrue(board.getPlant(0, 0) != null, "Re-did place command");
	}
	
	@Test
	public void testRedoEndTurnCommand() {
		game.placePlant(PlantTypes.SUNFLOWER, 0, 0);
		game.doEndOfTurn();
		Purse tempPurse = new Purse(purse);
		int currentTurn = game.getTurns();
		game.undo();
		game.redo();
		assertEquals(purse.getPoints(), tempPurse.getPoints(), "Recovered points into purse");
		assertEquals(currentTurn, game.getTurns(), "Returned to current turn");
	}
	
	@Test
	public void testUnsuccessfulPlaceCommand() {
		game.placePlant(PlantTypes.SUNFLOWER, 0, 0);
		Purse noResourcePurse = new Purse(0);
		purse.setPoints(noResourcePurse);
		game.placePlant(PlantTypes.PEASHOOTER, 0, 1);
		game.undo();
		assertTrue(board.getPlant(0, 0) == null, "Unsuccessful Place command not stored in command queue");
		
	}
	
	@Test
	public void testRedoClear() {
		game.placePlant(PlantTypes.SUNFLOWER, 0, 0);
		game.placePlant(PlantTypes.PEASHOOTER, 0, 1);
		game.undo();
		game.placePlant(PlantTypes.PEASHOOTER, 0, 2);
		assertFalse(commandQueue.redo(), "Resets redo command queue when starting new queue of commands");
	}
}
