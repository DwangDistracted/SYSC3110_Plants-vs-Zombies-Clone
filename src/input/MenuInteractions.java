package input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import engine.Game;
import levels.LevelInfo;
import levels.LevelLoader;
import ui.*;

/**
 * This is a collection of statics that generate action listeners for Menu Buttons
 * @author david
 */
public class MenuInteractions {
	private MenuInteractions() {} //this is a collection of statics
	
	public static ActionListener getQuitHandler(JFrame frame) {
		return new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					frame.dispose();
				}
			};
	}
	
	public static ActionListener getPlayHandler (JFrame frame) {
		return new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					LevelInfo lvl = LevelLoader.getLevel(1); //Load first level
					Game game = new Game(lvl);
					
					new GameUI(game);
					frame.dispose();
					
					game.start();
				}
			};
	}
	
	public static ActionListener getLevelsHandler (JFrame frame) {
		return new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new LevelMenu();
					frame.dispose();
				}
			};
	}

	public static ActionListener getLoadHandler (JFrame frame) {
		return new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//not implemented
				}
			};
	}
}
