package ui;

import java.awt.Dimension;
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

		} else {
			plantImage = Images.getDefaultImage();
		}
		
		plantImage = plantImage.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		plantLabel.setIcon(new ImageIcon(plantImage));
		
		plantPanel.add(plantLabel);
		plantPanel.setPreferredSize(new Dimension(100, 100));
		
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
		
		if (numberOfZombieTypes == 0) {
			
			Image defaultImage = Images.getDefaultImage();
			defaultImage = defaultImage.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
			zombiePanel.add(new JLabel(new ImageIcon(defaultImage)));
			
			c.fill = GridBagConstraints.VERTICAL;
			c.weighty = 0.5;
			c.gridx = 1;
			c.gridy = 0;
			
			add(zombiePanel, c);
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
