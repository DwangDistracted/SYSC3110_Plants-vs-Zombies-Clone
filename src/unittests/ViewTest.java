package unittests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


import javax.swing.JLabel;
import javax.swing.JPanel;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import assets.PlantTypes;
import engine.Purse;
import engine.Game;
import levels.LevelInfo;
import levels.LevelLoader;
import ui.GameUI;
import ui.GridUI;

/**
 * Unit tests for the UI.
 * 
 * @author Derek Shao
 *
 */
public class ViewTest {
	
	private static Game game;
	private static Purse purse;
	private static LevelInfo lvl;
	private static GameUI gameUI;
	private static GridUI[][] boardTiles;
	
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
		boardTiles = gameUI.getBoardTiles();
	}
	
	@Test
	public void testPlacingPlantUpdatesView() {
		try {
			game.placePlant(PlantTypes.SUNFLOWER, 0, 0);
		}
		catch (Exception e) {
			e.addSuppressed(new ClassCastException());
		}
		GridUI placedLocation = boardTiles[0][0];
		assertNotNull(placedLocation.getPlantPanel());
		JPanel plantPanel = placedLocation.getPlantPanel();
		JLabel plantName = (JLabel) plantPanel.getComponent(0);
		assertNotNull(plantName, "A plant has been placed");
		assertEquals(plantName.getText(), "Sunflower", "Placed plant has correct name");
	}
	
	@Test 
	public void testRemovingPlantUpdatesView() {
		testPlacingPlantUpdatesView();
		game.removePlant(0, 0);
		GridUI removedLocation = boardTiles[0][0];
		JPanel plantPanel = removedLocation.getPlantPanel();
		assertTrue(plantPanel.getComponents().length == 0, "Plant removed from grid");
	}
	
	@Test
	public void testUpdateTurnLabel() {
		game.doEndOfTurn();
		JLabel turnMessage = gameUI.getTurnLabel();
		assertEquals(turnMessage.getText(), "<html><b>Turns: </b>1</html>", "Turn label updates");
	}
	
	@Test
	public void testUpdatePointsLabel() {
		game.doEndOfTurn();
		JLabel pointsMessage = gameUI.getPointsLabel();
		assertEquals(pointsMessage.getText(), "<html><b>Points: </b>" + Integer.toString(purse.getPoints()) + "</html>", "Points get updated every turn");
		
		game.placePlant(PlantTypes.SUNFLOWER, 0, 0);
		pointsMessage = gameUI.getPointsLabel();
		assertEquals(pointsMessage.getText(), "<html><b>Points: </b>" + Integer.toString(purse.getPoints()) + "</html>", "Placing a plant updates the points");
	}
	
	@Test
	public void testUpdatePointsDoesNotDisplayNegative() {
		Purse emptyPurse = new Purse(0);
		purse.setPoints(emptyPurse);
		gameUI.updatePurse();
		
		game.placePlant(PlantTypes.SUNFLOWER, 0, 0);
		JLabel pointsMessage = gameUI.getPointsLabel();
		assertEquals(pointsMessage.getText(), "<html><b>Points: </b>" + Integer.toString(purse.getPoints()) + "</html>", "Unsuccessful plant placement does not display wrong points");
	}
}
