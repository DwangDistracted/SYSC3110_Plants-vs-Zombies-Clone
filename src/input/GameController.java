package input;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import assets.Plant;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.oracle.tools.packager.Log;

import engine.Board;
import engine.Game;
import engine.Purse;
import levels.LevelLoader;
import ui.Card;
import ui.GameUI;
import ui.GridUI;
import ui.MainMenu;
import util.Logger;

public class GameController {
	private static Logger LOG = new Logger("Game Controller");
	
	private Game game;
	private GameUI ui;
	private Card selectedCard; //The selected card on click #1
	private Board gameBoard;
	private Purse userResources;
	
	// Selected to remove a plant
	private boolean removingPlant; 
	
	public GameController(GameUI ui, Game game) {
		this.game = game;
		this.ui = ui;
		this.selectedCard = null;
		this.gameBoard = this.game.getBoard();
		this.userResources = this.game.getPurse();
		this.removingPlant = false;
		
		this.ui.addGridListeners(new GridListener());
		this.ui.addUnitSelectionListeners(new UnitSelectListener());
		this.ui.addGameButtonListeners(new GameButtonListener());
		this.ui.addLawnMowerListeners(new LawnMowerListener());
	}
	
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
				case "End Turn":
					LOG.debug("Ending Turn");
					game.doEndOfTurn();
					
					switch (game.getState()) { //check if there was a resolution to the game
					
					case WON:
						LOG.debug("Player Won");
						int result = JOptionPane.showConfirmDialog(ui, "You won in " + game.getTurns() + " Turns! The Zombies have been slain! \n\n Do you want to play the next level?", "You WON!", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
						
						if (result == JOptionPane.YES_OPTION) { //load next level
							LOG.debug("Load Next Level");
							
							Game g = new Game(LevelLoader.getNextLevel());
							ui.dispose();
							new GameController(ui, g);
							new GameController(new GameUI(g), g);
							ui.dispose();
							LOG.debug(g.getBoard().displayBoard());
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
							LOG.debug(g.getBoard().displayBoard());
						} else { //return to main menu
							new MainMenu();
							ui.dispose();
						}
						
						break;
					default: //no action needed 
						break;
					}
			}
		}
	}
	
	private class GridListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			GridUI source = (GridUI) arg0.getSource();
			
			final int sourceRow = source.getRow();
			final int sourceCol = source.getCol();

			if (selectedCard != null) {
				LOG.debug("Planting in Grid");
				Plant selectedPlant = selectedCard.getPlant();
				if (userResources.canSpend(selectedPlant.getCost())) {
					if (gameBoard.getGrid(sourceRow, sourceCol).setPlant(selectedPlant)) {
						userResources.spendPoints(selectedPlant.getCost());
						source.renderPlant();
					}
				}
				ui.revertHighlight(selectedCard);
				selectedCard = null;
			} else if (removingPlant) {
				gameBoard.getGrid(sourceRow, sourceCol).removePlant();
				source.renderPlant();
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
