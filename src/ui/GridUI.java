package ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import assets.ZombieTypes;
import engine.Grid;

public class GridUI extends JPanel {

	private JPanel plantPanel;
	private Grid grid;
	
	public GridUI(Grid grid) {
		
		setLayout(new GridBagLayout());
		plantPanel = new JPanel();
		this.grid = grid;
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
		
		// Get the Plant located in this grid
		// Set the image if a plant is located here
		// if plant is null, set a default background
		
		add(plantPanel, c);
	}
	
	/**
	 * 
	 */
	public void renderZombies() {
		
		GridBagConstraints c = new GridBagConstraints();
	
		int numberOfZombieTypes = 0;
		
		for (ZombieTypes zombieType : grid.getZombieTypeCount().keySet()) {
			
			if (numberOfZombieTypes < 3) {
			
				JPanel zombiePanel = new JPanel();
				
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
	}
}
