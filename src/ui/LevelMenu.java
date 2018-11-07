package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import input.MenuInteractions;
import levels.LevelInfo;
import levels.LevelLoader;

public class LevelMenu extends JFrame {
	private static final long serialVersionUID = -4952911219010614232L;
	Font btnFont = new Font(Font.MONOSPACED, Font.PLAIN, 18);

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
		List<LevelInfo> levels = LevelLoader.getLevels();
		levelsPane.setLayout(new GridLayout(levels.size()%3 != 0? levels.size()/3 +1 : levels.size()/3, levels.size() > 3? 2 : levels.size())); //width should always be 3
		ButtonGroup levelOptions = new ButtonGroup();

		for (int i = 0; i < levels.size(); i++) {
			JRadioButton option = new JRadioButton("<html>&nbsp;" + levels.get(i).getName() + "<br>" + levels.get(i).getLevelRating() + " Stars</html>");
			option.setFont(btnFont);
			option.setHorizontalAlignment(SwingConstants.CENTER);
			option.setName(String.valueOf(i+1));
			levelOptions.add(option);
			levelsPane.add(option);
		}
		
		JPanel btnPane = new JPanel();
		btnPane.setLayout(new BoxLayout(btnPane, BoxLayout.LINE_AXIS));
		
		JButton playBtn = new JButton("Play Level");
		playBtn.setFont(btnFont);
		playBtn.setAlignmentX(RIGHT_ALIGNMENT);
		JButton backBtn = new JButton("Back");
		backBtn.setFont(btnFont);
		backBtn.setAlignmentX(RIGHT_ALIGNMENT);
		
		//Set Action Listeners
		playBtn.addActionListener(MenuInteractions.getPlayHandler(this, levelOptions));
		backBtn.addActionListener(MenuInteractions.getBackHandler(this));
		
		btnPane.add(Box.createHorizontalGlue());
		btnPane.add(playBtn);
		btnPane.add(backBtn);
		
		contents.add(titlePane, BorderLayout.NORTH);
		contents.add(levelsPane, BorderLayout.CENTER);
		contents.add(btnPane, BorderLayout.SOUTH);
		
		contents.setBackground(Color.WHITE); //TODO - Set Background Graphics
		titlePane.setBackground(Color.WHITE);
		levelsPane.setBackground(Color.WHITE);
		btnPane.setBackground(Color.WHITE);
		this.setVisible(true);
	}
}
