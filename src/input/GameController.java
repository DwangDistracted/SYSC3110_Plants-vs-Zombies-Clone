package input;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import assets.Plant;
import assets.PlantTypes;
import engine.Game;
import ui.GameUI;

public class GameController {
	
	private static Game game;
	private static GameUI ui;
	private static boolean firstClick;
	private static Plant selectedPlant;
	private static JPanel selectedPane;
	
	public GameController(GameUI ui, Game game) {
		this.game = game;
		this.ui = ui;
		this.selectedPlant = null;
		this.selectedPane = null;
		firstClick = true;
	}
	
	public ActionListener menuBarActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		};
	}
	
	public ActionListener boardActionListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		};
	}
	
	public ActionListener unitSelectActionListener(JFrame frame) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		};
	}
	
	public static MouseListener unitSelectMouseListener()
	{
		return new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
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
}
