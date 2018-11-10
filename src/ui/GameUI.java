package ui;
import engine.Game;
import engine.Purse;
import levels.LevelInfo;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import assets.PlantTypes;
import assets.Zombie;
import assets.ZombieTypes;



public class GameUI extends JFrame
{
	private final JPanel gui = new JPanel(new BorderLayout(200, 5));
	
	private JButton[][] boardTiles;
    private JPanel Board;
    private int row;
    private int column;
    
    private JPanel lawnMowers = new JPanel();
    private JButton[] mowers;
    
    private JPanel unitSelect = new JPanel();
    
    private int currentLevel;
    private JLabel levelMessage = new JLabel("Level: " + currentLevel);
    private int currentWave;
    private JLabel waveMessage = new JLabel("Wave: " + currentWave);
    private int points;
    private JLabel pointsAvaliable = new JLabel("Points: " + points);
    
    private LevelInfo lvl;
    private Purse userResources;
   
    
    public GameUI(LevelInfo lvl, Purse userResources)
    {
    	JFrame f = new JFrame("Plants Are Vegan");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLocationByPlatform(true);
        f.setSize(750,750);
        f.add(this.getGui());
        
    	this.row = lvl.getRows();
    	this.column = lvl.getColumns();
    	this.lvl = lvl;
    	this.points = userResources.getPoints();
    	this.userResources = userResources;
    	
    	boardTiles = new JButton[row][column];
    	mowers = new JButton[row];
    	
        initializeGui();
        initUnitSelection();
        
        f.setVisible(true);
        //f.pack();
    }

    public final void initializeGui()
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

        Board = new JPanel(new GridLayout(row, column + 1));
        Board.setBorder(new LineBorder(Color.BLACK));
        gui.add(Board);                                                                 //*
        
        
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
        
        
        ImageIcon img = new ImageIcon("images\\MTD_Lawn_Mower.JPG");
    	Image image = img.getImage(); // transform it
    	image = image.getScaledInstance(200,200, Image.SCALE_DEFAULT);
        // fill the black non-pawn piece row
        for (int r = 0; r < row; r++) 
        {
            for (int c = 0; c < column; c++)
            {
                switch (c)
                {
                    case 0:
                    	JButton b = new JButton(new ImageIcon (image));
                    	//b.setBorder(BorderFactory.createEmptyBorder());
                    	b.setContentAreaFilled(false);
                    	mowers[r] = b;
                        Board.add(mowers[r]);  //$
                    default:
                        Board.add(boardTiles[r][c]);                                //$
                }
            }
        }
        
        
    } //end of initialization
    
    public final void initUnitSelection()
    {
    	
    	Images image = new Images();
    	
    	JPanel cardHolder = new JPanel();
    	cardHolder.setLayout(new BoxLayout(cardHolder, BoxLayout.X_AXIS));
    	
    	for(PlantTypes p: lvl.getAllowedPlants())         //build plant cards
    	{
    		JPanel card = new JPanel(new BorderLayout(5, 5));
    		
    		Image img = null;
			try {
				img = image.getImage2(p.toPlant(p));
			} catch (IOException e) {
				System.out.println("Image error");
				e.printStackTrace();
			}
			
    		JLabel labelPic = new JLabel(new ImageIcon(img));
    		card.add(labelPic, BorderLayout.CENTER);
    		
    		JLabel nameLabel = new JLabel(p.toPlant(p).toString());
    		nameLabel.setHorizontalAlignment(JLabel.CENTER);
    		card.add(nameLabel, BorderLayout.NORTH);
    		
    		JLabel costLabel = new JLabel(String.valueOf(p.toPlant(p).getCost()));  //cost of the plant
    		costLabel.setHorizontalAlignment(JLabel.CENTER);
    		card.add(costLabel, BorderLayout.SOUTH);
    		
    		cardHolder.add(card);
    	}
    	JScrollPane pane = new JScrollPane(cardHolder,
    			ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
    			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    	
    	gui.add(pane, BorderLayout.SOUTH);
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
        return Board;
    }

    public final JComponent getGui() {
        return gui;
    }
    
    /*
    public static void main(String[] args)
    {
        Runnable r = new Runnable() 
        {
            @Override
            public void run() 
            {
            	
                //GameUI cb = new GameUI(5,8);

                JFrame f = new JFrame("Plants Are Vegan");
                f.add(cb.getGui());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);

               
                f.pack();  // ensures the frame is the minimum size it needs to be in order display the components within it
                f.setMinimumSize(f.getSize());  // ensures the minimum size is enforced.
                f.setVisible(true);
                
            }
        };
        SwingUtilities.invokeLater(r);
    } */
}
