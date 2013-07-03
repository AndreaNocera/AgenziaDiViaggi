/**
 * @project WebVoyager
 * 
 * @package utils.swing
 * 
 * @name FrameClosingListener.java
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */

package utils.swing;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
	
public class FrameClosingListener extends WindowAdapter {
	
	JFrame parent;
	
	
	public FrameClosingListener(JFrame parent) {
		this.parent = parent;
	}
		
	public void windowClosing(WindowEvent event) {
		int choice = JOptionPane.showConfirmDialog(this.parent, "Sei sicuro di voler uscire?", "Voyager", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (choice == JOptionPane.OK_OPTION) {
			System.exit(0);
		}
	}		
}
