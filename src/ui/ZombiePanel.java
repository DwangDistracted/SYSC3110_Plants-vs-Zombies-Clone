package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import assets.ZombieTypes;

public class ZombiePanel extends JPanel {
	private static final long serialVersionUID = 4727511739555951187L;
	private static final int MAX_ZOMBIE_TYPES = 1;
	
	private Map<ZombieTypes, Integer> zombieTypeCount;
	
	public ZombiePanel(Component parent, Map<ZombieTypes,Integer> map, MouseListener listener) {
		super();
		
		this.zombieTypeCount = map;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setOpaque(false);
		if (map.keySet().size() <= MAX_ZOMBIE_TYPES) {
			for (ZombieTypes zombieType : map.keySet()) {
				addZombieType(zombieType, parent, map.get(zombieType),map.keySet().size());
			}
		} else {
			int count = 0;
			for (ZombieTypes zombieType : map.keySet()) {
				count++;
				if (count > MAX_ZOMBIE_TYPES) break;

				addZombieType(zombieType, parent, map.get(zombieType),3);
			}
			
			JPanel showFullList = new JPanel();
			JLabel fullListLabel = new JLabel();
			showFullList.setOpaque(false);
			fullListLabel.setText("Show More");
			fullListLabel.setForeground(Color.WHITE);
			showFullList.add(fullListLabel);
			showFullList.addMouseListener(listener);
			
			this.add(showFullList);
		}
		
	}

	/**
	 * Add a zombie image to the panel based on the specified type and their count
	 * 
	 * @param zombieType the type of zombie
	 * @param parent the parent component
	 * @param count the number of zombies
	 * @param mapSize the different type of zombies
	 */
	private void addZombieType(ZombieTypes zombieType, Component parent, int count, int mapSize) {
		Image zombieImage = Images.getZombieImage(zombieType);
		zombieImage = zombieImage.getScaledInstance(parent.getHeight()/(mapSize + 1) == 0? 50: parent.getHeight()/(mapSize + 1), //try to set the dimensions to relative to the parent
													parent.getHeight()/mapSize == 0? 50: parent.getHeight()/mapSize,
													Image.SCALE_DEFAULT);
		
		JPanel zombieDisplay = new JPanel();
		zombieDisplay.setOpaque(false);
		JLabel zombieLbl = new JLabel(new ImageIcon(zombieImage));
		
		zombieLbl.setText(" x " + count);
		zombieLbl.setForeground(Color.WHITE);
		zombieDisplay.add(zombieLbl);
		this.add(zombieDisplay);
	}
	
	public void showFullZombieList() {
		
		JPanel fullZombieList = new JPanel();
		fullZombieList.setLayout(new GridLayout(zombieTypeCount.size(), 3));
		
		for (ZombieTypes zombieType : zombieTypeCount.keySet()) {
			JLabel zombieName = new JLabel(zombieType.toString());
			zombieName.setHorizontalAlignment(SwingConstants.CENTER);
			zombieName.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 15));
			
			Image zombieImage = Images.getZombieImage(zombieType);
			zombieImage = zombieImage.getScaledInstance(200, 200,
					Image.SCALE_DEFAULT);
			
			JLabel zombieImageLabel = new JLabel(new ImageIcon(zombieImage));
			JPanel zombieDisplay = new JPanel();
			zombieDisplay.add(zombieImageLabel);
			
			JLabel zombieCount = new JLabel("x" + zombieTypeCount.get(zombieType));
			zombieCount.setHorizontalAlignment(SwingConstants.CENTER);
			zombieCount.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 15));
					
			fullZombieList.add(zombieName);
			fullZombieList.add(zombieDisplay);
			fullZombieList.add(zombieCount);
		}
		
		JOptionPane.showMessageDialog(null, fullZombieList, "Zombie List", JOptionPane.INFORMATION_MESSAGE);
	}
}
