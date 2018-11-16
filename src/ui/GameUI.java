package ui;

import engine.Board;
import engine.Game;
import engine.Purse;
import levels.LevelInfo;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import assets.Flower;
import assets.PlantTypes;
import assets.Regular_Zombie;

public class GameUI extends JFrame
{
	private JPanel gui;
	
	private GridUI[][] boardTiles;
    private JPanel board;
    private int row;
    private int column;
    
    private JPanel lawnMowers;
    private JButton[] mowers;
    private JPanel unitSelect;
    
    private int currentLevel;
    private int currentTurn;
    private int points;
    
    private JLabel levelMessage;
    private JLabel turnMessage;
    private JLabel pointsAvaliable;
    
    //Menu Buttons
    private ArrayList<JButton> toolBarButtons;
    
    private LevelInfo lvl;
    private Purse userResources;
    private Board gameBoard;
    
    public GameUI(Game game)
    {
    	this.lvl = game.getLevelInfo();
    	this.row = lvl.getRows();
    	this.column = lvl.getColumns();
    	this.userResources = game.getPurse();
        this.points = userResources.getPoints();
        this.gameBoard = game.getBoard();

    	initializeComponents();
    	initializeMenu();
    	initializeBoard();
        initUnitSelection();
        initializeJFrame();
    }

    private final void initializeJFrame() 
    {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		
    	setTitle("Plants Are Vegan");
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setExtendedState(JFrame.MAXIMIZED_BOTH); 
	    setSize(width, height);
	    add(gui);
	    setVisible(true);
    }
    
    private final void initializeComponents() 
    {   	
    	levelMessage = new JLabel("Level: " + currentLevel);
    	turnMessage = new JLabel("Turn: " + currentTurn);
    	pointsAvaliable = new JLabel("Points: " + points);
    	boardTiles = new GridUI[row][column];
    	mowers = new JButton[row];
    	gui = new JPanel(new BorderLayout(200, 5));
    	lawnMowers = new JPanel();
    	unitSelect  = new JPanel();
    	
    	toolBarButtons = new ArrayList<JButton>();
    	toolBarButtons.add(new JButton("Menu"));
    	toolBarButtons.add(new JButton("Save"));
    	toolBarButtons.add(new JButton("Undo"));
    	toolBarButtons.add(new JButton("Quit"));
    	toolBarButtons.add(new JButton("End Turn"));
    }
    
    private final void initializeMenu()
    {
        gui.setBorder(new EmptyBorder(5,5,5,5));
        
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START); 
        
        for (JButton button : toolBarButtons) {
        	tools.add(button);
        }
        
        tools.addSeparator();
        tools.add(levelMessage);
        tools.addSeparator();
        tools.add(turnMessage);                               
        tools.addSeparator();
        tools.add(pointsAvaliable);
        
    }
    
    
    private final void initializeBoard() {
    	
        board = new JPanel(new GridLayout(row, column + 1, 5, 0));
        board.setBorder(new LineBorder(Color.BLACK));
        gui.add(board);
        

        for (int r = 0; r < boardTiles.length; r++)
        {
            for (int c = 0; c < boardTiles[r].length; c++)
            {
            	gameBoard.getGrid(r, c).addZombie(new Regular_Zombie());
            	gameBoard.getGrid(r, c).setPlant(new Flower());
                boardTiles[r][c] = new GridUI(gameBoard.getGrid(r, c));
            }
        }
        
        
        Image image = Images.getLawnMowerImage();
    	image = image.getScaledInstance(200,100, Image.SCALE_DEFAULT);
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
                        board.add(mowers[r]);
                    default:
                        board.add(boardTiles[r][c]);
                }
            }
        }
        
    }
    
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
    	currentTurn = wave;
    	turnMessage.setText("Level: " + currentTurn);
    }
    
    public void setPointsMessage(int points)
    {
    	this.points = points;
    	turnMessage.setText("Points: " + points);
    }
    
    public final JComponent getBoard() {
        return board;
    }

    public final JComponent getGui() {
        return gui;
    }
    
    public void addGridListeners(MouseListener listener) {
    	
    	for (int i = 0; i < boardTiles.length; i++) {
    		for (int j = 0; j < boardTiles[i].length; j++) {
    			boardTiles[i][j].addMouseListener(listener);
    		}
    	}
    }
    
    public void addUnitSelectionListeners(MouseListener listener) {
    	
    }
    
    public void addMenuButtonListeners(ActionListener listener) {
    	for (JButton button : toolBarButtons) {
    		button.addActionListener(listener);
    	}
    }
}
