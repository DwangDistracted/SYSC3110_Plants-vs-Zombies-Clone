package input;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import assets.PlantTypes;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import engine.Game;
import ui.Card;
import ui.GameUI;
import ui.GridUI;
import ui.ZombiePanel;
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
		
	// Selected to remove a plant
	private boolean removingPlant; 
	
	public GameController(GameUI ui, Game game) {
		this.game = game;
		this.ui = ui;
		this.selectedCard = null;
		this.removingPlant = false;
		
		this.ui.addGridListeners(new GridListener());
		this.ui.addUnitSelectionListeners(new UnitSelectListener());
		this.ui.addGameButtonListeners(new GameButtonListener());
		this.ui.addLawnMowerListeners(new LawnMowerListener());
		this.ui.addShowFullListPanelListeners(new ShowFullZombieList());
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
					game.undo();
					break;
				case "Redo":
					game.redo();
					break;
				case "End Turn": //@author David Wang
					LOG.debug("Ending Turn");
					game.doEndOfTurn();
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
				game.placePlant(selectedPlantType, sourceRow, sourceCol);
				
				ui.revertHighlight(selectedCard);
				selectedCard = null;
			} else if (removingPlant) {
				game.removePlant(sourceRow, sourceCol);
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
	 * Listener to allow player see full list of zombies in a grid.
	 * 
	 * @author Derek Shao
	 *
	 */
	private class ShowFullZombieList implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			JPanel showFullListPanel = (JPanel) arg0.getSource();
			ZombiePanel parentZombiePanel = (ZombiePanel) showFullListPanel.getParent();

			parentZombiePanel.showFullZombieList();
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			
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
					game.getBoard().useLawnMower(i);
					ui.updateMower(i);
					game.getBoard().removeMower(i);
	
					LOG.info("Mower's Not Implemented for Milestone 2");
					//not implemented yet - need to remove all zombies and plants in the specified row 
				}
			}
		}
	}
}
