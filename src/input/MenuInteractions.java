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
import util.GameSerializer;
/**
 * This is a collection of statics that generate action listeners for Menu Buttons
 * @author David Wang
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

	public static ActionListener getLevelsHandler (JFrame frame) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new LevelMenu();
				frame.dispose();
			}
		};
	}

	public static ActionListener getSaveHandler (Game game) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameSerializer.serialize(game);
			}
		};
	}

	public static ActionListener getLoadHandler (JFrame frame) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new LoadLevelMenu();
				frame.dispose();
			}
		};
	}

	public static ActionListener getPlayHandler(JFrame frame) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				LevelInfo lvl = LevelLoader.getLevel(1); //Load first level
				Game game = new Game(lvl);
				GameUI ui = new GameUI(game);
				new GameController(ui, game);

				frame.dispose();
			}
		};
	}

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
				GameUI ui = new GameUI(game);
				new GameController(ui, game);

				frame.dispose();
			}
		};
	}
	
	public static ActionListener getLoadGameHandler(JFrame frame, ButtonGroup levelOptions) {
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

				Game game = GameSerializer.savedGames.get(selected);
				GameUI ui = new GameUI(game);
				new GameController(ui, game);
				
				frame.dispose();
			}
		};
	}


	public static ActionListener getBackHandler(JFrame frame) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainMenu();
				frame.dispose();
			}
		};
	}

	public static ActionListener getDesignHandler(JFrame frame) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new LevelDesignerUI();
				frame.dispose();
			}
		};
	}

	public static ActionListener getSaveLevelHandler(LevelDesignerUI frame) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LevelLoader.getLevelFactory()
					.setName(frame.getNameFieldValue())
					.setGridSize(frame.getGridXFieldValue(), frame.getGridYFieldValue())
					.setInitResources(frame.getInitResourcesFieldValue())
					.setResPerTurn(frame.getResPerTurnFieldValue())
					.addAllAllowedPlants(frame.getSelectedPlants())
					.addAllZombies(frame.getSelectedZombies())
					.toXML();
				LevelLoader.refreshLevelLists();
				new LevelMenu();
				frame.dispose();
			}
		};
	}
}
