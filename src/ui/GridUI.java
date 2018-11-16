package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import assets.ZombieTypes;
import engine.Grid;

public class GridUI extends JPanel {

	private static final int MAX_ZOMBIE_TYPES = 3;
	
	private JPanel plantPanel;
	private Grid grid;
	private int row;
	private int col;
	
	public GridUI(Grid grid) {
		
		setLayout(new GridBagLayout());
		plantPanel = new JPanel();
		this.grid = grid;
		this.row = grid.getRow();
		this.col = grid.getCol();
		
		renderPlant();
		renderZombies();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image backgroundImage = Images.getDefaultImage();
		g.drawImage(backgroundImage, 0, 0, null);
	}
	
	/**
	 * 
	 */
	public void renderPlant() {
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.VERTICAL;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.NORTHWEST;
		
		JLabel plantLabel = new JLabel();
		
		Image plantImage;

		if (grid.getPlant() != null) {
			plantImage = Images.getPlantImage(grid.getPlant().getPlantType());
			plantImage = plantImage.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
			plantPanel.setPreferredSize(new Dimension(100, 100));
			plantLabel.setIcon(new ImageIcon(plantImage));
			plantPanel.add(plantLabel);
		} 

		plantPanel.setOpaque(false);
		add(plantPanel, c);
	}
	
	/**
	 * 
	 */
	public void renderZombies() {
		
		GridBagConstraints c = new GridBagConstraints();
	
		int numberOfZombieTypes = 0;
		JPanel zombiePanel = new JPanel();
		zombiePanel.setPreferredSize(new Dimension(100, 100));
		
		for (ZombieTypes zombieType : grid.getZombieTypeCount().keySet()) {
			
			if (numberOfZombieTypes < MAX_ZOMBIE_TYPES) {
				
				Image zombieImage = Images.getZombieImage(zombieType);
				zombieImage = zombieImage.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
				
				zombiePanel.add(new JLabel(new ImageIcon(zombieImage)));
				
				c.fill = GridBagConstraints.VERTICAL;
				c.weighty = 0.5;
				c.gridx = 1;
				c.gridy = numberOfZombieTypes;
				
				zombiePanel.setOpaque(false);
				add(zombiePanel, c);
				
				numberOfZombieTypes++;
			
			} else {
				
				JPanel fullZombieListPanel = new JPanel();
				
				c.fill = GridBagConstraints.VERTICAL;
				c.weighty = 0.5;
				c.gridx = 1;
				c.gridy = numberOfZombieTypes;
				
				add(fullZombieListPanel, c);
			}
		}
	}

	/**
	 * Get the row of the grid 
	 * 
	 * @return
	 */
	public int getRow() {

		return row;
	}

	/**
	 * Get the column the grid
	 * 
	 * @return
	 */
	public int getCol() {

		return col;
	}

	/**
	 * Set the row of where this grid is located
	 * 
	 * @param row
	 */
	public void setRow(int row) {

		this.row = row;
	}

	/**
	 * Set the column of where this grid is located
	 * 
	 * @param col
	 */
	public void setCol(int col) {

		this.col = col;
	}
}
