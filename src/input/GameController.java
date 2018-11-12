package input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import engine.Game;
import ui.GameUI;

public class GameController {
	
	private Game game;
	private GameUI ui;
	
	public GameController(GameUI ui, Game game) {
		this.game = game;
		this.ui = ui;
	}
	
	public ActionListener menuBarActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		};
	}
	
	public ActionListener boardActionListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		};
	}
	
	public ActionListener unitSelectActionListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		};
	}
}
