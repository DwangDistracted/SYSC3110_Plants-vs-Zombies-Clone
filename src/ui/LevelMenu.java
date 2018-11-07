package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import input.MenuInteractions;

public class LevelMenu extends JFrame {
	private static final long serialVersionUID = -4952911219010614232L;

	public LevelMenu() {
		this.setTitle("Zombies are Vegan - Levels");
		
		this.setTitle("Zombies are Vegan");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setUndecorated(true);
		
		GraphicsDevice gd = //Multi-Screen Support
				GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		
		this.setSize((int)width/3, (int)height/2); //set size relative to screen
		this.setLocationRelativeTo(null); //create at screen center
		this.setResizable(false); //prevent resizing
		
		Container contents = this.getContentPane();
		contents.setLayout(new BorderLayout()); //Layout Manager
		
		JPanel titlePane = new JPanel();
		titlePane.setLayout(new BorderLayout());
		
		JLabel titleFld = new JLabel("<html><br>&nbsp;Zombies are Vegan</html>");
		titleFld.setHorizontalAlignment(SwingConstants.LEFT);
		titleFld.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 15));
		titlePane.add(titleFld, BorderLayout.NORTH);
		
		JLabel levelFld = new JLabel("<html>&nbsp;Select Level</html>");
		levelFld.setHorizontalAlignment(SwingConstants.LEFT);
		levelFld.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		titlePane.add(levelFld, BorderLayout.WEST);
		
		JPanel levelsPane = new JPanel();
		//TODO - Display all levels
		
		JPanel btnPane = new JPanel();
		btnPane.setLayout(new BoxLayout(btnPane, BoxLayout.LINE_AXIS));
		Font btnFont = new Font(Font.MONOSPACED, Font.PLAIN, 18);
		
		JButton playBtn = new JButton("Play Level");
		playBtn.setFont(btnFont);
		playBtn.setAlignmentX(RIGHT_ALIGNMENT);
		JButton backBtn = new JButton("Back");
		backBtn.setFont(btnFont);
		backBtn.setAlignmentX(RIGHT_ALIGNMENT);
		
		//Set Action Listeners
		playBtn.addActionListener(MenuInteractions.getPlayHandler(this));
		backBtn.addActionListener(MenuInteractions.getBackHandler(this));
		
		btnPane.add(Box.createHorizontalGlue());
		btnPane.add(playBtn);
		btnPane.add(backBtn);
		
		contents.add(titlePane, BorderLayout.NORTH);
		contents.add(levelsPane, BorderLayout.SOUTH);
		contents.add(btnPane, BorderLayout.SOUTH);
		
		contents.setBackground(Color.WHITE); //TODO - Set Background Graphics
		titlePane.setBackground(Color.WHITE);
		levelsPane.setBackground(Color.WHITE);
		btnPane.setBackground(Color.WHITE);
		
		this.setVisible(true);
	}
}
