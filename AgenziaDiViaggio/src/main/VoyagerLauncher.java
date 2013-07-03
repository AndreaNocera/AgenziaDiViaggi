/**
 * @project WebVoyager
 * 
 * @package main
 * 
 * @name VoyagerLauncher.java
 *
 * @description
 *
 * @author TEAM 9: Giacomo Marciani
 * 
 */

package main;

import gestioneutenti.view.BoundaryLogin;

import java.awt.*;
import javax.swing.*;


public class VoyagerLauncher {

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

