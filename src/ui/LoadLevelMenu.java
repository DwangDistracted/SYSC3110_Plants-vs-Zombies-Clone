package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import engine.Game;
import input.MenuInteractions;
import util.GameSerializer;

public class LoadLevelMenu extends JFrame {

	private static final long serialVersionUID = 6709476189918936450L;
	
	public LoadLevelMenu() {
		
		this.setTitle("Zombies Are Vegan - Load Game");
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		
		this.setSize((int)width/3, (int)height/2); //set size relative to screen
		this.setLocationRelativeTo(null); //create at screen center
		this.setResizable(false); //prevent resizing
		
		Container contents;
		try { //try to use a background image
			contents = new JImagePanel(ImageIO.read(new File("images/title-top-background.jpg")));
		} catch (IOException e) {
			contents = new JPanel();
			e.printStackTrace();
		}
		this.setContentPane(contents);
		contents.setLayout(new BorderLayout());
		
		JPanel titlePane = new JPanel();
		titlePane.setLayout(new BorderLayout());
		
		JLabel titleFld = new JLabel("<html><br>&nbsp;Zombies are Vegan</html>");
		titleFld.setHorizontalAlignment(SwingConstants.LEFT);
		titleFld.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 15));
		titleFld.setForeground(Color.WHITE);
		titlePane.add(titleFld, BorderLayout.NORTH);
		
		JLabel levelFld = new JLabel("<html>&nbsp;Continue Game</html>");
		levelFld.setHorizontalAlignment(SwingConstants.LEFT);
		levelFld.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		levelFld.setForeground(Color.WHITE);
		titlePane.add(levelFld, BorderLayout.WEST);	
		
		JPanel savedGamePane = new JPanel();
		List<Game> savedGames = GameSerializer.savedGames;
		if (savedGames.size() == 0) {
			JLabel noGamesSaved = new JLabel("There are no games currently saved.");
			noGamesSaved.setFont(MainMenu.btnFont);
			noGamesSaved.setOpaque(false);
			noGamesSaved.setForeground(Color.WHITE);
			savedGamePane.add(noGamesSaved);
			savedGamePane.setLayout(new GridBagLayout());
		} else {
			savedGamePane.setLayout(new GridLayout(savedGames.size() % 3 != 0 ? savedGames.size()/3 + 1 : savedGames.size()/3, savedGames.size() > 3 ? 2 : savedGames.size())); //width should always be 3
		}
		ButtonGroup levelOptions = new ButtonGroup();

		for (int i = 0; i < GameSerializer.savedGames.size(); i++) {
			Game savedGame = savedGames.get(i);
			JRadioButton option = new JRadioButton("<html>" + savedGame.getLevelInfo().getName() + " Turn: " + savedGame.getTurns()  + "</html>");
			option.setFont(MainMenu.btnFont);
			option.setHorizontalAlignment(SwingConstants.CENTER);
			option.setName(String.valueOf(i)); //the name of the button is what gets passed to LevelLoader
			option.setOpaque(false);
			option.setForeground(Color.WHITE);
			
			levelOptions.add(option);
			savedGamePane.add(option);
		}
		
		JPanel btnPane = new JPanel();
		btnPane.setLayout(new BoxLayout(btnPane, BoxLayout.LINE_AXIS));
		
		JButton loadBtn = new JButton("Continue Game");
		loadBtn.setFont(MainMenu.btnFont);
		loadBtn.setAlignmentX(RIGHT_ALIGNMENT);
		JButton backBtn = new JButton("Back");
		backBtn.setFont(MainMenu.btnFont);
		backBtn.setAlignmentX(RIGHT_ALIGNMENT);
		
		//Set Action Listeners
		loadBtn.addActionListener(savedGames.size() == 0 ? null : MenuInteractions.getLoadGameHandler(this, levelOptions));
		backBtn.addActionListener(MenuInteractions.getBackHandler(this));

		btnPane.add(Box.createHorizontalGlue());
		btnPane.add(loadBtn);
		btnPane.add(backBtn);

		contents.add(titlePane, BorderLayout.NORTH);
		contents.add(savedGamePane, BorderLayout.CENTER);
		contents.add(btnPane, BorderLayout.SOUTH);
		
		titlePane.setOpaque(false);
		savedGamePane.setOpaque(false);
		btnPane.setOpaque(false);
		this.setVisible(true);
	}
}
