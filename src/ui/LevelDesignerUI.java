package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import assets.PlantTypes;
import assets.ZombieTypes;
import input.MenuInteractions;

public class LevelDesignerUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTextField nameField;
	private JSpinner initResourcesField;
	private JSpinner resPerTurnField;
	private JSlider gridXField;
	private JSlider gridYField;
	private ArrayList<PlantTypes> acceptablePlantsField = new ArrayList<PlantTypes>();
	private HashMap<ZombieTypes, Integer> zombiesField = new HashMap<ZombieTypes, Integer>();
	
	public LevelDesignerUI() {
		this.setTitle("Zombies are Vegan - Level Designer");
		
		this.setTitle("Zombies are Vegan");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		GraphicsDevice gd = //Multi-Screen Support
				GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		
		this.setSize((int)(width/1.2), (int)(height/1.5)); //set size relative to screen
		this.setLocationRelativeTo(null); //create at screen center
		
		Container contents;
		try { //try to use a background image
			contents = new JImagePanel(ImageIO.read(new File("images/title-top-background.jpg")));
		} catch (IOException e) {
			contents = new JPanel();
			e.printStackTrace();
		}
		this.setContentPane(contents);
		contents.setLayout(new BorderLayout()); //Layout Manager

		JPanel titlePane = new JPanel();
		titlePane.setLayout(new BorderLayout());
		
		JLabel titleFld = new JLabel("<html><br>&nbsp;Zombies are Vegan</html>");
		titleFld.setHorizontalAlignment(SwingConstants.LEFT);
		titleFld.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 15));
		titleFld.setForeground(Color.WHITE);
		titlePane.add(titleFld, BorderLayout.NORTH);

		JPanel designPane = new JPanel();
		designPane.setLayout(new BorderLayout());
		
		JPanel nameFieldPane = new JPanel();
		nameFieldPane.add(new JLabel("Level Name: "));
		nameField = new JTextField("My Custom Level");
		nameFieldPane.add(nameField);
		
		JPanel resourcePanel = new JPanel();
		resourcePanel.add(new JLabel("Initial Resources"));
		initResourcesField = new JSpinner();
		initResourcesField.setModel(new SpinnerNumberModel(200, 0, 2000, 25));
		resourcePanel.add(initResourcesField);

		resourcePanel.add(new JLabel("Resources Gain Per Turn"));
		resPerTurnField = new JSpinner();
		resPerTurnField.setModel(new SpinnerNumberModel(25, 0, 200, 5));
		resourcePanel.add(resPerTurnField);

		JPanel gridXPanel = new JPanel();
		gridXPanel.add(new JLabel("Game Grid Horizontal Size"));
		gridXField = new JSlider(JSlider.HORIZONTAL, 6, 20, 8);
		gridXField.setSnapToTicks(true);
		gridXField.setMajorTickSpacing(2);
		gridXField.setMinorTickSpacing(1);
		gridXField.setPaintTicks(true);
		gridXField.setPaintLabels(true);
		gridXPanel.add(gridXField);

		JPanel gridYPanel = new JPanel();
		gridYPanel.add(new JLabel("Game Grid Vertical Size"));
		gridYField = new JSlider(JSlider.HORIZONTAL, 6, 20, 8);
		gridYField.setSnapToTicks(true);
		gridYField.setMajorTickSpacing(2);
		gridYField.setMinorTickSpacing(1);
		gridYField.setPaintTicks(true);
		gridYField.setPaintLabels(true);
		gridYPanel.add(gridYField);

		JPanel acceptablePlantsPanel = new JPanel();
		JCheckBox[] acceptablePlantsBtns = new JCheckBox[PlantTypes.values().length];
		int i = 0;
		for (PlantTypes p : PlantTypes.values()) {
			acceptablePlantsBtns[i] = new JCheckBox(PlantTypes.toPlant(p).toString());
			acceptablePlantsBtns[i].addItemListener (
					new ItemListener() {
						@Override
						public void itemStateChanged(ItemEvent e) {
							if (e.getStateChange() == ItemEvent.DESELECTED) {
								acceptablePlantsField.remove(p);
							} else {
								acceptablePlantsField.add(p);
							}
						}
					});
			acceptablePlantsPanel.add(acceptablePlantsBtns[i]);
			i++;
		}
		JScrollPane plantScrollPane = new JScrollPane(acceptablePlantsPanel);
		
		JPanel zombieNumberPanel = new JPanel();
		zombieNumberPanel.setLayout(new BoxLayout(zombieNumberPanel, BoxLayout.PAGE_AXIS));
		JCheckBox[] zombieBtns = new JCheckBox[ZombieTypes.values().length];
		JSpinner[] zombieNumber = new JSpinner[ZombieTypes.values().length];
		
		int j = 0;
		for (ZombieTypes z : ZombieTypes.values()) {
			JPanel type = new JPanel();
			type.setOpaque(false);
			zombieBtns[j] = new JCheckBox(ZombieTypes.toZombie(z).toString());
			type.add(zombieBtns[j]);
			
			zombieNumber[j] = new JSpinner();
			zombieNumber[j].setModel(new SpinnerNumberModel(1,0,100,1));

			int x = j;
			zombieBtns[j].addItemListener (
					new ItemListener() {
						@Override
						public void itemStateChanged(ItemEvent e) {
							if (e.getStateChange() == ItemEvent.DESELECTED) {
								zombiesField.remove(z);
								zombieNumber[x].setEnabled(true);
							} else {
								zombiesField.put(z, (Integer)zombieNumber[x].getValue());
								zombieNumber[x].setEnabled(false);
							}
						}
					});
			type.add(zombieNumber[j]);
			zombieNumberPanel.add(type);
			j++;
		}
		JScrollPane zombieScrollPane = new JScrollPane(zombieNumberPanel);
		
		JPanel temp = new JPanel();
		temp.setLayout(new BoxLayout(temp, BoxLayout.PAGE_AXIS));
		temp.add(nameFieldPane);
		temp.add(gridXPanel);
		temp.add(gridYPanel);
		temp.add(resourcePanel);
		designPane.add(temp, BorderLayout.NORTH);
		designPane.add(zombieScrollPane, BorderLayout.CENTER);
		designPane.add(plantScrollPane, BorderLayout.SOUTH);
		
		JPanel btnPane = new JPanel();
		btnPane.setLayout(new BoxLayout(btnPane, BoxLayout.LINE_AXIS));
		
		JButton saveLevelBtn = new JButton("Save Level");
		saveLevelBtn.setFont(MainMenu.btnFont);
		saveLevelBtn.addActionListener(MenuInteractions.getSaveLevelHandler(this));
		JButton cancelLevelBtn = new JButton("Back");
		cancelLevelBtn.setFont(MainMenu.btnFont);
		cancelLevelBtn.addActionListener(MenuInteractions.getLevelsHandler(this));

		btnPane.add(saveLevelBtn);
		btnPane.add(Box.createHorizontalGlue());
		btnPane.add(cancelLevelBtn);
		
		contents.add(titlePane, BorderLayout.NORTH);
		contents.add(designPane, BorderLayout.CENTER);
		contents.add(btnPane, BorderLayout.SOUTH);
		
		titlePane.setOpaque(false); //must be transparent to see background
		designPane.setOpaque(false);
		btnPane.setOpaque(false);
		this.setVisible(true);
	}

	public String getNameFieldValue() {
		return nameField.getText();
	}

	public Integer getInitResourcesFieldValue() {
		return (Integer)initResourcesField.getValue();
	}

	public Integer getResPerTurnFieldValue() {
		return (Integer)resPerTurnField.getValue();
	}

	public Integer getGridXFieldValue() {
		return (Integer)gridXField.getValue();
	}

	public Integer getGridYFieldValue() {
		return (Integer)gridYField.getValue();
	}
	
	public ArrayList<PlantTypes> getSelectedPlants() {
		return acceptablePlantsField;
	}
	
	public HashMap<ZombieTypes, Integer> getSelectedZombies() {
		return zombiesField;
	}
}
