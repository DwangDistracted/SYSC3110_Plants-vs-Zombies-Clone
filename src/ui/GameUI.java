package ui;
import engine.Board;
import engine.Game;
import input.MenuInteractions;
import levels.LevelInfo;
import levels.LevelLoader;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import assets.Plant;
import assets.PlantTypes;

/**
 * This class acts as the view (gui) when running the basic game loop.
 */

public class GameUI extends JFrame
{	
	private static Font lblFont = new Font(Font.MONOSPACED, Font.PLAIN, 15);
	private static Font btnFont = new Font(Font.MONOSPACED, Font.PLAIN, 18);
	private static Font endTurnFont = new Font(Font.MONOSPACED, Font.BOLD, 20);
	
	private static final long serialVersionUID = -717683255015646823L;

	private JPanel gui;

	private GridUI[][] boardTiles;
    private JPanel board;
    private int row;
    private int column;

    private JButton[] mowers;

	private JPanel cardHolder;

    private String currentLevel;
    private int currentLevelNum;
    
    private JLabel levelMessage;
    private JLabel turnMessage;
    private JLabel pointsAvailable;

    private ArrayList<JMenuItem> menuButtons;
    private ArrayList<JButton> gameButtons;

    private LevelInfo lvl;
	private Board gameBoard;

    public GameUI(Game game)
    {
    	this.lvl = game.getLevelInfo();
    	this.currentLevel = game.getLevelInfo().getName();
    	this.currentLevelNum = LevelLoader.getCurrentLevel();
    	this.row = lvl.getRows();
    	this.column = lvl.getColumns();
        this.gameBoard = game.getBoard();

    	initializeComponents();
    	initializeMenu();
    	initializeBoard();
        initUnitSelection();
        initializeJFrame();
        initializeImages();
    }

    /**
     * Initializes the Mower Images
     * @author David Wang
     */
    private void initializeImages() {
    	for (int i = 0; i < mowers.length; i++) {
	        Image image = Images.getLawnMowerImage();
	    	image = image.getScaledInstance(mowers[i].getHeight(), mowers[i].getHeight(), Image.SCALE_DEFAULT);
	    	mowers[i].setIcon(new ImageIcon(image));
	    	mowers[i].setText("");
    	}
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

    	setTitle("Plants Are Vegan");
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setExtendedState(JFrame.MAXIMIZED_BOTH);
	    setSize(width, height);
	    add(gui);
	    setVisible(true);
    }

    /**
     * Initializes the gui components
     * @author Michael Patsula and David Wang
     */
    private final void initializeComponents()
    {
    	levelMessage = new JLabel("<html><b>Level:</b> " + currentLevelNum + " - " + currentLevel + "</html>");
    	levelMessage.setFont(lblFont);
    	turnMessage = new JLabel("<html><b>Turn:</b> 0</html>");
    	turnMessage.setFont(lblFont);
    	pointsAvailable = new JLabel("<html><b>Points:</b> " + this.lvl.getInitResources() + "</html>");
    	pointsAvailable.setFont(lblFont);
    	boardTiles = new GridUI[row][column];
    	mowers = new JButton[row];
    	gui = new JPanel(new BorderLayout(200, 5));

        menuButtons = new ArrayList<JMenuItem>();
		JMenuItem backItem = new JMenuItem("Main Menu");
		backItem.addActionListener(MenuInteractions.getBackHandler(this));
		menuButtons.add(backItem);
		JMenuItem saveItem = new JMenuItem("Save");
		saveItem.addActionListener(MenuInteractions.getSaveHandler(this));
		menuButtons.add(saveItem);
		JMenuItem quitItem = new JMenuItem("Quit");
		quitItem.addActionListener(MenuInteractions.getQuitHandler(this));
		menuButtons.add(quitItem);

    	gameButtons = new ArrayList<JButton>();
    	JButton digUpButton = new JButton("Dig Up");
    	digUpButton.setFont(btnFont);
    	digUpButton.setActionCommand("Dig Up");
    	JButton undoButton = new JButton("Undo");
    	undoButton.setFont(btnFont);
    	undoButton.setActionCommand("Undo");
    	JButton endTurnButton = new JButton("End Turn");
    	endTurnButton.setFont(endTurnFont);
    	endTurnButton.setActionCommand("End Turn");
    	gameButtons.add(digUpButton);
    	gameButtons.add(undoButton);
    	gameButtons.add(endTurnButton);
    }

    /**
     * Initializes the menu components
     * @author Michael Patsula and David Wang
     */
    private final void initializeMenu()
    {
        gui.setBorder(new EmptyBorder(5,5,5,5));

        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        for (JMenuItem button : menuButtons) {
        	menu.add(button);
        }
        menubar.add(menu);
        this.setJMenuBar(menubar);

        JToolBar tools = new JToolBar();
        tools.add(Box.createHorizontalGlue());
        tools.add(levelMessage);
        tools.addSeparator();
        tools.add(turnMessage);
        tools.addSeparator();
        tools.add(pointsAvailable);
        tools.addSeparator();

        for (JButton button : gameButtons) {
        	tools.add(button);
        }

        tools.setFloatable(false);
        tools.setOpaque(false);
        gui.add(tools, BorderLayout.PAGE_START);
    }

    /**
     * Initializes the PVZ game board (a grid of tiles)
     * The game board consists of unit tiles and the lawn mower functionality/buttons.
     * @author Michael Patsula and David Wang
     */
    private final void initializeBoard() {
        board = new JImagePanel(Images.getGrassTileImage(), new GridLayout(row, column + 1, 5, 0));
        board.setBorder(new LineBorder(Color.BLACK));
        gui.add(board);
        
        for (int r = 0; r < boardTiles.length; r++)
        {
            for (int c = 0; c < boardTiles[r].length; c++)
            {
                boardTiles[r][c] = new GridUI(gameBoard.getGrid(r, c));
            }
        }

        // fill the black non-pawn piece row
        for (int r = 0; r < row; r++)
        {
            for (int c = 0; c < column; c++)
            {
                switch (c)
                {
                    case 0:
                    	JButton b = new JButton("Mower");
                    	b.setContentAreaFilled(false);
                    	mowers[r] = b;
                        board.add(mowers[r]);
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
    	cardHolder = new JPanel();
    	cardHolder.setOpaque(false);
    	cardHolder.setLayout(new BoxLayout(cardHolder, BoxLayout.X_AXIS));
    	cardHolder.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

    	for(PlantTypes p: lvl.getAllowedPlants())      //build plant cards
    	{
    		Card card = new Card(new BorderLayout(5,5), PlantTypes.toPlant(p));
    		card.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
    		card.setMaximumSize(new Dimension(90, 140));
    		card.setUnit(PlantTypes.toPlant(p));

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

    /**
     * Once called, the specified unit card will be "highlighted".
     * ie boarder will change color
     * @param p - JPanel that will be "highlighted"
     * @author Michael Patsula
     */
    public void setHighlight(JPanel p)
    {
    	if (p != null) {
        	p.setBorder(BorderFactory.createLineBorder(Color.CYAN, 5));
    	}
    }
    /**
     * once called, the specified JPanel will have a black boarder
     * Used to "unhighlight" a unit card
     * @param p - JPanel that will be "unhighlighted"
     * @author Michael Patsula
     */
    public void revertHighlight(JPanel p)
    {
    	if (p != null) {
    		p.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
    	}
    }
    
    /**
     * Sets the displayed wave number
     * @param wave - ultimately should be the current wave number
     * @author Michael Patsula and David Wang
     */
    public void setTurnLabel(int turns) {
		turnMessage.setText("<html><b>Turns: </b>" + Integer.toString(turns) + "</html>");
	}
 
    /**
     * @return the JPanel holding all the unit selection cards
     * @author Michael Patsula
     */
    public JPanel getCardHolder()
    {
    	return cardHolder;
    }
    /**
     * @return a list of JMenuItem components
     * @author Michael Patsula
     */
    public ArrayList<JMenuItem> getMenuButtons()
    {
    	return menuButtons;
    }
    /**
     * @return a list of JMeuItem components
     * @author Michael Patsula
     */
    public ArrayList<JButton> getGameButtons()
    {
    	return gameButtons;
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
    
    public JButton[] getMowers()
    {
    	return mowers;
    }


    /**
     * Add listeners for the board tiles
     * 
     * @param listener
     * @author Derek Shao
     */
    public void addGridListeners(MouseListener listener) {
    	
    	for (int i = 0; i < boardTiles.length; i++) {
    		for (int j = 0; j < boardTiles[i].length; j++) {
    			boardTiles[i][j].addMouseListener(listener);
    		}
    	}
    }

    /**
     * Sets the unit selection cards as mouse listeners
     * @param e
     * @author Michael Patsula
     */
    public void addUnitSelectionListeners(MouseListener listener)
    {
    	for (Component c : cardHolder.getComponents()) {
    		c.addMouseListener(listener);
    	}
    }

    /**
     * Sets the game buttons as action listeners
     * @param e
     * @author Michael Patsula
     */
    public void addGameButtonListeners(ActionListener listener) {
    	for (JButton button : gameButtons) {
    		button.addActionListener(listener);
    	}
    }

    /**
     * Sets the lawn mower buttons as action listeners
     * @param e
     * @author Michael Patsula
     */
    public void addLawnMowerListeners(ActionListener listener) {
    	for (JButton button : mowers) {
    		button.addActionListener(listener);
    	}
    }
    
    /**
     * Set the text for indicating remaining points
     * @author Derek Shao
     */
    public void setPointsLabel(int points) {
        pointsAvailable.setText("<html><b>Points: </b>" + Integer.toString(points) + "</html>");
    }
    
    /**
     * Indicate message notifying player they can not afford 
     * the specified plant
     * 
     * @param plant the plant they can not afford
     * @author Derek Shao
     */
    public void showInsufficientFundsOptionPane(Plant plant) {
    	JOptionPane.showMessageDialog(null, "You do not have enough funds for: " + plant.toString());
    }
    
    /**
     * Retrieve all board tiles from board.
     * @return all boards tiless
     */
    public GridUI[][] getBoardTiles() {
    	
    	return boardTiles;
    }
}
