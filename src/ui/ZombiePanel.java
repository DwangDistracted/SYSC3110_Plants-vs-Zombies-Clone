package ui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import assets.ZombieTypes;

public class ZombiePanel extends JPanel {
	
	private int count;
	private ZombieTypes zombieType;
	
	public ZombiePanel(ZombieTypes zombie, int count) {
		
		this.zombieType = zombie;
		this.count = count;
		
		Image zombieImage = Images.getZombieImage(zombie);
		zombieImage = zombieImage.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		JPanel zombieDisplay = new JPanel();
		zombieDisplay.setOpaque(false);
		zombieDisplay.add(new JLabel(new ImageIcon(zombieImage)));
		add(zombieDisplay);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, 50)); 
		g.drawString("x " + Integer.toString(count), 75, 75);
	}
}
