package input;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;

import assets.Plant;
import engine.Board;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import assets.Plant;
import assets.PlantTypes;
import engine.Game;
import engine.Purse;
import ui.GameUI;
import ui.GridUI;

public class GameController {
	
	private Game game;
	private GameUI ui;
	private Board gameBoard;
	private Purse userResources;
	
	// Selected a plant to place
	private Plant selectedPlant;
	
	// Selected to remove a plant
	private boolean removingPlant; 
	private static boolean firstClick;
	private static JPanel selectedPane;
	
	public GameController(GameUI ui, Game game) {
		this.game = game;
		this.ui = ui;
		
		this.gameBoard = this.game.getBoard();
		this.userResources = this.game.getPurse();
		
		this.ui.addGridListeners(new GridListener());
		this.ui.addMenuButtonListeners(new MenuBarListener());
		this.ui.addUnitSelectionListeners(new UnitSelectListener());
		firstClick = true;
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
			
			if (selectedPlant != null) {
				if (userResources.canSpend(selectedPlant.getCost())) {
					if (gameBoard.getGrid(sourceRow, sourceCol).setPlant(selectedPlant)) {
						userResources.spendPoints(selectedPlant.getCost());
						source.renderPlant();
					}
				}
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
	
	private class UnitSelectListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e)
		{
			System.out.println("unit select clicked");
			//if this is the first pick and a square with a piece was picked,
			// remember the piece, check if it is viable and highlight the card
			if(firstClick)
			{
				JPanel p = (JPanel)e.getSource();
				for(JPanel i : ui.getCardCollection().keySet())
				{
					if(i ==  p)
					{
						if(game.getPurse().spendPoints(ui.getCardCollection().get(i).getCost()))
						{
							selectedPlant = ui.getCardCollection().get(i); //save the selected plant type
							selectedPane = i; //save the selected JPanel
							
							ui.setHighlight(i);
							ui.setPointsMessage(game.getPurse().getPoints()); //update the number of points the player has
							firstClick = false;
						}
						else
						{
							System.out.println("you dont have enough points to purchase this plant");
						}
					}
				}
			}
			else //its second click... ie place unit on board
			{
				ui.revertHighlight(selectedPane);
				firstClick = true;
			}
			
			JPanel p = (JPanel)e.getSource();
			for(JPanel i : ui.getCardCollection().keySet())
			{
				if(i ==  p)
				{
					if(game.getPurse().spendPoints(ui.getCardCollection().get(i).getCost()))
					{
						selectedPlant = ui.getCardCollection().get(i); //save the selected plant type
						selectedPane = i; //save the selected JPanel
						
						ui.setHighlight(i);
						ui.setPointsMessage(game.getPurse().getPoints()); //update the number of points the player has
						firstClick = false;
					}
					else
					{
						System.out.println("you dont have enough points to purchase this plant");
					}
				}
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
	}
	
	private class LawnMowerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}
