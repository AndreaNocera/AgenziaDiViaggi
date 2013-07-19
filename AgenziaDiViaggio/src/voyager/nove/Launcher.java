
package voyager.nove;

import java.awt.EventQueue;

import javax.swing.JFrame;

import voyager.nove.view.BoundaryLogin;

public class Launcher {

	public Launcher() {}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				BoundaryLogin frame = new BoundaryLogin();
				frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				frame.setVisible(true);				
			}
		});

	}

}
