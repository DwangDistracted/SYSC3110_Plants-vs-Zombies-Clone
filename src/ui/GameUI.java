package ui;
import engine.Game;
import engine.Purse;
import input.GameController;
import input.MenuInteractions;
import levels.LevelInfo;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.sun.glass.events.MouseEvent;

import assets.Plant;
import assets.PlantTypes;
/**
 * This class acts as the view (gui) when running the basic game loop.
 */
public class GameUI
{
	private JFrame f;
	private JPanel gui;
	
	private JButton[][] boardTiles;
    private JPanel board;
    private int row;
    private int column;
    
    private JPanel lawnMowers;
    private JButton[] mowers;
    
	private HashMap<JPanel, Plant> cardCollection; 
    
    private int currentLevel;
    private int currentTurn;
    private int points;
    
    private JLabel levelMessage;
    private JLabel turnMessage;
    private JLabel pointsAvaliable;
    
    //Menu Buttons
    private JButton menuButton;
    private JButton saveButton;
    private JButton undoButton;
    private JButton quitButton;
    
    private LevelInfo lvl;
    private Purse userResources;
    
    public GameUI(Game game)
    {
    	this.lvl = game.getLevelInfo();
    	this.row = lvl.getRows();
    	this.column = lvl.getColumns();
    	this.userResources = game.getPurse();
    	this.points = userResources.getPoints();
    	this.cardCollection = new HashMap<JPanel,Plant>();

    	initializeComponents();
    	initializeMenu();
    	initializeBoard();
        initUnitSelection();
        initializeJFrame();
    }

    /**
     * Initializes the JFrame that contains all the GameUI gui components
     * @author Michael Patsula
     */
    private final void initializeJFrame() 
    {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		
    	f = new JFrame("Plants Are Vegan");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        f.setSize(width, height);
        f.add(gui);
        f.setVisible(true);
    }
    
    /**
     * Initializes the gui components
     * @author Michael Patsula
     */
    private final void initializeComponents() 
    {   	
    	levelMessage = new JLabel("Level: " + currentLevel);
    	turnMessage = new JLabel("Turn: " + currentTurn);
    	pointsAvaliable = new JLabel("Points: " + points);
    	boardTiles = new JButton[row][column];
    	mowers = new JButton[row];
    	gui = new JPanel(new BorderLayout(200, 5));
    	lawnMowers = new JPanel();
    	
    	menuButton = new JButton("Menu");
    	saveButton = new JButton("Save");
    	undoButton = new JButton("Undo");
        quitButton = new JButton("Quit");
    }
    
    /**
     * Initializes the menu Jtool bar 
     * @author Michael Patsula
     */
    private final void initializeMenu()
    {
        gui.setBorder(new EmptyBorder(5,5,5,5));
        
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START); 
        tools.add(menuButton); 
        tools.add(saveButton); 
        tools.add(undoButton);
        tools.addSeparator();
        tools.add(quitButton);
        tools.addSeparator();
        tools.add(levelMessage);
        tools.addSeparator();
        tools.add(turnMessage);                               
        tools.addSeparator();
        tools.add(pointsAvaliable);
        
    }
    
    /**
     * Initializes the PVZ game board (a grid of tiles)
     * The game board consists of unit tiles and the lawn mower functionality/buttons.
     * @author Michael Patsula
     */
    private final void initializeBoard() {
    	
        board = new JPanel(new GridLayout(row, column + 1));
        board.setBorder(new LineBorder(Color.BLACK));
        gui.add(board);                                                                 
        
        
        // create the chess board squares
        Insets buttonMargin = new Insets(25,25,25,25); 
        for (int r = 0; r < boardTiles.length; r++)
        {
            for (int c = 0; c < boardTiles[r].length; c++)
            {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
         
                //ImageIcon icon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                //b.setIcon(icon);
                Image image = Images.getGrassTileImage();
            	image = image.getScaledInstance(200,200, Image.SCALE_DEFAULT);
                b.setIcon(new ImageIcon(image));
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
                        board.add(boardTiles[r][c]);                                
                }
            }
        }
        
    }
    
    /**
     * Initializes the unit selection panel.
     * This gui will allow the user to select a particular unit among many to 
     * place on the board
     * @author Michael Patsula
     */
    private final void initUnitSelection()
    {
    	
    	JPanel cardHolder = new JPanel();
    	cardHolder.setLayout(new BoxLayout(cardHolder, BoxLayout.X_AXIS));
    	cardHolder.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
    	
    	int index = 0; //to keep track of cardCollection index
    	for(PlantTypes p: lvl.getAllowedPlants())      //build plant cards
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
        	
        	cardCollection.put(card, PlantTypes.toPlant(p)); //store the card for actionlisteners implementation
        	index++;
    	}
    	
    	gui.add(cardHolder, BorderLayout.SOUTH);
    }
    
    /**
     * Once called, the specified unit card will be "highlighted".
     * ie boarder will change color
     * @param p - JPanel that will be "highlighted"
     * @author Michael Patsula
     */
    public void setHighlight(JPanel p)
    {
    	p.setBorder(BorderFactory.createLineBorder(Color.CYAN, 5));
    }
    /**
     * once called, the specified JPanel will have a black boarder
     * Used to "unhighlight" a unit card
     * @param p - JPanel that will be "unhighlighted"
     * @author Michael Patsula
     */
    public void revertHighlight(JPanel p)
    {
    	p.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
    }
    /**
     * Sets the displayed level number 
     * @param level - ultimately should be the current level number
     * @author Michael Patsula
     */
    public void setLevelMessage(int level)
    {
    	currentLevel = level;
    	levelMessage.setText("Level: " + currentLevel);
    }
    /**
     * Sets the displayed wave number
     * @param wave - ultimately should be the current wave number
     * @author Michael Patsula
     */
    public void setWaveMessage(int wave)
    {
    	currentTurn = wave;
    	turnMessage.setText("Level: " + currentTurn);
    }
    /**
     * Sets the displayed amount of points
     * @param points - ultimately should be the current amount of points the user has.
     * @author Michael Patsula
     */
    public void setPointsMessage(int points)
    {
    	this.points = points;
    	turnMessage.setText("Points: " + points);
    }
    /**
     * @return the board JPanel containing the game board grid
     * @author Michael Patsula
     */
    public final JComponent getBoard()
    {
        return board;
    }
    /**
     * @return the gui JPanel which consists of the all the gui components that
     * make up the GameUI
     * @author Michael Patsula
     */
    public final JComponent getGui()
    {
        return gui;
    }
    /**
     * @return the cardCollection data structure containing card JPanels (for unit selection) and 
     * the Plant sub-class that it corresponds to.
     * @author Michael Patsula
     */
    public HashMap<JPanel, Plant> getCardCollection()
    {
    	return cardCollection;
    }
    
    /**
     * Sets mouse listeners to gameui's interactable components
     * @param e
     * @author Michael Patsula
     */
    public void setMouseListeners(MouseListener e)
    {
    	for(JPanel i : cardCollection.keySet())
    	{
    		i.addMouseListener(GameController.unitSelectMouseListener());
    	}
    }
}
