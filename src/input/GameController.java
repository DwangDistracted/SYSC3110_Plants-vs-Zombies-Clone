package input;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import assets.Plant;
import assets.PlantTypes;
import javax.swing.BorderFactory;

import assets.Plant;
import engine.Board;
import engine.Game;
import engine.Purse;
import ui.Card;
import ui.GameUI;
import ui.GridUI;

public class GameController {
	
	private static Game game;
	private static GameUI ui;
	private static boolean firstClick; //Every click should toggle the first flag
	private static Card selectedCard; //The selected card on click #1
	private static Board gameBoard;
	private static Purse userResources;
	
	// Selected to remove a plant
	private boolean removingPlant; 
	
	public GameController(GameUI ui, Game game) {
		this.game = game;
		this.ui = ui;
		this.selectedCard = null;
		this.firstClick = true;
		this.gameBoard = this.game.getBoard();
		this.userResources = this.game.getPurse();
		
		this.ui.addGridListeners(new GridListener());
	}
	
	private class MenuBarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			switch(e.getActionCommand()) {
				case "Menu":
					break;
				case "End turn":
					break;
				case "Quit":
					break;
			}
		}
	}
	
	private class GridListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			GridUI source = (GridUI) arg0.getSource();
			
			final int sourceRow = source.getRow();
			final int sourceCol = source.getCol();
			
			Plant selectedPlant = selectedCard.getPlant();
			if (selectedCard != null) {
				if (userResources.canSpend(selectedPlant.getCost())) {
					if (gameBoard.getGrid(sourceRow, sourceCol).setPlant(selectedPlant)) {
						userResources.spendPoints(selectedPlant.getCost());
						source.renderPlant();
					}
				}
				selectedCard = null;
				ui.revertHighlight(selectedCard);
			} else if (removingPlant) {
				gameBoard.getGrid(sourceRow, sourceCol).setPlant(null);
				source.renderPlant();
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
	
	
	public static MouseListener unitSelectMouseListener()
	{
		return new MouseListener()
		{
			System.out.println("unit select clicked");
			//if this is the first pick and a square with a piece was picked,
			// remember the piece, check if it is viable and highlight the card
			if(firstClick)
			@Override
			public void mouseClicked(MouseEvent e)
			{
				Card card = (Card)e.getSource(); 
		        //if this is the first pick and a square with a piece was picked,
		        // remember the piece, check if it is viable and highlight the card
				if(firstClick)
				{
					selectedCard = card; //save the selected card (to perhaps compare for second click)
					ui.setHighlight(card);
					firstClick = false;
				}
				else //indicates that the second click is on another unit card 
				{
					ui.revertHighlight(selectedCard);
					ui.setHighlight(card);
					selectedCard = card;
					firstClick = true;
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}
		};
	}
	
	
	private class LawnMowerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}

	}
}
