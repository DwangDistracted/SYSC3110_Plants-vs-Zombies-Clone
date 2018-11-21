package input;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import assets.Plant;
import assets.PlantTypes;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import engine.Board;
import engine.Game;
import engine.Purse;
import levels.LevelInfo;
import levels.LevelLoader;
import ui.Card;
import ui.GameUI;
import ui.GridUI;
import ui.MainMenu;
import util.Logger;

/**
 * Controller class handling all user interactions
 * 
 * @author Derek Shao
 *
 */
public class GameController {
	private static Logger LOG = new Logger("Game Controller");
	
	private Game game;
	private GameUI ui;
	private Card selectedCard; //The selected card on click #1
	private Board gameBoard;
	private Purse userResources;
	
	private CommandQueue cQ;
	
	// Selected to remove a plant
	private boolean removingPlant; 
	
	public GameController(GameUI ui, Game game) {
		this.game = game;
		this.ui = ui;
		this.selectedCard = null;
		this.gameBoard = this.game.getBoard();
		this.userResources = this.game.getPurse();
		this.removingPlant = false;
		
		this.cQ = new CommandQueue(ui,gameBoard,userResources);
		this.ui.addGridListeners(new GridListener());
		this.ui.addUnitSelectionListeners(new UnitSelectListener());
		this.ui.addGameButtonListeners(new GameButtonListener());
		this.ui.addLawnMowerListeners(new LawnMowerListener());
	}
	
	/**
	 * Class to handle button clicks.
	 * 
	 * @author Derek Shao
	 *
	 */
	private class GameButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			switch(e.getActionCommand())
			{
				case "Dig Up": 
					LOG.debug("Digging up plant");
					removingPlant = true;
					if(selectedCard != null)
					{
						ui.revertHighlight(selectedCard); 
						selectedCard = null; // Scenario in which if person clicks card and then clicks digup, The card is deselected
					}
					break;
				case "Undo":
					if (!cQ.undo()) {
						JOptionPane.showMessageDialog(ui, "No more moves to undo", "Cannot Undo", JOptionPane.PLAIN_MESSAGE);
					}
					break;
				case "End Turn": //@author David Wang
					cQ.clear(); //allow undo only until the end of a turn
					LOG.debug("Ending Turn");
					game.doEndOfTurn();
					ui.setPointsLabel(userResources.getPoints());
					
					GridUI [][] gridTiles = ui.getBoardTiles();
					
					// re-render every grid tiles
					for (int i = 0; i < gridTiles.length; i++) {
						for (int j = 0; j < gridTiles[i].length; j++) {
							gridTiles[i][j].renderPlant();
							gridTiles[i][j].renderZombies();
						}
					}
					
					switch (game.getState()) { //check if there was a resolution to the game
					
					case WON:
						LOG.debug("Player Won");
						int result = JOptionPane.showConfirmDialog(ui, "You won in " + game.getTurns() + " Turns! The Zombies have been slain! \n\n Do you want to play the next level?", "You WON!", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
						
						if (result == JOptionPane.YES_OPTION) { //load next level
							LOG.debug("Load Next Level");
							LevelInfo next = LevelLoader.getNextLevel();
							if (next == null) {
								JOptionPane.showMessageDialog(ui, "No More Levels Available. Returning to Main Menu");//return to main menu
								new MainMenu();
								ui.dispose();
							} else {
								Game g = new Game(next);
								LOG.debug(g.getBoard().displayBoard());
								new GameController(new GameUI(g), g);
								ui.dispose();
							}
						} else { //return to main menu
							new MainMenu();
							ui.dispose();
						}
						
						break;
					case LOST:
						LOG.debug("Player Lost");
						int result1 = JOptionPane.showConfirmDialog(ui, "You lost in " + game.getTurns() + " Turns! You were eaten by Zombies! \n\n Do you want to retry?", "You Lost", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);

						if (result1 == JOptionPane.YES_OPTION && game != null) { //reload this level
							LOG.debug("Reloading Level");
							Game g = new Game(game.getLevelInfo());
							LOG.debug(g.getBoard().displayBoard());
							new GameController(new GameUI(g), g);
							ui.dispose();
						} else { //return to main menu
							new MainMenu();
							ui.dispose();
						}
						
						break;
					default: //update user points if still playing
						ui.setTurnLabel(game.getTurns());
						ui.setPointsLabel(userResources.getPoints());
						break;
					}
			}
		}
	}
	
	/**
	 * Class to handle grid clicks.
	 * 
	 * @author Derek Shao
	 *
	 */
	private class GridListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			GridUI source = (GridUI) arg0.getSource();
			
			final int sourceRow = source.getRow();
			final int sourceCol = source.getCol();

			if (selectedCard != null) {
				LOG.debug("Planting in Grid");
				PlantTypes selectedPlantType = selectedCard.getPlantType();
				
				cQ.registerPlace(selectedPlantType,sourceRow,sourceCol);
				
				Plant selectedPlant = PlantTypes.toPlant(selectedPlantType);
				if (userResources.canSpend(selectedPlant.getCost())) {
					if (gameBoard.placePlant(selectedPlant, sourceRow, sourceCol)) {
						userResources.spendPoints(selectedPlant.getCost());
						ui.setPointsLabel(userResources.getPoints());
						source.renderPlant();
					}
				} else {
					ui.showInsufficientFundsOptionPane(selectedPlant);
				}
				ui.revertHighlight(selectedCard);
				selectedCard = null;
			} else if (removingPlant) {
				cQ.registerDig(gameBoard.getPlant(sourceRow, sourceCol).getPlantType(),sourceRow,sourceCol);
				
				gameBoard.removePlant(sourceRow, sourceCol);
				source.renderPlant();
				source.repaint();
				removingPlant = false;
				LOG.debug("Removed Plant");
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			GridUI source = (GridUI) arg0.getSource();
			
			source.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			GridUI source = (GridUI) arg0.getSource();
			
			source.setBorder(BorderFactory.createEmptyBorder());
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// not implemented
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// not implemented
		}
		
	}
	
	/**
	 * Class to handle unit selection.
	 * 
	 * @author Derek Shao
	 *
	 */
	private class UnitSelectListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			Card card = (Card)e.getSource(); 
			
			if (selectedCard != null) {
				ui.revertHighlight(selectedCard);
			}
			
			if (selectedCard == null || selectedCard != card) {
				ui.setHighlight(card);
				selectedCard = card;
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			//Not Implemented
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			//Not Implemented
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			//Not Implemented
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			//Not Implemented
		}
		
	}
	
	/**
	 * Not Implemented For Milestone 2
	 * calls the lawn mower functionalility for a specified row
	 * The row is dependent on which button the user presses.
	 * @author michael
	 */
	private class LawnMowerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton) e.getSource();
			JButton[] mowers = ui.getMowers();
			
			for(int i = 0; i < ui.getMowers().length; i++)
			{
				if(source == mowers[i]) //see what row the button was pressed in
				{
					LOG.info("Mower's Not Implemented for Milestone 2");
					//not implemented yet - need to remove all zombies and plants in the specified row 
				}
			}
		}
	}
}
