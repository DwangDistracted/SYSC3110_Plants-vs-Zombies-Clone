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
import javax.swing.JComponent;
import javax.swing.JMenuItem;

import assets.Plant;
import engine.Board;
import engine.Game;
import engine.Purse;
import ui.Card;
import ui.GameUI;
import ui.GridUI;

public class GameController {
	
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
	}
	
	private class GameButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			JButton source = (JButton) e.getSource();
			
			if(ui.getDigUp() == source)
			{
				removingPlant = true;
				ui.revertHighlight(selectedCard);
				selectedCard = null; // Scenario in which if person clicks card and then clicks digup, The card is deselected
			}
			else if(ui.getEndTurn() == source)
			{
				game.zombieTurn();	
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
				gameBoard.getGrid(sourceRow, sourceCol).setPlant(null);
				source.renderPlant();
				removingPlant = false;
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
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class LawnMowerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}

	}
}
