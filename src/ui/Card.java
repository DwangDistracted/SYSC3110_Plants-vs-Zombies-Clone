package ui;

import java.awt.LayoutManager;
import javax.swing.JPanel;
import assets.Plant;

/**
 * Each Card instance will represent a unit selection card initialized within the GameUI class.
 * A Card instance will remember the unit selection card JPanel and Plant type
 * @author Michael Patsula
 */

public class Card extends JPanel
{
	private Plant plant;
	
	public Card(LayoutManager layout, Plant unit)
	{
		super(layout);
	}
	
	public Plant getPlant()
	{
		return plant;
	}
	
	public void setUnit(Plant unit)
	{
		this.plant = unit;
	}
}
