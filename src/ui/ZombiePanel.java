package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import assets.ZombieTypes;

public class ZombiePanel extends JPanel {
	private static final long serialVersionUID = 4727511739555951187L;
	private static final int MAX_ZOMBIE_TYPES = 3;
	
	public ZombiePanel(Component parent, Map<ZombieTypes,Integer> map) {
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
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
			
			JButton fullList = new JButton("...");
			this.add(fullList);
		}
		
	}

	private void addZombieType(ZombieTypes zombieType,Component parent, int count, int mapSize) {
		Image zombieImage = Images.getZombieImage(zombieType);
		zombieImage = zombieImage.getScaledInstance(parent.getHeight()/mapSize == 0? 50: parent.getHeight()/mapSize, //try to set the dimensions to relative to the parent
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
}
