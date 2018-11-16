package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import input.MenuInteractions;

/**
 * Main Menu UI for Zombies are Vegan
 * @author David Wang
 *
 */
public class MainMenu extends JFrame {
	private static final long serialVersionUID = -1199792392732674767L;
	private static final String VERSION = "Milestone 2";
	private static final String DATE = "2018/11/11";

	public static Font btnFont = new Font(Font.MONOSPACED, Font.PLAIN, 18);
		
	public MainMenu() {
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
		
		JPanel titlePane;
		try { //try to use a background image
			titlePane = new JImagePanel(ImageIO.read(new File("images/title-top-background.jpg")));
		} catch (IOException e1) {
			titlePane = new JPanel();
			titlePane.setBackground(Color.WHITE);
			e1.printStackTrace();
		}
		titlePane.setLayout(new BoxLayout(titlePane, BoxLayout.PAGE_AXIS));
		
		JLabel titleFld = new JLabel("<html><br>Zombies are Vegan</html>");
		titleFld.setHorizontalAlignment(SwingConstants.CENTER);
		titleFld.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		titleFld.setForeground(Color.white);
		
		JLabel authorFld = new JLabel("<html>Tanisha Garg | Michael Patsula | Derek Shao | David Wang</html>");
		authorFld.setHorizontalAlignment(SwingConstants.CENTER);
		authorFld.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 15));
		authorFld.setForeground(Color.white);
		
		titlePane.add(titleFld);
		titlePane.add(authorFld);
		contents.add(titlePane, BorderLayout.NORTH);

		JPanel btnPane;
		try { //try to use a background image
			btnPane = new JImagePanel(ImageIO.read(new File("images/title-top-background.jpg")));
		} catch (IOException e1) {
			btnPane = new JPanel();
			btnPane.setBackground(Color.WHITE);
			e1.printStackTrace();
		}
		btnPane.setLayout(new BoxLayout(btnPane, BoxLayout.LINE_AXIS));
		
		JLabel versionFld = new JLabel();
		versionFld.setText("<html>&nbsp;" + VERSION +  " - " + DATE + "</html>");
		versionFld.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 10));
		versionFld.setVerticalAlignment(SwingConstants.BOTTOM);
		versionFld.setForeground(Color.WHITE);
		
		Font btnFont = new Font(Font.MONOSPACED, Font.PLAIN, 18);
		
		JButton playBtn = new JButton("PLAY");
		playBtn.setToolTipText("Play from the First Level");
		playBtn.setFont(btnFont);
		JButton levelsBtn = new JButton("LEVELS");
		levelsBtn.setToolTipText("Select a Level to Play");
		levelsBtn.setFont(btnFont);
		JButton loadBtn = new JButton("LOAD");
		loadBtn.setFont(btnFont);
		loadBtn.setToolTipText("NOT YET IMPLEMENTED");
		JButton quitBtn = new JButton("QUIT");
		quitBtn.setToolTipText("Quit to Desktop");
		quitBtn.setFont(btnFont);

		//TODO - set Action Handlers
		playBtn.addActionListener(MenuInteractions.getPlayHandler(this));
		levelsBtn.addActionListener(MenuInteractions.getLevelsHandler(this));
		loadBtn.addActionListener(MenuInteractions.getLoadHandler(this));
		quitBtn.addActionListener(MenuInteractions.getQuitHandler(this));
		
		btnPane.add(versionFld);
		btnPane.add(playBtn);
		btnPane.add(levelsBtn);
		btnPane.add(loadBtn);
		btnPane.add(quitBtn);
		contents.add(btnPane, BorderLayout.SOUTH);
		
		BufferedImage myPicture;
		try { //insert a background image
			myPicture = ImageIO.read(new File("images/title-background.jpg"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT)));
			contents.add(picLabel, BorderLayout.CENTER);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			contents.setBackground(Color.WHITE);
			btnPane.setBackground(Color.WHITE);
			titlePane.setBackground(Color.WHITE);
		}
		
		this.setVisible(true); //Show this monstrosity
	}
}
