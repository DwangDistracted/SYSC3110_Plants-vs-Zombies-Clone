package ui;

import java.awt.LayoutManager;
import javax.swing.JPanel;
import assets.PlantTypes;

/**
 * Each Card instance will represent a unit selection card initialized within the GameUI class.
 * A Card instance will remember the unit selection card JPanel and Plant type
 * @author Michael Patsula
 */

public class Card extends JPanel
{
	private static final long serialVersionUID = 1L;
	private PlantTypes plant;
	
	public Card(LayoutManager layout, PlantTypes unit)
	{
		super(layout);
		plant = unit;
	}
	
	public PlantTypes getPlantType()
	{
		return plant;
	}
	
	public void setUnit(PlantTypes unit)
	{
		this.plant = unit;
	}
}
