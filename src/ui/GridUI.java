package ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.Grid;

/**
 * Custom grids to render both plant and zombies on the grid.
 * 
 * @author Derek Shao
 *
 */
public class GridUI extends JPanel {
	private static final long serialVersionUID = -6720923029166827998L;
	
	private JPanel plantPanel;
	private JLabel plantLabel;
	private ZombiePanel zombiePanel;
	private Grid grid;
	private int row;
	private int col;
	
	private MouseListener showFullListListener;
	
	public GridUI(Grid grid) {
		setLayout(new GridBagLayout());
		
		plantPanel = new JPanel();
		plantLabel = new JLabel();
		plantPanel.setOpaque(false);
		
		this.grid = grid;
		this.row = grid.getRow();
		this.col = grid.getCol();
		this.setOpaque(false);
		
		renderPlant();
		renderZombies();
	}
	
	/**
	 * Render the plant panel
	 */
	public void renderPlant() {
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.VERTICAL;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.NORTHWEST;
		
		Image plantImage;

		if (grid.getPlant() != null) {
			plantImage = Images.getPlantImage(grid.getPlant().getPlantType());
			
			if (plantImage != null) {
				plantImage = plantImage.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
				plantPanel.setPreferredSize(new Dimension(100, 100));
				plantLabel.setIcon(new ImageIcon(plantImage));
			} else {
				plantLabel.setText(grid.getPlant().toString());
			}
			plantPanel.add(plantLabel);
		} else {
			plantPanel.remove(plantLabel);
		}

		plantPanel.setOpaque(false);
		add(plantPanel, c);
	}
	
	/**
	 * Render the zombie panel
	 */
	public void renderZombies() {
		
		if (zombiePanel != null) {
			this.remove(zombiePanel);
			repaint();
		}
		grid.updateZombieTypeCount();
		
		zombiePanel = new ZombiePanel(this, grid.getZombieTypeCount(), showFullListListener);

		add(zombiePanel);
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
	
	/**
	 * Get the plant panel
	 * 
	 * @return plant panel
	 */
	public JPanel getPlantPanel() {
		
		return plantPanel;
	}
	
	/**
	 * Get the JPanel displaying zombies on this grid
	 *
	 * @return the panel displaying zombies
	 */
	public ZombiePanel getZombiePanel() {
		
		return zombiePanel;
	}
	
	/**
	 * Store the mouse listener that will be added to the show full list
	 * panel every time the zombie panel is created
	 * 
	 * @param listener
	 */
	public void addShowFullListListener(MouseListener listener) {
		showFullListListener = listener;
	}
}
