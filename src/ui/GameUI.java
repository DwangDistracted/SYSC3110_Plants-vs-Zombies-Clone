package ui;
import engine.Game;
import engine.GameListener;
import input.GameController;
import input.GameController.GameButtonListener;
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

import assets.PlantTypes;

/**
 * This class acts as the view (gui) when running the basic game loop.
 */

public class GameUI extends JFrame implements GameListener
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

    private Game game;
    private LevelInfo lvl;
    
    private static boolean testMode = false;

    public GameUI(Game game)
    {
    	this.game = game;
    	game.addListener(this);
    	this.lvl = game.getLevelInfo();
    	this.currentLevel = game.getLevelInfo().getName();
    	this.currentLevelNum = LevelLoader.getCurrentLevel();
    	this.row = lvl.getRows();
    	this.column = lvl.getColumns();
    	
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
	        try {
		    	image = image.getScaledInstance(mowers[i].getHeight(), mowers[i].getHeight(), Image.SCALE_DEFAULT);
		    	mowers[i].setIcon(new ImageIcon(image));
	        }
	        catch (Exception e) {
	        	e.addSuppressed(new NullPointerException());
	        }
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
	    setVisible(!testMode);
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
    	digUpButton.setActionCommand(GameButtonListener.DIG);
    	JButton undoButton = new JButton("Undo");
    	undoButton.setFont(btnFont);
    	undoButton.setActionCommand(GameButtonListener.UNDO);
    	JButton redoButton = new JButton("Redo");
    	redoButton.setFont(btnFont);
    	redoButton.setActionCommand(GameButtonListener.REDO);
    	JButton endTurnButton = new JButton("End Turn");
    	endTurnButton.setFont(endTurnFont);
    	endTurnButton.setActionCommand(GameButtonListener.END_TURN);
    	gameButtons.add(digUpButton);
    	gameButtons.add(undoButton);
    	gameButtons.add(redoButton);
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
                boardTiles[r][c] = new GridUI(game.getBoard().getGrid(r, c));
            }
        }

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
    		Card card = new Card(new BorderLayout(5,5), p);
    		card.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
    		card.setMaximumSize(new Dimension(90, 140));

    		Image img = Images.getPlantImage(p);

			JLabel labelPic = new JLabel();
			labelPic.setSize(80, 80);
			Image plantImage = null; 
			try {
				plantImage = img.getScaledInstance(labelPic.getWidth(), labelPic.getHeight(), Image.SCALE_SMOOTH);
				labelPic.setIcon(new ImageIcon(plantImage));
			}
			catch (Exception e) {
				e.addSuppressed(new NullPointerException());
			}
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
    private void setTurnLabel(int turns) {
		turnMessage.setText("<html><b>Turns: </b>" + Integer.toString(turns) + "</html>");
	}
    
    /**
     * Return a copy of the turn label message.
     * 
     * @return the turn message
     */
    public JLabel getTurnLabel() {
    	
    	// return a copy instead of a reference to prevent public modification
    	JLabel turnMessageCopy = new JLabel();
    	turnMessageCopy.setText(turnMessage.getText());
    	
    	return turnMessageCopy;
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
     * Add listeners to each grid that will show all zombies in a grid.
     * @param listener
     * @author Derek Shao
     */
    public void addShowFullListPanelListeners(MouseListener listener) {
    	for (int i = 0; i < boardTiles.length; i++) {
    		for (int j = 0; j < boardTiles[i].length; j++) {
    			boardTiles[i][j].addShowFullListListener(listener);
    		}
    	}
    }
    
    /**
     * Set the text for indicating remaining points
     * @author Derek Shao
     */
    private void setPointsLabel(int points) {
    	pointsAvailable.setText("<html><b>Points: </b>" + Integer.toString(points) + "</html>");
    }
    
    /**
     * Return a copy of the points label message.
     * 
     * @return the points message
     */
    public JLabel getPointsLabel() {
    	
    	// return a copy instead of a reference to prevent public modification
    	JLabel pointsMessageCopy = new JLabel();
    	pointsMessageCopy.setText(pointsAvailable.getText());
    	
    	return pointsMessageCopy;
    } 
    
    /**
     * Retrieve all board tiles from board.
     * @return all boards tiles
     */
    public GridUI[][] getBoardTiles() {
    	return boardTiles;
    }
    
    private void refreshAllGrids() {
    	for (int i = 0; i < boardTiles.length; i++) {
    		for (int j = 0; j < boardTiles[i].length; j++) {
    			boardTiles[i][j].renderPlant();
    			boardTiles[i][j].renderZombies();
    			boardTiles[i][j].repaint();
    			boardTiles[i][j].revalidate();
    		}
    	}
    }

	
    @Override
	public void updateGrid(int x, int y) {
		boardTiles[x][y].renderPlant();
		boardTiles[x][y].renderZombies();
		boardTiles[x][y].repaint();
		boardTiles[x][y].revalidate();
	}

	@Override
	public void updateAllGrids() {
		refreshAllGrids();
	}
	@Override
	public void updatePurse() {
		setPointsLabel(game.getPurse().getPoints());
	}
	@Override
	public void updateTurnNumber() {
		setTurnLabel(game.getTurns());
	}
	@Override
	public void updateEndTurn() {
		switch (game.getState()) { //check if there was a resolution to the game
			case WON:
				int result = JOptionPane.showConfirmDialog(this, "You won in " + game.getTurns() + " Turns! The Zombies have been slain! \n\n Do you want to play the next level?", "You WON!", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
				
				if (result == JOptionPane.YES_OPTION) { //load next level
					LevelInfo next = LevelLoader.getNextLevel();
					if (next == null) {
						JOptionPane.showMessageDialog(this, "No More Levels Available. Returning to Main Menu");//return to main menu
						new MainMenu();
						this.dispose();
					} else {
						Game g = new Game(next);
						new GameController(new GameUI(g), g);
						this.dispose();
					}
				} else { //return to main menu
					new MainMenu();
					this.dispose();
				}
				
				break;
			case LOST:
				int result1 = JOptionPane.showConfirmDialog(this, "You lost in " + game.getTurns() + " Turns! You were eaten by Zombies! \n\n Do you want to retry?", "You Lost", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
	
				if (result1 == JOptionPane.YES_OPTION && game != null) { //reload this level
					Game g = new Game(game.getLevelInfo());
					new GameController(new GameUI(g), g);
					this.dispose();
				} else { //return to main menu
					new MainMenu();
					this.dispose();
				}
				
				break;
			default: //update user points if still playing
				this.updatePurse();
				this.updateTurnNumber();
				break;
		}
	}
    @Override
    public void updateMessage(String title, String message) {
    	
    	if (testMode) {
    		return;
    	}
    	
    	JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Removes the lawn mower icon if the row lawn mower is available
     * Otherwise, the lawn mower icon is set
     * @param row - the row in which the lawn mower icon will be removed
     * @param isMowerAvailable - true if the lawn mower for the row is avaliable, false otherwise
     */
    @Override
    public void updateMower(int row, boolean isMowerAvailable){
    	if(isMowerAvailable)
    	{
    		mowers[row].setIcon(null);
    		return;
    	}
    	Image image = Images.getLawnMowerImage();
        try {
	    	image = image.getScaledInstance(mowers[row].getHeight(), mowers[row].getHeight(), Image.SCALE_DEFAULT);
	    	mowers[row].setIcon(new ImageIcon(image));
        }
        catch (Exception e) {
        	e.addSuppressed(new NullPointerException());
        }
    }

    
    /**
     * For unit testing. Do not show UI when testing.
     * 
     */
    public static void setTestMode() {
    	testMode = true;
    }
}
