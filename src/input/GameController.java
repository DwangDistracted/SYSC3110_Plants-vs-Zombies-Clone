package input;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;

import assets.Plant;
import engine.Board;
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
	
	public GameController(GameUI ui, Game game) {
		this.game = game;
		this.ui = ui;
		
		this.gameBoard = this.game.getBoard();
		this.userResources = this.game.getPurse();
		
		this.ui.addGridListeners(new GridListener());
		this.ui.addMenuButtonListeners(new MenuBarListener());
	}
	
	private class MenuBarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
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
	
	private class UnitSelectListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	
	private class LawnMowerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}
