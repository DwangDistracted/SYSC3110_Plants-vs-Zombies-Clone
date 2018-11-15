package input;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;

import engine.Game;
import levels.LevelInfo;
import levels.LevelLoader;
import ui.*;

/**
 * This is a collection of statics that generate action listeners for Menu Buttons
 * @author David Wang
 */
public class MenuInteractions {
	private MenuInteractions() {} //this is a collection of statics

	/**
	 * Generates a Quit Button Action Listener for a given frame
	 * @param frame
	 * @return An ActionListener
	 */
	public static ActionListener getQuitHandler(JFrame frame) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		};
	}

	/**
	 * Generates an Action Listener that creates a LevelMenu frame and closes the given frame.
	 * @param frame
	 * @return An ActionListener
	 */
	public static ActionListener getLevelsHandler (JFrame frame) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new LevelMenu();
				frame.dispose();
			}
		};
	}

	/**
	 * Generates an Action Listeners that triggers the loading of a save game
	 * @param frame
	 * @return An ActionListener
	 */
	public static ActionListener getLoadHandler (JFrame frame) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//not implemented
			}
		};
	}

	/**
	 * Generates an Action Listener that starts level 1
	 * @param frame
	 * @return An ActionListener
	 */
	public static ActionListener getPlayHandler(JFrame frame) {
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

	/**
	 * Generates an Action Listener that starts a selected Level
	 * @param frame
	 * @param levelOptions
	 * @return An ActionListener
	 */
	public static ActionListener getPlayHandler(JFrame frame, ButtonGroup levelOptions) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Load selected level
				int selected = 1;
				Enumeration<AbstractButton> options = levelOptions.getElements();
				while (options.hasMoreElements()) {
					AbstractButton option = options.nextElement();
					if (option.isSelected()) {
						selected = Integer.parseInt(option.getName());
						break;
					}
				}
				LevelInfo lvl = LevelLoader.getLevel(selected);
				Game game = new Game(lvl);

				new GameUI(game);
				frame.dispose();

				game.start();
			}
		};
	}

	/**
	 * Generates an Action Listener that returns the user to the Main Menu
	 * @param frame
	 * @return An ActionListener
	 */
	public static ActionListener getBackHandler(JFrame frame) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainMenu();
				frame.dispose();
			}
		};
	}
}
