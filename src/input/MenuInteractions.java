
package input;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import ui.*;
/**
 * This is a collection of statics that generate action listeners for Menu Buttons
 * @author david
 */
public class MenuInteractions {
	private MenuInteractions() {} //this is a collection of statics
	
	public static ActionListener getPlayHandler(JFrame frame) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new GameUI();
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

}
