package unittests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import assets.PlantTypes;
import engine.Board;
import engine.Purse;
import input.GameController;
import input.GameController.GameButtonListener;
import input.GameController.UnitSelectListener;
import engine.Game;
import levels.LevelInfo;
import levels.LevelLoader;
import ui.Card;
import ui.GameUI;

/**
 * Unit test for the game controller. 
 * 
 * @author Derek Shao
 *
 */
public class ControllerTest {

	private static Game game;
	private static Purse purse;
	private static LevelInfo lvl;
	private static GameUI gameUI;
	private static Board board;
	private static GameController gameController;
	
	@BeforeAll
	public static void loadLevel() {
		GameUI.setTestMode();
		LevelLoader.init();
		lvl = LevelLoader.getLevel(1);
	}
	
	@BeforeEach
	public void setUp() {
		game = new Game(lvl);
		purse = game.getPurse();
		gameUI = new GameUI(game);
		board = game.getBoard();
		gameController = new GameController(gameUI, game);
	}
	
	@Test
	public void testGameButtonListener() {
		GameButtonListener gameButtonListener = gameController.getGameButtonListener();
		
		ActionEvent digEvent = new ActionEvent(new JButton(), 1, GameButtonListener.DIG);
		gameButtonListener.actionPerformed(digEvent);
		assertTrue(gameController.isRemovingPlant(), "Dig event sets removing plant flag");
		
		ActionEvent undoEvent = new ActionEvent(new JButton(), 1, GameButtonListener.UNDO);
		game.placePlant(PlantTypes.SUNFLOWER, 0, 0);
		gameButtonListener.actionPerformed(undoEvent);
		assertTrue(!board.getGrid(0, 0).isOccupied(), "Undo event removes placed plant");
		
		ActionEvent redoEvent = new ActionEvent(new JButton(), 1, GameButtonListener.REDO);
		game.placePlant(PlantTypes.SUNFLOWER, 0, 0);
		game.undo();
		gameButtonListener.actionPerformed(redoEvent);
		assertTrue(board.getGrid(0, 0).isOccupied(), "Redo event re-places plant");

		ActionEvent endTurnEvent = new ActionEvent(new JButton(), 1, GameButtonListener.END_TURN);
		game.placePlant(PlantTypes.SUNFLOWER, 1, 1);
		Purse tempPurse = new Purse(game.getPurse());
		int previousTurn = game.getTurns();
		gameButtonListener.actionPerformed(endTurnEvent);
		assertNotEquals(purse.getPoints(), tempPurse.getPoints(), "End turn event increased amount of points");
		assertEquals(previousTurn + 1, game.getTurns(), "End turn event triggered next turn");
	}
	
	@Test
	public void testUnitSelectListener() {
		UnitSelectListener unitSelectListener = gameController.getUnitSelectListener();
		
		Card selectedCard = new Card(new BorderLayout(5, 5), PlantTypes.SUNFLOWER);
		MouseEvent cardSelectEvent = new MouseEvent(selectedCard, 1, 0, 0, 0, 0, 0, false);
		unitSelectListener.mouseClicked(cardSelectEvent);
		assertEquals(gameController.getCardSelected().getPlantType(), selectedCard.getPlantType(), "Card select event caches selected card");
		
		Card otherCard = new Card(new BorderLayout(5, 5), PlantTypes.PEASHOOTER);
		MouseEvent otherCardSelectEvent = new MouseEvent(otherCard, 1, 0, 0, 0, 0, 0, false);
		unitSelectListener.mouseClicked(otherCardSelectEvent);
		assertEquals(gameController.getCardSelected().getPlantType(), otherCard.getPlantType(), "Card select event swaps cached card");
	}
}
