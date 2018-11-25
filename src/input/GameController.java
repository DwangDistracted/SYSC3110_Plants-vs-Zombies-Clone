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
		
	private GridListener gridListener;
	private UnitSelectListener unitSelectListener;
	private GameButtonListener gameButtonListener;
	private LawnMowerListener lawnMowerListener;
	private ShowFullZombieListListener showFullZombieListListener;
	
	// Selected to remove a plant
	private boolean removingPlant; 
	
	public GameController(GameUI ui, Game game) {
		this.game = game;
		this.ui = ui;
		this.selectedCard = null;
		this.removingPlant = false;
		

		gridListener = new GridListener();
		unitSelectListener = new UnitSelectListener();
		gameButtonListener = new GameButtonListener();
		lawnMowerListener = new LawnMowerListener();
		showFullZombieListListener = new ShowFullZombieListListener();
		
		
		this.ui.addGridListeners(gridListener);
		this.ui.addUnitSelectionListeners(unitSelectListener);
		this.ui.addGameButtonListeners(gameButtonListener);
		this.ui.addShowFullListPanelListeners(showFullZombieListListener);
	}
	
	/**
	 * Class to handle button clicks.
	 * 
	 * @author Derek Shao
	 *
	 */
	public class GameButtonListener implements ActionListener {
		
		public static final String DIG = "Dig Up";
		public static final String UNDO = "Undo";
		public static final String REDO = "Redo";
		public static final String END_TURN = "End Turn";
		
		@Override
		public void actionPerformed(ActionEvent e) {
			switch(e.getActionCommand())
			{
				case DIG: 
					LOG.debug("Digging up plant");
					removingPlant = true;
					if(selectedCard != null)
					{
						ui.revertHighlight(selectedCard); 
						selectedCard = null; // Scenario in which if person clicks card and then clicks digup, The card is deselected
					}
					break;
				case UNDO:
					game.undo();
					break;
				case REDO:
					game.redo();
					break;
				case END_TURN: //@author David Wang
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
	public class GridListener implements MouseListener {

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
	public class UnitSelectListener implements MouseListener {

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
	public class ShowFullZombieListListener implements MouseListener {

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
	 * Get the grid listener
	 * 
	 * @return the grid listener
	 */
	public GridListener getGridListener() {
		
		return gridListener;
	}
	
	/**
	 * Get the game button listener
	 * 
	 * @return the game button listener
	 */
	public GameButtonListener getGameButtonListener() {
		
		return gameButtonListener;
	}
	
	/**
	 * Get the unit select listener
	 * 
	 * @return the unit select listener
	 */
	public UnitSelectListener getUnitSelectListener() {
		
		return unitSelectListener;
	}
	
	/**
	 * Get the show full zombie list listener
	 * 
	 * @return the full zombie list listener
	 */
	public ShowFullZombieListListener getShowFullZombieListListener() {
		
		return showFullZombieListListener;
	}
	
	/**
	 * Get the lawn mower listener
	 * 
	 * @return the lawn mower listener
	 */
	public LawnMowerListener getLawnMowerListener() {
		
		return lawnMowerListener;
	}
	
	/**
	 * Indicate whether a plant is prepared to be removed
	 * 
	 * @return flag indicating whether a plant is being removed
	 */
	public boolean isRemovingPlant() {
		
		return removingPlant;
	}
	
	/**
	 * Create a copy of the selected card and return the card
	 * 
	 * @return copy of the selected card
	 */
	public Card getCardSelected() {
		
		if (selectedCard != null) {
			Card card = new Card(selectedCard.getLayout(), selectedCard.getPlantType());
			return card;
		}
		
		return null;
	}
}
