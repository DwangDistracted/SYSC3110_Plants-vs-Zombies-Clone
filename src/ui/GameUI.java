package ui;
import engine.Purse;
import levels.LevelInfo;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import assets.PlantTypes;

public class GameUI
{
	private JPanel gui;
	
	private JButton[][] boardTiles;
    private JPanel board;
    private int row;
    private int column;
    
    private JPanel lawnMowers;
    private JButton[] mowers;
    private JPanel unitSelect;
    
    private int currentLevel;
    private int currentWave;
    private int points;
    
    private JLabel levelMessage;
    private JLabel waveMessage;
    private JLabel pointsAvaliable;
    
    private LevelInfo lvl;
    private Purse userResources;
    
    public GameUI(LevelInfo lvl, Purse userResources)
    {
    	this.row = lvl.getRows();
    	this.column = lvl.getColumns();
    	this.lvl = lvl;
    	this.points = userResources.getPoints();
    	this.userResources = userResources;

    	initializeComponents();
        initializeGui();
        initUnitSelection();
        initializeJFrame();
    }

    private final void initializeJFrame() 
    {
		GraphicsDevice gd =GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		
    	JFrame f = new JFrame("Plants Are Vegan");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLocationByPlatform(true);
        f.setSize(width, height);
        f.add(gui);
        f.setVisible(true);
    }
    
    private final void initializeComponents() 
    {   	
    	levelMessage = new JLabel("Level: " + currentLevel);
    	waveMessage = new JLabel("Wave: " + currentWave);
    	pointsAvaliable = new JLabel("Points: " + points);
    	boardTiles = new JButton[row][column];
    	mowers = new JButton[row];
    	gui = new JPanel(new BorderLayout(200, 5));
    	lawnMowers = new JPanel();
    	unitSelect  = new JPanel();
    }
    
    private final void initializeGui()
    {
        gui.setBorder(new EmptyBorder(5,5,5,5));
        
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);                                            //*
        tools.add(new JButton("Menu")); // TODO - add functionality
        tools.add(new JButton("Save")); // TODO - add functionality
        tools.add(new JButton("Undo")); // TODO - add functionality
        tools.addSeparator();
        tools.add(new JButton("Quit")); // TODO - add functionality
        tools.addSeparator();
        tools.add(levelMessage);
        tools.addSeparator();
        tools.add(waveMessage);                               
        tools.addSeparator();
        tools.add(pointsAvaliable);

        board = new JPanel(new GridLayout(row, column + 1));
        board.setBorder(new LineBorder(Color.BLACK));
        gui.add(board);                                                                 //*
        
        
        // create the chess board squares
        Insets buttonMargin = new Insets(25,25,25,25); 
        for (int r = 0; r < boardTiles.length; r++)
        {
            for (int c = 0; c < boardTiles[r].length; c++)
            {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
         
                ImageIcon icon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                b.setBackground(Color.GREEN);

                boardTiles[r][c] = b;
            }
        }
        
        
        Image image = Images.getLawnMowerImage();
    	image = image.getScaledInstance(200,200, Image.SCALE_DEFAULT);
        // fill the black non-pawn piece row
        for (int r = 0; r < row; r++) 
        {
            for (int c = 0; c < column; c++)
            {
                switch (c)
                {
                    case 0:
                    	JButton b = new JButton(new ImageIcon(image));
                    	b.setContentAreaFilled(false);
                    	mowers[r] = b;
                        board.add(mowers[r]);  //$
                    default:
                        board.add(boardTiles[r][c]);                                //$
                }
            }
        }
        
        
    } //end of initialization
    
    private final void initUnitSelection()
    {
    	
    	JPanel cardHolder = new JPanel();
    	cardHolder.setLayout(new BoxLayout(cardHolder, BoxLayout.X_AXIS));
    	cardHolder.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));


    	for(PlantTypes p: lvl.getAllowedPlants())         //build plant cards
    	{
    		JPanel card = new JPanel(new BorderLayout(5, 5));
    		card.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
    		card.setMaximumSize(new Dimension(90, 140));
    		
    		Image img = Images.getPlantImage(p);
			
			JLabel labelPic = new JLabel();
			labelPic.setSize(80, 80);
			Image plantImage = img.getScaledInstance(labelPic.getWidth(), labelPic.getHeight(), Image.SCALE_SMOOTH);
			labelPic.setIcon(new ImageIcon(plantImage));
    		card.add(labelPic, BorderLayout.CENTER);
    		
    		JLabel nameLabel = new JLabel(PlantTypes.toPlant(p).toString());
    		nameLabel.setHorizontalAlignment(JLabel.CENTER);
    		card.add(nameLabel, BorderLayout.NORTH);
    		
    		JLabel costLabel = new JLabel(String.valueOf(PlantTypes.toPlant(p).getCost()));  //cost of the plant
    		costLabel.setHorizontalAlignment(JLabel.CENTER);
    		card.add(costLabel, BorderLayout.SOUTH);
    		cardHolder.add(card);
        	cardHolder.add(Box.createRigidArea(new Dimension(5, 5)));	
    	}
    	
    	gui.add(cardHolder, BorderLayout.SOUTH);
    }
    
    public void setLevelMessage(int level)
    {
    	currentLevel = level;
    	levelMessage.setText("Level: " + currentLevel);
    }
    
    public void setWaveMessage(int wave)
    {
    	currentWave = wave;
    	waveMessage.setText("Level: " + currentWave);
    }
    
    public void setPointsMessage(int points)
    {
    	this.points = points;
    	waveMessage.setText("Points: " + points);
    }
    
    public final JComponent getBoard() {
        return board;
    }

    public final JComponent getGui() {
        return gui;
    }
}
