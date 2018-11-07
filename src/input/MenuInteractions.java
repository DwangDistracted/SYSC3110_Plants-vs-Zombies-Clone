package input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.*;

/**
 * This is a collection of statics that generate action listeners for Menu Buttons
 * @author david
 */
public class MenuInteractions {
	private MenuInteractions() {} //this is a collection of statics
	
	public static ActionListener getQuitHandler() {
		return new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					MainMenu.close();
				}
			};
	}
	
	public static ActionListener getPlayHandler () {
		return new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new GameUI();
					MainMenu.close();
				}
			};
	}
	
	public static ActionListener getLevelsHandler () {
		return new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new LevelMenu();
					MainMenu.close();
				}
			};
	}

	public static ActionListener getLoadHandler () {
		return new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//not implemented
				}
			};
	}
}
